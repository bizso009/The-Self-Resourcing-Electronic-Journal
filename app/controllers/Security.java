package controllers;

<<<<<<< HEAD
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

=======
import java.sql.*;
import models.User;
>>>>>>> 3f61d287d86cb816570ef17cb576a03d003664c6
import play.db.DB;

public class Security extends Secure.Security
{
    static boolean authenticate(String username, String password)
    {
        Connection c = DB.getConnection();
        try
        {
            PreparedStatement stmt = c.prepareStatement("select ID from `Users` where id = ? and PasswordHash=MD5(?)");
            stmt.setInt(1, Integer.parseInt(username));
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            rs.beforeFirst();
            while (rs.next())
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static boolean anon()
    {
        return !isConnected();
    }
    
    public static User loggedUser()
    {
        if (isConnected())
        {
            return User.findById(Long.parseLong(connected()));
        }
        else
        {
            return null;
        }
    }
    
    static boolean check(String profile)
    {
        if ((profile!=null))
        {
            Connection c = DB.getConnection();
            try
            {
                PreparedStatement stmt = c.prepareStatement("select U.id from `Users` as U, `UserRoles` as UR where U.id = ? and U.role_id = UR.id and UR.name = ?");
                stmt.setInt(1, Integer.parseInt(connected()));
                stmt.setString(2, profile);
                ResultSet rs = stmt.executeQuery();
                rs.beforeFirst();
                while (rs.next())
                {
                    return true;
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
