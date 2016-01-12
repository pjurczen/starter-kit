package pl.spring.demo.common;

import java.util.Collection;
import java.util.stream.Stream;


public class Container<T> {
    
    private Collection<T> container;
    
    public Container(Collection<T> container) {
        this.container = container;
    }
    
    public void add(T t) {
        container.add(t);
    }
    
    public Collection<T> getAll() {
        return container;
    }

    public Stream<T> getAsStream() {
        return container.stream();
    }
}
