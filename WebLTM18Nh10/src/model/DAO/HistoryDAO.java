package model.DAO;

import model.Bean.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDAO {
	
    private static HistoryDAO instance;
    String path = "MyLoginDB SQLite.db";
    Connection c;
    private static final String INSERT_SQL = "INSERT INTO HISTORY VALUES(?, ?, ?, ?, ?, ?, ?)";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        HistoryDAO dao = new HistoryDAO();

        // GetAll
        System.out.println("Get All Sessions");
        List<Session> sessions = dao.getAllSession("abc");
        for (Session s: sessions) {
            System.out.println(s.getLink_to_file());
        }

        // GetLast
        System.out.println("Get Session");
        ArrayList<Session> s= dao.getSession("abc");
        System.out.println(s.get(0).getLink_to_file());

    }
    
    public static HistoryDAO getInstance() {
    	if(instance == null) {
    		try {
				instance = new HistoryDAO();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	return instance;
    }
    
    private HistoryDAO() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        this.c = DriverManager.getConnection("jdbc:sqlite:"+path);
        this.c.createStatement().execute("PRAGMA foreign_keys=ON");
    }
    
    
    public int insertSession(Session s) {
    	 int numRowsInserted = 0;
         PreparedStatement ps = null;
         try {
             ps = c.prepareStatement(INSERT_SQL);
             ps.setInt(1, s.getMa_session());
             ps.setString(2, s.getUser_id());
             ps.setString(3, s.getLink_to_file());
             ps.setInt(4,  s.getStatus());
             ps.setString(5, s.getResult());  
             ps.setBoolean(6, s.isIs_running());
             ps.setTimestamp(7, s.getTimestamp());
             numRowsInserted = ps.executeUpdate();
         } catch (SQLException e) {
             e.printStackTrace();
         } finally {
             close(ps);
         }
         return numRowsInserted;
    }
    
    public ArrayList<Session> getSession(String user_id){
        try {
            String sql = "SELECT * FROM HISTORY WHERE HISTORY.user_id = ? order by ma_session desc";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setString(1, user_id);
            ResultSet rs = prep.executeQuery();
            
            ArrayList<Session> foundSession = new ArrayList<>();
            while(rs.next()) {
                Session s = extractSession(user_id, rs);
                foundSession.add(s);
            }
            prep.close();
            return foundSession;

        } catch (Exception e){
            return null;
        }
    }

    private Session extractSession(String user_id, ResultSet rs) throws SQLException {
        int ma_session = rs.getInt("ma_session");
        String link_to_file = rs.getString("link_to_file");
        int status =rs.getInt("status");
        String result = rs.getString("result");
        Boolean is_running = rs.getBoolean("is_running");
        Timestamp last_update = rs.getTimestamp("last_update");
        return new Session(ma_session, user_id, link_to_file, status, result, is_running, last_update);
    }

    public List<Session> getAllSession(String user_id){

        List<Session> res = new ArrayList<>();
        try {
            String sql = "SELECT * FROM HISTORY WHERE HISTORY.user_id = ?";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setString(1, user_id);
            ResultSet rs = prep.executeQuery();

            while (rs.next()) {
                res.add(extractSession(user_id, rs));
            }

            prep.close();
            return res;
        } catch (Exception e){
            return null;
        }

    }

    
    public static void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
