package br.com.traderhelper.importador.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by Michael Sta. Helena on 13/12/2016.
 */
@Entity
@Table(name = "ACAO")
public class Acao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    //CODNEG - CÓDIGO DE NEGOCIAÇÃO DO PAPEL
    @Column(name = "CODNEG", unique = true, nullable = false)
    private String codigoPapel;

    //NOMRES - NOME RESUMIDO DA EMPRESA EMISSORA DO PAPEL
    @Column(name = "NOMRES")
    private String nomeResumidoPapel;

    //CODBDI - CÓDIGO BDI
    @Column(name = "CODBDI")
    private String codigoBdi;

    //TPMERC - TIPO DE MERCADO
    @Column(name = "TPMERC")
    private String codigoTipoMercado;

    //ESPECI - ESPECIFICAÇÃO DO PAPEL
    @Column(name = "ESPECI")
    private String codigoEspecificacaoPapel;

    //INDOPC - INDICADOR DE CORREÇÃO DE PREÇOS DE EXERCÍCIOS OU VALORES DE CONTRATO PARA OS MERCADOS DE OPÇÕES OU TERMO SECUNDÁRIO
    @Column(name = "INDOPC")
    private String indicadorCorrecao;

    //CODISI - CÓDIGO DO PAPEL NO SISTEMA ISIN OU CÓDIGO INTERNO DO PAPEL
    @Column(name = "CODISI")
    private String codigoIsin;

    //DISMES - NÚMERO DE DISTRIBUIÇÃO DO PAPEL
    @Column(name = "DISMES")
    private String numeroDistribuicaoPapel;

    @OneToMany(mappedBy = "acao", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Cotacao> cotacoes;

    public Acao() {
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

    public String getCodigoPapel() {
        return codigoPapel;
    }

    public void setCodigoPapel(String codigoPapel) {
        this.codigoPapel = codigoPapel;
    }

    public String getNomeResumidoPapel() {
        return nomeResumidoPapel;
    }

    public void setNomeResumidoPapel(String nomeResumidoPapel) {
        this.nomeResumidoPapel = nomeResumidoPapel;
    }

    public String getCodigoBdi() {
        return codigoBdi;
    }

    public void setCodigoBdi(String codigoBdi) {
        this.codigoBdi = codigoBdi;
    }

    public String getCodigoTipoMercado() {
        return codigoTipoMercado;
    }

    public void setCodigoTipoMercado(String codigoTipoMercado) {
        this.codigoTipoMercado = codigoTipoMercado;
    }

    public String getCodigoEspecificacaoPapel() {
        return codigoEspecificacaoPapel;
    }

    public void setCodigoEspecificacaoPapel(String codigoEspecificacaoPapel) {
        this.codigoEspecificacaoPapel = codigoEspecificacaoPapel;
    }

    public String getIndicadorCorrecao() {
        return indicadorCorrecao;
    }

    public void setIndicadorCorrecao(String indicadorCorrecao) {
        this.indicadorCorrecao = indicadorCorrecao;
    }

    public String getCodigoIsin() {
        return codigoIsin;
    }

    public void setCodigoIsin(String codigoIsin) {
        this.codigoIsin = codigoIsin;
    }

    public String getNumeroDistribuicaoPapel() {
        return numeroDistribuicaoPapel;
    }

    public void setNumeroDistribuicaoPapel(String numeroDistribuicaoPapel) {
        this.numeroDistribuicaoPapel = numeroDistribuicaoPapel;
    }

    public List<Cotacao> getCotacoes() {
        return cotacoes;
    }

    public void setCotacoes(List<Cotacao> cotacoes) {
        this.cotacoes = cotacoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Acao acao = (Acao) o;
        return Objects.equals(id, acao.id) &&
                Objects.equals(codigoPapel, acao.codigoPapel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigoPapel);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("codigoPapel", codigoPapel)
                .append("nomeResumidoPapel", nomeResumidoPapel)
                .append("codigoBdi", codigoBdi)
                .append("codigoTipoMercado", codigoTipoMercado)
                .append("codigoEspecificacaoPapel", codigoEspecificacaoPapel)
                .append("indicadorCorrecao", indicadorCorrecao)
                .append("codigoIsin", codigoIsin)
                .append("numeroDistribuicaoPapel", numeroDistribuicaoPapel)
                .append("cotacoes", cotacoes)
                .toString();
    }
}
