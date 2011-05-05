package controllers;

import play.*;
import play.db.DB;
import play.mvc.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


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
        render();
    }

}