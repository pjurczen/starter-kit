package pl.spring.demo.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.to.BookTo;

@Component
@Aspect
public class BookId {
    
    private ContainerSequence<BookTo> containerSequence;

    @Autowired
    public BookId(ContainerSequence<BookTo> sequence) {
        this.containerSequence = sequence;
    }
    
    @Before("execution (* pl.spring.demo.dao.BookDao.save(..)) && args(book)")
    public void setNewId(BookTo book) {
        book.setId(containerSequence.nextValue());
    }
}
