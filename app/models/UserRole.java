package models;

import play.*;
import play.db.jpa.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="UserRoles")
public class UserRole extends Model
{
    @Lob
    public String name;
}
