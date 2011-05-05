package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.*;

@Entity
@Table(name="Marks")
public class Mark extends Model 
{
    /*
    @Id
    @Column(name="ID")
    public BigInteger id;
    */
    @Lob
    public String name;
}
