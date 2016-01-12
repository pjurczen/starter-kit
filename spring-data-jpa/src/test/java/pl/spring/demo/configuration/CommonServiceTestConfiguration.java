package pl.spring.demo.configuration;

import java.util.Arrays;

import org.mockito.Mock;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pl.spring.demo.dao.BookDao;

@Configuration
@EnableCaching
@Import(ApplicationConfiguration.class)
public class CommonServiceTestConfiguration {
    @Bean
    public CacheManager cacheManager() {
       SimpleCacheManager cacheManager = new SimpleCacheManager();
       cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("booksCache")));
       return cacheManager;
    }
    
    @Mock
    BookDao bookDao;
}
