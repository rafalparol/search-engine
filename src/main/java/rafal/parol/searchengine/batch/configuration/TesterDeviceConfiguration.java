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
import rafal.parol.searchengine.batch.model.TesterDevice;
import rafal.parol.searchengine.batch.processors.TesterDeviceItemProcessor;
import rafal.parol.searchengine.batch.readers.BlankLineRecordSeparatorPolicy;
import rafal.parol.searchengine.batch.writers.NoOpItemWriter;

@Configuration
public class TesterDeviceConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public NoOpItemWriter<TesterDevice> testerDeviceWriter() {
        return new NoOpItemWriter<>();
    }

    @Bean
    public FlatFileItemReader<TesterDevice> testerDeviceReader() {
        return new FlatFileItemReaderBuilder<TesterDevice>()
                .name("testerDeviceItemReader")
                .resource(new ClassPathResource("/static/tester_device.csv"))
                .delimited()
                .names(new String[]{"testerId", "deviceId"})
                .recordSeparatorPolicy(new BlankLineRecordSeparatorPolicy())
                .linesToSkip(1)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<TesterDevice>() {{
                    setTargetType(TesterDevice.class);
                }})
                .build();
    }

    @Autowired
    public TesterDeviceItemProcessor testerDeviceProcessor;

    @Bean(name="testerDeviceJobBean")
    public Job testerDeviceJob(JobCompletionNotificationListener listener, Step testerDeviceStep) {
        return jobBuilderFactory.get("testerDeviceJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(testerDeviceStep)
                .end()
                .build();
    }

    @Bean
    public Step testerDeviceStep(NoOpItemWriter<TesterDevice> testerDeviceWriter) {
        return stepBuilderFactory.get("testerDeviceStep")
                .<TesterDevice, TesterDevice> chunk(10)
                .reader(testerDeviceReader())
                .processor(testerDeviceProcessor)
                .writer(testerDeviceWriter)
                .build();
    }
}
