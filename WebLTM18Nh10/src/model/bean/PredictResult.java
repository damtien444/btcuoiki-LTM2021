package model.bean;


public class PredictResult {
	String result, progress;
	String runningStatus;
	String fileName;
	
	public PredictResult(String result, String progress, String runningStatus, String fileName) {
		super();
		this.result = result;
		this.progress = progress;
		this.runningStatus = runningStatus;
		this.fileName = fileName;
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getRunningStatus() {
		return runningStatus;
	}
	public void setRunningStatus(String runningStatus) {
		this.runningStatus = runningStatus;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
