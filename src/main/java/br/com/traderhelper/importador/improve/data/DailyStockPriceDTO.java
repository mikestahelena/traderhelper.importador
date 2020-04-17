package br.com.traderhelper.importador.improve.data;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
@Builder
public class DailyStockPriceDTO {

    // TIPREG - TIPO DE REGISTRO
    private String TIPREG;
    // DATA DO PREGÃO
    private Date DATAPG;
    // CODBDI - CÓDIGO BDI
    private String CODBDI;
    // CODNEG - CÓDIGO DE NEGOCIAÇÃO DO PAPEL
    private String CODNEG;
    // TPMERC - TIPO DE MERCADO
    private String TPMERC;
    // NOMRES - NOME RESUMIDO DA EMPRESA EMISSORA DO PAPEL
    private String NOMRES;
    // ESPECI - ESPECIFICAÇÃO DO PAPEL
    private String ESPECI;
    // PRAZOT - PRAZO EM DIAS DO MERCADO A TERMO
    private BigInteger PRAZOT;
    // MODREF - MOEDA DE REFERÊNCIA
    private String MODREF;
    // PREABE - PREÇO DE ABERTURA DO PAPEL-MERCADO NO PREGÃO
    private BigDecimal PREABE;
    // PREMAX - PREÇO MÁXIMO DO PAPEL-MERCADO NO PREGÃO
    private BigDecimal PREMAX;
    // PREMIN - PREÇO MÍNIMO DO PAPEL MERCADO NO PREGÃO
    private BigDecimal PREMIN;
    // PREMED - PREÇO MÉDIO DO PAPEL MERCADO NO PREGÃO
    private BigDecimal PREMED;
    // PREULT - PREÇO DO ÚLTIMO NEGÓCIO DO PAPEL-MERCADO NO PREGÃO
    private BigDecimal PREULT;
    // PREOFC - PREÇO DA MELHOR OFERTA DE COMPRA DO PAPEL-MERCADO
    private BigDecimal PREOFC;
    // PREOFV - PREÇO DA MELHOR OFERTA DE VENDA DO PAPEL-MERCADO
    private BigDecimal PREOFV;
    // TOTNEG - NEG. - NÚMERO DE NEGÓCIOS EFETUADOS COM O PAPEL MERCADO NO PREGÃO
    private BigInteger TOTNEG;
    // QUATOT - QUANTIDADE TOTAL DE TÍTULOS NEGOCIADOS NESTE PAPEL MERCADO
    private BigInteger QUATOT;
    // VOLTOT - VOLUME TOTAL DE TÍTULOS NEGOCIADOS NESTE PAPEL MERCADO
    private BigDecimal VOLTOT;
    // PREEXE - PREÇO DE EXERCÍCIO PARA O MERCADO DE OPÇÕES OU VALOR DO CONTRATO
    // PARA O MERCADO DE TERMO SECUNDÁRIO
    private BigDecimal PREEXE;
    // INDOPC - INDICADOR DE CORREÇÃO DE PREÇOS DE EXERCÍCIOS OU VALORES DE CONTRATO
    // PARA OS MERCADOS DE OPÇÕES OU TERMO SECUNDÁRIO
    private String INDOPC;
    // DATVEN - DATA DO VENCIMENTO PARA OS MERCADOS DE OPÇÕES OU TERMO SECUNDÁRIO
    private String DATVEN;
    // FATCOT - FATOR DE COTAÇÃO DO PAPEL
    private String FATCOT;
    // PTOEXE - PREÇO DE EXERCÍCIO EM PONTOS PARA OPÇÕES REFERENCIADAS EM DÓLAR OU
    // VALOR DE CONTRATO EM PONTOS PARA TERMO SECUNDÁRIO
    private String PTOEXE;
    // CODISI - CÓDIGO DO PAPEL NO SISTEMA ISIN OU CÓDIGO INTERNO DO PAPEL
    private String CODISI;
    // DISMES - NÚMERO DE DISTRIBUIÇÃO DO PAPEL
    private String DISMES;
}
