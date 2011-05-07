package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import models.Conversation;
import models.Message;
import models.User;
import play.db.DB;
import play.mvc.Controller;
import play.mvc.Http;

public class Chat extends Controller
{
    public static class ChatMessage
    {
        public Integer id;
        public String  name;
        public String  role;
        public String  message;
        public String  timestamp;

        public ChatMessage(Integer id, String role, String name, String message, String timestamp)
        {
            this.id = id;
            this.role = role;
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
                    .prepareStatement("select M.id, UR.name, U.id, M.contents, M.timeSent from `Person` as U, `Conversations` as C, `Messages` as M, `UserRoles` as UR "
                            + "where UR.id = U.role_id and C.id=M.conversation_id and U.id=M.user_id and C.id = ? and M.id>? " + "ORDER BY M.id");
            stmt.setInt(1, convID);
            stmt.setInt(2, fromMsgID);
            ResultSet rs = stmt.executeQuery();
            rs.beforeFirst();
            ArrayList<ChatMessage> msgdata = new ArrayList<ChatMessage>();
            Integer maxID = fromMsgID;
            while (rs.next())
            {
                ChatMessage msg = new ChatMessage(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                if (msg.name.equals(Security.connected()))
                {
                    msg.name = "You";
                    msg.role = "";
                }
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

    public static void view(Integer convID, Boolean readOnly)
    {
        render(convID, readOnly);
    }

    public static void refreshScript(Integer convID, Boolean readOnly)
    {
        Http.Header h = new Http.Header();
        h.name = "Content-Type";
        h.values.add("text/javascript");
        Chat.response.headers.put("Content-Type", h);
        render(convID, readOnly);
    }

    public static void post(Long convID, String msg)
    {
        if ( !Security.isConnected())
            return;
        Conversation conv = Conversation.findById(convID);
        if (conv == null)
        {
            conv = new Conversation();
            conv.id = convID;
            conv.save();
        }
        Message mess = new Message();
        mess.conversation = conv;
        mess.user = Security.loggedUser();
        mess.contents = msg;
        mess.timeSent = new Date();
        mess.save();
    }
}
