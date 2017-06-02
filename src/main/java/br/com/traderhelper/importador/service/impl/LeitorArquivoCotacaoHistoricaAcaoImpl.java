package br.com.traderhelper.importador.service.impl;


import br.com.traderhelper.importador.domain.acoes.CotacaoHistoricaAcao;
import br.com.traderhelper.importador.domain.acoes.RegistroCotacaoDiariaAcao;
import br.com.traderhelper.importador.domain.acoes.RegistroTrailer;
import br.com.traderhelper.importador.service.LeitorArquivoCotacaoHistoricaAcao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Michael Sta. Helena on 13/12/2016.
 */
@Service
public class LeitorArquivoCotacaoHistoricaAcaoImpl implements LeitorArquivoCotacaoHistoricaAcao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String CODIGO_REGISTRO_COTACAO_DIARIA = "1";
    private final String CODIGO_REGISTRO_TRAILER = "99";

    private List<String> acoesCarteiraBovespa = getAcoesCarteiraBovespa();

    private static String trataValores(String valor) {
        return trataValores(valor, false);
    }

    private static String trataValores(String valor, boolean isDecimal) {
        valor = removeZerosAEsquerda(valor.trim());
        if (isDecimal) {
            valor = colocaPontoDecimalDuasCasas(valor);
        }
        return valor;
    }

    private static String removeZerosAEsquerda(String valor) {
        if (valor != null) {
            while (valor.startsWith("0") && valor.length() > 1) {
                valor = valor.replaceFirst("0", "");
            }
        }
        return valor;
    }

    private static String colocaPontoDecimalDuasCasas(String valor) {
        if (valor != null) {
            while (valor.length() < 3) {
                valor = "0" + valor;
            }
            int tam = valor.length();
            valor = valor.substring(0, tam - 2) + "." + valor.substring(tam - 2, tam);
        }
        return valor;
    }

    private static List<String> getAcoesCarteiraBovespa() {
        final String acoes[] = {"ABEV3", "BBAS3", "BBDC3", "BBDC4", "BBSE3", "BRAP4", "BRFS3", "BRKM5", "BRML3", "BVMF3", "CCRO3",
                "CIEL3", "CMIG4", "CPFE3", "CPLE6", "CSAN3", "CSNA3", "CTIP3", "CYRE3", "ECOR3", "EGIE3", "EMBR3", "ENBR3",
                "EQTL3", "ESTC3", "FIBR3", "GGBR4", "GOAU4", "HYPE3", "ITSA4", "ITUB4", "JBSS3", "KLBN11", "KROT3", "LAME4",
                "LREN3", "MRFG3", "MRVE3", "MULT3", "NATU3", "PCAR4", "PETR3", "PETR4", "QUAL3", "RADL3", "RENT3", "RUMO3",
                "SANB11", "SBSP3", "SMLE3", "SUZB5", "TIMP3", "UGPA3", "USIM5", "VALE3", "VALE5", "VIVT4", "WEGE3"};
        /*String acoes[] = {"ABEV3", "BBAS3", "PETR4", "VALE5"};*/
        return Arrays.asList(acoes);
    }

    public List<CotacaoHistoricaAcao> lerTodosArquivosCotacaoHistorica(Path path) throws IOException {
        List<Path> files = new ArrayList<>();
        List<CotacaoHistoricaAcao> cotacoes = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path entry : stream) {
                files.add(entry);
            }
        }
        for (Path pathFiles : files) {
            cotacoes.add(lerArquivoCotacaoHistorica(pathFiles));
        }
        return cotacoes;
    }

    public CotacaoHistoricaAcao lerArquivoCotacaoHistorica(Path caminho) throws IOException {
        List<String> linhasArquivo = Files.readAllLines(caminho, Charset.forName("ISO-8859-1"));
        logger.info("Arquivo lido. Quantidade de registros:" + linhasArquivo.size());
        logger.info("Gerando objeto de CotacaoHistoricaAcao...");
        CotacaoHistoricaAcao cotacaoHistoricaAcao = lerLinhas(linhasArquivo);
        logger.info("CotacaoHistoricaAcao finalizado. Quantidade de registros:" + cotacaoHistoricaAcao.getRegistroCotacaoDiariaAcaoList().size());
        return cotacaoHistoricaAcao;
    }

    private CotacaoHistoricaAcao lerLinhas(List<String> lines) {
        CotacaoHistoricaAcao cotacaoHistoricaAcao = new CotacaoHistoricaAcao();
        for (String linha : lines) {
            lerRegistros(linha, cotacaoHistoricaAcao);
        }
        return cotacaoHistoricaAcao;
    }

    private void lerRegistros(String linha, CotacaoHistoricaAcao cotacaoHistoricaAcao) {
        final String tipReg = trataValores(linha.substring(1 - 1, 2));
        final String codNeg = trataValores(linha.substring(13 - 1, 24));
        if (CODIGO_REGISTRO_COTACAO_DIARIA.equals(tipReg)) {
            if (isAcaoCarteiraBovespa(codNeg)) {
                cotacaoHistoricaAcao.addCotacaoDiaria(setDadosRegistroCotacaoDiaria(linha));
            }
        } else if (CODIGO_REGISTRO_TRAILER.equals(tipReg)) {
            cotacaoHistoricaAcao.setRegistroTrailer(setDadosRegistroTrailer(linha));
        }
    }

    private boolean isAcaoCarteiraBovespa(String codNeg) {
        for (String acao : acoesCarteiraBovespa) {
            if (acao.equals(codNeg)) {
                return true;
            }
        }
        return false;
    }

    private RegistroTrailer setDadosRegistroTrailer(String s) {
        RegistroTrailer registroTrailer = new RegistroTrailer();
        registroTrailer.setTIPREG(CODIGO_REGISTRO_TRAILER);//trataValores(s.substring(1 - 1, 2)));
        registroTrailer.setNOMARQ(trataValores(s.substring(3 - 1, 15)));
        registroTrailer.setCDORIG(trataValores(s.substring(16 - 1, 23)));
        registroTrailer.setDATGER(trataValores(s.substring(24 - 1, 31)));
        registroTrailer.setTOTREG(trataValores(s.substring(32 - 1, 42)));
        return registroTrailer;
    }

    private RegistroCotacaoDiariaAcao setDadosRegistroCotacaoDiaria(String s) {
        return new RegistroCotacaoDiariaAcao.RegistroCotacaoDiariaBuilder()
                //TIPREG - TIPO DE REGISTRO
                .withTIPREG(CODIGO_REGISTRO_COTACAO_DIARIA)//trataValores(s.substring(1 - 1, 2)));
                //DATA DO PREGÃO
                .withDATAPG(trataValores(s.substring(3 - 1, 10)))
                //CODBDI - CÓDIGO BDI
                .withCODBDI(trataValores(s.substring(11 - 1, 12)))
                //CODNEG - CÓDIGO DE NEGOCIAÇÃO DO PAPEL
                .withCODNEG(trataValores(s.substring(13 - 1, 24)))
                //TPMERC - TIPO DE MERCADO
                .withTPMERC(trataValores(s.substring(25 - 1, 27)))
                //NOMRES - NOME RESUMIDO DA EMPRESA EMISSORA DO PAPEL
                .withNOMRES(trataValores(s.substring(28 - 1, 39)))
                //ESPECI - ESPECIFICAÇÃO DO PAPEL
                .withESPECI(trataValores(s.substring(40 - 1, 49)))
                //PRAZOT - PRAZO EM DIAS DO MERCADO A TERMO
                .withPRAZOT(trataValores(s.substring(50 - 1, 52)))
                //MODREF - MOEDA DE REFERÊNCIA
                .withMODREF(trataValores(s.substring(53 - 1, 56)))
                //PREABE - PREÇO DE ABERTURA DO PAPEL-MERCADO NO PREGÃO
                .withPREABE(trataValores(s.substring(57 - 1, 69), true))
                //PREMAX - PREÇO MÁXIMO DO PAPEL-MERCADO NO PREGÃO
                .withPREMAX(trataValores(s.substring(70 - 1, 82), true))
                //PREMIN - PREÇO MÍNIMO DO PAPEL MERCADO NO PREGÃO
                .withPREMIN(trataValores(s.substring(83 - 1, 95), true))
                //PREMED - PREÇO MÉDIO DO PAPEL MERCADO NO PREGÃO
                .withPREMED(trataValores(s.substring(96 - 1, 108), true))
                //PREULT - PREÇO DO ÚLTIMO NEGÓCIO DO PAPEL-MERCADO NO PREGÃO
                .withPREULT(trataValores(s.substring(109 - 1, 121), true))
                //PREOFC - PREÇO DA MELHOR OFERTA DE COMPRA DO PAPEL-MERCADO
                .withPREOFC(trataValores(s.substring(122 - 1, 134), true))
                //PREOFV - PREÇO DA MELHOR OFERTA DE VENDA DO PAPEL-MERCADO
                .withPREOFV(trataValores(s.substring(135 - 1, 147), true))
                //TOTNEG - NEG. - NÚMERO DE NEGÓCIOS EFETUADOS COM O PAPEL MERCADO NO PREGÃO
                .withTOTNEG(trataValores(s.substring(148 - 1, 152)))
                //QUATOT - QUANTIDADE TOTAL DE TÍTULOS NEGOCIADOS NESTE PAPEL MERCADO
                .withQUATOT(trataValores(s.substring(153 - 1, 170)))
                //VOLTOT - VOLUME TOTAL DE TÍTULOS NEGOCIADOS NESTE PAPEL MERCADO
                .withVOLTOT(trataValores(s.substring(171 - 1, 188), true))
                //PREEXE - PREÇO DE EXERCÍCIO PARA O MERCADO DE OPÇÕES OU VALOR DO CONTRATO PARA O MERCADO DE TERMO SECUNDÁRIO
                .withPREEXE(trataValores(s.substring(189 - 1, 201)))
                //INDOPC - INDICADOR DE CORREÇÃO DE PREÇOS DE EXERCÍCIOS OU VALORES DE CONTRATO PARA OS MERCADOS DE OPÇÕES OU TERMO SECUNDÁRIO
                .withINDOPC(trataValores(s.substring(202 - 1, 202)))
                //DATVEN - DATA DO VENCIMENTO PARA OS MERCADOS DE OPÇÕES OU TERMO SECUNDÁRIO
                .withDATVEN(trataValores(s.substring(203 - 1, 210)))
                //FATCOT - FATOR DE COTAÇÃO DO PAPEL
                .withFATCOT(trataValores(s.substring(211 - 1, 217)))
                //PTOEXE - PREÇO DE EXERCÍCIO EM PONTOS PARA OPÇÕES REFERENCIADAS EM DÓLAR OU VALOR DE CONTRATO EM PONTOS PARA TERMO SECUNDÁRIO
                /*.withPTOEXE(trataValores(s.substring(218 - 1, 230), true))*/
                //CODISI - CÓDIGO DO PAPEL NO SISTEMA ISIN OU CÓDIGO INTERNO DO PAPEL
                .withCODISI(trataValores(s.substring(231 - 1, 242)))
                //DISMES - NÚMERO DE DISTRIBUIÇÃO DO PAPEL
                .withDISMES(trataValores(s.substring(243 - 1, 245)))
                .build();
    }
}
