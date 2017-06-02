package br.com.traderhelper.importador.service;

import br.com.traderhelper.importador.domain.commodities.CotacaoHistoricaCommodity;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by Michael on 24/03/2017.
 */
public interface LeitorArquivoCotacaoHistoricaCommodity {

    List<CotacaoHistoricaCommodity> lerTodosArquivosCotacaoHistorica(Path path) throws IOException;
}
