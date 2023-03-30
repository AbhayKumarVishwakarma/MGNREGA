package com.ui;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.dao.BDOimp;
import com.dao.BDOintr;
import com.dto.GPM;
import com.dto.Project;
import com.dto.Worker;
import com.exception.GPMException;
import com.exception.NoRecordFound;
import com.exception.ProjectException;
import com.exception.WorkerException;

public class BDOmain {

	public static void Main(Scanner sc) {
		System.out.println("\n-=-=-=-=-= Welcome to BDO =-=-=-=-=-");
		int choice = 0;

        do {
        	System.out.println("\n1. Create Project \n2. View List Of Project \n3. Create GPM \n4. View all GPM\n5. Allocate Project to GPM \n6. Viwe all worker \n0. Log out");
            System.out.print("Enter your selection ");
            choice = sc.nextInt();
            
            switch(choice) {
                  case 1:
                	  creatProjectUI(sc);
                	  break;
                  case 2:
                	  showAllProjectUI();
                	  break;
                  case 3:
                	  createGPMUI(sc);
                	  break;
                  case 4:
                	  showAllGPMUI();
                	  break;
                  case 5:
                	  assignProToGPMUI(sc);
                	  break;
                  case 6:
                	  showAllWorkerUI();
                	  break;
                  case 0:
                	  System.out.println("\nLogged out!");
                	  break;
                  default :
                	  System.out.println("\nInvalid selection, please enter valid selection\n");
		    	
		    }
            
        }while(choice != 0);
	}

	private static void creatProjectUI(Scanner sc) {
		try {
			System.out.print("\nEnter project name ");
			String proName = sc.next();
			System.out.print("Enter start date ");
			LocalDate strDate = LocalDate.parse(sc.next());
			System.out.print("Enter end date ");
			LocalDate endDate = LocalDate.parse(sc.next());
			System.out.print("Enter wages per day ");
			int wage = sc.nextInt();
			
			Project pro = new Project(proName, strDate, endDate, wage);
			BDOintr bdo = new BDOimp();
			
			try {
				String msg = bdo.createProject(pro);
				System.out.println(msg);
			} catch (ProjectException e) {
				System.out.println(e.getMessage());
			}
			
		} catch (InputMismatchException e) {
			System.out.println("\nInvalid input, please enter valid input\n");
		}
	}

	private static void showAllProjectUI() {
		BDOintr bdo = new BDOimp();
		try {
			List<Project> list = bdo.showAllProject();
			System.out.println("\n----------- All project ------------\n");
			list.forEach(t -> System.out.println(t));
			System.out.println();
		} catch (ProjectException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void createGPMUI(Scanner sc) {
		try {
			System.out.print("\nEnter GPM name ");
			String gpmName = sc.next();
			System.out.print("Enter aadhar number ");
			String aadhar = sc.next();
			System.out.print("Enter date of birth ");
			LocalDate dob = LocalDate.parse(sc.next());
			System.out.print("Enter gender ");
			String gender = sc.next();
			System.out.print("Enter email ");
			String email = sc.next();
			System.out.print("Enter password ");
			String password = sc.next();
			System.out.print("Enter gram panchayat name ");
			String gpName = sc.next();
			System.out.print("Enter district ");
			String district = sc.next();
			System.out.print("Enter state ");
			String state = sc.next();
			
			GPM gpm = new GPM(gpmName, aadhar, dob, gender, email, password, gpName, district, state);
			BDOintr bdo = new BDOimp();
			
			try {
				String msg = bdo.createGPM(gpm);
			    System.out.println(msg);
			    System.out.println();
			} catch (GPMException e) {
				System.out.println(e.getMessage());
			}
			
		} catch (InputMismatchException e) {
			System.out.println("\nInvalid input, please enter valid input\n");
		}
	}

	private static void showAllGPMUI() {
		BDOintr bdo = new BDOimp();
		try {
			List<GPM> list = bdo.showAllGPM();
			System.out.println("\n----------- All GPM ------------\n");
			list.forEach(t -> System.out.println(t));
			System.out.println();
		} catch (GPMException | NoRecordFound e) {
			System.out.println(e.getMessage());
		}
	}

	private static void assignProToGPMUI(Scanner sc) {
		try {
			System.out.print("\nEnter project ID ");
			int pID = sc.nextInt();
			System.out.print("Enter gram panchayat ID ");
			int gpmID = sc.nextInt();
			
			BDOintr bdo = new BDOimp();
			try {
				String msg = bdo.assignProToGPM(pID, gpmID);
				System.out.println(msg);
			} catch (ProjectException | GPMException e) {
				System.out.println(e.getMessage());
			}
			
		} catch (InputMismatchException e) {
			System.out.println("\nInvalid input, please enter valid input\n");
		}
	}

	private static void showAllWorkerUI() {
		BDOintr bdo = new BDOimp();
		try {
			List<Worker> list = bdo.showAllWorker();
			System.out.println("\n----------- All worker ------------\n");
			list.forEach(t -> System.out.println(t));
			System.out.println();
		} catch (WorkerException | NoRecordFound e) {
			System.out.println(e.getMessage());
		}
	}
}
