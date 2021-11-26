package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;

import model.BO.FileSaverBO;
import model.BO.FileUploadBO;
import model.BO.RetrieveUploadAttemptBO;
import model.Bean.Account;
import model.Bean.PredictResult;
import model.Bean.Session;
import util.Util;

@WebServlet("/FileUploadServlet")

public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FileUploadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("FileUploadServlet.doGet()");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDir = getServletContext().getInitParameter("file-upload");
		Account curr_account = (Account) request.getSession().getAttribute("account");
    	try {
    		ArrayList<String> newlySavedFile = FileUploadBO.saveFile(request, saveDir);
    		if(newlySavedFile != null  && newlySavedFile.size() > 0) {	 
    			System.out.println("Upload completed");
	    		if(curr_account != null) {
	    			System.out.println("Saving upload history to account: " + curr_account.getId());
					FileSaverBO.saveNewUploadAttempt(curr_account, newlySavedFile);		
	    			System.out.println("Saved...");
	    		}
    		}
    		else {
    			System.out.println("No any files were uploaded");
    		}
    			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}    	 

		System.out.println("Returning to Home page...");
		ArrayList<Session> all_saved_updload_attempt = RetrieveUploadAttemptBO.getAllSessionFromAccount(curr_account);
		
		ArrayList<PredictResult> resultList = Util.getResultList(all_saved_updload_attempt);
		System.out.println("Reset Resultlist : " + resultList);
		request.getSession().setAttribute("resultList", resultList);
		response.sendRedirect("ViewUploaded.jsp");
	}
}
