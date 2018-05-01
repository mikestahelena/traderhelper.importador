package br.com.traderhelper.importador.entity.builder;

import br.com.traderhelper.importador.entity.Commodity;
import br.com.traderhelper.importador.entity.CommodityCotacao;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Michael Sta. Helena on 02/06/2017.
 */
public final class CommodityCotacaoBuilder {
	private Long id;
	private Commodity commodity;
	private Date dataCotacao;
	private BigDecimal precoFechamento;
	private BigDecimal precoAbertura;
	private BigDecimal precoMaximo;
	private BigDecimal precoMinimo;

	public CommodityCotacaoBuilder() {
	}

	public static CommodityCotacaoBuilder aCommodityCotacao() {
		return new CommodityCotacaoBuilder();
	}

	public CommodityCotacaoBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public CommodityCotacaoBuilder withCommodity(Commodity commodity) {
		this.commodity = commodity;
		return this;
	}

	public CommodityCotacaoBuilder withDataCotacao(Date dataCotacao) {
		this.dataCotacao = dataCotacao;
		return this;
	}

	public CommodityCotacaoBuilder withPrecoFechamento(BigDecimal precoFechamento) {
		this.precoFechamento = precoFechamento;
		return this;
	}

	public CommodityCotacaoBuilder withPrecoAbertura(BigDecimal precoAbertura) {
		this.precoAbertura = precoAbertura;
		return this;
	}

	public CommodityCotacaoBuilder withPrecoMaximo(BigDecimal precoMaximo) {
		this.precoMaximo = precoMaximo;
		return this;
	}

	public CommodityCotacaoBuilder withPrecoMinimo(BigDecimal precoMinimo) {
		this.precoMinimo = precoMinimo;
		return this;
	}

	public CommodityCotacao build() {
		CommodityCotacao commodityCotacao = new CommodityCotacao();
		commodityCotacao.setId(id);
		commodityCotacao.setCommodity(commodity);
		commodityCotacao.setDataCotacao(dataCotacao);
		commodityCotacao.setPrecoFechamento(precoFechamento);
		commodityCotacao.setPrecoAbertura(precoAbertura);
		commodityCotacao.setPrecoMaximo(precoMaximo);
		commodityCotacao.setPrecoMinimo(precoMinimo);
		return commodityCotacao;
	}
}
