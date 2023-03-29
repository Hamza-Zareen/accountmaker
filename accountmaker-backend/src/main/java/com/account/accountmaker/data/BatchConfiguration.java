package com.account.accountmaker.data;

import com.account.accountmaker.model.Customer;
import com.account.accountmaker.model.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfiguration {


    @Bean
    public FlatFileItemReader<CustomerInput> customerInputFlatFileItemReader() {
        return new FlatFileItemReaderBuilder<CustomerInput>()
                .name("CustomerInputItemReader")
                .resource(new ClassPathResource("customers.csv"))
                .delimited()
                .names(new String[]{"customerId", "name", "surname"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(CustomerInput.class);
                }})
                .build();
    }

    @Bean
    public CustomerItemProcessor customerItemProcessor() {
        return new CustomerItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Customer> customerJdbcBatchItemWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Customer>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO customer (customer_id, name, surname) VALUES (:customerId, :name, :surname)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager, JdbcBatchItemWriter<Customer> customerJdbcBatchItemWriter) {
        return new StepBuilder("step1", jobRepository)
                .<CustomerInput, Customer> chunk(10, transactionManager)
                .reader(customerInputFlatFileItemReader())
                .processor(customerItemProcessor())
                .writer(customerJdbcBatchItemWriter)
                .build();
    }

    @Bean
    public FlatFileItemReader<UserInput> userInputFlatFileItemReader() {
        return new FlatFileItemReaderBuilder<UserInput>()
                .name("UserInputItemReader")
                .resource(new ClassPathResource("user.csv"))
                .delimited()
                .names(new String[]{"id", "active", "password", "roles", "user_name"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(UserInput.class);
                }})
                .build();
    }

    @Bean
    public UserItemProcessor userItemProcessor() {
        return new UserItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<User> userJdbcBatchItemWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<User>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO myuser (id, active, password, roles, user_name) VALUES (:id, :active, :password, :roles, :userName)")
                .dataSource(dataSource)
                .build();
    }
    @Bean
    public Step step2(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager, JdbcBatchItemWriter<User> userJdbcBatchItemWriter) {
        return new StepBuilder("step2", jobRepository)
                .<UserInput, User> chunk(10, transactionManager)
                .reader(userInputFlatFileItemReader())
                .processor(userItemProcessor())
                .writer(userJdbcBatchItemWriter)
                .build();
    }


    @Bean
    public Job importCustomerJob(JobRepository jobRepository,
                             JobCompletionNotificationListener listener, Step step1,Step step2) {
        return new JobBuilder("importCustomerJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .next(step2)
                .end()
                .build();
    }
}
