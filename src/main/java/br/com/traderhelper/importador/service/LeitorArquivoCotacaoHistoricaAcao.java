package br.com.traderhelper.importador.service;

import br.com.traderhelper.importador.domain.acoes.CotacaoHistoricaAcao;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by Michael on 24/03/2017.
 */
public interface LeitorArquivoCotacaoHistoricaAcao {

    List<CotacaoHistoricaAcao> lerTodosArquivosCotacaoHistorica(Path path) throws IOException;
}
