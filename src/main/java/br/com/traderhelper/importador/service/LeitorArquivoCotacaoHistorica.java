package br.com.traderhelper.importador.service;

import br.com.traderhelper.importador.domain.CotacaoHistorica;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by Michael on 24/03/2017.
 */
public interface LeitorArquivoCotacaoHistorica {

    List<CotacaoHistorica> lerTodosArquivosCotacaoHistorica(Path path) throws IOException;
}
