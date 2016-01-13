package pl.spring.demo.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

import pl.spring.demo.dao.BookDao;

public class MockServiceTestConfiguration {
    
    @Bean
    public BookDao bookDao() {
        return Mockito.mock(BookDao.class);
    }
    
}
