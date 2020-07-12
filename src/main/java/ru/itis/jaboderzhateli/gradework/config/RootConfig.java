package ru.itis.jaboderzhateli.gradework.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@PropertySource("classpath:application.properties")

@EnableAspectJAutoProxy
@ComponentScan(basePackages = "ru.itis.jaboderzhateli.gradework")
public class RootConfig {

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multiPartResolver() {
        return new CommonsMultipartResolver();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ObjectMapper objectMapper() {
        var objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newCachedThreadPool();
    }

    @Bean
    public MappingJackson2HttpMessageConverter customJson() {
        return new MappingJackson2HttpMessageConverter(
                new Jackson2ObjectMapperBuilder()
                        .indentOutput(true)
                        .serializationInclusion(JsonInclude.Include.NON_NULL)
                        .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                        .build()
        );
    }

}
