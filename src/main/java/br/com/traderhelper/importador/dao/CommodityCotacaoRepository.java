package br.com.traderhelper.importador.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.traderhelper.importador.entity.CommodityCotacao;

/**
 * Created by Michael on 23/03/2017.
 */
public interface CommodityCotacaoRepository
		extends CrudRepository<CommodityCotacao, Long>, CommodityCotacaoRepositoryCustom {
}
