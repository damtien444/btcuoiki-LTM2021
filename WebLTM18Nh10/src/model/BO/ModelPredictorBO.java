package model.BO;

import java.time.Instant;

public class ModelPredictorBO {

	private static ModelPredictorBO instance;
	private long current, atStart, pass;
	private boolean isRunning = false;
	private final long CAP = 21000;
	
	private ModelPredictorBO(){
		atStart = 0;
	}
	
	public static ModelPredictorBO getInstance() {
		if(instance == null) {
			instance = new ModelPredictorBO();
		}		
		return instance;
	}

	public void startToPredict(String fileName, String saveDir) {
		String fullFilePath = fileName + saveDir;
		
		predict(fullFilePath);
		
	}

	private void predict(String file) {
		isRunning   = true;
		atStart = Instant.now().toEpochMilli();
	}
	
	public String getCurrentProgress() {

		current = Instant.now().toEpochMilli();
		pass = current - atStart;
		if(isRunning && pass <= CAP) {
			return "running, progress: " + (float) pass / 1000 + " seconds ";
		}
		if(isRunning && pass > CAP) {
			return "Done, result = 69% " ;
		}
		return "Not started";
	}

}
