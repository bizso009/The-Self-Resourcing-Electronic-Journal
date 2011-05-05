package models;

import play.*;
import play.db.jpa.*;
import javax.persistence.*;
import java.util.*;

@Entity
public class User extends Model
{
   
    public String passwordHash;
    
    public boolean subscribed;

    @ManyToOne
    public PersonDetail personDetail;
    
    @ManyToOne
    public UserRole role;
}
