package controllers;

import java.sql.*;
import java.util.*;
import play.db.DB;
import play.mvc.*;

public class Chat extends Controller
{
    public static class ChatMessage
    {
        public String id;
        public String name;
        public String message;
        public String timestamp;
        
        public ChatMessage(String id, String name, String message, String timestamp)
        {
            this.id = id;
            this.name = name;
            this.message = message;
            this.timestamp = timestamp;
        }
    }
    
    public static void contents(Integer convID)
    {
        Connection c = DB.getConnection();
        try
        {
            PreparedStatement stmt = c.prepareStatement("select C.ID, U.FirstName, M.Contents, M.TimeSent from `Users` as U, `Conversations` as C, `Messages` as M "+
            "where C.ID=M.ConversationID and U.ID=M.UserID and C.ID = ?");
            stmt.setInt(1, convID);            
            ResultSet rs = stmt.executeQuery();
            rs.beforeFirst();
            ArrayList<ChatMessage> msgdata = new ArrayList<ChatMessage>();
            while (rs.next())
            {
                ChatMessage msg = new ChatMessage(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                msgdata.add(msg);
            }
            render(msgdata);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }        
    }

    public static void view()
    {
        render();
    }
    
    public static void post(String convID, String fromMsgID, String msg)
    {
        if (!Security.isConnected()) return;
        Connection c = DB.getConnection();
        try
        {
            PreparedStatement stmt;
            stmt = c.prepareStatement("SELECT ID FROM `Conversations` WHERE ID=?");
            stmt.setInt(1, Integer.parseInt(convID));
            ResultSet rs = stmt.executeQuery();
            rs.beforeFirst();
            if (!rs.next())
            {
                stmt.close();
                stmt = c.prepareStatement("INSERT INTO `Conversations`(`ID`) "+
                        "values (?)");
                stmt.setInt(1, Integer.parseInt(convID));
                stmt.execute();                
            }
            stmt.close();
            stmt = c.prepareStatement("INSERT INTO `Messages`(`ConversationID`,`Contents`,`UserID`,`TimeSent`) "+
                    "values (?,?,?,CURRENT_TIMESTAMP)");
            stmt.setInt(1, Integer.parseInt(convID));
            stmt.setString(2, msg);
            stmt.setInt(3, Integer.parseInt(Security.connected()));
            stmt.execute();
            stmt.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
