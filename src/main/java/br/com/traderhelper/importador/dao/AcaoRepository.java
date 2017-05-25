package br.com.traderhelper.importador.dao;

import br.com.traderhelper.importador.entity.Acao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Michael on 23/03/2017.
 */
public interface AcaoRepository extends CrudRepository<Acao, Long>, AcaoRepositoryCustom {

    List<Acao> findAll();

    Acao save(Acao acao);

    Acao findById(Long id);

    Acao findByCodigoPapel(String codigo);
}
