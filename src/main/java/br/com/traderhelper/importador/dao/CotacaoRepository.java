package br.com.traderhelper.importador.dao;

import br.com.traderhelper.importador.entity.Acao;
import br.com.traderhelper.importador.entity.Cotacao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Michael on 23/03/2017.
 */
public interface CotacaoRepository extends CrudRepository<Cotacao, Long>, CotacaoRepositoryCustom {

    List<Cotacao> findAll();

    Cotacao save(Cotacao cotacao);

    Cotacao findById(Long id);

    Cotacao findTopDistinctPrecoAberturaByAcao(Acao acao);

    List<Cotacao> findByAcao(Acao acao);

    List<Cotacao> findByAcaoId(Long acao);

    /*List<Cotacao> findByAcaoIdByOrderBy(Long acao);*/
}
