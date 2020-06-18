package br.com.traderhelper.importador.improve.processor;

import br.com.traderhelper.importador.improve.data.DailyStockPriceDTO;
import br.com.traderhelper.importador.improve.exception.TheException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
@StepScope
@Component
public class TheProcessor implements ItemProcessor<String, DailyStockPriceDTO> {

    private static final String END_OF_FILE = "99";
    private static final String TPMERC_TERMO = "30";
    private static final String CODBDI_FUNDOS_IMOBILIARIOS = "12";
    private static final String ESPECI_DIREITO_SUBSCRICAO = "DIR";
    private static final String NUMBER_ZERO = "0";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final String[] TICKERS = {"ABCB4", "ABEV3", "ALPA4", "ALSO3", "ALUP11", "AMAR3",
            "ARZZ3", "AZUL4", "B3SA3", "BBAS3", "BBDC3", "BBDC4",
            "BBSE3", "BEEF3", "BIDI11", "BIDI4", "BKBR3", "BPAC11",
            "BPAN4", "BRAP4", "BRDT3", "BRFS3", "BRKM5", "BRML3",
            "BRPR3", "BRSR6", "BTOW3", "CAML3", "CCRO3", "CESP6",
            "CIEL3", "CMIG3", "CMIG4", "CNTO3", "COGN3", "CPFE3",
            "CPLE3", "CPLE6", "CRFB3", "CSAN3", "CSMG3", "CSNA3",
            "CVCB3", "CYRE3", "DIRR3", "DTEX3", "ECOR3", "EGIE3",
            "ELET3", "ELET6", "EMBR3", "ENAT3", "ENBR3", "ENEV3",
            "ENGI11", "EQTL3", "EVEN3", "EZTC3", "FLRY3", "GFSA3",
            "GGBR4", "GNDI3", "GOAU4", "GOLL4", "GRND3", "GUAR3",
            "HAPV3", "HBOR3", "HGTX3", "HYPE3", "IGTA3", "IRBR3",
            "ITSA4", "ITUB3", "ITUB4", "JBSS3", "JHSF3", "JSLG3",
            "KLBN11", "LAME3", "LAME4", "LCAM3", "LEVE3", "LIGT3",
            "LINX3", "LOGG3", "LOGN3", "LREN3", "MDIA3", "MEAL3",
            "MGLU3", "MILS3", "MOVI3", "MRFG3", "MRVE3", "MULT3",
            "MYPK3", "NEOE3", "NTCO3", "ODPV3", "OMGE3", "PARD3",
            "PCAR3", "PETR3", "PETR4", "POMO4", "PRIO3", "PSSA3",
            "QUAL3", "RADL3", "RAIL3", "RAPT4", "RENT3", "RLOG3",
            "SANB11", "SAPR11", "SAPR4", "SBSP3", "SEER3", "SLCE3",
            "SMLS3", "SMTO3", "SQIA3", "STBP3", "SULA11", "SUZB3",
            "TAEE11", "TASA4", "TCSA3", "TEND3", "TGMA3", "TIET11",
            "TIMP3", "TOTS3", "TRIS3", "TRPL4", "TUPY3", "UGPA3",
            "UNIP6", "USIM5", "VALE3", "VIVT4", "VLID3", "VVAR3",
            "WEGE3", "WIZS3", "YDUQ3"};

