//==========================================================================================
// COSC 310 Assignment 2
// 
// Happy Home Furnishings Call Center
// Group #1: Mary Whitten, Nick Gerristen, Ryan Pinkney, Clark Richardson Jordan Kozakevich
//==========================================================================================

import java.util.Scanner;
import java.util.ArrayList;

public class callCenter {
	//Initializing Global Variables
	static String[] productList = {"chair", "table", "couch", "love seat", "night stand", "lamp", "wardrobe", "stool", "kitchen appliance"};
	static String[][] userReviews = new String[100][2];
	static ArrayList<String> userComplaints = new ArrayList<String>();
	static String str;
	static boolean callActive = true;
	static int category = 0;
	static int creditCardNumber = 0;
	static int pID = 0;	
	static Scanner in = new Scanner(System.in);
	
	//Main method
	public static void main(String[]args) {
		
		System.out.println("Welcome to the Happy Home Furnishings Call Center!");
		
		while(callActive) {
			System.out.println("What can I help you with?");
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
					case 4:
						orderstatus();
						break;
					case 5:
						help();
						break;
					case 6:
						moving();
						break;
					default:
						outsideResponseMenu();
				}
			}
		}
		
		System.out.println("I hope we were able to help.");
		System.out.println("Have a nice day!");
			
	}
	
	//Gets user response for what they need help with
	static int chooseCategory(){
		System.out.println("\t 1. Product Satisfaction");
		System.out.println("\t 2. Complaints");
		System.out.println("\t 3. Reviews");
		System.out.println("\t 4. Order Status");
		System.out.println("\t 5. Help");
		System.out.println("\t 6. Moving");

		//We can add more
		System.out.println("\t 0. Exit");//If they enter zero the program will exit
		System.out.println("Please enter a number from the menu above: ");
		
		return(in.nextInt());
	}
	
	  ///////////////////////////////////
	 //  Product Satisfaction Section //
	///////////////////////////////////
	static void productSatisfaction() {
		int issue = 0;
		int solution = 0;
		boolean validEntry = false;
		
		while(!validEntry) {
			//Displaying options
			System.out.println("\nProduct Satisfaction");
			System.out.println("What seems to be the issue?");
			System.out.println("\t 1. Product Defect");
			System.out.println("\t 2. Missing Parts");
			System.out.println("\t 3. Not what you expected");
			System.out.println("\t 0. Exit");//If they enter zero the program will exit
			System.out.println("Please enter a number from the menu above: ");
			issue = in.nextInt();
			
			switch (issue) {
				case 1:
					System.out.println("We are sorry to hear that you had a defect");
					validEntry = true;
					break;
				case 2:
					System.out.println("We are sorry to hear that you are missing a part");
					validEntry = true;
					break;
				case 3:
					System.out.println("We are sorry to hear that you recieved a part that is not what you expexted");
					validEntry = true;
					break;
				case 0:
					validEntry = true;
					break;
				default:
					notValid();
					break;
			}
			if(issue == 0) {
				break;
			}else {
				validEntry = false;
				while(!validEntry) {
					System.out.println("At Happy Home Furnishings we guarantee that are customers are satisfied what would you like to do?");
					System.out.println("Okay so we can offer you two options");
					System.out.println("We can either:");
					System.out.println("\t 1. Send back your order and recive a new item");
					System.out.println("\t 2. Send you a refund");
					System.out.println("\t 3. Be directed to review department");
					System.out.println("\t 4. Be directed to complaints department");
					System.out.println("\t 0. Return to main menu");
					solution = in.nextInt();
					
					switch(solution) {//Directory for further  satisfaction
						case 1:
							validateOrder();
							System.out.println("Please go to your local post office and give them this shipping code and your order will be returned for free");
							System.out.println("Your shipping number is " + generateShippingCode());
							validEntry = true;
							break;
						case 2:
							validateOrder();
							System.out.println("A refund will be sent to you credit card within the next 5 business days");
							validEntry = true;
							break;
						case 3:
							reviews();
							break;
						case 4:
							complaints();
							break;
						case 0:
							validEntry = true;
							break;
						default:
							notValid();
							break;
					}
				}
			}
			if(solution == 0) {
				break;
			}
			callActive = false;
		}	
	}
	
	  ///////////////////////////
	 //  Order Status Section //
	///////////////////////////
	static void orderstatus() {
		boolean orderstat;
		int trackingNum;
		boolean shipCode = true;
		boolean payed = false;
		
		System.out.println("Would you like to check your order status? Type true or false to answer.");
		orderstat = in.nextBoolean();
		int response;
		if (orderstat) {
			while (!payed) {
				System.out.println("Has your order been payed for?\n Enter 1 for yes, 2 for no.");
				response = in.nextInt();
				switch (response) {
				case 1:
					System.out.println("Were you given a shipping code? Enter 1 for yes, 2 for no.");
					response = in.nextInt();
					while (shipCode) {
						if (response == 1) {
							System.out.println("Please enter your 7 digit shipping code");
							trackingNum = in.nextInt();
							System.out.println(
									"Your order has been processed and shipped! Has it been more than 5 business days since you placed your order? Enter 1 for yes, 2 for no.");
							response = in.nextInt();
							if (response == 2)
								System.out.println("You should recieve your order within 5 business days.");
							else if (response == 1)
								System.out.println(
										"Sorry for the delay, we are experiencing a high volume of orders at the moment. If your order is not recieved within two business days please check in again and we can further assist you.");
							shipCode = false;
						} else if (response == 2) {
							System.out.println("Your shipping code is " + generateShippingCode());
							response = 1;
						}
					}
					payed = true;
					break;

				case 2:
					validateOrder();
					break;

				}
			}
			callActive = false;
		}
		

	}
  
	  /////////////////////////
	 //  Complaints Section //
	/////////////////////////
	static void complaintCheck() {//
		in.nextLine();
		System.out.println("I'm very sorry to hear you've had a bad experience.");
		System.out.println("Is there something we could do to fix the situation, yes or no?");
		String fixable = in.nextLine();
			if (fixable.contains("yes")) {
				productSatisfaction();//direct to product satisfaction to deal with complaint
			} if (fixable.contains("no")) {
				complaints();//continue to compliants section
			} else {
				notValid();
			}
	}
  
	static void complaints() {
		//obtaining customer information
		System.out.println("We will need some basic information from you to process your complaint");
		System.out.println("What is your name?");
		String name = in.nextLine();
		
		System.out.println("Ok "+ name +" what product would you like to leave a complaint for?");
		String product = in.nextLine();
		boolean found = false;
		boolean wrong = false;
		
		//Getting compliant information
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
	  /////////////////////
	 //  Review Section //
	/////////////////////
	static void reviews() {
		loop: while(true) {
			System.out.println("You have reached the Reviews section. Please select one from the following menu: ");
			System.out.println("1. Leave a review");
			System.out.println("2. See the reviews of a product");
			System.out.println("3. Edit one of your existing reviews");
			System.out.println("4. Go back to the previous menu.");
			int category = in.nextInt();
			
			switch(category) {
			case 1:
				leaveReview();
				break;
			case 2:
				seeReviews();
				break;
			case 3:
				editReview();
				break;
			case 4:
				back();
				break loop;
			default:
				notValid();
			}
		}
	}
  
  public static void leaveReview() {//user writes a review
		in.nextLine(); //Clear input
		System.out.println("It seems that you want to leave a review. Can you tell me which product you would like to review?");
		String str = in.nextLine();
		boolean found = false;
		
		for(int i = 0; i < productList.length && found == false; i++) { //Try and find product to review
			if(str.toLowerCase().contains(productList[i])) {
				found = true;
				System.out.printf("Great, you would like to review %s! Please leave your review now and I will submit it.\n",productList[i]);
				str = in.nextLine();
				boolean notfound = true;
				for(int j = 0; j < userReviews.length; j++) {
					if(userReviews[j][0] == null && notfound) {
						notfound = false;
						userReviews[j][0] = productList[i];
						userReviews[j][1] = str;
					}
				}
				System.out.println("Thank you for your review. Can I help you with anything else?");
			} else if(i == productList.length-1 && !found)
				System.out.println("Sorry, we can't seem to find a product that matches your query. Can I help you with anything else?");
		}
	}
	
	public static void seeReviews() {//user views reviews
		in.nextLine(); //Clear input
		System.out.println("It seems that you are searching for product reviews. Can you tell me which product you are looking for?");
		String str = in.nextLine();
		boolean found = false;
		
		for(int i = 0; i < productList.length; i++) { //Try and find product to review
			if(str.toLowerCase().contains(productList[i])) {
				found = true;
				System.out.printf("Here are the reviews for %s:\n",productList[i]);
				System.out.printf("I think that %s was a great product! 5 stars.\n", productList[i]);
				System.out.printf("Bought %s two days ago and already recieved it! Missing 1 screw but they offered to send me the missing part with no extra charge. 4 stars.\n", productList[i]);
				System.out.printf("I think that %s was a great product! 5 stars.\n", productList[i]);
				System.out.printf("That is the end of the reviews.\n\n");
			} else if(i == productList.length-1 && !found)
				System.out.printf("Sorry, we can't seem to find a product that matches your query. Can I help you with anything else?\n\n");
		}
	}
	
	public static void editReview() {//user can edit reviews
		System.out.println("You would like to edit your review. Please select the review number you would like to edit.");
		if(userReviews[0][0] == null) {
			System.out.printf("Sorry, it seems that you haven't left any reviews.\n\n");
			return;
		}
		int i;
		for(i = 1; i < userReviews.length && userReviews[i-1][0] != null; i++) {
			System.out.printf("%d. %s: %s\n",i, userReviews[i-1][0], userReviews[i-1][1]);
		}
		System.out.printf("%d. Go back to the previous menu.\n",i);
		int productNum = in.nextInt();
		in.nextLine(); //Clear input
		
		if(productNum < i && productNum > 0) {
			System.out.println("Please leave your *NEW* review now:");
			String review = in.nextLine();
			userReviews[productNum-1][1] = review;
		} 
		else if (productNum == i) {
			System.out.printf("You have selected to go back to the previous menu.\n\n");
			return;
		}
		else
			notValid();
	}
		
	public static void back() {
		System.out.printf("You will now be taken back to the previous menu.\n\n");
	}
	
	public static void populateReviews() {
		userReviews[0][0] = productList[0];
		userReviews[0][1] = "This chair is awesome!";
		userReviews[1][0] = productList[1];
		userReviews[1][1] = "Table works exactly as expected";
		userReviews[2][0] = productList[5];
		userReviews[2][1] = "I love lamp.";
	}
	
  
	  ///////////////////
	 //  Help Section //
	///////////////////
	static void help() {
		System.out.println("enter 0 at any time to exit.");
		System.out.println("What product do you need help with?");
		
		for(int i = 0; i < productList.length; i++) {
			System.out.print(productList[i] + ", ");
		}
		
		int product = -1;
		boolean badinput = true;
		in.nextLine();
		
		while(badinput) {//ensures valid input from user
			String userInput = in.nextLine();
			userInput = userInput.toLowerCase();
			for(int i = 0; i < productList.length; i++) {
				if(productList[i].contentEquals(userInput)) {
					product = i;
					badinput = false;
				}
			}
			if(userInput == "0") {
				System.exit(0);
			}
			else if(product == -1) {
				notValid();
			}
		}
		
		int userint = -1;
		while(userint < 0) {//determines the issue
			System.out.println("What do you need help with?");
			System.out.println("1. Assembly");
			System.out.println("2. Something Broke");
			System.out.println("3. I want to return it");
			System.out.println("9. return to main");
			System.out.println("0. Exit program");
			userint = in.nextInt();
	
			switch(userint) {
				case 0:
					System.exit(0);
					break;
				case 1:
					System.out.println("Here are the assembly instructions");
					assembly(product);
					break;
				case 2:
					System.out.println("Bring the purchase along with receipt to your nearest Happy Home Furnishings store and we will attempt to fix or replace the piece");
					break;
				case 3:
					System.out.println("Bring the purchase along with receipt to your nearest Happy Home Furnishings store and if the furniture is in good condition we will refund you");
					break;
				case 9:
					break;
				default:
					notValid();
					userint = -1;
					break;
			}

		}
	}
	
	static void assembly(int product) {
		String[] instructions = {"Take each of the legs and screw them into the holes in the bottom",
				"Screw the sides of the dresser to the back part, then do the top, then screw the rails inside and insert the drawers",
				"Screw the legs into the bottom of the object, then insert the drawer",
				"screw each of the legs into the bottom of the top table part",
				"take each leg and screw them into the flat sitting part, then screw the back into the holes on the top side",
				"take each of the two cushions and place one on the left half of the sofa and one on the right half",
				"For each of the three cushions that come with your sofa place one on the right side, one on the left side, and one in between the other two",
				"Attach to the bottom each of the four side parts, then attach the top part, then screw the four legs into the bottom part"};
		System.out.println(instructions[product]);
	}

	  /////////////////////////////
	 //  Supplemental Functions //
	/////////////////////////////
	static int generateShippingCode() {
		return (int)(Math.random()*100000000);	
	}
	static int generateOrderId() {
		return (int) (Math.random() * 1000000);
	}
	static void validateOrder() {
		System.out.println("To confirm your order, please enter your product ID and credit card information");
		System.out.println("Credit Card Number: ");
		creditCardNumber = in.nextInt();
		System.out.println("Product ID: ");
		pID = in.nextInt();
	}

	  ////////////
	 // Moving //
	////////////
	static void moving() {
		System.out.println("Welcome to the moving station, here you can get help moving your furniture");
		//important variables
		boolean moreFurniture = true;
		int[] personalFurn = new int[productList.length];
		int product = -1;
		String userInput;
		//clears the input for strings
		in.nextLine();
		//select the furniture you need moved and the amount you need to move
		while(moreFurniture) {
			//displays the entire product list
			System.out.println("what furniture do you need moved from the following list?");
			for(int i = 0; i < productList.length; i++) {
				System.out.print(productList[i] + " ");
			}
			//select which item your picking
			userInput = in.nextLine();
			for(int i = 0; i < productList.length; i++) {
				if(productList[i].contentEquals(userInput)) {
					product = i;
				}
			}
			//checks the validity of the input
			if(product == -1) {
				System.out.println("Sorry you may have misspelled something");
			}
			else {
				System.out.println("How many peices of this do you need to move");
				int amount = in.nextInt();
				personalFurn[product] = amount;
				System.out.println("Currently you need to move: ");
				//displays all selected furniture that needs to be moved
				for(int i = 0; i < productList.length; i++) {
					if(personalFurn[i] != 0) {
						System.out.println(personalFurn[i] + " " + productList[i] );
					}
				}
			}
			//select if you want to add more furniture to move or not
			System.out.println("enter y if you need to have anything else moved: ");
			in.nextLine();
			product = -1;
			userInput = in.nextLine();
			if(!userInput.contentEquals("y")) {
				moreFurniture = false;
			}
		}
		//enter what address you wish the furniture to be moved to
		System.out.println("To what address do you need this furniture moved?");
		userInput = in.nextLine();
		System.out.println("Thank you for choosing Happy Home Furnishings");
		
		
	}

	  ////////////////////////
	  //  Invalid Responses //
	  ////////////////////////
	static void outsideResponseMenu() {
		System.out.println("I'm sorry, that isn't an available option. Please try again.");
		chooseCategory();
	}
	static void notValid() {
		int n = (int) (Math.random()*4);
		switch(n) {
		case 0: 
			System.out.println("I don't think that's a valid reponse. Please try again.");
			break;
		case 1:
			System.out.println("I didn't understand that, could you try again?");
			break;
		case 2:
			System.out.println("I think there may have been a typo, please try again.");
			break;
		case 3:
			System.out.println("I'm sorry, that isn't an available option. Please try again.");
			break;
		case 4:
			System.out.println("Please try again, that was not a valid entry.");
			break;
		default:
			System.out.println("I'm sorry, I didn't understand that. Could you try again?");
			break;
			
		}
	}
}

