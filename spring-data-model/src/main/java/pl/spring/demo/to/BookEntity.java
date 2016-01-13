package pl.spring.demo.to;

import java.util.ArrayList;
import java.util.Collection;

public class BookEntity implements IdAware {
    private Long id;
    private String title;
    private Collection<AuthorTo> authors;
    
    /*public BookEntity(Long id, String title, Collection<AuthorTo> authors) {
        this.id = id;
        this.title = title;
        this.authors = authors;
    }*/
    
    public BookEntity(Long id, String title, String authors) {
        this.authors = new ArrayList<AuthorTo>();
        this.id = id;
        this.title = title;
        String[] tmpAuthor = authors.split(" ");
        if(tmpAuthor.length > 1) {
            this.authors.add(new AuthorTo(1L, tmpAuthor[0], tmpAuthor[1]));
        } else if (tmpAuthor.length > 0){
            this.authors.add(new AuthorTo(1L, "", tmpAuthor[0]));
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
