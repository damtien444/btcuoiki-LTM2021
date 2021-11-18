package model.DAO;
import java.io.File;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import org.sqlite.SQLiteDataSource;

import model.Bean.Account;

public class CheckLoginDAO {
	public static final String sqliteURL = "E:\\Download\\googleDownload\\apache-tomcat-8.5.72\\dbs\\account.db";
	
	
	public static void main(String[] args) {
		checkAccount("pmtuan", "123456");
	}
	
	public static Account checkAccount(String username, String pw) {
		
		Account pseudo_account = new Account();
		pseudo_account.setId( "sadakdfmakfkaf");
		pseudo_account.setName("pseudo-name");
		pseudo_account.setPw("6996");
		pseudo_account.setStatus("Implement actual query to accountDB later...");
		
		return pseudo_account;
	    //return checkAccountSqlite(username, pw);
		
	}
	
	private static Account checkAccountSqlite(String username, String pw) {
		SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:" + sqliteURL);
        try (Connection conn = ds.getConnection()) {
            System.out.println("Connected.");
            String sql = 
                    "SELECT * FROM \"account\"";
            try (
                Statement s = conn.createStatement();
                ResultSet rs = s.executeQuery(sql)) {
                rs.next();
                System.out.println(rs.getString(1));
                
                if(rs.getString(2).equals(username)){
                	
                    Account a = new Account();
                    
                    a.setId( rs.getString(1));
            		a.setName(rs.getString(2));
            		a.setPw(rs.getString(3));
            		a.setStatus(rs.getString(4));
                    System.out.println("found:  " + a.getName()
                    );
                    return a;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
	    return null;
	}
	

}
