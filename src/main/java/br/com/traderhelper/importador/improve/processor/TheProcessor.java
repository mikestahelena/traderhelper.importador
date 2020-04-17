package br.com.traderhelper.importador.improve.processor;

import br.com.traderhelper.importador.improve.data.DailyStockPriceDTO;
import br.com.traderhelper.importador.improve.exception.TheException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@StepScope
@Component
public class TheProcessor implements ItemProcessor<String, DailyStockPriceDTO> {

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

    @Override
    public DailyStockPriceDTO process(String in) {

        final String TIPREG = trataValores(in.substring(1 - 1, 2));
        if(TIPREG.equals("99")){
            throw new TheException("TRAILER");
        }
        final String CODBDI = trataValores(in.substring(11 - 1, 12));
        final String CODNEG = trataValores(in.substring(13 - 1, 24));
        if(CODBDI.equals("96")){
            throw new TheException(CODBDI, CODNEG, "CODBDI INVALIDO");
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
                .TPMERC(trataValores(in.substring(25 - 1, 27)))
                // NOMRES - NOME RESUMIDO DA EMPRESA EMISSORA DO PAPEL
                .NOMRES(trataValores(in.substring(28 - 1, 39)))
                // ESPECI - ESPECIFICAÇÃO DO PAPEL
                .ESPECI(trataValores(in.substring(40 - 1, 49)))
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
                .PREEXE(getValorBigDecimal(trataValores(in.substring(189 - 1, 201))))
                // INDOPC - INDICADOR DE CORREÇÃO DE PREÇOS DE EXERCÍCIOS OU VALORES DE CONTRATO
                // PARA OS MERCADOS DE OPÇÕES OU TERMO SECUNDÁRIO
                .INDOPC(trataValores(in.substring(202 - 1, 202)))
                // DATVEN - DATA DO VENCIMENTO PARA OS MERCADOS DE OPÇÕES OU TERMO SECUNDÁRIO
                .DATVEN(trataValores(in.substring(203 - 1, 210)))
                // FATCOT - FATOR DE COTAÇÃO DO PAPEL
                .FATCOT(trataValores(in.substring(211 - 1, 217)))
                // PTOEXE - PREÇO DE EXERCÍCIO EM PONTOS PARA OPÇÕES REFERENCIADAS EM DÓLAR OU
                // VALOR DE CONTRATO EM PONTOS PARA TERMO SECUNDÁRIO
                /* .PTOEXE(trataValores(in.substring(218 - 1, 230), true)) */
                // CODISI - CÓDIGO DO PAPEL NO SISTEMA ISIN OU CÓDIGO INTERNO DO PAPEL
                .CODISI(trataValores(in.substring(231 - 1, 242)))
                // DISMES - NÚMERO DE DISTRIBUIÇÃO DO PAPEL
                .DISMES(trataValores(in.substring(243 - 1, 245)))
                .build();

        //log.info("-> {}", dailyStockPriceDTO.toString());
        return dailyStockPriceDTO;
    }

    private String trataValores(String valor) {
        return trataValores(valor, false);
    }


    private String trataValores(String valor, boolean isDecimal) {
        valor = removeZerosAEsquerda(valor.trim());
        if (isDecimal) {
            valor = colocaPontoDecimalDuasCasas(valor);
        }
        return valor;
    }

    private String removeZerosAEsquerda(String valor) {
        if (valor != null) {
            while (valor.startsWith("0") && valor.length() > 1) {
                valor = valor.replaceFirst("0", "");
            }
        }
        return valor;
    }

    private String colocaPontoDecimalDuasCasas(String valor) {
        if (valor != null) {
            while (valor.length() < 3) {
                valor = "0" + valor;
            }
            int tam = valor.length();
            valor = valor.substring(0, tam - 2) + "." + valor.substring(tam - 2, tam);
        }
        return valor;
    }

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