package com.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.dao.BDOimp;
import com.dao.BDOintr;
import com.dao.GPMimp;
import com.dao.GPMintr;
import com.colors.ConsoleColors;

public class Main {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		
		System.out.println(ConsoleColors.BLACK_BOLD_BRIGHT + ConsoleColors.BLACK_ITALIC + ConsoleColors.TEAL_BACKGROUND + "-=-=-=-=-= Welcome to MGNREGA =-=-=-=-=-" + ConsoleColors.RESET);
		do {
			System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT +
					"+---+--------------+\n"
				  + "| 1 |  Login BDO   |\n"
				  + "| 2 |  Login GPM   |\n"
				  + "| 0 |  Exit        |\n"
				  + "+---+--------------+"+ ConsoleColors.RESET);
			System.out.print(ConsoleColors.BLUE_BOLD + ConsoleColors.DARK_BLUE + "Enter your selection " + ConsoleColors.RESET);
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
			    		System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Are you sure about exit (Yes/No) " + ConsoleColors.RESET);
			    		String ch = null;
			    		boolean flag = false;
			    		int i = 3;
			    		do {
			    			ch = sc.next();
				    		if(ch.equalsIgnoreCase("Yes")) {
				    			System.out.println(ConsoleColors.BLACK_BOLD + ConsoleColors.BLACK_ITALIC + ConsoleColors.LIGHT_PURPLE_BACKGROUND + "\nThank you, visit again\n" + ConsoleColors.RESET);
				    			break;
				    		}
				    		else if(ch.equalsIgnoreCase("No")) {
				    			System.out.print(ConsoleColors.CYAN_BOLD + "\nPlease Enter valid selection for login" + ConsoleColors.RESET);
				    			choice = 3;
				    			break;
				    		}
				    		else {
				    			i--;
				    			if(i!=0) System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "\nPlease Enter (Yes/No) " + ConsoleColors.RESET);
				    		}
			    		}while(!ch.equalsIgnoreCase("Yes") && i!=0);
			    		if(flag == true)
			    			break;
			    		if(i==0) {
			    			System.out.println(ConsoleColors.RED_BOLD + ConsoleColors.WHITE_BACKGROUND + "\nSorry, try again leter\n" + ConsoleColors.RESET);
			    			break;
			    		}
			    	}
			    	else {
			    		System.out.print(ConsoleColors.RED_BACKGROUND + ConsoleColors.BLACK_BOLD + "\nInvalid selection, please enter valid selection\n" + ConsoleColors.RESET);
			    	}
			}
			
		}while(choice!=0);
	}

	private static void BDOlogin(Scanner sc) {
//		BDOmain.Main(sc);
		
		try {
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "\nEnter username " + ConsoleColors.RESET);
			String username = sc.next();
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter password " + ConsoleColors.RESET);
			String password = sc.next();
			
			BDOintr bdo = new BDOimp();
			boolean check = bdo.login(username, password);
			if(check == true) {
				System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT  + ConsoleColors.GREEN_BACKGROUND + "\nLogin successful" + ConsoleColors.RESET);
				BDOmain.Main(sc);
			}
			else {
				System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.BLACK_BOLD + "\nWrong username or password\n" + ConsoleColors.RESET);
			}
		} catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void GPMlogin(Scanner sc) {
		
//		GPMmain.Main(sc);
		
		try {
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "\nEnter email " + ConsoleColors.RESET);
			String email = sc.next();
			System.out.print(ConsoleColors.WHITE_BOLD_BRIGHT + "Enter password " + ConsoleColors.RESET);
			String password = sc.next();
			
			GPMintr gpm = new GPMimp();
			boolean check = gpm.login(email, password);
			if(check == true) {
				System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT  + ConsoleColors.GREEN_BACKGROUND + "\nLogin successful" + ConsoleColors.RESET);
				GPMmain.Main(sc);
			}
			else {
				System.out.println(ConsoleColors.RED_BACKGROUND + ConsoleColors.BLACK_BOLD + "\nWrong username or password\n" + ConsoleColors.RESET);
			}
		} catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		}
	}
}
