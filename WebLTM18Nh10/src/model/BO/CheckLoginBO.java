package model.BO;

import model.DAO.CheckLoginDAO;
import model.bean.Account;

public class CheckLoginBO {

	public static Account checkAccount(String id, String pw) {
		return CheckLoginDAO.checkAccount(id, pw);
	}

}
