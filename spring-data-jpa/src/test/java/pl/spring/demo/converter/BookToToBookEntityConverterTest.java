package pl.spring.demo.converter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import pl.spring.demo.common.BookToToBookEntityConverter;
import pl.spring.demo.configuration.CommonServiceTestConfiguration;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = CommonServiceTestConfiguration.class)
public class BookToToBookEntityConverterTest {
    
    @Autowired
    private BookToToBookEntityConverter bookToToBookEntityConverter;
    
    @Test
    public void testShouldHandleConverting() {
        //given
        BookTo book = new BookTo(1L, "Title", "Author");
        // when
        BookEntity bookConverted = bookToToBookEntityConverter.convert(book);
        // then
        Assert.assertNotNull(bookConverted);
    }
}
