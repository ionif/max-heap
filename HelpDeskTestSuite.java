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

public class HelpDeskTestSuite extends HelpDesk{
	//unused constructor
	public HelpDeskTestSuite(int size) {
		super(size);
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		//tests
		testCompareSupportTicket();
		testToString();
		testCreateTicket();
		testCheckTicket();
		testCloseTicket();
		testIndexHelpers();
		testSwap();
		testPropagateUp();
		testPropagateDown();
	}
	/**
	 * test if comparing support tickets works
	 * @return true if tests pass
	 */
	public static boolean testCompareSupportTicket() {
		int passed = 2;
		//Test 1
		//basic A before B
		SupportTicket a = new SupportTicket("AAA");
		SupportTicket b = new SupportTicket("BBB");
		if (a.compareTo(b) != -1) {
			System.out.println("FAILED: testCompareSupportTicket Test 1. Expected -1, but value returned " + a.compareTo(b));
			passed--;
			return false;
		}
		//Test 2
		//test length
		SupportTicket c = new SupportTicket("This message is long.");
		SupportTicket d = new SupportTicket("This one short.");
		if (c.compareTo(d) != 1) {
			System.out.println("FAILED: testCompareSupportTicket Test 2. Expected 1, but value returned " + c.compareTo(d));
			passed--;
			return false;
		}
		System.out.println("testCompareSupportTicket(): Passed " + passed + " of 2 tests.");
		return true;
	}

	/**
	 * test SupportTicket toString
	 * @return true if tests pass
	 */
	public static boolean testToString() {
		int passed = 1;
		SupportTicket a = new SupportTicket("AAA");
		if (!a.toString().equals("AAA")) {
			System.out.println("FAILED: testToString() Test 1. Expected false, but value returned " + !a.toString().equals("AAA"));
			passed--;
			return false;
		}
		System.out.println("testToString(): Passed " + passed + " of 1 tests.");
		return true;
	}

	/**
	 * test creating the ticket
	 * @return true if tests pass
	 */
	public static boolean testCreateTicket() {
		int passed = 2;
		//create helpdesk
		HelpDesk helpdesk = new HelpDesk(3);
		//Test 1
		//test createNewTicket
		helpdesk.createNewTicket("f");
		if (helpdesk.size != 1) {
			System.out.println("FAILED: testCreateTicket() Test 1. Expected false, but value returned " + !helpdesk.checkNextTicket().equals("f"));
			passed--;
			return false;
		}
		//Test 2
		helpdesk.createNewTicket("g");
		helpdesk.createNewTicket("gggg");
		if (helpdesk.size != 3) {
			System.out.println("FAILED: testCreateTicket() Test 2. Expected size to be 2, but value returned " + helpdesk.size);
			passed--;
			return false;
		}
		System.out.println("testCreateTicket(): Passed " + passed + " of 2 tests.");
		return true;
	}

	/**
	 * test checking the ticket with multiple operations
	 * @return true if tests pass
	 */
	public static boolean testCheckTicket() {
		int passed = 2;
		//create helpdesk
		HelpDesk helpdesk = new HelpDesk(3);
		//Test 1
		//test createNewTicket and checkNextTicket
		helpdesk.createNewTicket("f");
		if (!helpdesk.checkNextTicket().equals("f")) {
			System.out.println("FAILED: testCreateCheckClose() Test 1. Expected ticket to be f, but value returned " + helpdesk.checkNextTicket());
			passed--;
			return false;
		}
		//Test 2
		//test to see if it sorts the elements
		helpdesk.createNewTicket("g");
		helpdesk.createNewTicket("ZZZ");
		if (!helpdesk.checkNextTicket().equals("ZZZ")) {
			System.out.println("FAILED: testCreateCheckClose() Test 3. Expected root to be ZZZ, but value returned " + helpdesk.checkNextTicket());
			passed--;
			return false;
		}
		System.out.println("testCheckTicket(): Passed " + passed + " of 2 tests.");
		return true;
	}

	/**
	 * test removing the tickets
	 * @return true if tests pass
	 */
	public static boolean testCloseTicket() {
		int passed = 2;
		//create helpdesk
		HelpDesk helpdesk = new HelpDesk(2);
		//Test 1
		helpdesk.createNewTicket("f");
		helpdesk.closeNextTicket();
		if (helpdesk.size != 0) {
			System.out.println("FAILED: testCloseTicket() Test 1. Expected size to be 0, but value returned " + helpdesk.size);
			passed--;
			return false;
		}
		//Test 2
		//test to see if it sorts the elements
		helpdesk.createNewTicket("g");
		helpdesk.createNewTicket("ZZZ");
		helpdesk.closeNextTicket();
		if (!helpdesk.checkNextTicket().equals("g")) {
			System.out.println("FAILED: testCloseTicket() Test 2. Expected root to be g, but value returned " + helpdesk.checkNextTicket());
			passed--;
			return false;
		}
		System.out.println("testCloseTicket(): Passed " + passed + " of 2 tests.");
		return true;
	}

