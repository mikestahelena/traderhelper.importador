package br.com.traderhelper.importador.service.impl;

import br.com.traderhelper.importador.AppProperties;
import br.com.traderhelper.importador.dao.AcaoRepository;
import br.com.traderhelper.importador.dao.CotacaoRepository;
import br.com.traderhelper.importador.domain.CotacaoHistorica;
import br.com.traderhelper.importador.domain.RegistroCotacaoDiaria;
import br.com.traderhelper.importador.entity.Acao;
import br.com.traderhelper.importador.entity.Cotacao;
import br.com.traderhelper.importador.entity.builder.AcaoBuilder;
import br.com.traderhelper.importador.entity.builder.CotacaoBuilder;
import br.com.traderhelper.importador.service.ImportaDados;
import br.com.traderhelper.importador.service.LeitorArquivoCotacaoHistorica;
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
    CotacaoRepository cotacaoRepository;

    @Autowired
    LeitorArquivoCotacaoHistorica leitorArquivoCotacaoHistorica;

    @Autowired
    private AppProperties properties;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

    private List<CotacaoHistorica> listaCotacaoHistorica;

    private List<Acao> listaAcoes = new ArrayList<>();
    private List<Cotacao> listaCotacoes = new ArrayList<>();

    @Transactional
    public void iniciarImportacao() throws IOException {
        System.out.println("DATASOURCE = " + dataSource);

        logger.info("INICIANDO IMPORTACAO:");

        logger.info("INICIANDO LEITURA DO ARQUIVO DE COTACAO HISTORICA DA BOVESPA:");
        try {
            final Path CAMINHO_ARQUIVOS_COTACAO_HISTORICA_BOVESPA = Paths.get(properties.getPath());
            listaCotacaoHistorica = leitorArquivoCotacaoHistorica.lerTodosArquivosCotacaoHistorica(CAMINHO_ARQUIVOS_COTACAO_HISTORICA_BOVESPA);
        } catch (Exception e) {
            logger.error("Erro ao ler arquivo:", e);
            throw e;
        }
        for (CotacaoHistorica cotacaoHistorica : listaCotacaoHistorica) {
            logger.info("INICIANDO GERACAO DAS LISTAS DE ACOES / COTACOES:");
            try {
                gerarListasAcaoCotacao(cotacaoHistorica.getRegistroCotacaoDiariaList());
                logger.info("acoes: " + listaAcoes.size());
                logger.info("cotacoes: " + listaCotacoes.size());
            } catch (Exception e) {
                logger.error("Erro ao gerar listas de acoes / cotacoes");
                throw e;
            }
        }

        logger.info("ORDENAÇÃO DOS DADOS:");
        Comparator<Acao> compAcao = new BeanComparator("codigoPapel");
        Collections.sort(listaAcoes, compAcao);
        Comparator<Cotacao> comp = new BeanComparator("acao", compAcao);
        Collections.sort(listaCotacoes, comp);
        logger.info("Dados ordenados.");

        logger.info("INICIANDO PERSISTENCIA DOS DADOS:");
        try {
            acaoRepository.batchSave(listaAcoes);
            logger.info("Ações... dados persistidos.");
            cotacaoRepository.batchSave(listaCotacoes);
            logger.info("Cotações... dados persistidos.");
        } catch (Exception e) {
            logger.error("Erro ao persistir dados:", e);
            throw e;
        }

        /*logger.info("INICIANDO ATUALIZAÇÃO DOS DADOS:");
        for (Acao a : listaAcoes) {
            a.setCotacoes(cotacaoRepository.findByAcaoId(a.getId()));
            for (int i = 0; i < a.getCotacoes().size(); i++) {
                if (i == 0) {
                    a.getCotacoes().get(i).setVariacaoPercentualDiaAnterior(BigDecimal.ZERO);
                    a.getCotacoes().get(i).setVariacaoCentavosDiaAnterior(BigDecimal.ZERO);
                } else {
                    Cotacao cotacaoAnterior = a.getCotacoes().get(i - 1);

                    BigDecimal precoUltimoNegocioCotacaoAnterior = cotacaoAnterior.getPrecoUltimoNegocio();
                    BigDecimal precoUltimoNegocioCotacao = a.getCotacoes().get(i).getPrecoUltimoNegocio();

                    BigDecimal percentualVariacao = precoUltimoNegocioCotacao
                            .subtract(precoUltimoNegocioCotacaoAnterior)
                            .divide(precoUltimoNegocioCotacaoAnterior, 4, BigDecimal.ROUND_HALF_UP)
                            .multiply(new BigDecimal("100.00")).setScale(2);

                    BigDecimal centavosVariacao = precoUltimoNegocioCotacao.subtract(precoUltimoNegocioCotacaoAnterior);

                    a.getCotacoes().get(i).setVariacaoPercentualDiaAnterior(percentualVariacao);
                    a.getCotacoes().get(i).setVariacaoCentavosDiaAnterior(centavosVariacao);
                }
            }
            try {
                cotacaoRepository.batchMerge(a.getCotacoes());
            } catch (Exception e) {
                logger.error("Erro ao atualizar dados:", e);
            }
        }*/
    }

    private void gerarListasAcaoCotacao(List<RegistroCotacaoDiaria> registroCotacaoDiariaList) {
        //listaCotacoes = new ArrayList<>();
        registroCotacaoDiariaList.forEach(this::adicionarDadosListaAcaoCotacao);
    }

    private void adicionarDadosListaAcaoCotacao(RegistroCotacaoDiaria registroCotacaoDiaria) {
        Acao acao = null;
        try {
            for (Acao a : listaAcoes) {
                if (a.getCodigoPapel().equals(registroCotacaoDiaria.getCODNEG())) {
                    acao = a;
                    break;
                }
            }
            if (acao == null) {
                acao = new AcaoBuilder()
                        .withCodigoPapel(registroCotacaoDiaria.getCODNEG())
                        .withCodigoIsin(registroCotacaoDiaria.getCODISI())
                        .withNomeResumidoPapel(registroCotacaoDiaria.getNOMRES())
                        .withNumeroDistribuicaoPapel(registroCotacaoDiaria.getDISMES())
                        .withCodigoBdi(registroCotacaoDiaria.getCODBDI())
                        .withCodigoEspecificacaoPapel(registroCotacaoDiaria.getESPECI().trim())
                        .withCodigoTipoMercado(registroCotacaoDiaria.getTPMERC())
                        .withIndicadorCorrecao(registroCotacaoDiaria.getINDOPC()) //Sempre 0?
                        .build();
                listaAcoes.add(acao);
            }
        } catch (Exception e) {
            logger.error("Erro ao criar acao.", e);
            throw e;
        }

        try {
            listaCotacoes.add(new CotacaoBuilder()
                    .withDataPregao(getDateFromString(registroCotacaoDiaria.getDATAPG()))
                    .withPrecoAbertura(getValorBigDecimal(registroCotacaoDiaria.getPREABE()))
                    .withPrecoMaximo(getValorBigDecimal(registroCotacaoDiaria.getPREMAX()))
                    .withPrecoMinimo(getValorBigDecimal(registroCotacaoDiaria.getPREMIN()))
                    .withPrecoMedio(getValorBigDecimal(registroCotacaoDiaria.getPREMED()))
                    .withPrecoMelhorOfertaCompra(getValorBigDecimal(registroCotacaoDiaria.getPREOFC()))
                    .withPrecoMelhorOfertaVenda(getValorBigDecimal(registroCotacaoDiaria.getPREOFV()))
                    .withPrecoUltimoNegocio(getValorBigDecimal(registroCotacaoDiaria.getPREULT()))
                    .withTotalNegociosEfetuados(getValorBigInteger(registroCotacaoDiaria.getTOTNEG()))
                    .withTotalTitulosNegociados(getValorBigInteger(registroCotacaoDiaria.getQUATOT()))
                    .withVolumeTitulosNegociados(getValorBigDecimal(registroCotacaoDiaria.getVOLTOT()))
                    .withFatorCotacao(registroCotacaoDiaria.getFATCOT())
                    .withPrecoPontosOpcoes(getValorBigDecimal(registroCotacaoDiaria.getPTOEXE())) //Sempre 0?
                    .withPrazoDiasMercadoTermo(getValorBigInteger(registroCotacaoDiaria.getPRAZOT()))
                    .withMoedaReferencia(registroCotacaoDiaria.getMODREF())
                    .withDataVencimentoOpcoes(registroCotacaoDiaria.getDATVEN())
                    .withPrecoExercicio(getValorBigDecimal(registroCotacaoDiaria.getPREEXE()))
                    .withAcao(acao)
                    .build());
        } catch (Exception e) {
            logger.error("Erro ao criar cotacao.", e);
            throw e;
        }
    }

    private void popularBase(CotacaoHistorica cotacaoHistorica) {
        /*ControleAtualizacaoDao controleAtualizacaoDao = new ControleAtualizacaoDaoImpl();
        ControleAtualizacao controleAtualizacao = gerarControleAtualizacao(cotacaoHistorica.getRegistroTrailer());
        controleAtualizacaoDao.create(controleAtualizacao);*/

        acaoRepository.batchSave(listaAcoes);

        cotacaoRepository.batchSave(listaCotacoes);

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
