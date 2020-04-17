
package br.com.traderhelper.importador.improve.tasklet;

import br.com.traderhelper.importador.improve.data.StockPriceDTO;
import br.com.traderhelper.importador.improve.repository.TheRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class TheTasklet implements Tasklet {

	@Autowired
	private TheRepository theRepository;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
			throws Exception {

        //List<String> lista = theRepository.findAllCodneg();
        //for(String codneg : lista) {
			List<StockPriceDTO> stockPriceDTOS = theRepository.findAllStockPricesCodneg("PETR4");

			StockPriceDTO maiorAlta = null;
			BigDecimal maiorDif = BigDecimal.ZERO;
			BigDecimal maiorAltaDif = BigDecimal.ZERO;
			for(StockPriceDTO stockPriceDTO : stockPriceDTOS) {
				maiorAltaDif = stockPriceDTO.getPREULT().subtract(stockPriceDTO.getPREABE());
				if(maiorAltaDif.compareTo(maiorDif) == 1) {
					maiorDif = maiorAltaDif;
					maiorAlta = stockPriceDTO;
				}
			}
			if(Objects.nonNull(maiorAlta)) {
				BigDecimal percentual = maiorDif.divide(maiorAlta.getPREABE(), 5, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
				log.info("Maior Alta: {} - {}", maiorAlta, percentual);
			}

			maiorDif = BigDecimal.ZERO;
			StockPriceDTO maiorBaixa = null;
			BigDecimal maiorBaixaDif = BigDecimal.ZERO;
			for(StockPriceDTO stockPriceDTO : stockPriceDTOS) {
				maiorBaixaDif = stockPriceDTO.getPREABE().subtract(stockPriceDTO.getPREULT());
				if(maiorAltaDif.compareTo(maiorDif) == 1) {
					maiorDif = maiorBaixaDif;
					maiorBaixa = stockPriceDTO;
				}
			}
			if(Objects.nonNull(maiorBaixa)) {
				BigDecimal percentual = maiorDif.divide(maiorBaixa.getPREABE(), 5, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
				log.info("Maior Baixa: {} - {}", maiorBaixa, percentual);
			}
        //}
		
		return RepeatStatus.FINISHED;
	}

}
