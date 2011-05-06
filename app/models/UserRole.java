package models;

import play.*;
import play.db.jpa.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "UserRoles")
public class UserRole extends Model
{
    public static enum Role
    {
        READER, AUTHOR, EDITOR
    }

    @Lob
    public String name;

    public static UserRole findByRole(Role role)
    {
        return find("byName", role.toString().toLowerCase()).first();
    }
}
