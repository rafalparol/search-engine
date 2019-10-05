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
import rafal.parol.searchengine.batch.model.Device;
import rafal.parol.searchengine.batch.processors.DeviceItemProcessor;
import rafal.parol.searchengine.batch.readers.BlankLineRecordSeparatorPolicy;

import javax.sql.DataSource;

@Configuration
public class DeviceConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<Device> deviceReader() {
        return new FlatFileItemReaderBuilder<Device>()
                .name("deviceItemReader")
                .resource(new ClassPathResource("/static/devices.csv"))
                .delimited()
                .names(new String[]{"deviceId", "description"})
                .recordSeparatorPolicy(new BlankLineRecordSeparatorPolicy())
                .linesToSkip(1)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Device>() {{
                    setTargetType(Device.class);
                }})
                .build();
    }

    @Bean
    public DeviceItemProcessor deviceProcessor() {
        return new DeviceItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Device> deviceWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Device>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO devices (device_id, description) VALUES (:deviceId, :description)")
                .dataSource(dataSource)
                .build();
    }

    @Bean(name="deviceJobBean")
    public Job deviceJob(JobCompletionNotificationListener listener, Step deviceStep) {
        return jobBuilderFactory.get("deviceJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(deviceStep)
                .end()
                .build();
    }

    @Bean
    public Step deviceStep(JdbcBatchItemWriter<Device> deviceWriter) {
        return stepBuilderFactory.get("deviceStep")
                .<Device, Device> chunk(10)
                .reader(deviceReader())
                .processor(deviceProcessor())
                .writer(deviceWriter)
                .build();
    }
}
