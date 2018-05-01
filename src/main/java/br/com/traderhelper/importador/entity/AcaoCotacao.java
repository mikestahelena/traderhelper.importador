package br.com.traderhelper.importador.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by Michael Sta. Helena on 13/12/2016.
 */
@Entity
@Table(name = "ACAO_COTACAO")
public class AcaoCotacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ACAO_ID")
	private Acao acao;

	@Column(name = "DATAPG")
	@Temporal(TemporalType.DATE)
	private Date dataPregao;

	// PREABE - PREÇO DE ABERTURA DO PAPEL-MERCADO NO PREGÃO
	@Column(name = "PREABE", precision = 10, scale = 2)
	private BigDecimal precoAbertura;

	// PREMAX - PREÇO MÁXIMO DO PAPEL-MERCADO NO PREGÃO
	@Column(name = "PREMAX", precision = 10, scale = 2)
	private BigDecimal precoMaximo;

	// PREMIN - PREÇO MÍNIMO DO PAPEL MERCADO NO PREGÃO
	@Column(name = "PREMIN", precision = 10, scale = 2)
	private BigDecimal precoMinimo;

	// PREMED - PREÇO MÉDIO DO PAPEL MERCADO NO PREGÃO
	@Column(name = "PREMED", precision = 10, scale = 2)
	private BigDecimal precoMedio;

	// PREULT - PREÇO DO ÚLTIMO NEGÓCIO DO PAPEL-MERCADO NO PREGÃO (FECHAMENTO)
	@Column(name = "PREULT", precision = 10, scale = 2)
	private BigDecimal precoUltimoNegocio;

	// PREOFC - PREÇO DA MELHOR OFERTA DE COMPRA DO PAPEL-MERCADO
	@Column(name = "PREOFC", precision = 10, scale = 2)
	private BigDecimal precoMelhorOfertaCompra;

	// PREOFV - PREÇO DA MELHOR OFERTA DE VENDA DO PAPEL-MERCADO
	@Column(name = "PREOFV", precision = 10, scale = 2)
	private BigDecimal precoMelhorOfertaVenda;

	// TOTNEG - NEG. - NÚMERO DE NEGÓCIOS EFETUADOS COM O PAPEL MERCADO NO PREGÃO
	@Column(name = "TOTNEG")
	private BigInteger totalNegociosEfetuados;

	// QUATOT - QUANTIDADE TOTAL DE TÍTULOS NEGOCIADOS NESTE PAPEL MERCADO
	@Column(name = "QUATOT")
	private BigInteger totalTitulosNegociados;

	// VOLTOT - VOLUME TOTAL DE TÍTULOS NEGOCIADOS NESTE PAPEL MERCADO
	@Column(name = "VOLTOT", precision = 16, scale = 2)
	private BigDecimal volumeTitulosNegociados;

	// PREEXE - PREÇO DE EXERCÍCIO PARA O MERCADO DE OPÇÕES OU VALOR DO CONTRATO
	// PARA O MERCADO DE TERMO SECUNDÁRIO
	@Column(name = "PREEXE", precision = 16, scale = 2)
	private BigDecimal precoExercicio;

	// PRAZOT - PRAZO EM DIAS DO MERCADO A TERMO
	@Column(name = "PRAZOT", precision = 4, scale = 0)
	private BigInteger prazoDiasMercadoTermo;

	// MODREF - MOEDA DE REFERÊNCIA
	@Column(name = "MODREF")
	private String moedaReferencia;

	// DATVEN - DATA DO VENCIMENTO PARA OS MERCADOS DE OPÇÕES OU TERMO SECUNDÁRIO
	@Column(name = "DATVEN")
	private String dataVencimentoOpcoes;

	// FATCOT - FATOR DE COTAÇÃO DO PAPEL
	@Column(name = "FATCOT")
	private String fatorCotacao;

	// PTOEXE - PREÇO DE EXERCÍCIO EM PONTOS PARA OPÇÕES REFERENCIADAS EM DÓLAR OU
	// VALOR DE CONTRATO EM PONTOS PARA TERMO SECUNDÁRIO
	@Column(name = "PTOEXE", precision = 18, scale = 6)
	private BigDecimal precoPontosOpcoes;

	// VARPDA - VARIAÇÃO PERCENTUAL EM RELAÇÃO AO DIA ANTERIOR
	@Column(name = "VARPDA", precision = 10, scale = 2)
	private BigDecimal variacaoPercentualDiaAnterior;

	// VARCDA - VARIAÇÃO EM CENTAVOS EM RELAÇÃO AO DIA ANTERIOR
	@Column(name = "VARCDA", precision = 10, scale = 2)
	private BigDecimal variacaoCentavosDiaAnterior;

	public AcaoCotacao() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Date getDataPregao() {
		return dataPregao;
	}

	public void setDataPregao(Date dataPregao) {
		this.dataPregao = dataPregao;
	}

	public BigDecimal getPrecoAbertura() {
		return precoAbertura;
	}

	public void setPrecoAbertura(BigDecimal precoAbertura) {
		this.precoAbertura = precoAbertura;
	}

	public BigDecimal getPrecoMaximo() {
		return precoMaximo;
	}

	public void setPrecoMaximo(BigDecimal precoMaximo) {
		this.precoMaximo = precoMaximo;
	}

	public BigDecimal getPrecoMinimo() {
		return precoMinimo;
	}

	public void setPrecoMinimo(BigDecimal precoMinimo) {
		this.precoMinimo = precoMinimo;
	}

	public BigDecimal getPrecoMedio() {
		return precoMedio;
	}

	public void setPrecoMedio(BigDecimal precoMedio) {
		this.precoMedio = precoMedio;
	}

	public BigDecimal getPrecoUltimoNegocio() {
		return precoUltimoNegocio;
	}

	public void setPrecoUltimoNegocio(BigDecimal precoUltimoNegocio) {
		this.precoUltimoNegocio = precoUltimoNegocio;
	}

	public BigDecimal getPrecoMelhorOfertaCompra() {
		return precoMelhorOfertaCompra;
	}

	public void setPrecoMelhorOfertaCompra(BigDecimal precoMelhorOfertaCompra) {
		this.precoMelhorOfertaCompra = precoMelhorOfertaCompra;
	}

	public BigDecimal getPrecoMelhorOfertaVenda() {
		return precoMelhorOfertaVenda;
	}

	public void setPrecoMelhorOfertaVenda(BigDecimal precoMelhorOfertaVenda) {
		this.precoMelhorOfertaVenda = precoMelhorOfertaVenda;
	}

	public BigInteger getTotalNegociosEfetuados() {
		return totalNegociosEfetuados;
	}

	public void setTotalNegociosEfetuados(BigInteger totalNegociosEfetuados) {
		this.totalNegociosEfetuados = totalNegociosEfetuados;
	}

	public BigInteger getTotalTitulosNegociados() {
		return totalTitulosNegociados;
	}

	public void setTotalTitulosNegociados(BigInteger totalTitulosNegociados) {
		this.totalTitulosNegociados = totalTitulosNegociados;
	}

	public BigDecimal getVolumeTitulosNegociados() {
		return volumeTitulosNegociados;
	}

	public void setVolumeTitulosNegociados(BigDecimal volumeTitulosNegociados) {
		this.volumeTitulosNegociados = volumeTitulosNegociados;
	}

	public BigDecimal getPrecoExercicio() {
		return precoExercicio;
	}

	public void setPrecoExercicio(BigDecimal precoExercicio) {
		this.precoExercicio = precoExercicio;
	}

	public BigInteger getPrazoDiasMercadoTermo() {
		return prazoDiasMercadoTermo;
	}

	public void setPrazoDiasMercadoTermo(BigInteger prazoDiasMercadoTermo) {
		this.prazoDiasMercadoTermo = prazoDiasMercadoTermo;
	}

	public String getMoedaReferencia() {
		return moedaReferencia;
	}

	public void setMoedaReferencia(String moedaReferencia) {
		this.moedaReferencia = moedaReferencia;
	}

	public String getDataVencimentoOpcoes() {
		return dataVencimentoOpcoes;
	}

	public void setDataVencimentoOpcoes(String dataVencimentoOpcoes) {
		this.dataVencimentoOpcoes = dataVencimentoOpcoes;
	}

	public String getFatorCotacao() {
		return fatorCotacao;
	}

	public void setFatorCotacao(String fatorCotacao) {
		this.fatorCotacao = fatorCotacao;
	}

	public BigDecimal getPrecoPontosOpcoes() {
		return precoPontosOpcoes;
	}

	public void setPrecoPontosOpcoes(BigDecimal precoPontosOpcoes) {
		this.precoPontosOpcoes = precoPontosOpcoes;
	}

	public BigDecimal getVariacaoPercentualDiaAnterior() {
		return variacaoPercentualDiaAnterior;
	}

	public void setVariacaoPercentualDiaAnterior(BigDecimal variacaoPercentualDiaAnterior) {
		this.variacaoPercentualDiaAnterior = variacaoPercentualDiaAnterior;
	}

	public BigDecimal getVariacaoCentavosDiaAnterior() {
		return variacaoCentavosDiaAnterior;
	}

	public void setVariacaoCentavosDiaAnterior(BigDecimal variacaoCentavosDiaAnterior) {
		this.variacaoCentavosDiaAnterior = variacaoCentavosDiaAnterior;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		AcaoCotacao acaoCotacao = (AcaoCotacao) o;

		return id.equals(acaoCotacao.id);

	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return "AcaoCotacao{" + "id=" + id + ", acao=" + acao + ", dataPregao=" + dataPregao + ", precoAbertura="
				+ precoAbertura + ", precoMaximo=" + precoMaximo + ", precoMinimo=" + precoMinimo + ", precoMedio="
				+ precoMedio + ", precoUltimoNegocio=" + precoUltimoNegocio + ", precoMelhorOfertaCompra="
				+ precoMelhorOfertaCompra + ", precoMelhorOfertaVenda=" + precoMelhorOfertaVenda
				+ ", totalNegociosEfetuados=" + totalNegociosEfetuados + ", totalTitulosNegociados="
				+ totalTitulosNegociados + ", volumeTitulosNegociados=" + volumeTitulosNegociados + ", precoExercicio="
				+ precoExercicio + ", prazoDiasMercadoTermo=" + prazoDiasMercadoTermo + ", moedaReferencia='"
				+ moedaReferencia + '\'' + ", dataVencimentoOpcoes='" + dataVencimentoOpcoes + '\'' + ", fatorCotacao='"
				+ fatorCotacao + '\'' + ", precoPontosOpcoes=" + precoPontosOpcoes + ", variacaoPercentualDiaAnterior="
				+ variacaoPercentualDiaAnterior + ", variacaoCentavosDiaAnterior=" + variacaoCentavosDiaAnterior + '}';
	}
}
