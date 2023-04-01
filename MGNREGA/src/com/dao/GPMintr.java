package com.dao;

import java.util.List;

import com.dto.Worker;
import com.exception.ProjectException;
import com.exception.WorkerException;

public interface GPMintr {

	// Login with login credentials.
	public boolean login(String email, String password);

	// Add worker with details aadhaar_number, name, dob, gender, panchayat name, district, state
	public String createWorker(Worker worker) throws WorkerException;
	
	// Show all worker of this Gram Panchayat
	public List<Worker> showAllWorkerOfGPM() throws WorkerException;

	// View the details of workers using aadhaar_number
	public Worker searchWorkerByAadhar(String aadhar) throws WorkerException;

	// Assign workers to Project from list of project assigned to this GMP.
	public String assignProToWorker(int proID, int wID) throws ProjectException, WorkerException;

	// View worker name & the total number of days a worker worked on a project
	public void showWorkerAndWorkingDay() throws WorkerException;

	// View worker name & total wages paid to him.
	public void ShowWorkerAndWages() throws WorkerException;

	// delete a worker
	public String deleteWorker(int wID) throws WorkerException;
	
	// no of worker in project
	public void countNoOfWorker();

	// Logout his account
}
