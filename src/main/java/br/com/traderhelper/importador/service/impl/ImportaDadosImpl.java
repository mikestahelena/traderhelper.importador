package br.com.traderhelper.importador.service.impl;

import br.com.traderhelper.importador.AppProperties;
import br.com.traderhelper.importador.dao.AcaoCotacaoRepository;
import br.com.traderhelper.importador.dao.AcaoRepository;
import br.com.traderhelper.importador.dao.CommodityCotacaoRepository;
import br.com.traderhelper.importador.dao.CommodityRepository;
import br.com.traderhelper.importador.domain.acoes.CotacaoHistoricaAcao;
import br.com.traderhelper.importador.domain.acoes.RegistroCotacaoDiariaAcao;
import br.com.traderhelper.importador.domain.commodities.CotacaoHistoricaCommodity;
import br.com.traderhelper.importador.domain.commodities.RegistroCotacaoDiariaCommodity;
import br.com.traderhelper.importador.entity.Acao;
import br.com.traderhelper.importador.entity.AcaoCotacao;
import br.com.traderhelper.importador.entity.Commodity;
import br.com.traderhelper.importador.entity.CommodityCotacao;
import br.com.traderhelper.importador.entity.builder.AcaoBuilder;
import br.com.traderhelper.importador.entity.builder.AcaoCotacaoBuilder;
import br.com.traderhelper.importador.entity.builder.CommodityBuilder;
import br.com.traderhelper.importador.entity.builder.CommodityCotacaoBuilder;
import br.com.traderhelper.importador.service.ImportaDados;
import br.com.traderhelper.importador.service.LeitorArquivoCotacaoHistoricaAcao;
import br.com.traderhelper.importador.service.LeitorArquivoCotacaoHistoricaCommodity;
import org.apache.commons.beanutils.BeanComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Michael Sta. Helena on 26/12/2016.
 */
@Service
public class ImportaDadosImpl implements ImportaDados {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DataSource dataSource;

    @Autowired
    AcaoRepository acaoRepository;

    @Autowired
    AcaoCotacaoRepository acaoCotacaoRepository;

    @Autowired
    CommodityRepository commodityRepository;

    @Autowired
    CommodityCotacaoRepository commodityCotacaoRepository;

    @Autowired
    LeitorArquivoCotacaoHistoricaAcao leitorArquivoCotacaoHistoricaAcao;

    @Autowired
    LeitorArquivoCotacaoHistoricaCommodity leitorArquivoCotacaoHistoricaCommodity;

    @Autowired
    private AppProperties properties;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

    private List<Acao> listaAcoes = new ArrayList<>();
    private List<Commodity> listaCommodities = new ArrayList<>();
    private List<AcaoCotacao> listaAcaoCotacoes = new ArrayList<>();
    private List<CommodityCotacao> listaCommodityCotacoes = new ArrayList<>();

    @Transactional
    public void iniciarImportacao() throws IOException {
        System.out.println("DATASOURCE = " + dataSource);

        logger.info("INICIANDO IMPORTACAO:");

        importacaoBovespa();
        importacaoCommodity();

    }

    private void importacaoBovespa() throws IOException {
        logger.info("INICIANDO LEITURA DO ARQUIVO DE COTACAO HISTORICA DA BOVESPA:");
        List<CotacaoHistoricaAcao> listaCotacaoHistoricaAcao;
        try {
            final Path CAMINHO_ARQUIVOS_COTACAO_HISTORICA_BOVESPA = Paths.get(properties.getPathBovespa());
            listaCotacaoHistoricaAcao = leitorArquivoCotacaoHistoricaAcao.lerTodosArquivosCotacaoHistorica(CAMINHO_ARQUIVOS_COTACAO_HISTORICA_BOVESPA);
        } catch (Exception e) {
            logger.error("Erro ao ler arquivo:", e);
            throw e;
        }
        for (CotacaoHistoricaAcao cotacaoHistoricaAcao : listaCotacaoHistoricaAcao) {
            logger.info("INICIANDO GERACAO DAS LISTAS DE ACOES / COTACOES:");
            try {
                gerarListasAcaoCotacao(cotacaoHistoricaAcao.getRegistroCotacaoDiariaAcaoList());
                logger.info("acoes: " + listaAcoes.size());
                logger.info("cotacoes: " + listaAcaoCotacoes.size());
            } catch (Exception e) {
                logger.error("Erro ao gerar listas de acoes / cotacoes");
                throw e;
            }
        }

        logger.info("ORDENAÇÃO DOS DADOS:");
        Comparator<Acao> compAcao = new BeanComparator("codigoPapel");
        Collections.sort(listaAcoes, compAcao);
        Comparator<AcaoCotacao> comp = new BeanComparator("acao", compAcao);
        Collections.sort(listaAcaoCotacoes, comp);
        logger.info("Dados ordenados.");

        logger.info("INICIANDO PERSISTENCIA DOS DADOS:");
        try {
            acaoRepository.batchSave(listaAcoes);
            logger.info("Ações... dados persistidos.");
            acaoCotacaoRepository.batchSave(listaAcaoCotacoes);
            logger.info("Cotações... dados persistidos.");
        } catch (Exception e) {
            logger.error("Erro ao persistir dados:", e);
            throw e;
        }
    }

