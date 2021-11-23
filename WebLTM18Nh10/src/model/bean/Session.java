package model.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Session implements Serializable{

    int ma_session;
    String user_id;
    String link_to_file;
    String status;
    String result;
    boolean is_running;
    Timestamp timestamp;
    
    public Session() {
    	
    }
    
    public Session(int ma_session, String user_id, String link_to_file, String status, String result, boolean is_running, Timestamp timestamp) {
        this.ma_session = ma_session;
        this.user_id = user_id;
        this.link_to_file = link_to_file;
        this.status = status;
        this.result = result;
        this.is_running = is_running;
        this.timestamp = timestamp;
    }

    public int getMa_session() {
        return ma_session;
    }

    public void setMa_session(int ma_session) {
        this.ma_session = ma_session;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLink_to_file() {
        return link_to_file;
    }

    public void setLink_to_file(String link_to_file) {
        this.link_to_file = link_to_file;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isIs_running() {
        return is_running;
    }

    public void setIs_running(boolean is_running) {
        this.is_running = is_running;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
