package models;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name = "UserRoles")
public class UserRole extends Model
{
    public static final String READER = "reader";
    public static final String AUTHOR_REVIEWER = "author/reviewer";
    public static final String EDITOR = "editor";
    

    @Lob
    public String name;


	public static UserRole findByRole(String role) {
		return find("byName", role).first();
	}

}
