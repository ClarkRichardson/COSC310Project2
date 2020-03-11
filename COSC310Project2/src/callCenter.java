
import java.util.ArrayList;
import java.util.Scanner;

public class callCenter {
	
	static boolean callActive = true;
	static int category = 0;
	static int creditCardNumber = 0;
	static int pID = 0;	
	static Scanner in = new Scanner(System.in);
		
	static String[] productList = {"chair", "table", "couch", "love seat", 
			"night stand", "lamp", "wardrobe", "stool", "kitchen appliance"};
	static ArrayList<String> userComplaints = new ArrayList<String>();
	 
	
	public static void main(String[]args) {
System.out.println("Welcome to ****** Call Center!");//not sure of the name
		
		while(callActive) {
			category = chooseCategory();
			if(category==0) {
				callActive = false;//if zero exit
			}else {
				switch(category) {
				case 1:
					productSatisfaction();
					break;
				case 2:
					complaintCheck();
					break;
				case 3:
					reviews();
					break;
				default:
					System.out.println("Invalid entry. Please Select another category");
					chooseCategory();
				}
			}
		}
		
		System.out.println("I hope we were able to help.");
		System.out.println("Have a nice day!");
			
	}
	static int chooseCategory(){
		System.out.println("What can I help you with?");
		System.out.println("1. Product Satisfaction");
		System.out.println("2. Complaints");
		System.out.println("3. Reviews");
		//We can add more
		System.out.println("0. Exit");//If they enter zero the program will exit
		System.out.println("Please enter a number from the menu above: ");
		
		return(in.nextInt());
	}
	
	static void complaintCheck() {
		
		in.nextLine();
		System.out.println("I'm very sorry to hear you've had a bad experience.");
		System.out.println("Is there something we could do to fix the situation, yes or no?");
		String fixable = in.nextLine();
			if (fixable.contains("yes")) {
				productSatisfaction();
			} if (fixable.contains("no")) {
				complaints();
			} else {
				System.out.println("Invalid entry, please respond yes or no");
			}
		
	}
	static void complaints() {
		System.out.println("We will need some basic information from you to process your complaint");
		System.out.println("What is your name?");
		String name = in.nextLine();
		
		System.out.println("Ok "+ name +" what product would you like to leave a complaint for?");
		String product = in.nextLine();
		boolean found = false;
		boolean wrong = false;
		
		for(int i = 0; found == false && i < productList.length; i++) {
			if(product.contains(productList[i])) {
				found = true;
				product = productList[i];
				System.out.printf("I am very sorry to hear you have a complaint about one of our %ss.", product);
				System.out.println("\nPlease leave your complaint below:");
				String complaint = in.nextLine();
				userComplaints.add(product + ": " + complaint);
			}
			else if(i == productList.length-1 && !found) {
				System.out.println("I'm sorry, that doesn't sound like one of our products.");
				wrong = true;
			}
			
		}
		if(wrong == true) {chooseCategory();}
		
		System.out.println("Thank you very much for your feedback.");
		System.out.printf("We will work to ensure that the issues you've had with %s do not happen again.\n", product);
		
		System.out.println("Is there anything else I can do for you?");
		String reply = in.nextLine();
		if(reply.contains("no")) {
			System.out.println("Have a lovely day, and thank you for shopping with us!");
			System.exit(0);
		} else if(reply.contains("yes")) {
			chooseCategory();
		}
	}
	static void productSatisfaction() {}
	static void reviews() {}
}

