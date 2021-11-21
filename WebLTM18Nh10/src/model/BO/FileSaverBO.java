package model.BO;

import java.util.ArrayList;

public class FileSaverBO  {
	
    public static void save(ArrayList<String> fileNameList) {
    	for(String fileName : fileNameList) {
    		System.out.println("Saved " + fileName);
    	}
    	
    }


}
