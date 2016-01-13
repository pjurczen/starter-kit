package pl.spring.demo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.common.ContainerSequence;
import pl.spring.demo.to.BookTo;

@Aspect
@Component
public class BookIdAdvisor {
    
    private ContainerSequence<BookTo> containerSequence;

    @Autowired
    public BookIdAdvisor(ContainerSequence<BookTo> containerSequence) {
        this.containerSequence = containerSequence;
    }
    
    @Before("execution (* pl.spring.demo.dao.BookDao.save(..)) && args(book)")
    public void setNewId(BookTo book) {
        book.setId(containerSequence.nextValue());
    }
}
