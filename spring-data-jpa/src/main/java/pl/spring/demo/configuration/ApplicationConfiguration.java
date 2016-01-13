package pl.spring.demo.configuration;

import java.util.HashSet;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;

import pl.spring.demo.common.Container;
import pl.spring.demo.to.BookEntity;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("pl.spring.demo")
public class ApplicationConfiguration {
    
    @Bean
    public Container<BookEntity> books() {
        return new Container<BookEntity>(new HashSet<BookEntity>());
    }
    
    @Bean
    public PropertiesFactoryBean applicationProperties() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("config/application.properties"));
        return propertiesFactoryBean;
    }
}