    private void importacaoCommodity() throws IOException {
        logger.info("INICIANDO LEITURA DO ARQUIVO DE COTACAO HISTORICA DE COMMODITIES:");
        List<CotacaoHistoricaCommodity> listaCotacaoHistoricaCommodity;
        try {
            final Path CAMINHO_ARQUIVOS_COTACAO_HISTORICA_COMMODITY = Paths.get(properties.getPathCommodity());
            listaCotacaoHistoricaCommodity = leitorArquivoCotacaoHistoricaCommodity.lerTodosArquivosCotacaoHistorica(CAMINHO_ARQUIVOS_COTACAO_HISTORICA_COMMODITY);
        } catch (Exception e) {
            logger.error("Erro ao ler arquivo:", e);
            throw e;
        }
        for (CotacaoHistoricaCommodity cotacaoHistoricaCommodity : listaCotacaoHistoricaCommodity) {
            logger.info("INICIANDO GERACAO DAS LISTAS DE COMMODITIES / COTACOES:");
            try {
                gerarListasCommodityCotacao(cotacaoHistoricaCommodity.getRegistroCotacaoDiariaCommodityList());
                logger.info("commodities: " + listaCommodities.size());
                logger.info("cotacoes: " + listaCommodityCotacoes.size());
            } catch (Exception e) {
                logger.error("Erro ao gerar listas de commodities / cotacoes");
                throw e;
            }
        }

        /*logger.info("ORDENAÇÃO DOS DADOS:");
        Comparator<Commodity> compCommodity = new BeanComparator("codigoPapel");
        Collections.sort(listaCommodities, compCommodity);
        Comparator<CommodityCotacao> comp = new BeanComparator("commodity", compCommodity);
        Collections.sort(listaCommodityCotacoes, comp);
        logger.info("Dados ordenados.");*/

        logger.info("INICIANDO PERSISTENCIA DOS DADOS:");
        try {
            commodityRepository.batchSave(listaCommodities);
            logger.info("Ações... dados persistidos.");
            commodityCotacaoRepository.batchSave(listaCommodityCotacoes);
            logger.info("Cotações... dados persistidos.");
        } catch (Exception e) {
            logger.error("Erro ao persistir dados:", e);
            throw e;
        }
    }

    private void gerarListasAcaoCotacao(List<RegistroCotacaoDiariaAcao> registroCotacaoDiariaAcaoList) {
        registroCotacaoDiariaAcaoList.forEach(this::adicionarDadosListaAcaoCotacao);
    }

    private void gerarListasCommodityCotacao(List<RegistroCotacaoDiariaCommodity> registroCotacaoDiariaCommodityList) {
        registroCotacaoDiariaCommodityList.forEach(this::adicionarDadosListaCommodityCotacao);
    }

