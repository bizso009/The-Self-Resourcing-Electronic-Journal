package misc;

import java.sql.Connection;
import java.sql.SQLException;
import play.db.DB;
import models.*;

public class ComplexChecks
{
    public static boolean checkForPublication(Article a)
    {
        Connection c = DB.getConnection();
        try
        {
            c.prepareStatement("SELECT A.id FROM `Submission` as S, `Article` as A where S.id=A.submission_id");
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}
