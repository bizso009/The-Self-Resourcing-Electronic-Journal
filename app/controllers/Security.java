package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.*;
import models.User;
import models.UserRole;
import play.db.DB;

public class Security extends Secure.Security {
	static boolean authenticate(String username, String password) {
		return User.connect(username, password) != null;
	}

	public static boolean anon() {
		return !isConnected();
	}

	public static User loggedUser() {
		if (isConnected()) {
			return User.find("byEmail",connected()).first();
		} else {
			return null;
		}
	}

	static boolean check(String profile) {
		User user = User.find("byEmail", connected()).first();
		if (user == null) {
			return false;
		}
		UserRole profileRole = UserRole.findByRole(profile);
		return profileRole.compareTo(user.role) <=0 ;
	}
	
	static boolean checkeq(String profile){
		User user = User.find("byEmail", connected()).first();
		if (user == null) {
			return false;
		}
		UserRole profileRole = UserRole.findByRole(profile);
		System.out.println(profileRole.name.equals(profile));
		return profileRole.equals(user.role);
	}
}
