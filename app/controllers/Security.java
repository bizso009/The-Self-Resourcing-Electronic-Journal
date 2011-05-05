package controllers;

import play.mvc.*;
import models.*;

public class Security extends Secure.Security
{
    static boolean authenticate(String username, String password)
    {
        return true;
    }
}
