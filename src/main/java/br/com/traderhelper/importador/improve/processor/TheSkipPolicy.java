package br.com.traderhelper.importador.improve.processor;

import br.com.traderhelper.importador.improve.exception.TheException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TheSkipPolicy implements SkipPolicy {

    @Override
    public boolean shouldSkip(Throwable exception, int skipCount) throws SkipLimitExceededException {
        if (exception instanceof TheException) {
            //TheException e = (TheException) exception;
            //log.info(String.format("%s - %s - %s",exception.getMessage(), e.getCODNEG(), e.getCODBDI()));
            return true;
        } else {
            return false;
        }
    }

}
