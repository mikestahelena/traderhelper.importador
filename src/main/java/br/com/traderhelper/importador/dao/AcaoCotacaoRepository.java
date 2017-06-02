package br.com.traderhelper.importador.dao;

import br.com.traderhelper.importador.entity.Acao;
import br.com.traderhelper.importador.entity.AcaoCotacao;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Michael on 23/03/2017.
 */
public interface AcaoCotacaoRepository extends CrudRepository<AcaoCotacao, Long>, AcaoCotacaoRepositoryCustom {

    AcaoCotacao save(AcaoCotacao cotacao);

    AcaoCotacao findOne(Long id);

    boolean exists(Long id);

    List<AcaoCotacao> findAll();

    long count();

    void delete(Long id);

    void delete(AcaoCotacao cotacao);

    void deleteAll();

    AcaoCotacao findTopDistinctPrecoAberturaByAcao(Acao acao);

    List<AcaoCotacao> findByAcao(Acao acao);

    List<AcaoCotacao> findByAcaoId(Long acao);

    List<AcaoCotacao> findByAcaoAndDataPregaoBetween(Acao acao, Date de, Date ate);

    /*List<AcaoCotacao> findByAcaoIdByOrderBy(Long acao);*/
}