    @Override
    public DailyStockPriceDTO process(String in) {

        final String TIPREG = trataValores(in.substring(1 - 1, 2));
        if (END_OF_FILE.equals(TIPREG)) {
            throw new TheException("TRAILER");
        }
        final String CODBDI = trataValores(in.substring(11 - 1, 12));
        final String CODNEG = trataValores(in.substring(13 - 1, 24));
        final String ESPECI = trataValores(in.substring(40 - 1, 49));
        if ((!isCodnegImportable(CODNEG) && !isCodbdiImportable(CODBDI)) || !isEspeciImportable(ESPECI)) {
            throw new TheException(CODBDI, CODNEG, "NÃO IMPORTAR");
        }

        final String TPMERC = trataValores(in.substring(25 - 1, 27));
        if (TPMERC_TERMO.equals(TPMERC)) {
            throw new TheException(TPMERC, CODNEG, "TPMERC INVALIDO");
        }

        DailyStockPriceDTO dailyStockPriceDTO = DailyStockPriceDTO.builder()
                // TIPREG - TIPO DE REGISTRO
                .TIPREG(TIPREG)
                // DATA DO PREGÃO
                .DATAPG(getDateFromString(trataValores(in.substring(3 - 1, 10))))
                // CODBDI - CÓDIGO BDI
                .CODBDI(CODBDI)
                // CODNEG - CÓDIGO DE NEGOCIAÇÃO DO PAPEL
                .CODNEG(CODNEG)
                // TPMERC - TIPO DE MERCADO
                .TPMERC(TPMERC)
                // NOMRES - NOME RESUMIDO DA EMPRESA EMISSORA DO PAPEL
                .NOMRES(trataValores(in.substring(28 - 1, 39)))
                // ESPECI - ESPECIFICAÇÃO DO PAPEL
                .ESPECI(ESPECI)
                // PRAZOT - PRAZO EM DIAS DO MERCADO A TERMO
                .PRAZOT(getValorBigInteger(trataValores(in.substring(50 - 1, 52))))
                // MODREF - MOEDA DE REFERÊNCIA
                .MODREF(trataValores(in.substring(53 - 1, 56)))
                // PREABE - PREÇO DE ABERTURA DO PAPEL-MERCADO NO PREGÃO
                .PREABE(getValorBigDecimal(trataValores(in.substring(57 - 1, 69), true)))
                // PREMAX - PREÇO MÁXIMO DO PAPEL-MERCADO NO PREGÃO
                .PREMAX(getValorBigDecimal(trataValores(in.substring(70 - 1, 82), true)))
                // PREMIN - PREÇO MÍNIMO DO PAPEL MERCADO NO PREGÃO
                .PREMIN(getValorBigDecimal(trataValores(in.substring(83 - 1, 95), true)))
                // PREMED - PREÇO MÉDIO DO PAPEL MERCADO NO PREGÃO
                .PREMED(getValorBigDecimal(trataValores(in.substring(96 - 1, 108), true)))
                // PREULT - PREÇO DO ÚLTIMO NEGÓCIO DO PAPEL-MERCADO NO PREGÃO
                .PREULT(getValorBigDecimal(trataValores(in.substring(109 - 1, 121), true)))
                // PREOFC - PREÇO DA MELHOR OFERTA DE COMPRA DO PAPEL-MERCADO
                .PREOFC(getValorBigDecimal(trataValores(in.substring(122 - 1, 134), true)))
                // PREOFV - PREÇO DA MELHOR OFERTA DE VENDA DO PAPEL-MERCADO
                .PREOFV(getValorBigDecimal(trataValores(in.substring(135 - 1, 147), true)))
                // TOTNEG - NEG. - NÚMERO DE NEGÓCIOS EFETUADOS COM O PAPEL MERCADO NO PREGÃO
                .TOTNEG(getValorBigInteger(trataValores(in.substring(148 - 1, 152))))
                // QUATOT - QUANTIDADE TOTAL DE TÍTULOS NEGOCIADOS NESTE PAPEL MERCADO
                .QUATOT(getValorBigInteger(trataValores(in.substring(153 - 1, 170))))
                // VOLTOT - VOLUME TOTAL DE TÍTULOS NEGOCIADOS NESTE PAPEL MERCADO
                .VOLTOT(getValorBigDecimal(trataValores(in.substring(171 - 1, 188), true)))
                // PREEXE - PREÇO DE EXERCÍCIO PARA O MERCADO DE OPÇÕES OU VALOR DO CONTRATO
                // PARA O MERCADO DE TERMO SECUNDÁRIO
                .PREEXE(getValorBigDecimal(trataValores(in.substring(189 - 1, 201), true)))
                // INDOPC - INDICADOR DE CORREÇÃO DE PREÇOS DE EXERCÍCIOS OU VALORES DE CONTRATO
                // PARA OS MERCADOS DE OPÇÕES OU TERMO SECUNDÁRIO
                .INDOPC(trataValores(in.substring(202 - 1, 202)))
                // DATVEN - DATA DO VENCIMENTO PARA OS MERCADOS DE OPÇÕES OU TERMO SECUNDÁRIO
                .DATVEN(trataValores(in.substring(203 - 1, 210)))
                // FATCOT - FATOR DE COTAÇÃO DO PAPEL
                .FATCOT(trataValores(in.substring(211 - 1, 217)))
                // PTOEXE - PREÇO DE EXERCÍCIO EM PONTOS PARA OPÇÕES REFERENCIADAS EM DÓLAR OU
                // VALOR DE CONTRATO EM PONTOS PARA TERMO SECUNDÁRIO
                .PTOEXE(trataValores(in.substring(218 - 1, 230), true))
                // CODISI - CÓDIGO DO PAPEL NO SISTEMA ISIN OU CÓDIGO INTERNO DO PAPEL
                .CODISI(trataValores(in.substring(231 - 1, 242)))
                // DISMES - NÚMERO DE DISTRIBUIÇÃO DO PAPEL
                .DISMES(trataValores(in.substring(243 - 1, 245)))
                .build();

        //log.info("-> {}", dailyStockPriceDTO.toString());
        return dailyStockPriceDTO;
    }

