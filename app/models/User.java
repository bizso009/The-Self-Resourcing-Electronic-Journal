package models;

import play.*;
import play.db.jpa.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User extends Model
{
    @Id
    public Integer ID;
    
    @Lob
    public String Email;
    
    @Lob
    public String PasswordHash;
    
    @Lob
    public String FirstName;
    
    @Lob
    public String LastName;
    
    public Boolean Subscribed;
}