    private void adicionarDadosListaAcaoCotacao(RegistroCotacaoDiariaAcao registroCotacaoDiariaAcao) {
        Acao acao = null;
        try {
            for (Acao a : listaAcoes) {
                if (a.getCodigoPapel().equals(registroCotacaoDiariaAcao.getCODNEG())) {
                    acao = a;
                    break;
                }
            }
            if (acao == null) {
                acao = new AcaoBuilder()
                        .withCodigoPapel(registroCotacaoDiariaAcao.getCODNEG())
                        .withCodigoIsin(registroCotacaoDiariaAcao.getCODISI())
                        .withNomeResumidoPapel(registroCotacaoDiariaAcao.getNOMRES())
                        .withNumeroDistribuicaoPapel(registroCotacaoDiariaAcao.getDISMES())
                        .withCodigoBdi(registroCotacaoDiariaAcao.getCODBDI())
                        .withCodigoEspecificacaoPapel(registroCotacaoDiariaAcao.getESPECI().trim())
                        .withCodigoTipoMercado(registroCotacaoDiariaAcao.getTPMERC())
                        .withIndicadorCorrecao(registroCotacaoDiariaAcao.getINDOPC()) //Sempre 0?
                        .build();
                listaAcoes.add(acao);
            }
        } catch (Exception e) {
            logger.error("Erro ao criar acao.", e);
            throw e;
        }

        try {
            listaAcaoCotacoes.add(new AcaoCotacaoBuilder()
                    .withDataPregao(getDateFromString(registroCotacaoDiariaAcao.getDATAPG()))
                    .withPrecoAbertura(getValorBigDecimal(registroCotacaoDiariaAcao.getPREABE()))
                    .withPrecoMaximo(getValorBigDecimal(registroCotacaoDiariaAcao.getPREMAX()))
                    .withPrecoMinimo(getValorBigDecimal(registroCotacaoDiariaAcao.getPREMIN()))
                    .withPrecoMedio(getValorBigDecimal(registroCotacaoDiariaAcao.getPREMED()))
                    .withPrecoMelhorOfertaCompra(getValorBigDecimal(registroCotacaoDiariaAcao.getPREOFC()))
                    .withPrecoMelhorOfertaVenda(getValorBigDecimal(registroCotacaoDiariaAcao.getPREOFV()))
                    .withPrecoUltimoNegocio(getValorBigDecimal(registroCotacaoDiariaAcao.getPREULT()))
                    .withTotalNegociosEfetuados(getValorBigInteger(registroCotacaoDiariaAcao.getTOTNEG()))
                    .withTotalTitulosNegociados(getValorBigInteger(registroCotacaoDiariaAcao.getQUATOT()))
                    .withVolumeTitulosNegociados(getValorBigDecimal(registroCotacaoDiariaAcao.getVOLTOT()))
                    .withFatorCotacao(registroCotacaoDiariaAcao.getFATCOT())
                    .withPrecoPontosOpcoes(getValorBigDecimal(registroCotacaoDiariaAcao.getPTOEXE())) //Sempre 0?
                    .withPrazoDiasMercadoTermo(getValorBigInteger(registroCotacaoDiariaAcao.getPRAZOT()))
                    .withMoedaReferencia(registroCotacaoDiariaAcao.getMODREF())
                    .withDataVencimentoOpcoes(registroCotacaoDiariaAcao.getDATVEN())
                    .withPrecoExercicio(getValorBigDecimal(registroCotacaoDiariaAcao.getPREEXE()))
                    .withAcao(acao)
                    .build());
        } catch (Exception e) {
            logger.error("Erro ao criar cotacao.", e);
            throw e;
        }
    }

    private void adicionarDadosListaCommodityCotacao(RegistroCotacaoDiariaCommodity registroCotacaoDiariaCommodity) {
        Commodity commodity = null;
        try {
            for (Commodity c : listaCommodities) {
                if (c.getCodigoCommodity().equals(registroCotacaoDiariaCommodity.getCODECO())) {
                    commodity = c;
                    break;
                }
            }
            if (commodity == null) {
                commodity = new CommodityBuilder()
                        .withCodigoCommodity(registroCotacaoDiariaCommodity.getCODECO())
                        .build();
                listaCommodities.add(commodity);
            }
        } catch (Exception e) {
            logger.error("Erro ao criar commodity.", e);
            throw e;
        }

        try {
            listaCommodityCotacoes.add(new CommodityCotacaoBuilder()
                    .withDataCotacao(getDateFromString(registroCotacaoDiariaCommodity.getDATECT()))
                    .build());
        } catch (Exception e) {
            logger.error("Erro ao criar cotacao.", e);
            throw e;
        }
    }

    private void popularBase(CotacaoHistoricaAcao cotacaoHistoricaAcao) {
        /*ControleAtualizacaoDao controleAtualizacaoDao = new ControleAtualizacaoDaoImpl();
        ControleAtualizacao controleAtualizacao = gerarControleAtualizacao(cotacaoHistoricaAcao.getRegistroTrailer());
        controleAtualizacaoDao.create(controleAtualizacao);*/

        acaoRepository.batchSave(listaAcoes);

        acaoCotacaoRepository.batchSave(listaAcaoCotacoes);

        /*atualizarControle(controleAtualizacao);
        controleAtualizacaoDao.update(controleAtualizacao);*/
    }

    /*private ControleAtualizacao gerarControleAtualizacao(RegistroTrailer registroTrailer) {
        ControleAtualizacao controleAtualizacao = new ControleAtualizacao();
        controleAtualizacao.setDataGeracaoArquivo(registroTrailer.getDATGER());
        controleAtualizacao.setStatusAtualizacao("I");
        controleAtualizacao.setCreated(new Date());
        controleAtualizacao.setUpdated(new Date());
        return controleAtualizacao;
    }

    private void atualizarControle(ControleAtualizacao controleAtualizacao) {
        controleAtualizacao.setUpdated(new Date());
        controleAtualizacao.setStatusAtualizacao("F");
    }*/

    private Date getDateFromString(String datapg) {
        try {
            return formatter.parse(datapg);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private BigDecimal getValorBigDecimal(String valorString) {
        if (valorString == null) {
            return null;
        }
        if (valorString.isEmpty() || valorString.length() < 2) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(valorString);
    }

    private BigInteger getValorBigInteger(String valorString) {
        if (valorString == null) {
            return null;
        }
        if (valorString.isEmpty() || valorString.length() < 2) {
            return BigInteger.ZERO;
        }
        return new BigInteger(valorString);
    }
}
