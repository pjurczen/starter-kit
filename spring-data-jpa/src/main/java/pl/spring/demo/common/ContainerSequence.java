package pl.spring.demo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.to.IdAware;

@Component
public class ContainerSequence<T extends IdAware> extends Sequence {
    
    private Container<T> books;
    
    @Autowired
    public ContainerSequence(Container<T> books) {
        this.books = books;
    }
    
    public long nextValue() {
        return super.nextValue(books.getAll()) + 1;
    }
}
