package pl.spring.demo.configuration;

import java.util.HashSet;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
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
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("config/application.properties"));
        return propertiesFactoryBean;
    }
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    public ApplicationContextAware applicationContextAware() {
        return new ApplicationContextAware();
    }
}
