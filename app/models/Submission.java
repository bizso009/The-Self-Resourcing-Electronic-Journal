package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.*;
import play.data.validation.*;

@Entity
public class Submission extends Model
{
    public boolean prioratized;
    
    @OneToMany(mappedBy="submission",cascade = CascadeType.ALL)
    public List<Article> articles;
}