    private boolean isCodbdiImportable(String CODBDI) {
        return CODBDI_FUNDOS_IMOBILIARIOS.equals(CODBDI);
    }

    private boolean isCodnegImportable(String CODNEG) {
        return Arrays.asList(TICKERS).contains(CODNEG);
    }

    private boolean isEspeciImportable(String ESPECI) {
        return !ESPECI.contains(ESPECI_DIREITO_SUBSCRICAO);
    }

    private String trataValores(String valor) {
        return trataValores(valor, false);
    }

    private String trataValores(String valor, boolean isDecimal) {
        valor = valor.trim();
        if (StringUtils.isNumeric(valor)) {
            valor = removeZerosAEsquerda(valor.trim());
            if (isDecimal) {
                valor = colocaPontoDecimalDuasCasas(valor);
            }
        }
        return valor;
    }

    private String removeZerosAEsquerda(String valor) {
        if (valor != null) {
            if (valor.startsWith(NUMBER_ZERO) && valor.length() > 1) {
                valor = StringUtils.stripStart(valor, NUMBER_ZERO);
            }
        }
        return valor;
    }

    private String colocaPontoDecimalDuasCasas(String valor) {
        if (valor != null) {
            valor = StringUtils.leftPad(valor, 3, NUMBER_ZERO);
            int tam = valor.length();
            valor = valor.substring(0, tam - 2) + "." + valor.substring(tam - 2, tam);
        }
        return valor;
    }

    private LocalDate getDateFromString(String datapg) {
        try {
            return LocalDate.parse(datapg, FORMATTER);
        } catch (DateTimeParseException e) {
            log.info("Erro ao formatar data: {}", datapg);
        }
        return null;
    }

    private BigDecimal getValorBigDecimal(String valorString) {
        if (Objects.isNull(valorString) || valorString.isEmpty() || valorString.length() < 2) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(valorString);
    }

    private BigInteger getValorBigInteger(String valorString) {
        if (Objects.isNull(valorString) || valorString.isEmpty() || valorString.length() < 2) {
            return BigInteger.ZERO;
        }
        return new BigInteger(valorString);
    }

}