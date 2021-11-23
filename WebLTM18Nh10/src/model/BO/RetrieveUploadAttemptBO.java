package model.BO;

import java.util.ArrayList;

import model.DAO.HistoryDAO;
import model.bean.Account;
import model.bean.Session;

public class RetrieveUploadAttemptBO {

	public static ArrayList<Session> getAllSessionFromAccount(Account curr_account) {
		return HistoryDAO.getInstance().getAllSession(curr_account.getId());
	}

}
