package pl.spring.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.to.IdAware;

@Aspect
@Component
public class BookDaoAdvisor {
    
    private Sequence sequence;
    
    @Autowired
    public BookDaoAdvisor(Sequence sequence) {
        this.sequence = sequence;
    }
    
    @Before(value = "@annotation(pl.spring.demo.annotation.NullableId)")
    public void before(JoinPoint joinPoint) throws Throwable {
        Object[] arguments = joinPoint.getArgs();
        if (arguments[0] instanceof BookTo) {
            checkNotNullId(arguments[0]);
            BookDao bookDaoImpl = (BookDao) joinPoint.getThis();
            ((BookTo) arguments[0]).setId(sequence.nextValue(bookDaoImpl.findAll()));
        }
    }

    private void checkNotNullId(Object o) {
        if (o instanceof IdAware && ((IdAware) o).getId() != null) {
            throw new BookNotNullIdException();
        }
    }
}
