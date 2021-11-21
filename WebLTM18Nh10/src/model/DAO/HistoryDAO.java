package model.DAO;

import model.bean.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDAO {
    // đổi path của database
    String path = "MyLoginDB SQLite.db";


    Connection c;
    HistoryDAO() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        this.c = DriverManager.getConnection("jdbc:sqlite:"+path);
        this.c.createStatement().execute("PRAGMA foreign_keys=ON");
    }

    public Session getLastSession(String user_id){
        try {
            String sql = "SELECT * FROM HISTORY WHERE HISTORY.user_id = ? order by ma_session desc";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setString(1, user_id);
            ResultSet rs = prep.executeQuery();

            Session session = extractSession(user_id, rs);;
            prep.close();
            return session;

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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        HistoryDAO dao = new HistoryDAO();

        // GetAll
        System.out.println("Get All Sessions");
        List<Session> sessions = dao.getAllSession("abc");
        for (Session s: sessions) {
            System.out.println(s.getLink_to_file());
        }

        // GetLast
        System.out.println("Get last Session");
        Session s= dao.getLastSession("abc");
        System.out.println(s.getLink_to_file());

    }

}
