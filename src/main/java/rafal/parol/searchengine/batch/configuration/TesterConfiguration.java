package rafal.parol.searchengine.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import rafal.parol.searchengine.batch.listeners.JobCompletionNotificationListener;
import rafal.parol.searchengine.batch.model.Tester;
import rafal.parol.searchengine.batch.processors.TesterItemProcessor;
import rafal.parol.searchengine.batch.readers.BlankLineRecordSeparatorPolicy;

import javax.sql.DataSource;

@Configuration
public class TesterConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<Tester> testerReader() {
        return new FlatFileItemReaderBuilder<Tester>()
                .name("testerItemReader")
                .resource(new ClassPathResource("/static/testers.csv"))
                .delimited()
                .names(new String[]{"testerId", "firstName", "lastName", "country", "lastLogin"})
                .recordSeparatorPolicy(new BlankLineRecordSeparatorPolicy())
                .linesToSkip(1)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Tester>() {{
                    setTargetType(Tester.class);
                }})
                .build();
    }

    @Bean
    public TesterItemProcessor testerProcessor() {
        return new TesterItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Tester> testerWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Tester>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO testers (tester_id, first_name, last_name, country, last_login) VALUES (:testerId, :firstName, :lastName, :country, :lastLogin)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job testerJob(JobCompletionNotificationListener listener, Step testerStep) {
        return jobBuilderFactory.get("testerJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(testerStep)
                .end()
                .build();
    }

    @Bean
    public Step testerStep(JdbcBatchItemWriter<Tester> testerWriter) {
        return stepBuilderFactory.get("testerStep")
                .<Tester, Tester> chunk(10)
                .reader(testerReader())
                .processor(testerProcessor())
                .writer(testerWriter)
                .build();
    }
}
