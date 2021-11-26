package model.BO;

import java.util.ArrayList;

import model.DAO.HistoryDAO;
import model.Bean.Account;
import model.Bean.Session;

public class RetrieveUploadAttemptBO {

	public static ArrayList<Session> getAllSessionFromAccount(Account curr_account) {
		return HistoryDAO.getInstance().getSession(curr_account.getId());
	}

}
