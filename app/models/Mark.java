package models;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name = "Marks")
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
