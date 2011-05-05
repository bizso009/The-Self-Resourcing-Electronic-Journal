package controllers;

import play.*;
import play.db.DB;
import play.mvc.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import models.*;

public class Application extends Controller
{
    @Before
    static void setConnectedUser()
    {
        if (Security.isConnected())
        {
            renderArgs.put("user", Security.connected());
        }
    }

    public static void index()
    {
        String myName = null;
        Connection c = DB.getConnection();
        try
        {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("select * from `Users` where ID = 3");
            // ResultSet rs = stmt.executeQuery("select 'Kostadin'");
            rs.first();
            myName = rs.getString("FirstName");
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        render(myName);
    }

}