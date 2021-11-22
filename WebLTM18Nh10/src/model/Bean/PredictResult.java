package model.bean;

import java.io.Serializable;

public class PredictResult {
	protected String result;
	protected String fileName;
	
	public PredictResult(String fileName, String result) {
		this.fileName = fileName;
		this.result = result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getResult() {
		return result;
	}
	public String getFileName() {
		return fileName;
	}

	@Override
	public String toString() {
		return "PredictResult [result=" + result + ", fileName=" + fileName + "]";
	}
	
	
}
