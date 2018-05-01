package br.com.traderhelper.importador.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by Michael Sta. Helena on 02/06/2017.
 */
@Entity
@Table(name = "COMMODITY")
public class Commodity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "NOMECO")
	private String nomeCommodity;

	@Column(name = "CODECO")
	private String codigoCommodity;

	@Column(name = "SETORC")
	private String setorCommodity;

	@OneToMany(mappedBy = "commodity", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<CommodityCotacao> cotacoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCommodity() {
		return nomeCommodity;
	}

	public void setNomeCommodity(String nomeCommodity) {
		this.nomeCommodity = nomeCommodity;
	}

	public String getCodigoCommodity() {
		return codigoCommodity;
	}

	public void setCodigoCommodity(String codigoCommodity) {
		this.codigoCommodity = codigoCommodity;
	}

	public String getSetorCommodity() {
		return setorCommodity;
	}

	public void setSetorCommodity(String setorCommodity) {
		this.setorCommodity = setorCommodity;
	}

	public List<CommodityCotacao> getCotacoes() {
		return cotacoes;
	}

	public void setCotacoes(List<CommodityCotacao> cotacoes) {
		this.cotacoes = cotacoes;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("nomeCommodity", nomeCommodity)
				.append("codigoCommodity", codigoCommodity).append("setorCommodity", setorCommodity)
				.append("cotacoes", cotacoes).toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Commodity commodity = (Commodity) o;
		return Objects.equals(id, commodity.id) && Objects.equals(nomeCommodity, commodity.nomeCommodity)
				&& Objects.equals(codigoCommodity, commodity.codigoCommodity)
				&& Objects.equals(setorCommodity, commodity.setorCommodity)
				&& Objects.equals(cotacoes, commodity.cotacoes);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nomeCommodity, codigoCommodity, setorCommodity, cotacoes);
	}
}
