package br.com.traderhelper.importador.dao;

import br.com.traderhelper.importador.entity.CommodityCotacao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Michael on 23/03/2017.
 */
public interface CommodityCotacaoRepository extends CrudRepository<CommodityCotacao, Long>, CommodityCotacaoRepositoryCustom {

    CommodityCotacao save(CommodityCotacao commodityCotacao);

    CommodityCotacao findOne(Long id);

    boolean exists(Long id);

    List<CommodityCotacao> findAll();

    long count();

    void delete(Long id);

    void delete(CommodityCotacao commodityCotacao);

    void deleteAll();

}
