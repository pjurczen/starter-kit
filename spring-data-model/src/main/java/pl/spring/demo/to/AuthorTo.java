package pl.spring.demo.to;

public class AuthorTo implements IdAware {
    private Long id;
    private String firstName;
    private String lastName;
    
    public AuthorTo(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getLastName() {
        return lastName;
    }

    @Override
    public Long getId() {
        return id;
    }
}
