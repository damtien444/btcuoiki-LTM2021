package model.BO;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

import model.DAO.HistoryDAO;
import model.bean.Account;
import model.bean.Session;

public class FileSaverBO  {
	static Random r = new Random();
	
    public static void saveNewUploadAttempt(Account curr_account, ArrayList<String> fileNameList) {
    	for(String fileName : fileNameList) {
    		Session s = createSession(curr_account, fileName);
    		int numRowsInserted = HistoryDAO.getInstance().insertSession(s);
    		System.out.println("Inserted : " + numRowsInserted + " rows");
    		System.out.println("... session: " + s.getMa_session() + ", account: " + curr_account.getId());
    	}
    	
    }
    
    public static ArrayList<Session> getNewUploadAttempt(Account curr_account, ArrayList<String> fileNameList) {
    	ArrayList<Session> new_sessions = new ArrayList<Session>();
    	for(String fileName : fileNameList) {
    		Session s = createSession(curr_account, fileName);
    		new_sessions.add(s);
    		int numRowsInserted = HistoryDAO.getInstance().insertSession(s);
    		System.out.println("Inserted : " + numRowsInserted + " rows");
    		System.out.println("... session: " + s.getMa_session() + ", account: " + curr_account.getId());
    	}
    	return new_sessions;
    }
    

	private static Session createSession(Account curr_account, String fileName) {
		Session s = new Session();
		s.setIs_running(false);
		s.setLink_to_file(fileName);
		s.setMa_session(generateNewSessionId());
		s.setResult("None");
		s.setStatus("0%");
		s.setTimestamp(Timestamp.from(Instant.now()));
		s.setUser_id(curr_account.getId());
		return s;
	}

	private static int generateNewSessionId() {
		int result = r.nextInt(100) + (int) (Instant.now().getEpochSecond() /1000);
		System.out.println("NewSessionId" + result );
		return result;
	}


}
