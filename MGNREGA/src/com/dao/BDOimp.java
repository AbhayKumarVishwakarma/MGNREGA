package com.dao;

import java.util.List;

import com.dto.GPM;
import com.dto.Project;
import com.dto.Worker;
import com.exception.GPMException;
import com.exception.ProjectException;
import com.exception.WorkerException;

public class BDOimp implements BDOintr {

	@Override
	public boolean login(String usename, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String createProject(Project pro) throws ProjectException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> showAllProject() throws ProjectException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createGPM(GPM gpm) throws GPMException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GPM> showAllGPM() throws GPMException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String assignProToGPM(int proID, int gpmID) throws ProjectException, GPMException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Worker> showAllWorker() throws WorkerException {
		// TODO Auto-generated method stub
		return null;
	}

}
