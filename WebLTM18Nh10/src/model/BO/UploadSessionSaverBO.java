package model.BO;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

import model.DAO.HistoryDAO;
import model.bean.Account;
import model.bean.Session;

public class UploadSessionSaverBO  {
	
	static ArrayList<Session> recent_sessions = new ArrayList<Session>();
	
    public static void saveNewUploadAttempt(Account curr_account, ArrayList<String> fileNameList) {
    	recent_sessions.clear();
    	
    	for(String fileName : fileNameList) {
    		Session session = createNewSessionFrom(curr_account, fileName);
    		insertToDatabase(session);
    		recent_sessions.add(session);
    	}
    	
		System.out.println("Saved upload history from account: " + curr_account.getId());
    	
    }

	private static void insertToDatabase(Session session) {
		HistoryDAO.getInstance().insertSession(session);
	}

	public static ArrayList<Session> getRecentUploadSessions() {
		return recent_sessions;
	}   

	private static Session createNewSessionFrom(Account curr_account, String fileName) {
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
		int result = (int) (Instant.now().getEpochSecond());
		
		String resultAsString = "" + result;
		resultAsString = resultAsString.substring(3);
		result = Integer.parseInt(resultAsString);
		
		System.out.println("generated new Session id: " + result );
		return result;
	}



}
