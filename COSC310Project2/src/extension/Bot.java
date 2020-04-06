package extension;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;


public class Bot extends JFrame {
	//Setting up chat arrayList
	public static ArrayList<String> history = new ArrayList<String>();
	public ArrayList<String> path = new ArrayList<String>();
	public int questionNumber = 0;
	static String[] productList = {"chair", "table", "couch", "love seat", "night stand", "lamp", "wardrobe", "stool", "kitchen appliance"};

	
	public void userAdd(String newText) {
		if(newText.length() > 65) {
			history.add("Bot: " + newText.substring(0, 65) + "\n");
			userAdd(newText.substring(65, newText.length()));
			
		}else {
			history.add("You -> " + newText + "\n");
		}
	}
	
	public void botAdd(String newText) {
		if(newText.length() > 65) {
			history.add("Bot: " + newText.substring(0, 65) + "\n");
			botAdd(newText.substring(65, newText.length())); 
			
		}else {
			history.add("Bot: " + newText + "\n");
		}
		
	}
	public void botAddNoName(String newText) {
		if(newText.length() > 65) {
			history.add("Bot: " + newText.substring(0, 65) + "\n");
			botAddNoName(newText.substring(65, newText.length())); 
			
		}else {
			history.add("     " + newText + "\n");
		}; 
	}
	
	void initializeChat(){
		botAdd("Welcome to the Happy Home Furnishings Call Center!");
		botAdd("To communicate enter text in the text box below and press enter.");
		botAdd("What can I help you with?");
		botAddNoName("\t 1. Product Satisfaction");
		botAddNoName("\t 2. Complaints");
		botAddNoName("\t 3. Reviews");
		botAddNoName("\t 4. Order Status");
		botAddNoName("\t 5. Help");
		botAddNoName("\t 6. Moving");
		botAddNoName("\t 0. Exit");//If they enter zero the program will exit
		botAdd("Please enter a number from the menu above: ");
	}
	
	//Setting up GUI	
	private JLabel chatLabel = new JLabel("Chat Area");
	private JLabel inputTextLabel = new JLabel("Enter Text");
	private JTextArea chatArea = new JTextArea();
	private JTextField textInput = new JTextField();
	
	public Bot() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Happy Home Furnishings Call Center");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setSize(400, 705);
		
		//Chat label area
		frame.add(chatLabel);
		chatLabel.setBounds(4,4,100,10);  
		
		//Chat text area
		frame.add(chatArea);
		chatArea.setSize(386, 610);
		chatArea.setLocation(4,20);
		
		//Chat label area
		frame.add(inputTextLabel);
		inputTextLabel.setBounds(4,650,100,10);  
		
		//Input text area
		frame.add(textInput);
		textInput.setSize(310, 25);
		textInput.setLocation(80, 645);
		
		//load datainto chatHistory
		initializeChat();
		history.forEach((n) -> chatArea.append(n));
		
