package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import java.io.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.BO.FileSaverBO;
import model.BO.FileUploadBO;
import model.BO.ModelPredictorBO;

import javax.servlet.annotation.*;


@WebServlet("/FileUploadServlet" )

public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> fileNameList;

    public FileUploadServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FileUploadServlet.doGet()");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String saveDir = getServletContext().getInitParameter("file-upload");

		initFileNameList(request);
    	try {
    		ArrayList<String> newlySavedFile = FileUploadBO.saveFile(request, saveDir);
    		fileNameList.addAll(newlySavedFile);
			FileSaverBO.save(newlySavedFile);		
			
			request.getSession().setAttribute("fileNameList", fileNameList);
			ModelPredictorBO.getInstance().startToPredict(newlySavedFile.get(0), saveDir);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}    	 

		response.setIntHeader("Refresh",2);
		response.sendRedirect("MyHome.jsp");
	}
	

	private void initFileNameList(HttpServletRequest request) {
		fileNameList = (ArrayList<String>) request.getSession().getAttribute("fileNameList");
		if (fileNameList == null){
			fileNameList = new ArrayList<>();
		}		
	}

	


}
