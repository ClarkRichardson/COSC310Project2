COSC 310Project 2
Group 1:

Nicholas Gerritsen (42110149)
Jordan Kozakevich (13506150)
Ryan Pinkney (78458403)
Clark Richardson (46873155)
Mary Whitten (52484145)

Description
Our project was a call center agent that can handle several different types of issues and a few responses. The call agent directs the user towards important information that they request, like help with assembly, acquiring information on their shipments, or filing complaints and getting reviews. 

Features: 
GUI:
The gui was created using the javax.swing library. It allowed the GUI to be implemented using a text area and a text field. This allowed for a simple and intuitive interface. This interface allows the user to see the history of the conversation by adding all lines of text to be stored in an array list of strings. Lines are added to the interface untill the maximum number of lines that are able to be displayed. Once this point is reached the comments at the beginning of the list are deleted as new lines are added.  

Invalid Responses: 
In order to make the interactions with the bot feel more like conversation, the notValid() method was created. The purpose of this method is to randomly reply one of 6 possible reponses when a user types an input outside of the available inputs. This will make it feel more like conversation because the chat bot will not have the same repsonse everytime. For example:
Attempt #1:
Bot:  I'm very sorry to hear you've had a bad experience.
      Is there something we could do to fix the situation, yes or no?
User: maybe
Bot:  I'm sorry, that isn't an available option. Please try again.

Attempt #2:
Bot:  I'm very sorry to hear you've had a bad experience.
      Is there something we could do to fix the situation, yes or no?
User: bla
Bot:  I didn't understand that, could you try again?

Attempt #3:
Bot:  I'm very sorry to hear you've had a bad experience.
      Is there something we could do to fix the situation, yes or no?
User: possibly
Bot:  I think there may have been a typo, please try again.

Language Toolkits: 
Parts-of-Speech (Stanford CoreNLP)
This toolkit is used to identify names and products in a the complaint section. A customer writes their entire complaint
and the bot will identify the persons name and the item the are complaining about. 
Example snippet:
Bot:Please leave your name and item in your complaint below.
You -> My name is Jesse and I bought a chair last week. It has broken within two days.
Bot: Is this the item that your complaint was about? chair
You -> Yes
Bot: Thank you Jesse for the feedback about the chair. We have noted your issue and will try to fix the situation.

Sentiment Analysis (Stanford CoreNLP)
This toolkit is used to evaluate the tone of a review left by the user and appends a rating (out of 5) to the review.
Example snippet: 
Bot: It seems that you want to leave a review. Can you tell me which product you would like to review?
You -> table
Bot: Great, you would like to review table! Please leave your review now and I will submit it.
You -> I really love the table that I bought. I think that it has been a wonderful addition to my house!
Bot: Based on my Stanford algorithm, I have determined that your review will be rated as 5 stars!
*Review will be stored with [5 stars] appended to the user's input.

New Method:
A new method was added called moving. The user selects an amount of each of the furniture that they want moved and an address they want it to be moved to.
