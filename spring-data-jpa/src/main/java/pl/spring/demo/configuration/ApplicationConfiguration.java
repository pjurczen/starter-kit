package pl.spring.demo.configuration;

import java.util.HashSet;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;

import pl.spring.demo.common.Container;
import pl.spring.demo.to.BookTo;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("pl.spring.demo")
public class ApplicationConfiguration {
    @Bean
    public Container<BookTo> books() {
        return new Container<BookTo>(new HashSet<BookTo>());
    }
    
    @Bean
    public PropertiesFactoryBean applicationProperties() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("config/application.properties"));
        return bean;
    }
}
