
package extension;

//Change this path to your appropriate maven project path
import group1.nick.coreNLP.SentimentAnalysis;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import javax.swing.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;


public class Bot extends JFrame {
	//Setting up chat arrayList
	public static ArrayList<String> history = new ArrayList<String>();
	public ArrayList<String> path = new ArrayList<String>();
	public int questionNumber = 0;
	
	//Review Section attributes
	static String[] productList = {"chair", "table", "couch", "love seat", "night stand", "lamp", "wardrobe", "stool", "kitchen appliance"};
	static String[][] userReviews = new String[100][2];
	String firstAnswer="", secondAnswer="", thirdAnswer="";
	int reviewNum;
	String name = null;
	String item = null;
	
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
			botAddNoName(newText.substring(65, newText.length())); 
			
		}else {
			history.add("Bot: " + newText + "\n");
		}
		
	}
	public void botAddNoName(String newText) {
		if(newText.length() > 65) {
			history.add("Bot: " + newText.substring(0, 65) + "\n");
			botAddNoName(newText.substring(65, newText.length())); 
			
		}else {
			history.add("        " + newText + "\n");
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
			questionNumber++;
			productSatisfaction();
		}
		//Complaint Check
		else if(category.equals("2") || category.toLowerCase().equals("") ||  category.toLowerCase().equals("")) {
			questionNumber++;
			try {
				complaints();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//Reviews
		else if(category.equals("3") || category.toLowerCase().equals("") ||  category.toLowerCase().equals("")) {
			questionNumber++;
			reviews();
		}
		//Order Status
		else if(category.equals("4") || category.toLowerCase().equals("") ||  category.toLowerCase().equals("")) {
			
		}
		//Help
		else if(category.equals("5") || category.toLowerCase().equals("") ||  category.toLowerCase().equals("")) {
			help();
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
	void complaints() throws IOException, ClassNotFoundException  {
		if(path.size() ==1) {
			botAdd("Please include your name and item you had issues with in your complaint below.");
		}
		else if(path.size() ==2) {
			String text = path.get(1);
			MaxentTagger tagger =new MaxentTagger("taggers/left3words-wsj-0-18.tagger");
			List<String> nn = new ArrayList<String>();
			String tagged = tagger.tagString(text);
			String[] words = tagged.split(" ");
			for(int x = 0;x < words.length; x++ ) {
				if(words[x].contains("NN"))
					nn.add(words[x]);
			}
			for(int x = 0; x < nn.size();x++) {
				if(nn.get(x).contains("NNP")) {
					name = nn.get(x).substring(0, nn.get(x).indexOf("/"));
					break;
				}	
			}
			for(int x = 0;x < productList.length; x++ ) {
				for(int y = 0;y<nn.size();y++) {
					String temp = nn.get(y).substring(0,nn.get(y).indexOf("/"));
					if(temp.equals(productList[x]))
						item = temp;
				}
			}
			if(item != null)
				botAdd("Is this the item you had a complaint about? "+ item);
			else {
				botAdd("Please enter the category for your item complaint.");
				botAddNoName("chair, table, couch, love seat, night stand, lamp, wardrobe, stool, kitchen appliance");
			}
				
		}
		else if (path.size()==3) {
			String text = path.get(2);
			if(text.toLowerCase().equals("yes")) {
				botAdd("Thank you " + name + " for your feedback about the " + item +". We have noted your issue and will try and fix the situation.");
				botAddNoName("Is there anything else we can help you with?");
			
			}
			else if(text.toLowerCase().equals("no")) {
				botAdd("Please enter the category for your item complaint.");
				botAddNoName("chair, table, couch, love seat, night stand, lamp, wardrobe, stool, kitchen appliance");
			}
			else {
				item = text;
				botAdd("Thank you " + name + " for your feedback about the" + item +". We have noted your issue and will try and fix the situation.");
				botAddNoName("Is there anything else we can help you with?");
			}
			
		}
		else {
			String text = path.get(3);
			if (text.toLowerCase().equals("no")) {
				getResponse("exit");
			}
			else {
				botAdd("Thank you " + name + " for your feedback about the " + text +". We have noted your issue and will try and fix the situation.");
				getResponse("exit");
			}
			
		}
	}
	
	
	  /////////////////////
	 //  Review Section //
	/////////////////////
	void reviews() {
		if(path.size()==1) {
			botAdd("You have reached the Reviews section. Please select one from the following menu: ");
			botAddNoName("1. Leave a review");
			botAddNoName("2. See the reviews of a product");
			botAddNoName("3. Edit one of your existing reviews");
			botAddNoName("4. Go back to the previous menu.");
			botAdd("Please select an option from the choices above");
			return;
		}else if(path.size()==2) {
			
			firstAnswer = path.get(1);
			
			//Leave Review
			if(firstAnswer.equals("1") || firstAnswer.toLowerCase().equals("leave a review") ||  firstAnswer.toLowerCase().equals("leave review")) {
				botAdd("It seems that you want to leave a review. Can you tell me which product you would like to review?");
				return;
			}
			
			//See Reviews
			else if(firstAnswer.equals("2") || firstAnswer.toLowerCase().equals("see the reviews of a product") ||  firstAnswer.toLowerCase().equals("see")) {		
				botAdd("It seems that you are searching for product reviews. Can you tell me which product you are looking for?");
				return;
			}
			
			//Edit Review
			else if(firstAnswer.equals("3") || firstAnswer.toLowerCase().equals("edit one of your existing reviews") ||  firstAnswer.toLowerCase().equals("edit")) {
				botAdd("You would like to edit your review. Please select the review number you would like to edit.");
				
				if(userReviews[0][0] == null) {
					botAdd("Sorry, it seems that you haven't left any reviews.\n\n");
					return;
				}
				int i;
				for(i = 1; i < userReviews.length && userReviews[i-1][0] != null; i++) {
					botAdd(String.format("%d. %s: %s\n",i, userReviews[i-1][0], userReviews[i-1][1]));
				}
				botAdd(String.format("%d. Go back to the main menu.",i));
				return;
			}
			
			//Back
			else if(firstAnswer.equals("4") || firstAnswer.toLowerCase().equals("go back to the previous menu") ||  firstAnswer.toLowerCase().equals("back")) {
				back();
			}
			
			//Other (Please enter a valid command)
			else{
				botAdd("Please Enter Valid Input");
				//notValid();
				path.remove(path.size()-1);
			}
		}else if(path.size()==3) {
			secondAnswer = path.get(2);
			//Leave Review
			if(firstAnswer.equals("1") || firstAnswer.toLowerCase().equals("leave a review") ||  firstAnswer.toLowerCase().equals("leave review")) {
				//Find product to review
				boolean found = false;
				for(int i = 0; i < productList.length && found == false; i++) { //Try and find product to review
					if(secondAnswer.toLowerCase().contains(productList[i])) {
						found = true;
						botAdd(String.format("Great, you would like to review %s! Please leave your review now and I will submit it.\n",productList[i]));
					} else if(i == productList.length-1 && !found) {
						botAdd("Sorry, we can't seem to find a product that matches your query. Can I help you with anything else?");
						back();
					}
				}
				return;
			}
				
			//See Reviews
			else if(firstAnswer.equals("2") || firstAnswer.toLowerCase().equals("see the reviews of a product") ||  firstAnswer.toLowerCase().equals("see")) {			
				
				
				boolean found = false;
				
				for(int i = 0; i < productList.length; i++) { //Try and find product to review
					if(secondAnswer.toLowerCase().contains(productList[i])) {
						found = true;
						botAdd(String.format("Here are the reviews for %s:\n",productList[i]));
						botAddNoName(String.format("I think that %s was a great product! 5 stars.", productList[i]));
						botAddNoName(String.format("Bought %s two days ago and already recieved it! Missing 1 screw but they offered to send me the missing part with no extra charge. 4 stars.", productList[i]));
						botAddNoName(String.format("I think that %s was a great product! 5 stars.", productList[i]));
						botAddNoName("That is the end of the reviews.\n\n");
						back();
					} else if(i == productList.length-1 && !found)
						System.out.printf("Sorry, we can't seem to find a product that matches your query. Can I help you with anything else?\n\n");
				}
				return;
			}
			
			//Edit Review
			else if(firstAnswer.equals("3") || firstAnswer.toLowerCase().equals("edit one of your existing reviews") ||  firstAnswer.toLowerCase().equals("edit")) {
				//secondAnswer;
				//for()
				reviewNum = Integer.parseInt(secondAnswer);
				if(reviewNum < (userReviews.length+1) && reviewNum > 0) {
					botAdd("Please leave your *NEW* review now:");
					return;
				} 
				else if (reviewNum == (userReviews.length+1)) {
					botAdd("You have selected to go back to the main menu.\n\n");
					back();
				}
				else {
					botAdd("You made an invalid selection.");
					return;
				}
			}
		}else if(path.size()==4) {
			thirdAnswer = path.get(3);
			
			//Leave Review
			if(firstAnswer.equals("1") || firstAnswer.toLowerCase().equals("leave a review") ||  firstAnswer.toLowerCase().equals("leave review")) {
				//Find userReviews
				boolean notfound = true;
				for(int i = 0; i < userReviews.length; i++) {
					if(userReviews[i][0] == null && notfound) {
						notfound = false;
						int sentiment = SentimentAnalysis.computeSentiment(thirdAnswer);
						botAdd(String.format("Based on my Stanford algorithm, I have determined that your review will be rated as %d stars!\n\n",(sentiment+1)));
						if(sentiment < 3)
							botAdd("It seems that you were not happy with this product. Please consider navigating to our complaints section so we can try to help.");
						//I really love the chair that I bought. I think that it has been a wonderful addition to my house!
						String review = "[" + (sentiment+1) + " stars] " + thirdAnswer;
						userReviews[i][0] = productList[i];
						userReviews[i][1] = review;
					}
				}
				botAdd("Thank you for your review. Can I help you with anything else?");
				back();
			}
			
			//Edit Review
			else if(firstAnswer.equals("3") || firstAnswer.toLowerCase().equals("edit one of your existing reviews") ||  firstAnswer.toLowerCase().equals("edit")) {
				userReviews[reviewNum-1][1] = thirdAnswer;
			}
		}
	}

	void back() {
		path.clear(); //clear path
		initializeChat();//Repeat main menu
		return;
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
	
	public void help() {
		/////////FIRST QUESTION/////////
		if(path.size()==1) {
			botAdd("What product do you need help with?");
			for(int i = 0; i<productList.length; i++) {
				botAddNoName("\t" + i +": "+ productList[i]);
			}
			botAddNoName("\t 0. Exit");
			botAddNoName("\t 9. Return to main menu");
			botAdd("Please select an option from the choices above");
			return;
		}
		
		///////SECOND QUESTION/////////
		
		if(path.size()==2) {
			botAdd("What do you need help with?");
			botAddNoName("\t 1. Assmebly");
			botAddNoName("\t 2. Something Broke");
			botAddNoName("\t 3. I want to return it");
			botAddNoName("\t 0. Exit");
			botAddNoName("\t 9. Return to main menu");
			botAdd("Please select an option from the choices above");
			return;
		}else if(path.size()==3){
			
			
			String secondAnswer = path.get(2);
			
			//String helpProduct = path.get();
			
			//Send back
			if(secondAnswer.equals("1") || secondAnswer.toLowerCase().equals("assembly")) {
				botAdd("Here are the assembly instructions");
				botAddNoName(assembly(1));
				path.clear();
				return;
			}
			//Send refund
			else if(secondAnswer.equals("2") || secondAnswer.contains("broke") ||  secondAnswer.toLowerCase().equals("something")) {
				botAdd("Bring the purchase along with receipt to your nearest Happy Home Furnishings store and we will attempt to fix or replace the piece");
				path.clear();
				return;
			}
			//Leave a review
			else if(secondAnswer.equals("3") || secondAnswer.toLowerCase().contains("return")) {
				botAdd("Bring the purchase along with receipt to your nearest Happy Home Furnishings store and if the furniture is in good condition we will refund you");
				path.clear();
				return;
			}
			//Other (Please enter a valid command
			else{
				notValid();
				path.remove(path.size()-1);
			}
		}
	}
	
	

	  /////////////////////////////
	 //  Supplemental Functions //
	/////////////////////////////
	static int generateShippingCode() {
		return (int)(Math.random()*100000000);	
	}
	static String assembly(int product) {
		String instructions = "Take each of the legs and screw them into the holes in the bottom. Screw the sides of the dresser to the back part, then do the top, then screw the rails inside and insert the drawers. Screw the legs into the bottom of the object, then insert the drawer. Screw each of the legs into the bottom of the top table part. take each leg and screw them into the flat sitting part, then screw the back into the holes on the top side take each of the two cushions and place one on the left half of the sofa and one on the right half For each of the three cushions that come with your sofa place one on the right side, one on the left side, and one in between the other two Attach to the bottom each of the four side parts, then attach the top part, then screw the four legs into the bottom part";
		return instructions;
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
