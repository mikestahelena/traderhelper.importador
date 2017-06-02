package br.com.traderhelper.importador.domain.commodities;

import br.com.traderhelper.importador.domain.acoes.RegistroTrailer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 14/12/2016.
 */
public class CotacaoHistoricaCommodity {

    private List<RegistroCotacaoDiariaCommodity> registroCotacaoDiariaCommodityList;
    private RegistroTrailer registroTrailer;

    public List<RegistroCotacaoDiariaCommodity> getRegistroCotacaoDiariaCommodityList() {
        if (registroCotacaoDiariaCommodityList == null) {
            registroCotacaoDiariaCommodityList = new ArrayList<>();
        }
        return registroCotacaoDiariaCommodityList;
    }

    public void setRegistroCotacaoDiariaCommodityList(List<RegistroCotacaoDiariaCommodity> registroCotacaoDiariaCommodityList) {
        this.registroCotacaoDiariaCommodityList = registroCotacaoDiariaCommodityList;
    }

    public RegistroTrailer getRegistroTrailer() {
        return registroTrailer;
    }

    public void setRegistroTrailer(RegistroTrailer registroTrailer) {
        this.registroTrailer = registroTrailer;
    }

    public void addCotacaoDiaria(RegistroCotacaoDiariaCommodity registroCotacaoDiariaCommodity) {
        this.getRegistroCotacaoDiariaCommodityList().add(registroCotacaoDiariaCommodity);
    }
}
