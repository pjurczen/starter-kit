package pl.spring.demo.configuration;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.dao.impl.BookDaoImpl;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("pl.spring.demo")
public class ApplicationConfiguration {
    
    @Bean
    public BookDao bookDao() {
        return new BookDaoImpl();
    }
    
    @Bean
    public PropertiesFactoryBean applicationProperties() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("config/application.properties"));
        return propertiesFactoryBean;
    }
    
}
