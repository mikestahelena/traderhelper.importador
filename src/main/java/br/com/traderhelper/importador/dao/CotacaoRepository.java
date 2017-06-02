package br.com.traderhelper.importador.dao;

import br.com.traderhelper.importador.entity.Acao;
import br.com.traderhelper.importador.entity.Cotacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Michael on 23/03/2017.
 */
@Repository
public interface CotacaoRepository extends CrudRepository<Cotacao, Long>, CotacaoRepositoryCustom {

    Cotacao save(Cotacao cotacao);

    Cotacao findOne(Long id);

    boolean exists(Long id);

    List<Cotacao> findAll();

    long count();

    void delete(Long id);

    void delete(Cotacao cotacao);

    void deleteAll();

    Cotacao findTopDistinctPrecoAberturaByAcao(Acao acao);

    List<Cotacao> findByAcao(Acao acao);

    List<Cotacao> findByAcaoId(Long acao);

    List<Cotacao> findByAcaoAndDataPregaoBetween(Acao acao, Date de, Date ate);

    /*List<Cotacao> findByAcaoIdByOrderBy(Long acao);*/
}
