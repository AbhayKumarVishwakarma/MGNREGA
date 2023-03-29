package com.dao;

import com.dto.Worker;
import com.exception.ProjectException;
import com.exception.WorkerException;

public class GPMimp implements GPMintr {

	@Override
	public boolean login(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String createWorker(Worker worker) throws WorkerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Worker searchWorkerByAadhar(String aadhar) throws WorkerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String assignProToWorker(int proID, int wID) throws ProjectException, WorkerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showWorkerAndWorkingDay() throws WorkerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ShowWorkerAndWages() throws WorkerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String deleteWorker(int wID) throws WorkerException {
		// TODO Auto-generated method stub
		return null;
	}

}
