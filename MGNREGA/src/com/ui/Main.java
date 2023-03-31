package com.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.dao.BDOimp;
import com.dao.BDOintr;
import com.dao.GPMimp;
import com.dao.GPMintr;

public class Main {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		
		System.out.println("-=-=-=-=-= Welcome to MGNREGA =-=-=-=-=-");
		do {
			System.out.println("\n1. Login BDO \n2. Login GPM \n0. Exit");
			System.out.print("Enter your selection ");
			choice = sc.nextInt();
			
			switch(choice) {
			    case 1:
			    	BDOlogin(sc);
			    	break;
			    case 2:
			    	GPMlogin(sc);
			    	break;
			    default:
			    	if(choice == 0) {
			    		System.out.print("Are you sure about exit (Yes/No) ");
			    		String ch = null;
			    		boolean flag = false;
			    		int i = 3;
			    		do {
			    			ch = sc.next();
				    		if(ch.equalsIgnoreCase("Yes")) {
				    			System.out.println("\nThank you, visit again\n");
				    			break;
				    		}
				    		else if(ch.equalsIgnoreCase("No")) {
				    			System.out.print("\nPlease Enter valid selection for login");
				    			choice = 3;
				    			break;
				    		}
				    		else {
				    			i--;
				    			if(i!=0) System.out.print("\nPlease Enter (Yes/No) ");
				    		}
			    		}while(!ch.equalsIgnoreCase("Yes") && i!=0);
			    		if(flag == true)
			    			break;
			    		if(i==0) {
			    			System.out.println("\nSorry, try again leter\n");
			    			break;
			    		}
			    	}
			    	else {
			    		System.out.print("\nInvalid selection, please enter valid selection");
			    	}
			}
			
		}while(choice!=0);
	}

	private static void BDOlogin(Scanner sc) {
		try {
			System.out.print("\nEnter username ");
			String username = sc.next();
			System.out.print("Enter password ");
			String password = sc.next();
			
			BDOintr bdo = new BDOimp();
			boolean check = bdo.login(username, password);
			if(check == true) {
				BDOmain.Main(sc);
			}
			else {
				System.out.println("\nWrong username or password\n");
			}
		} catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void GPMlogin(Scanner sc) {
		try {
			System.out.print("\nEnter email ");
			String email = sc.next();
			System.out.print("Enter password ");
			String password = sc.next();
			
			GPMintr gpm = new GPMimp();
			boolean check = gpm.login(email, password);
			if(check == true) {
				GPMmain.Main(sc);
			}
			else {
				System.out.println("\nWrong username or password\n");
			}
		} catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		}
	}
}
