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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AuthorTo other = (AuthorTo) obj;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.toLowerCase().equals(other.firstName.toLowerCase()))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.toLowerCase().equals(other.lastName.toLowerCase()))
            return false;
        return true;
    }
    
    
}
