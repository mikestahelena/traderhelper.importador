package br.com.traderhelper.importador.entity.builder;

import br.com.traderhelper.importador.entity.Acao;
import br.com.traderhelper.importador.entity.AcaoCotacao;

import java.util.List;

/**
 * Created by Michael Sta. Helena on 26/12/2016.
 */
public final class AcaoBuilder {
    private Long id;
    //CODNEG - CÓDIGO DE NEGOCIAÇÃO DO PAPEL
    private String codigoPapel;
    //NOMRES - NOME RESUMIDO DA EMPRESA EMISSORA DO PAPEL
    private String nomeResumidoPapel;
    //CODBDI - CÓDIGO BDI
    private String codigoBdi;
    //TPMERC - TIPO DE MERCADO
    private String codigoTipoMercado;
    //ESPECI - ESPECIFICAÇÃO DO PAPEL
    private String codigoEspecificacaoPapel;
    //INDOPC - INDICADOR DE CORREÇÃO DE PREÇOS DE EXERCÍCIOS OU VALORES DE CONTRATO PARA OS MERCADOS DE OPÇÕES OU TERMO SECUNDÁRIO
    private String indicadorCorrecao;
    //CODISI - CÓDIGO DO PAPEL NO SISTEMA ISIN OU CÓDIGO INTERNO DO PAPEL
    private String codigoIsin;
    //DISMES - NÚMERO DE DISTRIBUIÇÃO DO PAPEL
    private String numeroDistribuicaoPapel;
    private List<AcaoCotacao> cotacoes;

    public static AcaoBuilder anAcao() {
        return new AcaoBuilder();
    }

    public AcaoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public AcaoBuilder withCodigoPapel(String codigoPapel) {
        this.codigoPapel = codigoPapel;
        return this;
    }

    public AcaoBuilder withNomeResumidoPapel(String nomeResumidoPapel) {
        this.nomeResumidoPapel = nomeResumidoPapel;
        return this;
    }

    public AcaoBuilder withCodigoBdi(String codigoBdi) {
        this.codigoBdi = codigoBdi;
        return this;
    }

    public AcaoBuilder withCodigoTipoMercado(String codigoTipoMercado) {
        this.codigoTipoMercado = codigoTipoMercado;
        return this;
    }

    public AcaoBuilder withCodigoEspecificacaoPapel(String codigoEspecificacaoPapel) {
        this.codigoEspecificacaoPapel = codigoEspecificacaoPapel;
        return this;
    }

    public AcaoBuilder withIndicadorCorrecao(String indicadorCorrecao) {
        this.indicadorCorrecao = indicadorCorrecao;
        return this;
    }

    public AcaoBuilder withCodigoIsin(String codigoIsin) {
        this.codigoIsin = codigoIsin;
        return this;
    }

    public AcaoBuilder withNumeroDistribuicaoPapel(String numeroDistribuicaoPapel) {
        this.numeroDistribuicaoPapel = numeroDistribuicaoPapel;
        return this;
    }

    public AcaoBuilder withCotacoes(List<AcaoCotacao> cotacoes) {
        this.cotacoes = cotacoes;
        return this;
    }

    public Acao build() {
        Acao acao = new Acao();
        acao.setId(id);
        acao.setCodigoPapel(codigoPapel);
        acao.setNomeResumidoPapel(nomeResumidoPapel);
        acao.setCodigoBdi(codigoBdi);
        acao.setCodigoTipoMercado(codigoTipoMercado);
        acao.setCodigoEspecificacaoPapel(codigoEspecificacaoPapel);
        acao.setIndicadorCorrecao(indicadorCorrecao);
        acao.setCodigoIsin(codigoIsin);
        acao.setNumeroDistribuicaoPapel(numeroDistribuicaoPapel);
        acao.setCotacoes(cotacoes);
        return acao;
    }
}
