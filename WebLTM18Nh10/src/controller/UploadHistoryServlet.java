package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BO.RetrieveUploadAttemptBO;
import model.Bean.Account;
import model.Bean.PredictResult;
import model.Bean.Session;
import util.Util;


@WebServlet("/UploadHistoryServlet")
public class UploadHistoryServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Account curr_account;
	private ArrayList<PredictResult> resultList;
       
    public UploadHistoryServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		curr_account = (Account) request.getSession().getAttribute("account");
		updatePredictResult();
		
		request.getSession().setAttribute("resultList", resultList);
		response.sendRedirect("ViewUploaded.jsp");
	}

	private void updatePredictResult() {
		ArrayList<Session> all_saved_updload_attempt = RetrieveUploadAttemptBO.getAllSessionFromAccount(curr_account);
		resultList = Util.getResultList(all_saved_updload_attempt);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
