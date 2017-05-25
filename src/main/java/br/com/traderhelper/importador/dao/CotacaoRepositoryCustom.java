package br.com.traderhelper.importador.dao;

import java.util.List;

/**
 * Created by Michael on 23/03/2017.
 */
public interface CotacaoRepositoryCustom {

    void batchSave(List cotacoes);

    void batchMerge(List cotacoes);

}
