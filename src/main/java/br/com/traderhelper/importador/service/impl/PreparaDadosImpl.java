package br.com.traderhelper.importador.service.impl;

import br.com.traderhelper.importador.dao.AcaoCotacaoRepository;
import br.com.traderhelper.importador.dao.AcaoRepository;
import br.com.traderhelper.importador.entity.Acao;
import br.com.traderhelper.importador.entity.AcaoCotacao;
import br.com.traderhelper.importador.service.PreparaDados;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Michael on 30/12/2016.
 */
@Service
public class PreparaDadosImpl implements PreparaDados {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AcaoRepository acaoRepository;

    @Autowired
    AcaoCotacaoRepository acaoCotacaoRepository;

    @Transactional
    public void calcularPercentualDeVariacao() {

        List<Acao> acoes = acaoRepository.findAll();

        for (Acao a : acoes) {
            List<AcaoCotacao> cotacoesPorAcao = acaoCotacaoRepository.findByAcaoId(a.getId());
            logger.info("Cotações encontradas para  " + a.getCodigoPapel() + ":" + cotacoesPorAcao.size());
            try {
                for (int i = 0; i < cotacoesPorAcao.size(); i++) {
                    if (i == 0) {
                        cotacoesPorAcao.get(i).setVariacaoPercentualDiaAnterior(BigDecimal.ZERO);
                        cotacoesPorAcao.get(i).setVariacaoCentavosDiaAnterior(BigDecimal.ZERO);
                    } else {
                        AcaoCotacao acaoCotacaoAnterior = cotacoesPorAcao.get(i - 1);

                        BigDecimal precoUltimoNegocioCotacaoAnterior = acaoCotacaoAnterior.getPrecoUltimoNegocio();
                        BigDecimal precoUltimoNegocioCotacao = cotacoesPorAcao.get(i).getPrecoUltimoNegocio();

                        BigDecimal percentualVariacao = precoUltimoNegocioCotacao
                                .subtract(precoUltimoNegocioCotacaoAnterior)
                                .divide(precoUltimoNegocioCotacaoAnterior, 4, BigDecimal.ROUND_HALF_UP)
                                .multiply(new BigDecimal("100.00")).setScale(2);

                        BigDecimal centavosVariacao = precoUltimoNegocioCotacao.subtract(precoUltimoNegocioCotacaoAnterior);

                        cotacoesPorAcao.get(i).setVariacaoPercentualDiaAnterior(percentualVariacao);
                        cotacoesPorAcao.get(i).setVariacaoCentavosDiaAnterior(centavosVariacao);
                    }
                }
            } catch (Exception e) {
                logger.error("Erro ao calcular variacao: " + a.getCodigoPapel(), e);
            }
            try {
                acaoCotacaoRepository.batchMerge(cotacoesPorAcao);
            } catch (Exception e) {
                logger.error("Erro ao persistir.", e);
            }
        }
    }
}
