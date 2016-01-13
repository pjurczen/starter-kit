package pl.spring.demo.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.to.BookTo;

@Component
@Aspect
public class BookId {
    
    private Sequence sequence;
    private BookDao bookDao;

    @Autowired
    public BookId(Sequence sequence, BookDao bookDao) {
        this.bookDao = bookDao;
        this.sequence = sequence;
    }
    
    @Before("execution (* pl.spring.demo.dao.BookDao.save(..)) && args(book)")
    public void setNewId(BookTo book) {
        book.setId(sequence.nextValue(bookDao.findAll())+1);
    }
}
