package br.com.traderhelper.importador.service.impl;

import br.com.traderhelper.importador.dao.AcaoRepository;
import br.com.traderhelper.importador.dao.CotacaoRepository;
import br.com.traderhelper.importador.entity.Acao;
import br.com.traderhelper.importador.entity.Cotacao;
import br.com.traderhelper.importador.service.PreparaDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Michael on 30/12/2016.
 */
@Service
public class PreparaDadosImpl implements PreparaDados {

    @Autowired
    AcaoRepository acaoRepository;

    @Autowired
    CotacaoRepository cotacaoRepository;

    public void calcularPercentualDeVariacao() {

        List<Acao> acoes = acaoRepository.findAll();
        for (Acao a : acoes) {
            List<Cotacao> cotacoesPorAcao = cotacaoRepository.findByAcaoId(a.getId());
            for (int i = 0; i < cotacoesPorAcao.size(); i++) {
                if (i == 0) {
                    cotacoesPorAcao.get(i).setVariacaoPercentualDiaAnterior(BigDecimal.ZERO);
                    cotacoesPorAcao.get(i).setVariacaoCentavosDiaAnterior(BigDecimal.ZERO);
                } else {
                    Cotacao cotacaoAnterior = cotacoesPorAcao.get(i - 1);

                    BigDecimal precoUltimoNegocioCotacaoAnterior = cotacaoAnterior.getPrecoUltimoNegocio();
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
            try {
                cotacaoRepository.batchMerge(cotacoesPorAcao);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
