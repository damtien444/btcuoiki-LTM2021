package model.DAO;

import java.sql.*;
import java.util.Date;

public class UploadDAO {

    String path = "C:\\\\LTM_webapps\\\\MyLoginDB SQlite.db";


    Connection c;
    UploadDAO() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        this.c = DriverManager.getConnection("jdbc:sqlite:"+path);
        this.c.createStatement().execute("PRAGMA foreign_keys=ON");
    }

    public boolean addSession(String userID, String video_path) {
        try {
            String sql = "INSERT INTO HISTORY (ma_session, user_id, link_to_file, status, result, is_running, last_update)"
                    + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement prep = this.c.prepareStatement(sql);
            prep.setString(2, userID);
            prep.setString(3, video_path);
            prep.setInt(4, 0);
            prep.setNull(5, Types.NULL);
            prep.setBoolean(6, true);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            prep.setTimestamp(7, timestamp);
            prep.executeUpdate();
            prep.close();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UploadDAO dao = new UploadDAO();
        dao.addSession("abc", "C://video.mp4");
    }
}
