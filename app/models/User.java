package models;

import play.*;
import play.db.jpa.*;
import javax.persistence.*;
import java.util.*;

@Entity
<<<<<<< HEAD
public class User extends Model
{
   
    public String passwordHash;
    
    public boolean subscribed;
=======
@Table(name = "Users")
public class User extends Model
{   
    @Lob
    public String Email;
>>>>>>> 66adabad87b4039bda916ef36506249c21cfae32
    
    @ManyToOne
    public UserRole userRole;
    
    @ManyToOne
    public PersonDetail personDetail;
    
<<<<<<< HEAD
=======
    @Lob
    public String LastName;
    
    public Boolean Subscribed;
    
    @ManyToOne
    public UserRole role;
>>>>>>> 66adabad87b4039bda916ef36506249c21cfae32
}
