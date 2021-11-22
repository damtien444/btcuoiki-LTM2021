package util;

import java.util.ArrayList;

import model.bean.PredictResult;
import model.bean.Session;

public class Util {

	public static ArrayList<PredictResult> getResultList(ArrayList<Session> all_saved_updload_attempt) {
		PredictResult pr;
		ArrayList<PredictResult> resultList = new ArrayList<>();
		
		for(Session s : all_saved_updload_attempt) {
			pr = new PredictResult(s.getLink_to_file(), s.getResult());
			System.out.println(pr);
			resultList.add(pr);
		}
		return resultList;
	}
}
