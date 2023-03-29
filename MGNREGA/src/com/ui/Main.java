package com.ui;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		
		System.out.println("-=-=-=-=-= Welcome to MGNREGA =-=-=-=-=-");
		do {
			System.out.println("1. Login BDO \n2. Login GPM \n0. Exit");
			System.out.print("Enter your selection ");
			choice = sc.nextInt();
			
			switch(choice) {
			    case 1:
			    	BDOlogin();
			    	break;
			    case 2:
			    	GPMlogin();
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
				    			System.out.println("\nThanks, visit again\n");
				    			break;
				    		}
				    		else if(ch.equalsIgnoreCase("No")) {
				    			System.out.print("\nPlease Enter valid selection for login\n");
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
			    		System.out.print("\nInvalid selection, please enter valid selection\n");
			    	}
			}
			
		}while(choice!=0);
	}

	private static void BDOlogin() {
		// TODO Auto-generated method stub
		
	}

	private static void GPMlogin() {
		// TODO Auto-generated method stub
		
	}
}
