package br.com.traderhelper.importador;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.SimpleJobExplorer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class DataSourceConfiguration {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String pass;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Bean
    public ResourcelessTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }

    @Bean
    public MapJobRepositoryFactoryBean mapJobRepositoryFactory(
            ResourcelessTransactionManager transactionManager)
            throws Exception {
        MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean(transactionManager);
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean
    public JobRepository jobRepository(MapJobRepositoryFactoryBean repositoryFactory)
            throws Exception {
        return repositoryFactory.getObject();
    }

    @Bean
    public JobExplorer jobExplorer(MapJobRepositoryFactoryBean repositoryFactory) {
        return new SimpleJobExplorer(repositoryFactory.getJobInstanceDao(),
                repositoryFactory.getJobExecutionDao(),
                repositoryFactory.getStepExecutionDao(),
                repositoryFactory.getExecutionContextDao());
    }

    @Bean
    public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(jobRepository);
        return launcher;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(final DataSource sgfDS) {
        return new JdbcTemplate(sgfDS);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(final DataSource sgfDS) {
        return new NamedParameterJdbcTemplate(sgfDS);
    }

    @Bean(name = "sgfDS")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName(driverClassName)
                .url(url)
                .username(user)
                .password(pass).build();
    }

    /*@Bean(name = "database")
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setUsername(user);
        config.setJdbcUrl(url);
        config.setPassword(pass);

        return new HikariDataSource(config);
    }*/

}

    /*@Value("${spring.datasource.hikari.auto-commit}")
    private boolean autoCommit;

    @Value("${spring.datasource.hikari.connection-timeout}")
    private long connectionTimeout;

    @Value("${spring.datasource.hikari.idle-timeout}")
    private long idleTimeout;

    @Value("${spring.datasource.hikari.max-lifetime}")
    private long maxLifetime;

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private int maximumPoolSize;

    @Value("${spring.datasource.hikari.minimum-idle}")
    private int minimumIdle;
*/
    /*@Bean
    public ResourcelessTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }

    @Bean
    public MapJobRepositoryFactoryBean mapJobRepositoryFactory(
            ResourcelessTransactionManager transactionManager)
            throws Exception {
        MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean(transactionManager);
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean
    public JobRepository jobRepository(MapJobRepositoryFactoryBean repositoryFactory)
            throws Exception {
        return repositoryFactory.getObject();
    }

    @Bean
    public JobExplorer jobExplorer(MapJobRepositoryFactoryBean repositoryFactory) {
        return new SimpleJobExplorer(repositoryFactory.getJobInstanceDao(),
                repositoryFactory.getJobExecutionDao(),
                repositoryFactory.getStepExecutionDao(),
                repositoryFactory.getExecutionContextDao());
    }

    @Bean
    public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(jobRepository);
        return launcher;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(final @Qualifier("database") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(final @Qualifier("database") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean(name = "database")
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setUsername(user);
        config.setJdbcUrl(url);
        config.setPassword(pass);

        *//*config.setConnectionTimeout(connectionTimeout);
        config.setIdleTimeout(idleTimeout);
        config.setMaxLifetime(maxLifetime);
        config.setMaximumPoolSize(maximumPoolSize);
        config.setMinimumIdle(minimumIdle);
        config.setAutoCommit(autoCommit);*//*

 *//*
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
*//*

        return new HikariDataSource(config);
    }
}
*/