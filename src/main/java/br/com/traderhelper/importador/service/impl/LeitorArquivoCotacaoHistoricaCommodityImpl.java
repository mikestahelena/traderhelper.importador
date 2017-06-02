package br.com.traderhelper.importador.service.impl;


import br.com.traderhelper.importador.domain.commodities.CotacaoHistoricaCommodity;
import br.com.traderhelper.importador.domain.commodities.RegistroCotacaoDiariaCommodity;
import br.com.traderhelper.importador.service.LeitorArquivoCotacaoHistoricaCommodity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael Sta. Helena on 13/12/2016.
 */
@Service
public class LeitorArquivoCotacaoHistoricaCommodityImpl implements LeitorArquivoCotacaoHistoricaCommodity {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<CotacaoHistoricaCommodity> lerTodosArquivosCotacaoHistorica(Path path) throws IOException {
        List<Path> files = new ArrayList<>();
        List<CotacaoHistoricaCommodity> cotacoes = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path entry : stream) {
                files.add(entry);
            }
        }
        for (Path pathFiles : files) {
            cotacoes.add(lerArquivoCotacaoHistorica(pathFiles));
        }
        return cotacoes;
    }

    public CotacaoHistoricaCommodity lerArquivoCotacaoHistorica(Path caminho) throws IOException {
        List<String> linhasArquivo = Files.readAllLines(caminho, Charset.forName("ISO-8859-1"));
        logger.info("Arquivo lido. Quantidade de registros:" + linhasArquivo.size());
        logger.info("Gerando objeto de CotacaoHistoricaAcao...");
        CotacaoHistoricaCommodity cotacaoHistoricaAcao = lerLinhas(linhasArquivo);
        logger.info("CotacaoHistoricaAcao finalizado. Quantidade de registros:" + cotacaoHistoricaAcao.getRegistroCotacaoDiariaCommodityList().size());
        return cotacaoHistoricaAcao;
    }

    private CotacaoHistoricaCommodity lerLinhas(List<String> lines) {
        CotacaoHistoricaCommodity cotacaoHistoricaAcao = new CotacaoHistoricaCommodity();
        for (String linha : lines) {
            lerRegistros(linha, cotacaoHistoricaAcao);
        }
        return cotacaoHistoricaAcao;
    }

    private void lerRegistros(String linha, CotacaoHistoricaCommodity cotacaoHistoricaAcao) {
        cotacaoHistoricaAcao.addCotacaoDiaria(setDadosRegistroCotacaoDiaria(linha));
    }

    private RegistroCotacaoDiariaCommodity setDadosRegistroCotacaoDiaria(String s) {
        String[] campo = s.split(";");
        return new RegistroCotacaoDiariaCommodity.RegistroCotacaoDiariaCommodityBuilder()
                .withCODECO(campo[0])
                .withNAMECO(campo[1])
                .withSETORC(campo[2])
                .withDATECT(campo[3])
                .withPRICEC(campo[4])
                .withOPENVL(campo[5])
                .withLOWVLE(campo[6])
                .build();
    }
}
