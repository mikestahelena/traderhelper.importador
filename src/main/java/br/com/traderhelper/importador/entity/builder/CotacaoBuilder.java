package br.com.traderhelper.importador.entity.builder;

import br.com.traderhelper.importador.entity.Acao;
import br.com.traderhelper.importador.entity.Cotacao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by Michael Sta. Helena on 26/12/2016.
 */
public final class CotacaoBuilder {
    private Long id;
    private Acao acao;
    private Date dataPregao;
    //PREABE - PREÇO DE ABERTURA DO PAPEL-MERCADO NO PREGÃO
    private BigDecimal precoAbertura;
    //PREMAX - PREÇO MÁXIMO DO PAPEL-MERCADO NO PREGÃO
    private BigDecimal precoMaximo;
    //PREMIN - PREÇO MÍNIMO DO PAPEL MERCADO NO PREGÃO
    private BigDecimal precoMinimo;
    //PREMED - PREÇO MÉDIO DO PAPEL MERCADO NO PREGÃO
    private BigDecimal precoMedio;
    //PREULT - PREÇO DO ÚLTIMO NEGÓCIO DO PAPEL-MERCADO NO PREGÃO (FECHAMENTO)
    private BigDecimal precoUltimoNegocio;
    //PREOFC - PREÇO DA MELHOR OFERTA DE COMPRA DO PAPEL-MERCADO
    private BigDecimal precoMelhorOfertaCompra;
    //PREOFV - PREÇO DA MELHOR OFERTA DE VENDA DO PAPEL-MERCADO
    private BigDecimal precoMelhorOfertaVenda;
    //TOTNEG - NEG. - NÚMERO DE NEGÓCIOS EFETUADOS COM O PAPEL MERCADO NO PREGÃO
    private BigInteger totalNegociosEfetuados;
    //QUATOT - QUANTIDADE TOTAL DE TÍTULOS NEGOCIADOS NESTE PAPEL MERCADO
    private BigInteger totalTitulosNegociados;
    //VOLTOT - VOLUME TOTAL DE TÍTULOS NEGOCIADOS NESTE PAPEL MERCADO
    private BigDecimal volumeTitulosNegociados;
    //PREEXE - PREÇO DE EXERCÍCIO PARA O MERCADO DE OPÇÕES OU VALOR DO CONTRATO PARA O MERCADO DE TERMO SECUNDÁRIO
    private BigDecimal precoExercicio;
    //PRAZOT - PRAZO EM DIAS DO MERCADO A TERMO
    private BigInteger prazoDiasMercadoTermo;
    //MODREF - MOEDA DE REFERÊNCIA
    private String moedaReferencia;
    //DATVEN - DATA DO VENCIMENTO PARA OS MERCADOS DE OPÇÕES OU TERMO SECUNDÁRIO
    private String dataVencimentoOpcoes;
    //FATCOT - FATOR DE COTAÇÃO DO PAPEL
    private String fatorCotacao;
    //PTOEXE - PREÇO DE EXERCÍCIO EM PONTOS PARA OPÇÕES REFERENCIADAS EM DÓLAR OU VALOR DE CONTRATO EM PONTOS PARA TERMO SECUNDÁRIO
    private BigDecimal precoPontosOpcoes;

    public static CotacaoBuilder aCotacao() {
        return new CotacaoBuilder();
    }

    public CotacaoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CotacaoBuilder withAcao(Acao acao) {
        this.acao = acao;
        return this;
    }

    public CotacaoBuilder withDataPregao(Date dataPregao) {
        this.dataPregao = dataPregao;
        return this;
    }

    public CotacaoBuilder withPrecoAbertura(BigDecimal precoAbertura) {
        this.precoAbertura = precoAbertura;
        return this;
    }

    public CotacaoBuilder withPrecoMaximo(BigDecimal precoMaximo) {
        this.precoMaximo = precoMaximo;
        return this;
    }

