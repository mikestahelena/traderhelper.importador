package br.com.traderhelper.importador;

import br.com.traderhelper.importador.improve.data.DailyStockPriceDTO;
import br.com.traderhelper.importador.improve.processor.TheProcessor;
import br.com.traderhelper.importador.improve.processor.TheSkipPolicy;
import br.com.traderhelper.importador.improve.reader.TheReader;
//import br.com.traderhelper.importador.improve.tasklet.TheTasklet;
import br.com.traderhelper.importador.improve.tasklet.TheTasklet;
import br.com.traderhelper.importador.improve.writer.TheWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "br.com.traderhelper.importador")
public class AppConfiguration {

    private static final String TRADER_HELPER_IMPORT = "TRADER HELPER IMPORT";

    @Value("${batch.chunk-size}")
    private int chunkSize;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private TheTasklet theTasklet;

    @Autowired
    private TheReader theReader;

    @Autowired
    private TheProcessor theProcessor;

    @Autowired
    private TheWriter theWriter;

    @Autowired
    private TheSkipPolicy theSkipPolicy;

    @Bean
    public Step step1() {
        return stepBuilderFactory
                .get(TRADER_HELPER_IMPORT)
                .tasklet(theTasklet)
                .build();
    }

    /*@Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .<String, DailyStockPriceDTO>chunk(chunkSize)
                .reader(theReader)
                .processor(theProcessor)
                .faultTolerant()
                .skipPolicy(theSkipPolicy)
                .writer(theWriter)
                .build();
    }*/

    @Bean
    public Job job() {
        return jobBuilderFactory
                .get(TRADER_HELPER_IMPORT)
                .incrementer(new RunIdIncrementer())
                .start(step1())
				//.next(step2())
                .build();
    }
}
