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
	
	
	public void userAdd(String newText) {
		history.add("You-> " + newText +"\n");
	}
	
	public void botAdd(String newText) {
		history.add("Bot: " + newText + "\n");
	}
	public void botAddNoName(String newText) {
		history.add("     " + newText + "\n");
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
		
		//load data into chat History
		initializeChat();
		history.forEach((n) -> chatArea.append(n));
		
		textInput.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String newEntry = textInput.getText();
				userAdd(newEntry);
				getResponse(newEntry);
				resetTextBoxes();
				if(history.size()>37) {
					System.out.println(history.size()-37);
					for(int i = 0; i <= history.size()-38; i++) {
						history.remove(0);
					}
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
			
		}
		//Other (Please enter a valid command
		else{
			notValid();
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
				notValid();
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
				notValid();
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
	
	
	

	  /////////////////////////////
	 //  Supplemental Functions //
	/////////////////////////////
	static int generateShippingCode() {
		return (int)(Math.random()*100000000);	
	}
	
	////////////////////////
	//  Invalid Responses //
	////////////////////////
	
	void notValid() {
		int n = (int) (Math.random()*5);
		switch(n) {
		case 0: 
			botAdd("I don't think that's a valid reponse. Please try again.");
			break;
		case 1:
			botAdd("I didn't understand that, could you try again?");
			break;
		case 2:
			botAdd("I think there may have been a typo, please try again.");
			break;
		case 3:
			botAdd("I'm sorry, that isn't an available option. Please try again.");
			break;
		case 4:
			botAdd("Please try again, that was not a valid entry.");
			break;
		default:
			botAdd("I'm sorry, I didn't understand that. Could you try again?");
			break;
			
		}
	}
}