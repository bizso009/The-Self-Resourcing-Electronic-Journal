package controllers;

import java.sql.*;
import java.util.*;
import play.db.DB;
import play.mvc.*;

public class Chat extends Controller
{
    public static class ChatMessage
    {
        public Integer id;
        public String  name;
        public String  message;
        public String  timestamp;

        public ChatMessage(Integer id, String name, String message, String timestamp)
        {
            this.id = id;
            this.name = name;
            this.message = message;
            this.timestamp = timestamp;
        }
    }

    public static void contents(Integer convID, Integer fromMsgID)
    {
        if (fromMsgID == null)
        {
            fromMsgID = 0;
        }
        Connection c = DB.getConnection();
        try
        {
            PreparedStatement stmt = c
                    .prepareStatement("select M.ID, U.FirstName, M.Contents, M.TimeSent from `Users` as U, `Conversations` as C, `Messages` as M "
                            + "where C.ID=M.ConversationID and U.ID=M.UserID and C.ID = ? and M.ID>? " + "ORDER BY C.ID");
            stmt.setInt(1, convID);
            stmt.setInt(2, fromMsgID);
            ResultSet rs = stmt.executeQuery();
            rs.beforeFirst();
            ArrayList<ChatMessage> msgdata = new ArrayList<ChatMessage>();
            Integer maxID = fromMsgID;
            while (rs.next())
            {
                ChatMessage msg = new ChatMessage(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                maxID = Math.max(maxID, msg.id);
                msgdata.add(msg);
            }
            render(msgdata, maxID);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void view(Integer convID)
    {
        render(convID);
    }
    
    public static void refreshScript(Integer convID)
    {
        Http.Header h = new Http.Header();
        h.name = "Content-Type";
        h.values.add("text/javascript");
        Chat.response.headers.put("Content-Type", h);
        render(convID);
    }

    public static void post(String convID, String msg)
    {
        if ( !Security.isConnected())
            return;
        Connection c = DB.getConnection();
        try
        {
            PreparedStatement stmt;
            stmt = c.prepareStatement("SELECT ID FROM `Conversations` WHERE ID=?");
            stmt.setInt(1, Integer.parseInt(convID));
            ResultSet rs = stmt.executeQuery();
            rs.beforeFirst();
            if ( !rs.next())
            {
                stmt.close();
                stmt = c.prepareStatement("INSERT INTO `Conversations`(`ID`) " + "values (?)");
                stmt.setInt(1, Integer.parseInt(convID));
                stmt.execute();
            }
            stmt.close();
            stmt = c.prepareStatement("INSERT INTO `Messages`(`ConversationID`,`Contents`,`UserID`,`TimeSent`) " + "values (?,?,?,CURRENT_TIMESTAMP)");
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
