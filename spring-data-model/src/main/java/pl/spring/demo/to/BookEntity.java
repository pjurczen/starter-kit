package pl.spring.demo.to;

import java.util.ArrayList;
import java.util.Collection;

public class BookEntity implements IdAware {
    private Long id;
    private String title;
    private Collection<AuthorTo> authors;
    
    public BookEntity(Long id, String title, String authors) throws IllegalArgumentException{
        this.authors = new ArrayList<AuthorTo>();
        this.id = id;
        this.title = title;
        String[] tmpAuthor = authors.split(" ");
        try {
            this.authors.add(new AuthorTo(1L, tmpAuthor[0], tmpAuthor[1])); 
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
    }
    
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
