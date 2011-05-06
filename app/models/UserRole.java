package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name = "UserRoles")
public class UserRole extends Model implements Comparable<UserRole>
{
    public static final String READER = "reader";
    public static final String AUTHOR_REVIEWER = "author/reviewer";
    public static final String EDITOR = "editor";
    

    @Lob
    public String name;

    @OneToMany(mappedBy = "role")
    public List<User> users;

	public static UserRole findByRole(String role) {
		return find("byName", role).first();
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRole other = (UserRole) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	@Override
	public int compareTo(UserRole o) {
		if (this.name.equals(o.name)){
			return 0;
		}
		if (this.name.equals(EDITOR)){
			return 1;
		}
		if (o.name.equals(EDITOR)){
			return -1;
		}
		if (this.name.equals(AUTHOR_REVIEWER)){
			return 1;
		}
		return -1;
	}
	
}
