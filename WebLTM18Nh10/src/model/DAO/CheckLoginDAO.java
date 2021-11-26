package model.DAO;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.sqlite.SQLiteDataSource;

import model.Bean.Account;

public class CheckLoginDAO {
	public static final String sqliteURL = "C:\\LTM_webapps\\MyLoginDB SQlite.db";
	
	
	public static void main(String[] args) {
		checkAccount("abc", "123");
	}
	
	public static Account checkAccount(String username, String pw) {
		
//		Account pseudo_account = new Account();
//		pseudo_account.setId( "sadakdfmakfkaf");
//		pseudo_account.setName("pseudo-name");
//		pseudo_account.setPw("6996");
//		pseudo_account.setStatus("Implement actual query to accountDB later...");
//		
//		return pseudo_account;
	    return checkAccountSqlite(username, pw);
		
	}
	
	private static Account checkAccountSqlite(String username, String pw) {
		//String path =  System.getProperty("user.dir");
		//System.out.println(path);
		
		
		SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:"   + sqliteURL);
        try (Connection conn = ds.getConnection()) {
            System.out.println("Connected");
            String sql = "SELECT * FROM Login";
            try (
                Statement s = conn.createStatement();
                ResultSet rs = s.executeQuery(sql)) {
            	
            	while(rs.next()) {
                    System.out.println(rs.getString(1) + "," + rs.getString(2));  
                    
                    if(rs.getString(1).equals(username) &&
                    		rs.getString(2).equals(pw)){
                    	
                        Account a = new Account();
                        
                        a.setId( rs.getString(1));
                		a.setPw(rs.getString(2));
                        System.out.println("found:  " + a.getId());
                        return a;
                    }
            	}
            	
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
	    return null;
	}
	

}
