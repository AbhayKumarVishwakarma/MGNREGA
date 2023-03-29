package com.dto;

import java.time.LocalDate;

public class GPM {
	 private int gpmID;
	 private String gpmName;
	 private String gpmAadhar;
	 private LocalDate gpmDob;
	 private String gpmGender;
	 private String gpmEmail;
	 private String gpmPassword;
	 private String gpName;
	 private String district;
	 private String state;
	 
	public GPM() {}
	 
	public GPM(int gpmID, String gpmName, String gpmAadhar, LocalDate gpmDob, String gpmGender, String gpmEmail,
			String gpmPassword, String gpName, String district, String state) {
		super();
		this.gpmID = gpmID;
		this.gpmName = gpmName;
		this.gpmAadhar = gpmAadhar;
		this.gpmDob = gpmDob;
		this.gpmGender = gpmGender;
		this.gpmEmail = gpmEmail;
		this.gpmPassword = gpmPassword;
		this.gpName = gpName;
		this.district = district;
		this.state = state;
	}
	
	 //gpmName, gpmAadhar, gpmDob, gpmGender, gpmEmail, gpmPassword, gpName, district, state
	public GPM(String gpmName, String gpmAadhar, LocalDate gpmDob, String gpmGender, String gpmEmail,
			String gpmPassword, String gpName, String district, String state) {
		super();
		this.gpmName = gpmName;
		this.gpmAadhar = gpmAadhar;
		this.gpmDob = gpmDob;
		this.gpmGender = gpmGender;
		this.gpmEmail = gpmEmail;
		this.gpmPassword = gpmPassword;
		this.gpName = gpName;
		this.district = district;
		this.state = state;
	}

	public int getGpmID() {
		return gpmID;
	}

	public String getGpmName() {
		return gpmName;
	}

	public void setGpmName(String gpmName) {
		this.gpmName = gpmName;
	}

	public String getGpmAadhar() {
		return gpmAadhar;
	}

	public void setGpmAadhar(String gpmAadhar) {
		this.gpmAadhar = gpmAadhar;
	}

	public LocalDate getGpmDob() {
		return gpmDob;
	}

	public void setGpmDob(LocalDate gpmDob) {
		this.gpmDob = gpmDob;
	}

	public String getGpmGender() {
		return gpmGender;
	}

	public void setGpmGender(String gpmGender) {
		this.gpmGender = gpmGender;
	}

	public String getGpmEmail() {
		return gpmEmail;
	}

	public void setGpmEmail(String gpmEmail) {
		this.gpmEmail = gpmEmail;
	}

	public String getGpmPassword() {
		return gpmPassword;
	}

	public void setGpmPassword(String gpmPassword) {
		this.gpmPassword = gpmPassword;
	}

	public String getGpName() {
		return gpName;
	}

	public void setGpName(String gpName) {
		this.gpName = gpName;
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
		return "GPM [gpmID=" + gpmID + ", gpmName=" + gpmName + ", gpmAadhar=" + gpmAadhar + ", gpmDob=" + gpmDob
				+ ", gpmGender=" + gpmGender + ", gpmEmail=" + gpmEmail + ", gpmPassword=" + gpmPassword + ", gpName="
				+ gpName + ", district=" + district + ", state=" + state + "]";
	}
}
