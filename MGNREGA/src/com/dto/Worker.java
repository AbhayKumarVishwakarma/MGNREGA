package com.dto;

import java.time.LocalDate;

public class Worker {
    private int wID;
    private String wName;
    private String wAadhar;
    private LocalDate wDob;
    private String wGender;
    private String gpName;
    private int gpmID;
    private int proID;
    private LocalDate workStrDate;
    private String district;
    private String state;
    
    public Worker() {}

	public Worker(int wID, String wName, String wAadhar, LocalDate wDob, String wGender, String gpName, int gpmID,
			int proID, LocalDate workStrDate, String district, String state) {
		super();
		this.wID = wID;
		this.wName = wName;
		this.wAadhar = wAadhar;
		this.wDob = wDob;
		this.wGender = wGender;
		this.gpName = gpName;
		this.gpmID = gpmID;
		this.proID = proID;
		this.workStrDate = workStrDate;
		this.district = district;
		this.state = state;
	}
    
	// wName, wAadhar, wDob, wGender, gpName, gpmID, workStrDate, district, state
	public Worker(String wName, String wAadhar, LocalDate wDob, String wGender, String gpName, int gpmID,
			LocalDate workStrDate, String district, String state) {
		super();
		this.wName = wName;
		this.wAadhar = wAadhar;
		this.wDob = wDob;
		this.wGender = wGender;
		this.gpName = gpName;
		this.gpmID = gpmID;
		this.workStrDate = workStrDate;
		this.district = district;
		this.state = state;
	}

	public int getwID() {
		return wID;
	}

	public void setwID(int wID) {
		this.wID = wID;
	}

	public String getwName() {
		return wName;
	}

	public void setwName(String wName) {
		this.wName = wName;
	}

	public String getwAadhar() {
		return wAadhar;
	}

	public void setwAadhar(String wAadhar) {
		this.wAadhar = wAadhar;
	}

	public LocalDate getwDob() {
		return wDob;
	}

	public void setwDob(LocalDate wDob) {
		this.wDob = wDob;
	}

	public String getwGender() {
		return wGender;
	}

	public void setwGender(String wGender) {
		this.wGender = wGender;
	}

	public String getGpName() {
		return gpName;
	}

	public void setGpName(String gpName) {
		this.gpName = gpName;
	}

	public int getGpmID() {
		return gpmID;
	}

	public void setGpmID(int gpmID) {
		this.gpmID = gpmID;
	}

	public int getProID() {
		return proID;
	}

	public void setProID(int proID) {
		this.proID = proID;
	}

	public LocalDate getWorkStrDate() {
		return workStrDate;
	}

	public void setWorkStrDate(LocalDate workStrDate) {
		this.workStrDate = workStrDate;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Worker [wID=" + wID + ", wName=" + wName + ", wAadhar=" + wAadhar + ", wDob=" + wDob + ", wGender="
				+ wGender + ", gpName=" + gpName + ", gpmID=" + gpmID + ", proID=" + proID + ", workStrDate="
				+ workStrDate + ", district=" + district + ", state=" + state + "]";
	}
}
