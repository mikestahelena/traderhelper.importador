package br.com.traderhelper.importador.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.traderhelper.importador.entity.Commodity;

/**
 * Created by Michael on 23/03/2017.
 */
public interface CommodityRepository extends CrudRepository<Commodity, Long>, CommodityRepositoryCustom {
}
