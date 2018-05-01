package br.com.traderhelper.importador.dao;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import br.com.traderhelper.importador.entity.AcaoCotacao;

/**
 * Created by Michael on 23/03/2017.
 */
public interface AcaoCotacaoRepository extends CrudRepository<AcaoCotacao, Long>, AcaoCotacaoRepositoryCustom {

	@Cacheable("cotacoes")
	List<AcaoCotacao> findByAcaoId(Long acao);

}
