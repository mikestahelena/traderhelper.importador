package br.com.traderhelper.importador.improve.reader;

import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class TheReader implements ItemReader<String> {

    private FlatFileItemReader<String> flatFileItemReader;

    @BeforeStep
    public void before() throws Exception{
        flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new FileSystemResource("/home/michaelhelena/Área de Trabalho/bovespach/COTAHIST_A2020.TXT"));
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(new PassThroughLineMapper());
        flatFileItemReader.afterPropertiesSet();
        flatFileItemReader.open(new ExecutionContext());
    }
    @Override
    public String read() throws Exception {
        return flatFileItemReader.read();
    }

    /*FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();

            tokenizer.setNames("ISIN", "Quantity", "Price", "Customer");
            tokenizer.setColumns(new Range(1-12),
                                 new Range(13-15),
                                 new Range(16-20),
                                 new Range(21-29));
            return tokenizer;*/

}


    /*@Bean
    public ItemReader<String> reader() {
        Resource[] resources = null;
        ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        try {
            resources = patternResolver.getResources("/myfolder/*.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        MultiResourceItemReader<String> reader = new MultiResourceItemReader<>();
        reader.setResources(resources);
        reader.setDelegate(new FlatFileItemReader<>(..))
		return reader;
    }*/