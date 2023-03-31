package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Utils.DBUtils;
import com.dto.Worker;
import com.exception.ProjectException;
import com.exception.WorkerException;

public class GPMimp implements GPMintr {
	public static int GPM_ID = 0;

	@Override
	public boolean login(String email, String password) {
        boolean flag = false;
		Connection con = null;
		try {
			con = DBUtils.getConnectionToDatabase();
			PreparedStatement ps = con.prepareStatement("select gpmID from GPM where gpmEmail = ? and gpmPassword = ? and is_delete = false");
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				flag = true;
				GPM_ID = rs.getInt(1);
			}
			else {
				System.out.println("\nWrong email or Password\n");
			}
			System.out.println(GPM_ID);
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public String createWorker(Worker worker) throws WorkerException {
		String msg = "\nUnable to create worker\n";
		Connection con = null;
		try {
			con = DBUtils.getConnectionToDatabase();
			
			PreparedStatement ps = con.prepareStatement("select gpName, district, state from GPM where gpmID = ? and is_delete = false");
			ps.setInt(1, GPM_ID);
			ResultSet rs = ps.executeQuery();
			String gpName = null;
			String district = null;
			String state = null;
			System.out.println("-----+");
			while(rs.next()) {
				gpName = rs.getString(1);
				district = rs.getString(2);
				state = rs.getString(3);
			}
			
			PreparedStatement ps1 = con.prepareStatement("insert into workers (wName, wAadhar, wDob, wGender, gpName, gpmID, district, state) values (?, ?, ?, ?, ?, ?, ?, ?)");
			ps1.setString(1, worker.getwName());
			ps1.setString(2, worker.getwAadhar());
			ps1.setDate(3, Date.valueOf(worker.getwDob()));
			ps1.setString(4, worker.getwGender());
			ps1.setString(5, gpName);
			ps1.setInt(6, GPM_ID);
			ps1.setString(7, district);
			ps1.setString(8, state);
			System.out.println("-----++");
			if(ps1.executeUpdate()>0) {
				msg = "\nWorker created successfully\n";
			}
			else {
				throw new WorkerException("\nError while creating new worker. Try again\n");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new WorkerException("\nSomething went wrong!");
//			throw new WorkerException(e.getMessage());
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
	public List<Worker> showAllWorkerOfGPM() throws WorkerException {
		List<Worker> list = new ArrayList<>();
		Connection con = null;
		try {
			con = DBUtils.getConnectionToDatabase();
			PreparedStatement ps = con.prepareStatement("select * from workers where gpmID = ? and is_delete = false");
			ps.setInt(1, GPM_ID);
			ResultSet rs = ps.executeQuery();
			
            boolean flag = false;
			while (rs.next()) {
				flag = true;
				list.add(new Worker(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getDate(9).toLocalDate(), rs.getString(10), rs.getString(11)));
			}
			
			if (flag == false) {
				throw new WorkerException("\nNo worker available in this GPM\n");
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

	@Override
	public Worker searchWorkerByAadhar(String aadhar) throws WorkerException {
		Worker w = null;
		Connection con = null;
		try {
			con = DBUtils.getConnectionToDatabase();
			PreparedStatement ps = con.prepareStatement("select * from workers where wAadhar = ? and is_delete = false");
			ps.setString(1, aadhar);
			
			ResultSet rs = ps.executeQuery();
			
            boolean flag = false;
			while(rs.next()) {
				flag = true;
				w = new Worker(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getDate(9).toLocalDate(), rs.getString(10), rs.getString(11));
			}
			
			if (flag == false) {
				throw new WorkerException("\nNo worker available with aadhar no " + aadhar +"\n");
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
		return w;
	}

	@Override
	public String assignProToWorker(int proID, int wID) throws ProjectException, WorkerException {
		String msg = "\nUnable to assign project\n";
		Connection con = null;
		try {
			con = DBUtils.getConnectionToDatabase();
			
			PreparedStatement ps = con.prepareStatement("select * from project where proID = ? and is_delete = false");
			ps.setInt(1, proID);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				PreparedStatement ps1 = con.prepareStatement("select * from workers where wID = ? and is_delete = false");
				ps1.setInt(1, wID);
				ResultSet rs1 = ps.executeQuery();
				
				if(rs1.next()) {
		    		PreparedStatement ps2 = con.prepareStatement("update workers set proID = ?, workStrDate = now() where wID = ?");
					ps2.setInt(1, proID);
					ps2.setInt(2, wID);
					
					if(ps2.executeUpdate()>0) {
						msg = "Project with ID: " + proID + " assigned to a worker with ID: " + wID;
					}
				}
				else {
					throw new WorkerException("\nWorker ID " + wID + " does't exist!\n");
				}
			}
			else {
				throw new ProjectException("\nProject ID " + proID + " doesn't exist!\n");
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
		return msg;
	}

	@Override
	public void showWorkerAndWorkingDay() throws WorkerException {
		Connection con = null;
		try {
			con = DBUtils.getConnectionToDatabase();
			PreparedStatement ps = con.prepareStatement("select wName, datediff(now(), workStrDate) from workers where gpmID = ? and is_delete = false");
			ps.setInt(1, GPM_ID);
			
            ResultSet rs = ps.executeQuery();
			
            boolean flag = false;
            System.out.println();
			while(rs.next()) {
				flag = true;
				System.out.println(rs.getString(1) + "\t\t" + rs.getInt(2));
			}
			System.out.println();
			
			if (flag == false) {
				throw new WorkerException("\nNo worker available\n");
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
	}

	@Override
	public void ShowWorkerAndWages() throws WorkerException {
		Connection con = null;
		try {
			con = DBUtils.getConnectionToDatabase();
			PreparedStatement ps = con.prepareStatement("select wagesParDay from project where gpmID = ? and is_delete = false");
			ps.setInt(1, GPM_ID);
			
            ResultSet rs = ps.executeQuery();
			int wagesPerDay = 0;
			while(rs.next()) {
				wagesPerDay = rs.getInt(1);
			}
			
			PreparedStatement ps1 = con.prepareStatement("select wName, (datediff(now(), workStrDate) * ?) from workers where gpmID = ? and is_delete = false");
			ps1.setInt(1, wagesPerDay);
			ps1.setInt(2, GPM_ID);
            ResultSet rs1 = ps1.executeQuery();
			
            boolean flag = false;
            System.out.println();
			while(rs1.next()) {
				flag = true;
				System.out.println(rs1.getString(1) + "\t\t" + rs1.getInt(2));
			}
			System.out.println();
			
			if (flag == false) {
				throw new WorkerException("No worker available");
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
	}

	@Override
	public String deleteWorker(int wID) throws WorkerException {
		String msg = "\nUnable to delete worker\n";
		Connection con = null;
		try {
			con = DBUtils.getConnectionToDatabase();
			PreparedStatement ps = con.prepareStatement("update workers set is_delete = true where wID = ? and is_delete = false");
			ps.setInt(1, wID);
			
			if(ps.executeUpdate()>0) {
				msg = "\nWorker ID no " + wID + " deleted\n";
			}
			else {
				throw new WorkerException("\nWorker ID " + wID + " does't exist!\n");
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
		return msg;
	}

	@Override
	public void countNoOfWorker(int gpmID) {
		// TODO Auto-generated method stub
		
	}
}
