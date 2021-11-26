package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;

import model.BO.ConnectModelServerBO;
import model.BO.UploadSessionSaverBO;
import model.Bean.Account;
import model.Bean.PredictResult;
import model.Bean.Session;
import model.BO.FileUploadBO;
import model.BO.RetrieveUploadAttemptBO;
import util.Util;

@WebServlet("/FileUploadServlet")

public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String whereToSaveOnDisk;
	private Account curr_account;
	private ArrayList<String> newlySavedFiles;

	public FileUploadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("FileUploadServlet.doGet()");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		whereToSaveOnDisk = getServletContext().getInitParameter("file-upload");
		
		try {
			uploadVideosAndPredictModel(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		redirectToUploadHistoryPage(request, response);
	}


	private void uploadVideosAndPredictModel(HttpServletRequest request) throws FileUploadException {

		curr_account = (Account) request.getSession().getAttribute("account");
		newlySavedFiles = saveFilesToDisk(request); 
		
		if (hasUploadedSomething()) {
			recordUploadedAttemptToDB(curr_account);
			runPythonModel(curr_account);
		} else {
			System.out.println("No files were uploaded");
		}
		
	}


	private ArrayList<String> saveFilesToDisk(HttpServletRequest request) throws FileUploadException {
		System.out.println("Saving uploaded files  to " + whereToSaveOnDisk);
		return FileUploadBO.saveFile(request, whereToSaveOnDisk);
	}
	
	private boolean hasUploadedSomething() {
		return newlySavedFiles != null && newlySavedFiles.size() > 0;
	}

	private void recordUploadedAttemptToDB(Account curr_account) {
		UploadSessionSaverBO.saveNewUploadAttempt(curr_account, newlySavedFiles);		
	}
	
	private void runPythonModel(Account curr_account) {
		System.out.println("Running python model for uploaded files");
		
		ArrayList<Session> recent_sessions = UploadSessionSaverBO.getRecentUploadSessions();
		
		ConnectModelServerBO connect_server = new ConnectModelServerBO(9900);
		connect_server.connectToServer();
		for (Session s : recent_sessions) {
			connect_server.sendMessageToServer(String.valueOf(s.getMa_session()));
		}
	}
	
	private void redirectToUploadHistoryPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList<Session> all_saved_updload_attempt = RetrieveUploadAttemptBO.getAllSessionFromAccount(curr_account);
		ArrayList<PredictResult> resultList = Util.getResultList(all_saved_updload_attempt);
		request.getSession().setAttribute("resultList", resultList);
		response.sendRedirect("ViewUploaded.jsp");
		
	}
}
