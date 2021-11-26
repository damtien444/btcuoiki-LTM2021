package model.BO;

import model.Bean.Account;
import model.DAO.CheckLoginDAO;

public class CheckLoginBO {

	public static Account checkAccount(String id, String pw) {
		return CheckLoginDAO.checkAccount(id, pw);
	}

}