		textInput.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String newEntry = textInput.getText();
				userAdd(newEntry);
				getResponse(newEntry);
				resetTextBoxes();
				while(history.size()>38) {
					history.remove(0);
				}
				history.forEach((n) -> chatArea.append(n)); 
				
			}
		});
		
		
	}
	void resetTextBoxes() {
		chatArea.setText("");
		textInput.setText("");
	}
	
	
	public static void main(String[]args) {
		new Bot();
	}

	public void getResponse(String newEntry) {
		//Checking for exit or return to main menu
		if(newEntry.equals("0") || newEntry.toLowerCase().equals("exit") ||  newEntry.toLowerCase().equals("quit")) {
			botAdd("Hopefully we were able to help!");
			botAdd("Have a nice day!");
			System.exit(0);//Exit
		}
		//Return to main menu
		else if(newEntry.equals("9") || newEntry.toLowerCase().equals("return to main menu") ||  newEntry.toLowerCase().equals("return") ||  newEntry.toLowerCase().equals("main menu")) {
			path.clear(); //clear path
			initializeChat();//Repeat main menu
			return;
		}
		
		//Adding new entry to path
		path.add(newEntry);
		
		String category = path.get(0);
		
		//Determine each category
		//Product Satisfaction
		if(category.equals("1") || category.toLowerCase().equals("product satisfaction") ||  category.toLowerCase().equals("satisfaction")) {
			questionNumber ++;
			productSatisfaction();
		}
		//Complaint Check
		else if(category.equals("2") || category.toLowerCase().equals("") ||  category.toLowerCase().equals("")) {
			
		}
		//Reviews
		else if(category.equals("3") || category.toLowerCase().equals("") ||  category.toLowerCase().equals("")) {
			
		}
		//Order Status
		else if(category.equals("4") || category.toLowerCase().equals("") ||  category.toLowerCase().equals("")) {
			
		}
		//Help
		else if(category.equals("5") || category.toLowerCase().equals("") ||  category.toLowerCase().equals("")) {
			help();
		}
		//Moving
		else if(category.equals("6") || category.toLowerCase().equals("") ||  category.toLowerCase().equals("")) {
			moving();
		}
		//Other (Please enter a valid command
		else{
			botAdd("Please Enter Valid Input");
			path.remove(newEntry);
		}
			
		
		
	}
	  ///////////////////////////////////
	 //  Product Satisfaction Section //
	///////////////////////////////////
	void productSatisfaction() {
		
		/////////FIRST QUESTION/////////
		if(path.size()==1) {
			botAdd("Product Satisfaction");
			botAdd("What seems to be the issue?");
			botAddNoName("\t 1. Product Defect");
			botAddNoName("\t 2. Missing Parts");
			botAddNoName("\t 3. Not what you expected");
			botAddNoName("\t 0. Exit");
			botAddNoName("\t 9. Return to main menu");
			botAdd("Please select an option from the choices above");
			return;
		}else if(path.size()==2){
		
			String firstAnswer = path.get(1);
			
			//Product Defect
			if(firstAnswer.equals("1") || firstAnswer.toLowerCase().equals("product defect") ||  firstAnswer.toLowerCase().equals("defect")) {
				botAdd("We are sorry to hear that you had a defect");
			}
			//Missing Parts
			else if(firstAnswer.equals("2") || firstAnswer.toLowerCase().equals("missing parts") ||  firstAnswer.toLowerCase().equals("missing")) {				
				botAdd("We are sorry to hear that you are missing a part");			
			}
			//Not what you expected
			else if(firstAnswer.equals("3") || firstAnswer.toLowerCase().equals("not what i expected") ||  firstAnswer.toLowerCase().equals("not expected")) {
				botAdd("We are sorry to hear that you recieved a part that is not what you expexted");
			}
			
			//Other (Please enter a valid command
			else{
				botAdd("Please Enter Valid Input");
				path.remove(path.size()-1);
			}
		
		}
	
		///////SECOND QUESTION/////////
		
		if(path.size()==2) {
			botAdd("At Happy Home Furnishings we guarantee that our customers are satisfied.");
			botAdd("What would you like to do?");
			botAddNoName("\t 1. Send back your order and recive a new item");
			botAddNoName("\t 2. Send you a refund");
			botAddNoName("\t 3. Be directed to review department");
			botAddNoName("\t 4. Be directed to complaints department");
			botAddNoName("\t 0. Exit");
			botAddNoName("\t 9. Return to main menu");
			botAdd("Please select an option from the choices above");
			return;
		}else if(path.size()==3){
			
			String secondAnswer = path.get(2);
			
			//Send back
			if(secondAnswer.equals("1") || secondAnswer.toLowerCase().equals("product defect") ||  secondAnswer.toLowerCase().equals("defect")) {
				botAdd("Please go to your local post office and give them this shipping code");
				botAdd("Your order will be returned for free");
				botAdd("Your shipping number is " + generateShippingCode());
				return;
			}
			//Send refund
			else if(secondAnswer.equals("2") || secondAnswer.toLowerCase().equals("missing parts") ||  secondAnswer.toLowerCase().equals("missing")) {
				botAdd("A refund will be sent to you credit card within the next 5");
				botAdd(" business days");
				return;
			}
			//Leave a review
			else if(secondAnswer.equals("3") || secondAnswer.toLowerCase().equals("not what i expected") ||  secondAnswer.toLowerCase().equals("not expected")) {
				/////////TODO
			}
			//Leave a complaint
			else if(secondAnswer.equals("4") || secondAnswer.toLowerCase().equals("exit") || secondAnswer.toLowerCase().equals("quit")) {
				////////TODO
			}
			//Other (Please enter a valid command
			else{
				botAdd("Please Enter Valid Input");
				path.remove(path.size()-1);
			}
		}
	}
	
	  /////////////////////////
	 //  Complaints Section //
	/////////////////////////
	
	
	
	
	
	  /////////////////////
	 //  Review Section //
	/////////////////////
	
	
	
	
	  ///////////////////
	 //  Help Section //
	///////////////////
	void help() {
		if(path.size()==1) {
		botAdd("enter 0 at any time to exit.");
		botAdd("What product do you need help with?");

		for(int i = 0; i < productList.length; i++) {
			botAdd(productList[i] + ", ");
		}
		return;
		} else if(path.size()==2) {
		int product = -1;
		boolean badinput = true;
		
		while(badinput) {//ensures valid input from user
			String userInput = path.get(path.size()-1);
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
				//notValid();
			}
		}
		
		int userint = -1;
		while(userint < 0) {//determines the issue
			botAdd("What do you need help with?");
			botAddNoName("1. Assembly");
			botAddNoName("2. Something Broke");
			botAddNoName("3. I want to return it");
			botAddNoName("9. return to main");
			botAddNoName("0. Exit program");
			userint = Integer.parseInt(path.get(path.size()-1));
	
			switch(userint) {
				case 0:
					System.exit(0);
					break;
				case 1:
					botAdd("Here are the assembly instructions");
					assembly(product);
					break;
				case 2:
					botAdd("Bring the purchase along with receipt to your nearest Happy Home Furnishings store and we will attempt to fix or replace the piece");
					break;
				case 3:
					botAdd("Bring the purchase along with receipt to your nearest Happy Home Furnishings store and if the furniture is in good condition we will refund you");
					break;
				case 9:
					break;
				default:
					//notValid();
					userint = -1;
					break;
			}

		}
		}
	}
	
	void assembly(int product) {
		String[] instructions = {"Take each of the legs and screw them into the holes in the bottom",
				"Screw the sides of the dresser to the back part, then do the top, then screw the rails inside and insert the drawers",
				"Screw the legs into the bottom of the object, then insert the drawer",
				"screw each of the legs into the bottom of the top table part",
				"take each leg and screw them into the flat sitting part, then screw the back into the holes on the top side",
				"take each of the two cushions and place one on the left half of the sofa and one on the right half",
				"For each of the three cushions that come with your sofa place one on the right side, one on the left side, and one in between the other two",
				"Attach to the bottom each of the four side parts, then attach the top part, then screw the four legs into the bottom part"};
		botAdd(instructions[product]);
	}
	  ////////////
	 // Moving //
	////////////
	void moving() {
		
		//important variables
		boolean moreFurniture = true;
		int[] personalFurn = new int[productList.length];
		int product = -1;
		String userInput;
		botAdd("Welcome to the moving section, here you can get help moving your furniture");
		if(path.size()==1) {
		//select the furniture you need moved and the amount you need to move
		path.remove(path.size()-1);
		while(moreFurniture) {
			//displays the entire product list
			botAdd("what furniture do you need moved from the following list?");
			for(int i = 0; i < productList.length; i++) {
				botAdd(productList[i] + " ");
			}
			//select which item your picking
			userInput = path.get(1);
			for(int i = 0; i < productList.length; i++) {
				if(productList[i].contentEquals(userInput)) {
					product = i;
				}
			}
		}} else if(path.size()==2) {
			//checks the validity of the input
			if(product == -1) {
				//notValid();
			}
			else {
				botAdd("How many peices of this do you need to move");
				int amount = Integer.parseInt(path.get(path.size()-1));
				personalFurn[product] = amount;
				botAdd("Currently you need to move: ");
				//displays all selected furniture that needs to be moved
				for(int i = 0; i < productList.length; i++) {
					if(personalFurn[i] != 0) {
						botAdd(personalFurn[i] + " " + productList[i] );
					}
				}
			}
			//select if you want to add more furniture to move or not
			botAdd("enter y if you need to have anything else moved: ");
			product = -1;
			userInput = path.get(path.size()-1);
			if(!userInput.contentEquals("y")) {
				moreFurniture = false;
			}
		}
		//enter what address you wish the furniture to be moved to
		botAdd("To what address do you need this furniture moved?");
		userInput = path.get(path.size()-1);
		botAdd("Thank you for choosing Happy Home Furnishings");
		
		
	}
	
	
	
	  /////////////////////////////
	 //  Supplemental Functions //
	/////////////////////////////
	
	
	
	

	  /////////////////////////////
	 //  Supplemental Functions //
	/////////////////////////////
	static int generateShippingCode() {
		return (int)(Math.random()*100000000);	
	}
}
