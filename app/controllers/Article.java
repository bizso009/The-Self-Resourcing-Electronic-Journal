package controllers;
 
import play.db.jpa.Blob;
import play.db.jpa.Model;
 
import javax.persistence.Entity;
 
@Entity
public class Article extends Model {
 
   public String name;
   public Blob photo;
}
