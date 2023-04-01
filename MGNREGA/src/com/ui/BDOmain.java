package com.ui;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.colors.ConsoleColors;
import com.dao.BDOimp;
import com.dao.BDOintr;
import com.dao.GPMimp;
import com.dao.GPMintr;
import com.dto.GPM;
import com.dto.Project;
import com.dto.Worker;
import com.exception.GPMException;
import com.exception.NoRecordFound;
import com.exception.ProjectException;
import com.exception.WorkerException;

public class BDOmain {

	public static void Main(Scanner sc) {
		System.out.println(ConsoleColors.BLACK_BOLD_BRIGHT + ConsoleColors.BLACK_ITALIC +  ConsoleColors.BANANA_YELLOW_BACKGROUND +  "\n-=-=-=-=-= Welcome to BDO =-=-=-=-=-" + ConsoleColors.RESET);
		int choice = 0;

        do {
        	System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + 
					"+---+--------------------------------+\n"
				  + "| 1 |  Create Project                |\n"
				  + "| 2 |  View List Of Project          |\n"
				  + "| 3 |  Create GPM                    |\n"
				  + "| 4 |  View all GPM                  |\n"
				  + "| 5 |  Allocate Project to GPM       |\n"
				  + "| 6 |  Viwe all worker               |\n"
				  + "| 7 |  Viwe all worker by GPM ID     |\n"
				  + "| 8 |  Viwe all worker by Project ID |\n"
				  + "| 0 |  Logout                        |\n"
				  + "+---+--------------------------------+"+ ConsoleColors.RESET);
        	System.out.print(ConsoleColors.BLUE_BOLD + ConsoleColors.DARK_BLUE + "Enter your selection " + ConsoleColors.RESET);
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
                  case 7:
                	  showWorkerByGPMIDUI(sc);
                	  break;
                  case 8:
                	  showWorkerByProIDUI(sc);
                	  break;
                  case 0:
                	  System.out.println(ConsoleColors.CYAN_BOLD + "\nLogged out!" + ConsoleColors.RESET);
                	  break;
                  default :
                	  System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.BLACK_BOLD + "\nInvalid selection, please enter valid selection" + ConsoleColors.RESET);
		    	
		    }
            
        }while(choice != 0);
	}

	private static void creatProjectUI(Scanner sc) {
		try {
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "\nEnter project name " + ConsoleColors.RESET);
			String proName = sc.next();
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter start date " + ConsoleColors.RESET);
			LocalDate strDate = LocalDate.parse(sc.next());
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter end date " + ConsoleColors.RESET);
			LocalDate endDate = LocalDate.parse(sc.next());
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter wages per day " + ConsoleColors.RESET);
			int wage = sc.nextInt();
			
			Project pro = new Project(proName, strDate, endDate, wage);
			BDOintr bdo = new BDOimp();
			
			try {
				String msg = bdo.createProject(pro);
				System.out.println(ConsoleColors.GREEN + msg + ConsoleColors.RESET);
			} catch (ProjectException e) {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT  + e.getMessage() + ConsoleColors.RESET);
			}
			
		} catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.BLACK_BOLD + "\nInvalid input, please enter valid input\n" + ConsoleColors.RESET);
		}
	}

	private static void showAllProjectUI() {
		BDOintr bdo = new BDOimp();
		try {
			List<Project> list = bdo.showAllProject();
			
			if(list.size()>0) {
				System.out.println("\n"+ ConsoleColors.LIGHT_PINK_BACKGROUND + ConsoleColors.BLACK_BOLD + "                                          All project                                         " + ConsoleColors.RESET);
//				System.out.println  ("                                          ");
				System.out.println(ConsoleColors.LIGHT_PINK +"----------------------------------------------------------------------------------------------"+ ConsoleColors.RESET);
				
				System.out.printf("%7s %16s %17s %17s %13s %13s %14s",ConsoleColors.WHITE_BOLD_BRIGHT + "PID", "PNAME","START", "END", "GPMID", "WORKER", "WAGE" + ConsoleColors.RESET + "\n");
				System.out.println(ConsoleColors.LIGHT_PINK +"----------------------------------------------------------------------------------------------"+ ConsoleColors.RESET);
				
				list.forEach(t -> System.out.format("%2s %22s %16s %16s %8s %12s %13s", t.getProID(), t.getProName(), t.getProStrDate(), t.getProEndDate(), t.getGpmID(), t.getNoOfWorker(), t.getWagesParDay() + "\n"));
				System.out.println(ConsoleColors.LIGHT_PINK +"----------------------------------------------------------------------------------------------"+ ConsoleColors.RESET);
				System.out.println();
			}
			else {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT  + "\nEmpty project\n" + ConsoleColors.RESET);
			}
			
		} catch (ProjectException e) {
			System.out.println(ConsoleColors.RED_BOLD_BRIGHT  + e.getMessage() + ConsoleColors.RESET);
		}
	}

	private static void createGPMUI(Scanner sc) {
		try {
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "\nEnter GPM name " + ConsoleColors.RESET);
			String gpmName = sc.next();
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter aadhar number " + ConsoleColors.RESET);
			String aadhar = sc.next();
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter date of birth " + ConsoleColors.RESET);
			LocalDate dob = LocalDate.parse(sc.next());
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter gender " + ConsoleColors.RESET);
			String gender = sc.next();
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter email " + ConsoleColors.RESET);
			String email = sc.next();
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter password " + ConsoleColors.RESET);
			String password = sc.next();
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter gram panchayat name " + ConsoleColors.RESET);
			String gpName = sc.next();
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter district " + ConsoleColors.RESET);
			String district = sc.next();
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter state " + ConsoleColors.RESET);
			String state = sc.next();
			
			GPM gpm = new GPM(gpmName, aadhar, dob, gender, email, password, gpName, district, state);
			BDOintr bdo = new BDOimp();
			
			try {
				String msg = bdo.createGPM(gpm);
				System.out.println(ConsoleColors.GREEN + msg + ConsoleColors.RESET);
			    System.out.println();
			} catch (GPMException e) {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT  + e.getMessage() + ConsoleColors.RESET);
			}
			
		} catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.BLACK_BOLD + "\nInvalid input, please enter valid input\n" + ConsoleColors.RESET);
		}
	}

	private static void showAllGPMUI() {
		BDOintr bdo = new BDOimp();
		try {
			List<GPM> list = bdo.showAllGPM();
			
			if(list.size()>0) {
				System.out.println("\n"+ ConsoleColors.LIGHT_PINK_BACKGROUND + ConsoleColors.BLACK_BOLD + "                                                Gram Panchayat Member                                                " + ConsoleColors.RESET);
//				System.out.println                                                                       ("GID   NAME     AADHAR          DOB       GENDER          EMAIL         PASSWORD      GPNAME      DISTRICT     STATE[0m");
				System.out.println(ConsoleColors.LIGHT_PINK +"---------------------------------------------------------------------------------------------------------------------"+ ConsoleColors.RESET);
				
				System.out.printf("%7s %6s %10s %12s %12s %14s %16s %11s %13s %14s",ConsoleColors.WHITE_BOLD_BRIGHT + "GID", "NAME", "AADHAR", "DOB", "GENDER", "EMAIL", "PASSWORD", "GPNAME", "DISTRICT", "STATE" + ConsoleColors.RESET + "\n");
				System.out.println(ConsoleColors.LIGHT_PINK +"---------------------------------------------------------------------------------------------------------------------"+ ConsoleColors.RESET);
				
				list.forEach(t -> System.out.format("%2s %7s %14s %12s %7s %19s %12s %13s %12s %7s", t.getGpmID(), t.getGpmName(), t.getGpmAadhar(), t.getGpmDob(), t.getGpmGender(), t.getGpmEmail(), t.getGpmPassword(), t.getGpName(), t.getDistrict(), t.getState() + "\n"));
				System.out.println(ConsoleColors.LIGHT_PINK +"---------------------------------------------------------------------------------------------------------------------"+ ConsoleColors.RESET);
				System.out.println();
			}
			else {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT  + "\nEmpty GPM\n" + ConsoleColors.RESET);
			}
			
			
		} catch (GPMException | NoRecordFound e) {
			System.out.println(ConsoleColors.RED_BOLD_BRIGHT  + e.getMessage() + ConsoleColors.RESET);
		}
	}

	private static void assignProToGPMUI(Scanner sc) {
		try {
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "\nEnter project ID " + ConsoleColors.RESET);
			int pID = sc.nextInt();
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter gram panchayat ID " + ConsoleColors.RESET);
			int gpmID = sc.nextInt();
			
			BDOintr bdo = new BDOimp();
			try {
				String msg = bdo.assignProToGPM(pID, gpmID);
				System.out.println(ConsoleColors.GREEN + msg + ConsoleColors.RESET);
			} catch (ProjectException | GPMException e) {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT  + e.getMessage() + ConsoleColors.RESET);
			}
			
		} catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.BLACK_BOLD + "\nInvalid input, please enter valid input\n" + ConsoleColors.RESET);
		}
	}

	private static void showAllWorkerUI() {
		BDOintr bdo = new BDOimp();
		try {
			List<Worker> list = bdo.showAllWorker();
			if(list.size()>0) {
				System.out.println("\n"+ ConsoleColors.LIGHT_PINK_BACKGROUND + ConsoleColors.BLACK_BOLD + "                                                   All Worker                                                    " + ConsoleColors.RESET);
				System.out.println(ConsoleColors.LIGHT_PINK +"-----------------------------------------------------------------------------------------------------------------"+ ConsoleColors.RESET);
				
				System.out.printf("%7s %9s %13s %12s %12s %12s %11s %8s %13s %14s",ConsoleColors.WHITE_BOLD_BRIGHT + "WID", "NAME","AADHAR", "DOB", "GENDER", "GPNAME", "GPMID", "PROID", "DISTRICT", "STATE" + ConsoleColors.RESET + "\n");
				System.out.println(ConsoleColors.LIGHT_PINK +"-----------------------------------------------------------------------------------------------------------------"+ ConsoleColors.RESET);
				
				list.forEach(t -> System.out.format("%2s %10s %16s %12s %8s %15s %7s %8s %16s %8s", t.getwID(), t.getwName(), t.getwAadhar(), t.getwDob(), t.getwGender(), t.getGpName(), t.getGpmID(), t.getProID(), t.getDistrict(), t.getState() + "\n"));
				System.out.println(ConsoleColors.LIGHT_PINK +"-----------------------------------------------------------------------------------------------------------------"+ ConsoleColors.RESET);
				System.out.println();
			}
			else {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT  + "\nEmpty Worker\n" + ConsoleColors.RESET);
			}
			
		} catch (WorkerException | NoRecordFound e) {
			System.out.println(ConsoleColors.RED_BOLD_BRIGHT  + e.getMessage() + ConsoleColors.RESET);
		}
	}
	
	private static void showWorkerByGPMIDUI(Scanner sc) { 
		try {
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "\nEnter GPM ID " + ConsoleColors.RESET);
			int gpmID = sc.nextInt();
			
			BDOintr bdo = new BDOimp();
			try {
				List<Worker> list = bdo.showAllWorkerByGPMID(gpmID);
				if(list.size()>0) {
					System.out.println("\n"+ ConsoleColors.LIGHT_PINK_BACKGROUND + ConsoleColors.BLACK_BOLD + "                                                       Worker                                                    " + ConsoleColors.RESET);
					System.out.println(ConsoleColors.LIGHT_PINK +"-----------------------------------------------------------------------------------------------------------------"+ ConsoleColors.RESET);
					
					System.out.printf("%7s %9s %13s %12s %12s %12s %11s %8s %13s %14s",ConsoleColors.WHITE_BOLD_BRIGHT + "WID", "NAME","AADHAR", "DOB", "GENDER", "GPNAME", "GPMID", "PROID", "DISTRICT", "STATE" + ConsoleColors.RESET + "\n");
					System.out.println(ConsoleColors.LIGHT_PINK +"-----------------------------------------------------------------------------------------------------------------"+ ConsoleColors.RESET);
					
					list.forEach(t -> System.out.format("%2s %10s %16s %12s %8s %15s %7s %8s %16s %8s", t.getwID(), t.getwName(), t.getwAadhar(), t.getwDob(), t.getwGender(), t.getGpName(), t.getGpmID(), t.getProID(), t.getDistrict(), t.getState() + "\n"));
					System.out.println(ConsoleColors.LIGHT_PINK +"-----------------------------------------------------------------------------------------------------------------"+ ConsoleColors.RESET);
					System.out.println();
				}
				else {
					System.out.println(ConsoleColors.RED_BOLD_BRIGHT  + "\nEmpty Worker\n" + ConsoleColors.RESET);
				}
				
			} catch (WorkerException | GPMException e) {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT  + e.getMessage() + ConsoleColors.RESET);
			}
		} catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.BLACK_BOLD + "\nInvalid input, please enter valid input\n" + ConsoleColors.RESET);
		}
	}
	
	private static void showWorkerByProIDUI(Scanner sc) { 
		try {
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "\nEnter project ID " + ConsoleColors.RESET);
			int proID = sc.nextInt();
			
			BDOintr bdo = new BDOimp();
			try {
				List<Worker> list = bdo.showAllWorkerByproID(proID);
				if(list.size()>0) {
					System.out.println("\n"+ ConsoleColors.LIGHT_PINK_BACKGROUND + ConsoleColors.BLACK_BOLD + "                                                       Worker                                                    " + ConsoleColors.RESET);
					System.out.println(ConsoleColors.LIGHT_PINK +"-----------------------------------------------------------------------------------------------------------------"+ ConsoleColors.RESET);
					
					System.out.printf("%7s %9s %13s %12s %12s %12s %11s %8s %13s %14s",ConsoleColors.WHITE_BOLD_BRIGHT + "WID", "NAME","AADHAR", "DOB", "GENDER", "GPNAME", "GPMID", "PROID", "DISTRICT", "STATE" + ConsoleColors.RESET + "\n");
					System.out.println(ConsoleColors.LIGHT_PINK +"-----------------------------------------------------------------------------------------------------------------"+ ConsoleColors.RESET);
					
					list.forEach(t -> System.out.format("%2s %10s %16s %12s %8s %15s %7s %8s %16s %8s", t.getwID(), t.getwName(), t.getwAadhar(), t.getwDob(), t.getwGender(), t.getGpName(), t.getGpmID(), t.getProID(), t.getDistrict(), t.getState() + "\n"));
					System.out.println(ConsoleColors.LIGHT_PINK +"-----------------------------------------------------------------------------------------------------------------"+ ConsoleColors.RESET);
					System.out.println();
				}
				else {
					System.out.println(ConsoleColors.RED_BOLD_BRIGHT  + "\nEmpty Worker\n" + ConsoleColors.RESET);
				}
				
			} catch (WorkerException | ProjectException e) {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT  + e.getMessage() + ConsoleColors.RESET);
			}
		} catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.BLACK_BOLD + "\nInvalid input, please enter valid input\n" + ConsoleColors.RESET);
		}
	}
	
}
