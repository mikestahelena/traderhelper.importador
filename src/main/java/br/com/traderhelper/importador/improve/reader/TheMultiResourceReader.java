package br.com.traderhelper.importador.improve.reader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TheMultiResourceReader implements ItemReader<MultiResourceItemReader<String>>, ItemStream {

    private FlatFileItemReader<String> flatFileItemReader;
    private MultiResourceItemReader<String> multiResourceItemReader;

    @Override
    public MultiResourceItemReader<String> read() throws Exception {
        log.info("Read");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] inputResources = resolver.getResources("file:/home/michaelhelena/Área de Trabalho/bovespach/*.TXT");
        flatFileItemReader = new FlatFileItemReader<>();
        multiResourceItemReader = new MultiResourceItemReader<>();
        multiResourceItemReader.setResources(inputResources);
        multiResourceItemReader.setStrict(false);
        flatFileItemReader.setStrict(false);

        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(new PassThroughLineMapper());
        flatFileItemReader.afterPropertiesSet();

        multiResourceItemReader.setDelegate(flatFileItemReader);
        return multiResourceItemReader;
    }


    @Override
    public void open(ExecutionContext arg0) throws ItemStreamException {
        log.info("open");
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        log.info("update");
    }

    @Override
    public void close() throws ItemStreamException {
        log.info("close");
        flatFileItemReader.close();
        multiResourceItemReader.close();
    }
}