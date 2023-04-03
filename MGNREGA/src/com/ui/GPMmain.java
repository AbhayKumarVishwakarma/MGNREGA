package com.ui;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.colors.ConsoleColors;
import com.dao.GPMimp;
import com.dao.GPMintr;
import com.dto.Worker;
import com.exception.ProjectException;
import com.exception.WorkerException;

public class GPMmain {
	
	public static void Main(Scanner sc) {
		System.out.println(ConsoleColors.BLACK_BOLD_BRIGHT + ConsoleColors.BLACK_ITALIC +  ConsoleColors.BANANA_YELLOW_BACKGROUND +  "\n-=-=-=-=-= Welcome to GPM =-=-=-=-=-" + ConsoleColors.RESET);
		int choice = 0;

        do {
//        	System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "\n1. Create worker \n2. View GP worker \n3. Search worker by aadhar \n4. Assign project to worker \n5. Viwe worker and working day \n6. Viwe worker and their wages \n7. Delete the worker \n0. Log out" + ConsoleColors.RESET);
        	System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT 
				  + "+---+--------------------------------+\n"
				  + "| 1 |  Create worker                 |\n"
				  + "| 2 |  View GP worker                |\n"
				  + "| 3 |  Search worker by aadhar       |\n"
				  + "| 4 |  Assign project to worker      |\n"
				  + "| 5 |  Viwe worker and working day   |\n"
				  + "| 6 |  Viwe worker and their wages   |\n"
				  + "| 7 |  Delete the worker             |\n"
				  + "| 0 |  Logout                        |\n"
				  + "+---+--------------------------------+"+ ConsoleColors.RESET);
        	System.out.print(ConsoleColors.BLUE_BOLD + ConsoleColors.DARK_BLUE + "Enter your selection " + ConsoleColors.RESET);
            choice = sc.nextInt();
            
            switch(choice) {
                  case 1:
                	  createWorkerUI(sc);
                	  break;
                  case 2:
                	  showWorkerOfGPMUI();
                	  break;
                  case 3:
                	  searchWorkerByAadharUI(sc);
                	  break;
                  case 4:
                	  assignProToWorkerUI(sc);
                	  break;
                  case 5:
                	  showWorkerAndWorkingDayUI(sc);
                	  break;
                  case 6:
                	  ShowWorkerAndWagesUI(sc);
                	  break;
                  case 7:
                	  deleteWorkerUI(sc);
                	  break;
                  case 0:
                	  System.out.println(ConsoleColors.CYAN_BOLD + "\nLogged out!" + ConsoleColors.RESET);
                	  break;
                  default :
                	  System.out.print(ConsoleColors.RED_BACKGROUND + ConsoleColors.BLACK_BOLD + "\nInvalid selection, please enter valid selection" + ConsoleColors.RESET);
		    }
            
        }while(choice != 0);
	}

	private static void createWorkerUI(Scanner sc) {
		try {
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "\nEnter name " + ConsoleColors.RESET);
			String wName = sc.next();
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter aadhar number " + ConsoleColors.RESET);
			String wAadhar = sc.next();
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter date of birth " + ConsoleColors.RESET);
			LocalDate wDob = LocalDate.parse(sc.next());
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter gender " + ConsoleColors.RESET);
			String wGender = sc.next();
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter start day of work " + ConsoleColors.RESET);
			LocalDate workStrDate = LocalDate.parse(sc.next());
			
			Worker worker = new Worker(wName, wAadhar, wDob, wGender, workStrDate);
			GPMintr gpm = new GPMimp();
			
			try {
				String msg = gpm.createWorker(worker);
				System.out.println(ConsoleColors.GREEN + msg + ConsoleColors.RESET);
			} catch (WorkerException e) {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT  + e.getMessage() + ConsoleColors.RESET);
			}
			
		} catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.BLACK_BOLD + "Invalid input, please enter valid input" + ConsoleColors.RESET);
		}
	}

	private static void showWorkerOfGPMUI() {
		GPMintr gpm = new GPMimp();
		try {
			List<Worker> list = gpm.showAllWorkerOfGPM();
			if(list.size()>0) {
				System.out.println();
				System.out.println(ConsoleColors.LIGHT_PINK_BACKGROUND + ConsoleColors.BLACK_BOLD + "                                                   All Worker                                                    " + ConsoleColors.RESET);
				System.out.println(ConsoleColors.LIGHT_PINK +"-----------------------------------------------------------------------------------------------------------------"+ ConsoleColors.RESET);
				
				System.out.printf("%7s %9s %13s %12s %12s %12s %11s %8s %13s %14s",ConsoleColors.WHITE_BOLD_BRIGHT + "WID", "NAME","AADHAR", "DOB", "GENDER", "GPNAME", "GPMID", "PROID", "DISTRICT", "STATE" + ConsoleColors.RESET + "\n");
				System.out.println(ConsoleColors.LIGHT_PINK +"-----------------------------------------------------------------------------------------------------------------"+ ConsoleColors.RESET);
				
				list.forEach(t -> System.out.format("%2s %10s %16s %12s %8s %13s %9s %8s %16s %8s", t.getwID(), t.getwName(), t.getwAadhar(), t.getwDob(), t.getwGender(), t.getGpName(), t.getGpmID(), t.getProID(), t.getDistrict(), t.getState() + "\n"));
				System.out.println(ConsoleColors.LIGHT_PINK +"-----------------------------------------------------------------------------------------------------------------"+ ConsoleColors.RESET);
				System.out.println();
			}
			else {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT  + "\nEmpty Worker\n" + ConsoleColors.RESET);
			}
		} catch (WorkerException e) {
			System.out.println(ConsoleColors.RED_BOLD_BRIGHT  + e.getMessage() + ConsoleColors.RESET);
		}
	}

	private static void searchWorkerByAadharUI(Scanner sc) {
		try {
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "\nEnter aadhar number " + ConsoleColors.RESET);
			String wAadhar = sc.next();
			
			GPMintr gpm = new GPMimp();
			try {
				Worker w = gpm.searchWorkerByAadhar(wAadhar);
				
                System.out.println(ConsoleColors.LIGHT_PINK +"\n-----------------------------------------------------------------------------------------------------------------"+ ConsoleColors.RESET);
				System.out.printf("%7s %9s %13s %12s %12s %12s %11s %8s %13s %14s",ConsoleColors.WHITE_BOLD_BRIGHT + "WID", "NAME","AADHAR", "DOB", "GENDER", "GPNAME", "GPMID", "PROID", "DISTRICT", "STATE" + ConsoleColors.RESET + "\n");
				System.out.println(ConsoleColors.LIGHT_PINK +"-----------------------------------------------------------------------------------------------------------------"+ ConsoleColors.RESET);
				
				System.out.format("%2s %10s %16s %12s %8s %13s %9s %8s %16s %8s", w.getwID(), w.getwName(), w.getwAadhar(), w.getwDob(), w.getwGender(), w.getGpName(), w.getGpmID(), w.getProID(), w.getDistrict(), w.getState() + "\n");
				System.out.println(ConsoleColors.LIGHT_PINK +"-----------------------------------------------------------------------------------------------------------------"+ ConsoleColors.RESET);
				System.out.println();
				
			} catch (WorkerException e) {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT  + e.getMessage() + ConsoleColors.RESET);
			}
		} catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.BLACK_BOLD + "\nInvalid input, please enter valid input\n" + ConsoleColors.RESET);
		}
	}

	private static void assignProToWorkerUI(Scanner sc) {
		try {
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "\nEnter project ID " + ConsoleColors.RESET);
			int proID = sc.nextInt();
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter worker ID " + ConsoleColors.RESET);
			int wID = sc.nextInt();
			
			GPMintr gpm = new GPMimp();
			try {
				String msg = gpm.assignProToWorker(proID, wID);
				System.out.println(ConsoleColors.GREEN + msg + ConsoleColors.RESET);
			} catch (WorkerException | ProjectException e) {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT  + e.getMessage() + ConsoleColors.RESET);
			}
		} catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.BLACK_BOLD + "\nInvalid input, please enter valid input\n" + ConsoleColors.RESET);
		}
		
		
	}

	private static void showWorkerAndWorkingDayUI(Scanner sc) {
		GPMintr gpm = new GPMimp();
		try {
			gpm.showWorkerAndWorkingDay();
		} catch (WorkerException e) {
			System.out.println(ConsoleColors.RED_BOLD_BRIGHT  + e.getMessage() + ConsoleColors.RESET);
		}
	}

	private static void ShowWorkerAndWagesUI(Scanner sc) {
		GPMintr gpm = new GPMimp();
		try {
			gpm.ShowWorkerAndWages();
		} catch (WorkerException e) {
			System.out.println(ConsoleColors.RED_BOLD_BRIGHT  + e.getMessage() + ConsoleColors.RESET);
		}
	}

	private static void deleteWorkerUI(Scanner sc) {
		try {
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "\nEnter worker ID " + ConsoleColors.RESET);
			int wID = sc.nextInt();
			
			GPMintr gpm = new GPMimp();
			try {
				String msg = gpm.deleteWorker(wID);
				System.out.println(ConsoleColors.GREEN + msg + ConsoleColors.RESET);
			} catch (WorkerException e) {
				System.out.println(ConsoleColors.RED_BOLD_BRIGHT  + e.getMessage() + ConsoleColors.RESET);
			}
		} catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.BLACK_BOLD + "\nInvalid input, please enter valid input\n" + ConsoleColors.RESET);
		}
	}
}
