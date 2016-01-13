package pl.spring.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.IdAware;

@Aspect
@Component
public class BookDaoAdvisor {
    
    @Before(value = "@annotation(pl.spring.demo.annotation.NullableId)")
    public void before(JoinPoint joinPoint) throws Throwable {
        Object[] arguments = joinPoint.getArgs();
        checkNotNullId(arguments[0]);
    }

    private void checkNotNullId(Object o) {
        if (o instanceof IdAware && ((IdAware) o).getId() != null) {
            throw new BookNotNullIdException();
        }
    }
}
