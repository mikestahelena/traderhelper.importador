package br.com.traderhelper.importador.dao;

import java.util.List;

/**
 * Created by Michael Sta. Helena on 02/06/2017.
 */
public interface CommodityCotacaoRepositoryCustom {

	void batchSave(List<?> cotacoes);

	void batchMerge(List<?> cotacoes);
}