	/**
	 * test index helper methods:
	 * parentOf, leftChildOf, rightChildOf
	 * @return true if tests pass
	 */
	public static boolean testIndexHelpers() {
		int passed = 6;
		//test leftchild
		if (HelpDesk.leftChildOf(0) != 1) {
			System.out.println("FAILED: testIndexHelpers() Test 1. Expected left child to be 1, but value returned " + HelpDesk.leftChildOf(0));
			passed--;
			return false;
		}
		//test 2 of left child
		if (HelpDesk.leftChildOf(1) != 3) {
			System.out.println("FAILED: testIndexHelpers() Test 2. Expected left child to be 3, but value returned " + HelpDesk.leftChildOf(1));
			passed--;
			return false;
		}
		//test right child
		if (HelpDesk.rightChildOf(0) != 2) {
			System.out.println("FAILED: testIndexHelpers() Test 3. Expected right child to be 2, but value returned " + HelpDesk.rightChildOf(0));
			passed--;
			return false;
		}
		//test 2 of right child
		if (HelpDesk.rightChildOf(1) != 4) {
			System.out.println("FAILED: testIndexHelpers() Test 4. Expected right child to be 4, but value returned " + HelpDesk.rightChildOf(1));
			passed--;
			return false;
		}
		//test leftchild of parentOf
		if (HelpDesk.parentOf(1) != 0) {
			System.out.println("FAILED: testIndexHelpers() Test 5. Expected parent of 1 to be 0, but value returned " + HelpDesk.parentOf(0));
			passed--;
			return false;
		}
		//test rightchild of parentOf
		if (HelpDesk.parentOf(2) != 0) {
			System.out.println("FAILED: testIndexHelpers() Test 6. Expected parent of 2 to be 0, but value returned " + HelpDesk.parentOf(2));
			passed--;
			return false;
		}
		System.out.println("testIndexHelpers(): Passed " + passed + " of 6 tests.");
		return true;
	}

	/**
	 * test if swapping elements in the heap works
	 * @return true if test passes
	 */
	public static boolean testSwap() {
		int passed = 1;
		//create helpdesk
		HelpDesk helpdesk = new HelpDesk(2);
		//Test 1
		helpdesk.createNewTicket("ticketA");
		helpdesk.createNewTicket("ticketB");
		helpdesk.swap(0, 1);
		if (!helpdesk.checkNextTicket().equals("ticketA")) {
			System.out.println("FAILED: testSwap() Test 1. Expected root to be ticketA, but value returned " + helpdesk.checkNextTicket());
			passed--;
			return false;
		}
		System.out.println("testSwap(): Passed " + passed + " of 1 tests.");
		return true;
	}

	/**
	 * test if propagating up from an index sorts the heap correctly
	 * @return true if test passes
	 */
	public static boolean testPropagateUp() {
		int passed = 2;
		//create helpdesk
		HelpDesk helpdesk = new HelpDesk(3);
		//Test 1
		//test root
		helpdesk.createNewTicket("A");
		helpdesk.createNewTicket("B");
		helpdesk.createNewTicket("ZZ");
		helpdesk.propagateUp(2);
		if (!helpdesk.checkNextTicket().equals("ZZ")) {
			System.out.println("FAILED: testSwap() Test 1. Expected root to be ZZ, but value returned " + helpdesk.checkNextTicket());
			passed--;
			return false;
		}
		//Test 2
		//test second greatest after root
		helpdesk.closeNextTicket();
		if (!helpdesk.checkNextTicket().equals("B")) {
			System.out.println("FAILED: testSwap() Test 2. Expected root to be B, but value returned " + helpdesk.checkNextTicket());
			passed--;
			return false;
		}
		System.out.println("testPropagateUp(): Passed " + passed + " of 2 tests.");
		return true;
	}

	/**
	 * test if propagating down from an index sorts correctly
	 * @return true if test passes
	 */
	public static boolean testPropagateDown() {
		int passed = 3;
		//create helpdesk
		HelpDesk helpdesk = new HelpDesk(6);
		//Test 1
		helpdesk.createNewTicket("A");
		helpdesk.createNewTicket("B");
		helpdesk.createNewTicket("C");
		helpdesk.createNewTicket("D");
		helpdesk.createNewTicket("E");
		helpdesk.createNewTicket("F");
		helpdesk.propagateDown(0);
		if (!helpdesk.checkNextTicket().equals("F")) {
			System.out.println("FAILED: testPropagateDown() Test 1. Expected root to be F, but value returned " + helpdesk.checkNextTicket());
			passed--;
			return false;
		}
		//test 2
		//test second greatest index
		helpdesk.closeNextTicket();
		if (!helpdesk.checkNextTicket().equals("E")) {
			System.out.println("FAILED: testPropagateDown() Test 2. Expected root to be E, but value returned " + helpdesk.checkNextTicket());
			passed--;
			return false;
		}
		//test 3
		//test third greatest index
		helpdesk.closeNextTicket();
		if (!helpdesk.checkNextTicket().equals("D")) {
			System.out.println("FAILED: testPropagateDown() Test 3. Expected root to be D, but value returned " + helpdesk.checkNextTicket());
			passed--;
			return false;
		}
		System.out.println("testPropagateDown(): Passed " + passed + " of 3 tests.");
		return true;
	}

}
