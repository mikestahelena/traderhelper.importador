package br.com.traderhelper.importador.entity.builder;

import br.com.traderhelper.importador.entity.Commodity;
import br.com.traderhelper.importador.entity.CommodityCotacao;

import java.util.List;

/**
 * Created by Michael Sta. Helena on 02/06/2017.
 */
public final class CommodityBuilder {
    private Long id;
    private String nomeCommodity;
    private String codigoCommodity;
    private String setorCommodity;
    private List<CommodityCotacao> cotacoes;

    public CommodityBuilder() {
    }

    public static CommodityBuilder aCommodity() {
        return new CommodityBuilder();
    }

    public CommodityBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CommodityBuilder withNomeCommodity(String nomeCommodity) {
        this.nomeCommodity = nomeCommodity;
        return this;
    }

    public CommodityBuilder withCodigoCommodity(String codigoCommodity) {
        this.codigoCommodity = codigoCommodity;
        return this;
    }

    public CommodityBuilder withSetorCommodity(String setorCommodity) {
        this.setorCommodity = setorCommodity;
        return this;
    }

    public CommodityBuilder withCotacoes(List<CommodityCotacao> cotacoes) {
        this.cotacoes = cotacoes;
        return this;
    }

    public Commodity build() {
        Commodity commodity = new Commodity();
        commodity.setId(id);
        commodity.setNomeCommodity(nomeCommodity);
        commodity.setCodigoCommodity(codigoCommodity);
        commodity.setSetorCommodity(setorCommodity);
        commodity.setCotacoes(cotacoes);
        return commodity;
    }
}
