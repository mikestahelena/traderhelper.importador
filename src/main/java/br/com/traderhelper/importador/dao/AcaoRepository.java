package br.com.traderhelper.importador.dao;

import br.com.traderhelper.importador.entity.Acao;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Michael on 23/03/2017.
 */
@Repository
public interface AcaoRepository extends CrudRepository<Acao, Long>, AcaoRepositoryCustom {

	@Cacheable("acoes")
	List<Acao> findAll();

}
