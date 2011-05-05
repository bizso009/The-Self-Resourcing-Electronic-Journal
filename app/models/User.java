package models;

import play.*;
import play.db.jpa.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Users")
public class User extends Model
{   
    @Lob
    public String Email;
    
    @Lob
    public String PasswordHash;
    
    @Lob
    public String FirstName;
    
    @Lob
    public String LastName;
    
    public Boolean Subscribed;
    
    @ManyToOne
    public UserRole role;
}
