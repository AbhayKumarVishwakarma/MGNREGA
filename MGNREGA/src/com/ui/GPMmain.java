package com.ui;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.dao.GPMimp;
import com.dao.GPMintr;
import com.dto.Worker;
import com.exception.ProjectException;
import com.exception.WorkerException;

public class GPMmain {
	
	public static void Main(Scanner sc) {
		System.out.println("\n-=-=-=-=-= Welcome to GPM =-=-=-=-=-");
		int choice = 0;

        do {
        	System.out.println("\n1. Create worker \n2. View GP worker \n3. Search worker by aadhar \n4. Assign project to worker \n5. Viwe worker and working day \n6. Viwe worker and their wages \n7. Delete the worker \n0. Log out");
            System.out.print("Enter your selection ");
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
                	  System.out.println("\nLogged out!");
                	  break;
                  default :
                	  System.out.print("\nInvalid selection, please enter valid selection");
		    }
            
        }while(choice != 0);
	}

	private static void createWorkerUI(Scanner sc) {
		try {
			System.out.print("\nEnter name ");
			String wName = sc.next();
			System.out.print("Enter aadhar number ");
			String wAadhar = sc.next();
			System.out.print("Enter date of birth ");
			LocalDate wDob = LocalDate.parse(sc.next());
			System.out.print("Enter gender ");
			String wGender = sc.next();
			System.out.print("Enter start day of work ");
			LocalDate workStrDate = LocalDate.parse(sc.next());
			
			Worker worker = new Worker(wName, wAadhar, wDob, wGender, workStrDate);
			GPMintr gpm = new GPMimp();
			
			try {
				String msg = gpm.createWorker(worker);
				System.out.println(msg);
			} catch (WorkerException e) {
				System.out.println(e.getMessage());
			}
			
		} catch (InputMismatchException e) {
			System.out.println("Invalid input, please enter valid input");
		}
	}

	private static void showWorkerOfGPMUI() {
		GPMintr gpm = new GPMimp();
		try {
			List<Worker> list = gpm.showAllWorkerOfGPM();
			System.out.println();
			list.forEach(t -> System.out.println(t));
			System.out.println();
			System.out.println();
		} catch (WorkerException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void searchWorkerByAadharUI(Scanner sc) {
		try {
			System.out.print("\nEnter aadhar number ");
			String wAadhar = sc.next();
			
			GPMintr gpm = new GPMimp();
			try {
				Worker w = gpm.searchWorkerByAadhar(wAadhar);
				System.out.println("\n" + w + "\n");
			} catch (WorkerException e) {
				System.out.println(e.getMessage());
			}
		} catch (InputMismatchException e) {
			System.out.println("\nInvalid input, please enter valid input\n");
		}
	}

	private static void assignProToWorkerUI(Scanner sc) {
		try {
			System.out.print("\nEnter project ID ");
			int proID = sc.nextInt();
			System.out.print("Enter worker ID ");
			int wID = sc.nextInt();
			
			GPMintr gpm = new GPMimp();
			try {
				String msg = gpm.assignProToWorker(proID, wID);
				System.out.println(msg);
			} catch (WorkerException | ProjectException e) {
				System.out.println(e.getMessage());
			}
		} catch (InputMismatchException e) {
			System.out.println("\nInvalid input, please enter valid input\n");
		}
		
		
	}

	private static void showWorkerAndWorkingDayUI(Scanner sc) {
		GPMintr gpm = new GPMimp();
		try {
			gpm.showWorkerAndWorkingDay();
		} catch (WorkerException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void ShowWorkerAndWagesUI(Scanner sc) {
		GPMintr gpm = new GPMimp();
		try {
			gpm.ShowWorkerAndWages();
		} catch (WorkerException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void deleteWorkerUI(Scanner sc) {
		try {
			System.out.print("\nEnter worker ID ");
			int wID = sc.nextInt();
			
			GPMintr gpm = new GPMimp();
			try {
				String msg = gpm.deleteWorker(wID);
				System.out.println(msg);
			} catch (WorkerException e) {
				System.out.println(e.getMessage());
			}
		} catch (InputMismatchException e) {
			System.out.println("\nInvalid input, please enter valid input\n");
		}
	}
}
