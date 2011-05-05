package controllers;

import java.sql.*;
import play.db.DB;
import play.mvc.*;

public class Security extends Secure.Security
{
    static boolean authenticate(String username, String password)
    {
        Connection c = DB.getConnection();
        try
        {
            PreparedStatement stmt = c.prepareStatement("select ID from `Users` where ID = ? and PasswordHash=MD5(?)");
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

    static boolean check(String profile)
    {
        if ((profile!=null))
        {
            Connection c = DB.getConnection();
            try
            {
                PreparedStatement stmt = c.prepareStatement("select U.ID from `Users` as U, `UserRoles` as UR where U.ID = ? and U.UserRoleID = UR.ID and UR.Name = ?");
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
