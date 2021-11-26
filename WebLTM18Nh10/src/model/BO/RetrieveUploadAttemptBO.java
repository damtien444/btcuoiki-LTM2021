package model.BO;

import java.util.ArrayList;

import model.Bean.Account;
import model.Bean.Session;
import model.DAO.HistoryDAO;

public class RetrieveUploadAttemptBO {

	public static ArrayList<Session> getAllSessionFromAccount(Account curr_account) {
		return HistoryDAO.getInstance().getAllSession(curr_account.getId());
	}

}
