package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Utils.DBUtils;
import com.dto.GPM;
import com.dto.Project;
import com.dto.Worker;
import com.exception.GPMException;
import com.exception.NoRecordFound;
import com.exception.ProjectException;
import com.exception.WorkerException;

public class BDOimp implements BDOintr {

	@Override
	public boolean login(String username, String password) {
		return username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin");
	}

	@Override
	public String createProject(Project pro) throws ProjectException {
		String msg = "\nUnable to create project\n";
		Connection con = null;
		try {
			con = DBUtils.getConnectionToDatabase();
			PreparedStatement ps = con.prepareStatement("insert into project (proName, proStrDate, proEndDate, wagesParDay) values (?, ?, ?, ?)");
			
			ps.setString(1, pro.getProName());
			ps.setDate(2, Date.valueOf(pro.getProStrDate()));
			ps.setDate(3, Date.valueOf(pro.getProEndDate()));
			ps.setInt(4, pro.getWagesParDay());
			
			if(ps.executeUpdate()>0) {
				msg = "Project created successfully";
			}
			else {
				throw new ProjectException("\nError while creating a new project. Try again\n");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new ProjectException("\nSomething went wrong.\n");
			
		} finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return msg;
	}

	@Override
	public List<Project> showAllProject() throws ProjectException {
		List<Project> list = new ArrayList<>();
		Connection con = null;
		try {
			con = DBUtils.getConnectionToDatabase();
			PreparedStatement ps = con.prepareStatement("select * from project where is_delete = false");
			ResultSet rs = ps.executeQuery();
			
            boolean flag = false;
			while (rs.next()) {
				flag = true;
				list.add(new Project(rs.getInt(1), rs.getString(2), rs.getDate(3).toLocalDate(), rs.getDate(4).toLocalDate(), rs.getInt(5), rs.getInt(6), rs.getInt(7)));
			}
			
			if (flag == false) {
				throw new ProjectException("\nNo Project available\n");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new ProjectException("\nSomething went wrong!\n");
		} finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public String createGPM(GPM gpm) throws GPMException {
		String msg = "\nUnable to create GPM\n";
		Connection con = null;
		try {
			con = DBUtils.getConnectionToDatabase();
			PreparedStatement ps = con.prepareStatement("insert into gpm (gpmName, gpmAadhar, gpmDob, gpmGender, gpmEmail, gpmPassword, gpName, district, state) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			ps.setString(1, gpm.getGpmName());
			ps.setString(2, gpm.getGpmAadhar());
			ps.setDate(3, Date.valueOf(gpm.getGpmDob()));
			ps.setString(4, gpm.getGpmGender());
			ps.setString(5, gpm.getGpmEmail());
			ps.setString(6, gpm.getGpmPassword());
			ps.setString(7, gpm.getGpName());
			ps.setString(8, gpm.getDistrict());
			ps.setString(9, gpm.getState());
			
			if(ps.executeUpdate()>0) {
				msg = "\nGPM created successfully\n";
			}
			else {
				throw new GPMException("\nError while creating a new GPM. Try again\n");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new GPMException("\nSomething went wrong!\n");
		} finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return msg;
	}

	@Override
	public List<GPM> showAllGPM() throws GPMException, NoRecordFound {
		List<GPM> list = new ArrayList<>();
		Connection con = null;
		try {
			con = DBUtils.getConnectionToDatabase();
			PreparedStatement ps = con.prepareStatement("select * from gpm where is_delete = false");
			ResultSet rs = ps.executeQuery();
			
            boolean flag = false;
			while (rs.next()) {
				flag = true;
                list.add(new GPM(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
			}
			
			if (flag == false) {
				throw new GPMException("\nNo GPM available\n");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new GPMException("\nSomething went wrong!\n");
		} finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public String assignProToGPM(int proID, int gpmID) throws ProjectException, GPMException {
		String msg = "\nUnable to assign project\n";
		Connection con = null;
		try {
			con = DBUtils.getConnectionToDatabase();
			
			// Checking project
			PreparedStatement ps = con.prepareStatement("select * from project where proID = ? and is_delete = false");
			ps.setInt(1, proID);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				// Checking GPM
				PreparedStatement ps1 = con.prepareStatement("select * from GPM where gpmID = ? and is_delete = false");
				ps1.setInt(1, gpmID);
				ResultSet rs1 = ps1.executeQuery();
				
				if (rs1.next()) {
					// Assigning project to GPM
					PreparedStatement ps2 = con.prepareStatement("update project set gpmID = ? where proID = ?");
					ps2.setInt(1, gpmID);
					ps2.setInt(2, proID);
					
					if(ps2.executeUpdate()>0) {
						msg = "\nProject ID " + proID + " assigned to Gram Panchayat ID " + gpmID +"\n";
					}
				}
				else {
					throw new GPMException("\nGPM Id " + gpmID + " doesn't exist!\n");
				}
			}
			else {
				throw new ProjectException("\nProject Id " + proID + " doesn't exist!\n");
			}
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("\nSomething went wrong!\n");
		} finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return msg;
	}

	@Override
	public List<Worker> showAllWorker() throws WorkerException, NoRecordFound {
		List<Worker> list = new ArrayList<>();
		Connection con = null;
		try {
			con = DBUtils.getConnectionToDatabase();
			PreparedStatement ps = con.prepareStatement("select * from workers where is_delete = false");
			ResultSet rs = ps.executeQuery();
			
            boolean flag = false;
			while (rs.next()) {
				flag = true;
				list.add(new Worker(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getDate(9).toLocalDate(), rs.getString(10), rs.getString(11)));
			}
			
			if (flag == false) {
				throw new WorkerException("\nNo Worker available\n");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new WorkerException("\nSomething went wrong!\n");
		} finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
