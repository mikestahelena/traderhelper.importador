package br.com.traderhelper.importador.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Michael Sta. Helena on 02/06/2017.
 */
@Entity
@Table(name = "COMMODITY_COTACAO")
public class CommodityCotacao {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COMMODITY_ID")
    private Commodity commodity;

    @Column(name = "DATECT")
    @Temporal(TemporalType.DATE)
    private Date dataCotacao;

    @Column(name = "PRICEC", precision = 10, scale = 2)
    private BigDecimal precoFechamento;

    @Column(name = "OPENVL", precision = 10, scale = 2)
    private BigDecimal precoAbertura;

    @Column(name = "HIGHVL", precision = 10, scale = 2)
    private BigDecimal precoMaximo;

    @Column(name = "LOWVLE", precision = 10, scale = 2)
    private BigDecimal precoMinimo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public Date getDataCotacao() {
        return dataCotacao;
    }

    public void setDataCotacao(Date dataCotacao) {
        this.dataCotacao = dataCotacao;
    }

    public BigDecimal getPrecoFechamento() {
        return precoFechamento;
    }

    public void setPrecoFechamento(BigDecimal precoFechamento) {
        this.precoFechamento = precoFechamento;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("commodity", commodity)
                .append("dataCotacao", dataCotacao)
                .append("precoFechamento", precoFechamento)
                .append("precoAbertura", precoAbertura)
                .append("precoMaximo", precoMaximo)
                .append("precoMinimo", precoMinimo)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommodityCotacao that = (CommodityCotacao) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(commodity, that.commodity) &&
                Objects.equals(dataCotacao, that.dataCotacao) &&
                Objects.equals(precoFechamento, that.precoFechamento) &&
                Objects.equals(precoAbertura, that.precoAbertura) &&
                Objects.equals(precoMaximo, that.precoMaximo) &&
                Objects.equals(precoMinimo, that.precoMinimo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, commodity, dataCotacao, precoFechamento, precoAbertura, precoMaximo, precoMinimo);
    }
}
