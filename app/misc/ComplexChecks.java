package misc;

import java.sql.Connection;
import play.db.DB;
import models.*;

public class ComplexChecks
{
    public static boolean checkForPublication(Article a)
    {
        Connection c = DB.getConnection();
        c.prepareStatement("SELECT A.id FROM `Submission` as S, `Article` as A where S.id=A.submission_id")
        return false;
    }
}
