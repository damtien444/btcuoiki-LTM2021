package util;

import java.util.ArrayList;

import model.Bean.PredictResult;
import model.Bean.Session;

public class Util {

	public static ArrayList<PredictResult> getResultList(ArrayList<Session> all_saved_updload_attempt) {
		PredictResult pr;
		ArrayList<PredictResult> resultList = new ArrayList<>();
		String runningStatus;
		for(Session s : all_saved_updload_attempt) {
			runningStatus = (s.isIs_running()) ? "Yes" : "Done";
			
			pr = new PredictResult(s.getStatus(), 
					s.getResult(),
					runningStatus,
					s.getLink_to_file());
			
			resultList.add(pr);
		}
		return resultList;
	}
}
