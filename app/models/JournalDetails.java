package models;

import javax.persistence.Entity;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name = "JournalDetails")
public class JournalDetails extends Model{
	
	public String title;
	public String info;
	public String templateLocation;

}
