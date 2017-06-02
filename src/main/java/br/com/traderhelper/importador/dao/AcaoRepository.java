package br.com.traderhelper.importador.dao;

import br.com.traderhelper.importador.entity.Acao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Michael on 23/03/2017.
 */
public interface AcaoRepository extends CrudRepository<Acao, Long>, AcaoRepositoryCustom {

    Acao save(Acao acao);

    Acao findOne(Long id);

    boolean exists(Long id);

    List<Acao> findAll();

    long count();

    void delete(Long id);

    void delete(Acao acao);

    void deleteAll();

    Acao findByCodigoPapel(String codigo);
}
