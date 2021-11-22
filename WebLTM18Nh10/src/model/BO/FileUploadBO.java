package model.BO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadBO {

	public static final int NAX_MEM_SIZE = 1024 * 1024 * 100;
	public static final long MAX_FILE_SIZE = 1024 * 1024 * 100;
	
	private static File tempFile;
	private static DiskFileItemFactory itemFactory;
	private static ServletFileUpload uploader;
	
    static {    	
    	itemFactory = new DiskFileItemFactory();
    	itemFactory.setSizeThreshold(NAX_MEM_SIZE);
    	itemFactory.setRepository(new File("c:\\temp"));

    	uploader = new ServletFileUpload(itemFactory);
		uploader.setSizeMax(MAX_FILE_SIZE);
    }  
    
	public static ArrayList<String> saveFile(HttpServletRequest request, String saveDir) throws FileUploadException {
		List<FileItem> fileItems = uploader.parseRequest(request);

		String saveFileDirectory = saveDir;
		ArrayList<String> fileNameList = new ArrayList<>();
		
	    for (FileItem fi : fileItems) {
	    	if (!fi.isFormField()) {
				String fileName = getFileName(fi);
				if(fileName != null && !fileName.isEmpty()) {
					tempFile = new File(saveFileDirectory + fileName);
					writeFile(fi);
					fileNameList.add(fileName);
					
					System.out.println("Uploaded Filename: " + fileName + "to: " + saveFileDirectory  );		
				}
			}
	    }
	    return fileNameList;
	}


	private static String getFileName(FileItem fi) {
		String fileName = fi.getName();
		if (fileName.contains("\\")) {
			fileName = fileName.substring(fileName.lastIndexOf("\\"));
		} 
		return fileName;
	}


	private static void writeFile(FileItem fi) {
		try {
			fi.write(tempFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
