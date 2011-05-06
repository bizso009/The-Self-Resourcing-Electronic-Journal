package models;

import play.*;
import play.db.jpa.*;
import javax.persistence.*;
import controllers.Secure.Security;
import java.util.*;

@Entity
@Table(name = "Users")
public class User extends Model
{
   
    public String passwordHash;
    
    public boolean subscribed;

    @ManyToOne
    public PersonDetail personDetail;
    
    @ManyToOne
    public UserRole role;
    
    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    public List<ReviewerAssignment> assignments;
}
