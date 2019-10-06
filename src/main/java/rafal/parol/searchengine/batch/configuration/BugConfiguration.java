package rafal.parol.searchengine.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import rafal.parol.searchengine.batch.listeners.JobCompletionNotificationListener;
import rafal.parol.searchengine.batch.model.Bug;
import rafal.parol.searchengine.batch.processors.BugItemProcessor;
import rafal.parol.searchengine.batch.readers.BlankLineRecordSeparatorPolicy;
import rafal.parol.searchengine.batch.writers.NoOpItemWriter;

@Configuration
public class BugConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public NoOpItemWriter<Bug> bugWriter() {
        return new NoOpItemWriter<>();
    }

    @Bean
    public FlatFileItemReader<Bug> bugReader() {
        return new FlatFileItemReaderBuilder<Bug>()
                .name("bugItemReader")
                .resource(new ClassPathResource("/static/bugs.csv"))
                .delimited()
                .names(new String[]{"bugId", "deviceId", "testerId"})
                .recordSeparatorPolicy(new BlankLineRecordSeparatorPolicy())
                .linesToSkip(1)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Bug>() {{
                    setTargetType(Bug.class);
                }})
                .build();
    }

    @Autowired
    public BugItemProcessor bugProcessor;

    @Bean(name="bugJobBean")
    public Job bugJob(JobCompletionNotificationListener listener, Step bugStep) {
        return jobBuilderFactory.get("bugJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(bugStep)
                .end()
                .build();
    }

    @Bean
    public Step bugStep(NoOpItemWriter<Bug> bugWriter) {
        return stepBuilderFactory.get("bugStep")
                .<Bug, Bug> chunk(10)
                .reader(bugReader())
                .processor(bugProcessor)
                .writer(bugWriter)
                .build();
    }
}
