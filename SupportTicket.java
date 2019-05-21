//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P10 Help Desk
// Files:   SupportTicket.java, HelpDesk.java, HelpDeskInterface.java, 
// HelpDeskTestSuite.java
// Course:   CS300       
//
// Author: Alex Ionkov       
// Email:  ionkov@wisc.edu
// Lecturer's Name: Gary Dahl
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

public class SupportTicket implements Comparable<SupportTicket>{
	private final String message; //support ticket message

	/**
	 * Constructor for Support Ticket
	 * @param message
	 * @throws NullPointerException if message is null
	 */
	public SupportTicket(String message) {
		if (message != null && !message.isEmpty()) {
			this.message = message;
		}
		else {
			throw new NullPointerException("Error: null message when trying to create SupportTicket.");
		}
	}
	/**
	 * compare support tickets together first by length and then by lexography
	 * return -1 if this is smaller, 1 if this is bigger
	 */
	@Override
	public int compareTo(SupportTicket o) {
		//if this length is greater return 1
		if (this.message.length() > o.message.length()) {
			return 1;
		}
		//if this length is lower return -1
		else if (this.message.length() < o.message.length()) {
			return -1;
		}
		//check if messages are the same
		else {
			return this.message.compareTo(o.message);
		}
	}

	// Returns a String representation of this DictionaryWord.
	public String toString() { 
		return this.message;
	}

}
