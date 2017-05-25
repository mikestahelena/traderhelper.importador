package br.com.traderhelper.importador;

import br.com.traderhelper.importador.service.ImportaDados;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Michael on 23/03/2017.
 */
@Component
public class Start implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ImportaDados importaDados;

    public void run(String... args) {
        try {
            importaDados.iniciarImportacao();
        } catch (Exception e) {
            logger.error("Erro ao iniciar importação dos dados: ", e);
        }
    }
}
