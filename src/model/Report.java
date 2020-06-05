package model;

public class Report {
	private int reportID;
	private String vaccined;
	private String condition;
	private int animalID;
	private String reportDate;
	private String clientID;
	
	public Report() {
		super();
	}

	public Report(int reportID, String vaccined, String condition, int animalID, String reportDate,
			String clientID) {
		super();
		this.reportID = reportID;
		this.vaccined = vaccined;
		this.condition = condition;
		this.animalID = animalID;
		this.reportDate = reportDate;
		this.clientID = clientID;
	}

	public int getReportID() {
		return reportID;
	}

	public void setReportID(int reportID) {
		this.reportID = reportID;
	}

	public String getVaccined() {
		return vaccined;
	}

	public void setVaccined(String vaccined) {
		this.vaccined = vaccined;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getAnimalID() {
		return animalID;
	}

	public void setAnimalID(int animalID) {
		this.animalID = animalID;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

}
