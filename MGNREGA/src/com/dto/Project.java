package com.dto;

import java.time.LocalDate;

public class Project {
	private int proID;
    private String proName;
    private LocalDate proStrDate;
    private LocalDate proEndDate;
    private int gpmID;
    private int noOfWorker;
    private int wagesParDay;
    
    public Project() {}
    
	public Project(int proID, String proName, LocalDate proStrDate, LocalDate proEndDate, int gpmID,
			int noOfWorker, int wagesParDay) {
		super();
		this.proID = proID;
		this.proName = proName;
		this.proStrDate = proStrDate;
		this.proEndDate = proEndDate;
		this.gpmID = gpmID;
		this.noOfWorker = noOfWorker;
		this.wagesParDay = wagesParDay;
	}
    
	// proName, proStrDate, proEndDate, wagesParDay
	public Project(String proName, LocalDate proStrDate, LocalDate proEndDate, int wagesParDay) {
		super();
		this.proName = proName;
		this.proStrDate = proStrDate;
		this.proEndDate = proEndDate;
		this.wagesParDay = wagesParDay;
	}

	public int getProID() {
		return proID;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public LocalDate getProStrDate() {
		return proStrDate;
	}

	public void setProStrDate(LocalDate proStrDate) {
		this.proStrDate = proStrDate;
	}

	public LocalDate getProEndDate() {
		return proEndDate;
	}

	public void setProEndDate(LocalDate proEndDate) {
		this.proEndDate = proEndDate;
	}

	public int getGpmID() {
		return gpmID;
	}

	public void setGpmID(int gpmID) {
		this.gpmID = gpmID;
	}

	public int getNoOfWorker() {
		return noOfWorker;
	}

	public void setNoOfWorker(int noOfWorker) {
		this.noOfWorker = noOfWorker;
	}

	public int getWagesParDay() {
		return wagesParDay;
	}

	public void setWagesParDay(int wagesParDay) {
		this.wagesParDay = wagesParDay;
	}

	@Override
	public String toString() {
		return "ProjectException [proID=" + proID + ", proName=" + proName + ", proStrDate=" + proStrDate
				+ ", proEndDate=" + proEndDate + ", gpmID=" + gpmID + ", noOfWorker=" + noOfWorker + ", wagesParDay="
				+ wagesParDay + "]";
	}
}