    public CotacaoBuilder withPrecoMinimo(BigDecimal precoMinimo) {
        this.precoMinimo = precoMinimo;
        return this;
    }

    public CotacaoBuilder withPrecoMedio(BigDecimal precoMedio) {
        this.precoMedio = precoMedio;
        return this;
    }

    public CotacaoBuilder withPrecoUltimoNegocio(BigDecimal precoUltimoNegocio) {
        this.precoUltimoNegocio = precoUltimoNegocio;
        return this;
    }

    public CotacaoBuilder withPrecoMelhorOfertaCompra(BigDecimal precoMelhorOfertaCompra) {
        this.precoMelhorOfertaCompra = precoMelhorOfertaCompra;
        return this;
    }

    public CotacaoBuilder withPrecoMelhorOfertaVenda(BigDecimal precoMelhorOfertaVenda) {
        this.precoMelhorOfertaVenda = precoMelhorOfertaVenda;
        return this;
    }

    public CotacaoBuilder withTotalNegociosEfetuados(BigInteger totalNegociosEfetuados) {
        this.totalNegociosEfetuados = totalNegociosEfetuados;
        return this;
    }

    public CotacaoBuilder withTotalTitulosNegociados(BigInteger totalTitulosNegociados) {
        this.totalTitulosNegociados = totalTitulosNegociados;
        return this;
    }

    public CotacaoBuilder withVolumeTitulosNegociados(BigDecimal volumeTitulosNegociados) {
        this.volumeTitulosNegociados = volumeTitulosNegociados;
        return this;
    }

    public CotacaoBuilder withPrecoExercicio(BigDecimal precoExercicio) {
        this.precoExercicio = precoExercicio;
        return this;
    }

    public CotacaoBuilder withPrazoDiasMercadoTermo(BigInteger prazoDiasMercadoTermo) {
        this.prazoDiasMercadoTermo = prazoDiasMercadoTermo;
        return this;
    }

    public CotacaoBuilder withMoedaReferencia(String moedaReferencia) {
        this.moedaReferencia = moedaReferencia;
        return this;
    }

    public CotacaoBuilder withDataVencimentoOpcoes(String dataVencimentoOpcoes) {
        this.dataVencimentoOpcoes = dataVencimentoOpcoes;
        return this;
    }

    public CotacaoBuilder withFatorCotacao(String fatorCotacao) {
        this.fatorCotacao = fatorCotacao;
        return this;
    }

    public CotacaoBuilder withPrecoPontosOpcoes(BigDecimal precoPontosOpcoes) {
        this.precoPontosOpcoes = precoPontosOpcoes;
        return this;
    }

    public Cotacao build() {
        Cotacao cotacao = new Cotacao();
        cotacao.setId(id);
        cotacao.setAcao(acao);
        cotacao.setDataPregao(dataPregao);
        cotacao.setPrecoAbertura(precoAbertura);
        cotacao.setPrecoMaximo(precoMaximo);
        cotacao.setPrecoMinimo(precoMinimo);
        cotacao.setPrecoMedio(precoMedio);
        cotacao.setPrecoUltimoNegocio(precoUltimoNegocio);
        cotacao.setPrecoMelhorOfertaCompra(precoMelhorOfertaCompra);
        cotacao.setPrecoMelhorOfertaVenda(precoMelhorOfertaVenda);
        cotacao.setTotalNegociosEfetuados(totalNegociosEfetuados);
        cotacao.setTotalTitulosNegociados(totalTitulosNegociados);
        cotacao.setVolumeTitulosNegociados(volumeTitulosNegociados);
        cotacao.setPrecoExercicio(precoExercicio);
        cotacao.setPrazoDiasMercadoTermo(prazoDiasMercadoTermo);
        cotacao.setMoedaReferencia(moedaReferencia);
        cotacao.setDataVencimentoOpcoes(dataVencimentoOpcoes);
        cotacao.setFatorCotacao(fatorCotacao);
        cotacao.setPrecoPontosOpcoes(precoPontosOpcoes);
        return cotacao;
    }
}
