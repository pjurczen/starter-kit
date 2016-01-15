package pl.spring.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pl.spring.demo.dao.BookDao;

@Configuration
@Import(CommonServiceTestConfiguration.class)
public class MockServiceTestConfiguration {
    
    @Bean
    public BookDao bookDao() {
        return Mockito.mock(BookDao.class);
    }
    
}
