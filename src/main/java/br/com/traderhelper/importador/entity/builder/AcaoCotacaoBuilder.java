package br.com.traderhelper.importador.entity.builder;

import br.com.traderhelper.importador.entity.Acao;
import br.com.traderhelper.importador.entity.AcaoCotacao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by Michael Sta. Helena on 26/12/2016.
 */
public final class AcaoCotacaoBuilder {
	private Long id;
	private Acao acao;
	private Date dataPregao;
	// PREABE - PREÇO DE ABERTURA DO PAPEL-MERCADO NO PREGÃO
	private BigDecimal precoAbertura;
	// PREMAX - PREÇO MÁXIMO DO PAPEL-MERCADO NO PREGÃO
	private BigDecimal precoMaximo;
	// PREMIN - PREÇO MÍNIMO DO PAPEL MERCADO NO PREGÃO
	private BigDecimal precoMinimo;
	// PREMED - PREÇO MÉDIO DO PAPEL MERCADO NO PREGÃO
	private BigDecimal precoMedio;
	// PREULT - PREÇO DO ÚLTIMO NEGÓCIO DO PAPEL-MERCADO NO PREGÃO (FECHAMENTO)
	private BigDecimal precoUltimoNegocio;
	// PREOFC - PREÇO DA MELHOR OFERTA DE COMPRA DO PAPEL-MERCADO
	private BigDecimal precoMelhorOfertaCompra;
	// PREOFV - PREÇO DA MELHOR OFERTA DE VENDA DO PAPEL-MERCADO
	private BigDecimal precoMelhorOfertaVenda;
	// TOTNEG - NEG. - NÚMERO DE NEGÓCIOS EFETUADOS COM O PAPEL MERCADO NO PREGÃO
	private BigInteger totalNegociosEfetuados;
	// QUATOT - QUANTIDADE TOTAL DE TÍTULOS NEGOCIADOS NESTE PAPEL MERCADO
	private BigInteger totalTitulosNegociados;
	// VOLTOT - VOLUME TOTAL DE TÍTULOS NEGOCIADOS NESTE PAPEL MERCADO
	private BigDecimal volumeTitulosNegociados;
	// PREEXE - PREÇO DE EXERCÍCIO PARA O MERCADO DE OPÇÕES OU VALOR DO CONTRATO
	// PARA O MERCADO DE TERMO SECUNDÁRIO
	private BigDecimal precoExercicio;
	// PRAZOT - PRAZO EM DIAS DO MERCADO A TERMO
	private BigInteger prazoDiasMercadoTermo;
	// MODREF - MOEDA DE REFERÊNCIA
	private String moedaReferencia;
	// DATVEN - DATA DO VENCIMENTO PARA OS MERCADOS DE OPÇÕES OU TERMO SECUNDÁRIO
	private String dataVencimentoOpcoes;
	// FATCOT - FATOR DE COTAÇÃO DO PAPEL
	private String fatorCotacao;
	// PTOEXE - PREÇO DE EXERCÍCIO EM PONTOS PARA OPÇÕES REFERENCIADAS EM DÓLAR OU
	// VALOR DE CONTRATO EM PONTOS PARA TERMO SECUNDÁRIO
	private BigDecimal precoPontosOpcoes;

	public static AcaoCotacaoBuilder aCotacao() {
		return new AcaoCotacaoBuilder();
	}

	public AcaoCotacaoBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public AcaoCotacaoBuilder withAcao(Acao acao) {
		this.acao = acao;
		return this;
	}

	public AcaoCotacaoBuilder withDataPregao(Date dataPregao) {
		this.dataPregao = dataPregao;
		return this;
	}

	public AcaoCotacaoBuilder withPrecoAbertura(BigDecimal precoAbertura) {
		this.precoAbertura = precoAbertura;
		return this;
	}

	public AcaoCotacaoBuilder withPrecoMaximo(BigDecimal precoMaximo) {
		this.precoMaximo = precoMaximo;
		return this;
	}

	public AcaoCotacaoBuilder withPrecoMinimo(BigDecimal precoMinimo) {
		this.precoMinimo = precoMinimo;
		return this;
	}

