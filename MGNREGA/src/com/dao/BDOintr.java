package com.dao;

import java.util.List;

import com.dto.GPM;
import com.dto.Project;
import com.dto.Worker;
import com.exception.GPMException;
import com.exception.NoRecordFound;
import com.exception.ProjectException;
import com.exception.WorkerException;

public interface BDOintr {

	// Login with login credentials.
	public boolean login(String username, String password);

	// Create a project with details projectName, startDate, endDate, noOfWorkers, per_day_wages
	public String createProject(Project pro) throws ProjectException;

	// View List of Projects
	public List<Project> showAllProject() throws ProjectException;

	// Add gram panchayat member with details aadhaar_number, name, dob, gender, panchayat name, district, state
	public String createGPM(GPM gpm) throws GPMException;

	// View all the GPMs
	public List<GPM> showAllGPM() throws GPMException, NoRecordFound;

	// Allocate a project to a GPM.
	public String assignProToGPM(int proID, int gpmID) throws ProjectException, GPMException;

	// See the details of all workers.
	public List<Worker> showAllWorker() throws WorkerException, NoRecordFound;

	// Logout
    
}
