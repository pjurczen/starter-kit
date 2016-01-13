package pl.spring.demo.to;

import java.util.Collection;

public class BookEntity implements IdAware {
    private Long id;
    private String title;
    private Collection<AuthorTo> authors;
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<AuthorTo> getAuthors() {
        return authors;
    }

    public void setAuthors(Collection<AuthorTo> authors) {
        this.authors = authors;
    }
    
    @Override
    public Long getId() {
        return id;
    }
    
}