	public AcaoCotacaoBuilder withPrecoMedio(BigDecimal precoMedio) {
		this.precoMedio = precoMedio;
		return this;
	}

	public AcaoCotacaoBuilder withPrecoUltimoNegocio(BigDecimal precoUltimoNegocio) {
		this.precoUltimoNegocio = precoUltimoNegocio;
		return this;
	}

	public AcaoCotacaoBuilder withPrecoMelhorOfertaCompra(BigDecimal precoMelhorOfertaCompra) {
		this.precoMelhorOfertaCompra = precoMelhorOfertaCompra;
		return this;
	}

	public AcaoCotacaoBuilder withPrecoMelhorOfertaVenda(BigDecimal precoMelhorOfertaVenda) {
		this.precoMelhorOfertaVenda = precoMelhorOfertaVenda;
		return this;
	}

	public AcaoCotacaoBuilder withTotalNegociosEfetuados(BigInteger totalNegociosEfetuados) {
		this.totalNegociosEfetuados = totalNegociosEfetuados;
		return this;
	}

	public AcaoCotacaoBuilder withTotalTitulosNegociados(BigInteger totalTitulosNegociados) {
		this.totalTitulosNegociados = totalTitulosNegociados;
		return this;
	}

	public AcaoCotacaoBuilder withVolumeTitulosNegociados(BigDecimal volumeTitulosNegociados) {
		this.volumeTitulosNegociados = volumeTitulosNegociados;
		return this;
	}

	public AcaoCotacaoBuilder withPrecoExercicio(BigDecimal precoExercicio) {
		this.precoExercicio = precoExercicio;
		return this;
	}

	public AcaoCotacaoBuilder withPrazoDiasMercadoTermo(BigInteger prazoDiasMercadoTermo) {
		this.prazoDiasMercadoTermo = prazoDiasMercadoTermo;
		return this;
	}

	public AcaoCotacaoBuilder withMoedaReferencia(String moedaReferencia) {
		this.moedaReferencia = moedaReferencia;
		return this;
	}

	public AcaoCotacaoBuilder withDataVencimentoOpcoes(String dataVencimentoOpcoes) {
		this.dataVencimentoOpcoes = dataVencimentoOpcoes;
		return this;
	}

	public AcaoCotacaoBuilder withFatorCotacao(String fatorCotacao) {
		this.fatorCotacao = fatorCotacao;
		return this;
	}

	public AcaoCotacaoBuilder withPrecoPontosOpcoes(BigDecimal precoPontosOpcoes) {
		this.precoPontosOpcoes = precoPontosOpcoes;
		return this;
	}

	public AcaoCotacao build() {
		AcaoCotacao acaoCotacao = new AcaoCotacao();
		acaoCotacao.setId(id);
		acaoCotacao.setAcao(acao);
		acaoCotacao.setDataPregao(dataPregao);
		acaoCotacao.setPrecoAbertura(precoAbertura);
		acaoCotacao.setPrecoMaximo(precoMaximo);
		acaoCotacao.setPrecoMinimo(precoMinimo);
		acaoCotacao.setPrecoMedio(precoMedio);
		acaoCotacao.setPrecoUltimoNegocio(precoUltimoNegocio);
		acaoCotacao.setPrecoMelhorOfertaCompra(precoMelhorOfertaCompra);
		acaoCotacao.setPrecoMelhorOfertaVenda(precoMelhorOfertaVenda);
		acaoCotacao.setTotalNegociosEfetuados(totalNegociosEfetuados);
		acaoCotacao.setTotalTitulosNegociados(totalTitulosNegociados);
		acaoCotacao.setVolumeTitulosNegociados(volumeTitulosNegociados);
		acaoCotacao.setPrecoExercicio(precoExercicio);
		acaoCotacao.setPrazoDiasMercadoTermo(prazoDiasMercadoTermo);
		acaoCotacao.setMoedaReferencia(moedaReferencia);
		acaoCotacao.setDataVencimentoOpcoes(dataVencimentoOpcoes);
		acaoCotacao.setFatorCotacao(fatorCotacao);
		acaoCotacao.setPrecoPontosOpcoes(precoPontosOpcoes);
		return acaoCotacao;
	}
}
