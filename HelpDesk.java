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

public class HelpDesk implements HelpDeskInterface {
	protected SupportTicket[] array; // zero-indexed max-heap
	protected int size;

	public HelpDesk(int size) {
		this.array = new SupportTicket[size];
		this.size = 0;
	}
	/**
	 * Creates and adds a new SupportTicket to this HelpDesk.
	 * @param message names the client and describes their need for support.
	 * @throws NullPointerException when the String message argument is null.
	 * @throws IndexOutOfBoundsException when called on HelpDesk with a full array
	 */
	@Override
	public void createNewTicket(String message) {
		if (message == null || message.isEmpty()) {
			throw new NullPointerException("Error: null message when trying to create SupportTicket.");
		}
		if (this.size == array.length) {
			throw new IndexOutOfBoundsException("Error: HelpDesk is full.");
		}
		//index at which to add new ticket
		int currentIndex = this.size;
		//create new ticket
		array[currentIndex] = new SupportTicket(message);
		this.size++;
		//re-sort heap
		propagateUp(currentIndex);
	}

	/**
	 * Returns the message within this HelpDesk that has the highest priority.
	 * This method does not change the state of this HelpDesk.
	 * @return the message within the highest priority SupportTicket.
	 * @throws IllegalStateException when called on a HelpDesk with zero SupportTickets.
	 */
	@Override
	public String checkNextTicket() {
		if (this.size == 0) {
			throw new IllegalStateException("Error: HelpDesk has zero SupportTickets.");
		}
		//return root message
		return array[0].toString();
	}

	/**
	 * Returns and removes the message within this HelpDesk that has the highest priority.
	 * @return the message within the highest priority SupportTicket (prior to its removal).
	 * @throws IllegalStateException when called on a HelpDesk with zero SupportTickets.
	 */
	@Override
	public String closeNextTicket() {
		if (this.size == 0) {
			throw new IllegalStateException("Error: HelpDesk has zero SupportTickets.");
		}
		//get the message of the root
		String messageOfRemoved = array[0].toString();
		//swap the root and the last item
		swap(0, this.size-1);
		//set last item to null
		array[this.size - 1] = null;
		//reduce size
		this.size--;
		//begin propagating down
		propagateDown(0);
		//return message of former root
		return messageOfRemoved;
	}

	/**
	 * Given an index into the heap array, this method returns that index's parent index.
	 * @param index
	 * @return parent index
	 */
	protected static int parentOf(int index) {
		//parent index
		int pIndex = (index - 1)/2;
		return pIndex;
	}

	/**
	 * Given an index into the heap array, this method returns that index's left child index.
	 * @param index
	 * @return left child index
	 */
	protected static int leftChildOf(int index) {
		//left index
		int lIndex = 2*index + 1;
		return lIndex;
	}

	/**
	 * Given an index into the heap array, this method returns that index's right child index.
	 * @param index
	 * @return right child index
	 */
	protected static int rightChildOf(int index) {
		//right index
		int rIndex = 2*index + 2;
		return rIndex;
	}

	/**
	 * Given two indexes into the heap array, this method swaps the SupportTickets at those indexes.
	 * @param indexA
	 * @param indexB
	 */
	protected void swap(int indexA, int indexB) {
		//assign indexA to temp variable and then reassign indexA to B and B to tmp
		SupportTicket tmp = array[indexA];
		array[indexA] = array[indexB];
		array[indexB] = tmp;
	}

	/**
	 * Given an index into the heap array, this method recursively swaps any SupportTickets 
	 * necessary to enforce the heap's order property between this index and the heap's root.
	 * @param index
	 */
	protected void propagateUp(int index) {
		//check if parent index is greater than or equal to zero
		if (parentOf(index) >= 0) {
			if (array[index].compareTo(array[parentOf(index)]) > 0) {
				//swap the greater child with the parent
				swap(index, parentOf(index));
				//call recursively
				propagateUp(parentOf(index));
			}
		}
	}

	/**
	 * Given an index into the heap array, this method recursively swaps any SupportTickets 
	 * necessary to enforce the heap's order property between this index and it's children.
	 * @param index
	 */
	protected void propagateDown(int index) {
		int leftChild = leftChildOf(index);
		int rightChild = rightChildOf(index);
		//check if left child exists
		if (leftChild < this.size) {
			//check if right child exists
			if (rightChild < this.size) {
				//if both exist, compare them to find the max 
				if (array[leftChild].compareTo(array[rightChild]) > 0) {
					//then compare the max to the original index
					if (array[index].compareTo(array[leftChild]) < 0) {
						swap(index, leftChild);
						propagateDown(leftChild);
					}
				}
				else {
					//else the max is the rightChild and we swap the root with it
					if (array[index].compareTo(array[rightChild]) < 0) {
						swap(index, rightChild);
						propagateDown(rightChild);
					}
				}
			}
			//right child does not exist so we just compare the left child
			else {
				if (array[index].compareTo(array[leftChild]) < 0) {
					swap(index, leftChild);
					propagateDown(leftChild);
				}
			}
		}
	}
}
