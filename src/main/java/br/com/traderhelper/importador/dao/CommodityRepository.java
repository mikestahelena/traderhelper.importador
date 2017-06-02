package br.com.traderhelper.importador.dao;

import br.com.traderhelper.importador.entity.Commodity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Michael on 23/03/2017.
 */
public interface CommodityRepository extends CrudRepository<Commodity, Long>, CommodityRepositoryCustom {

    Commodity save(Commodity commodity);

    Commodity findOne(Long id);

    boolean exists(Long id);

    List<Commodity> findAll();

    long count();

    void delete(Long id);

    void delete(Commodity commodity);

    void deleteAll();

}
