import java.io.PrintStream;
import java.util.*;



public class SortedLinkedListMultiset<T> extends Multiset<T>
{
	protected Node mHead;
	protected int mLength;
	public SortedLinkedListMultiset() {
		mHead = null;
		mLength = 0;
	}
		// Implement me!
	 // end of SortedLinkedListMultiset()
	
	
	public void add(T item) {
		Node newNode = new Node((String) item);
		Node currNode = mHead;
		Node prevNode = null;
		if(mHead == null){
			mHead = newNode;
			mLength++;
			return;
		}
		
		while(currNode != null) {
			//checking if it belongs in position
			if(currNode.getValue().compareTo((String)item) > 0) {
				break;
			}
			else {
				prevNode = currNode;
				currNode = currNode.getNext();
			}
		}
		//if it equals the same as head node
		if(currNode ==  mHead) {
			newNode.setNext(mHead);
			mHead = newNode;
		}
		else {
			prevNode.setNext(newNode);
			newNode.setNext(currNode);
		}
		//adding up length
		mLength++;
	} // end of add()
	
	
	public int search(T item) {
		Node currNode = mHead;
		
		for(int i = 0; i < mLength; ++i){
			if(currNode.getValue().equals((String) item)) {
				return currNode.getNumber();
			}
			currNode = currNode.getNext();
		}
		
		
		// default return, please override when you implement this method
		return 0;
	} // end of add()
	
	
	public void removeOne(T item) {
		Node currNode = mHead;
		Node prevNode = currNode;
		
		//looping to end
		while(currNode != null) {
			//if node matches search
			if(currNode.getValue().equals(item)) {
				//if position is at head!
				if(currNode == mHead) {
					mHead = mHead.getNext();
				}
				else {
					prevNode.setNext(currNode.getNext());
				}
				mLength--;
				break;
			}
			prevNode = currNode;
			currNode = currNode.getNext();
		}
	} // end of removeOne()
	
	
	public void removeAll(T item) {
		Node currNode = mHead;
		Node prevNode = null;
		int counter = 0;
		
		//going through whole list
		while(currNode != null) {
			//if item matches
			if(currNode.getValue().equals((String)item)){
				counter++;
				//if it is the head node
				if(mHead.equals(currNode)) {
					//move list down one
					mHead = mHead.getNext();
				}
				else {
					//else, remove position from list
					prevNode.setNext(currNode.getNext());
				}
				currNode = currNode.getNext();
				mLength--;
			}
			else if(counter != 0) {
				break;
			}
			else {
				prevNode = currNode;
				currNode = currNode.getNext();
			}
		}
	}
	 // end of removeAll()
	
	
	public void print(PrintStream out) {
		Node currNode = mHead;
		Node prevNode = currNode;
		int counter = 0;
		for(int x = 0; x < mLength; x++){
			//Looping to end of the line from position to check for copies
			if(checkCopies(prevNode)){
				counter = 0;
				currNode = mHead;
				while(currNode != null){
					if(counter != 0){
						break;
					}
					else if(currNode.getValue().equals(prevNode.getValue())){
						counter++;
					}
					currNode = currNode.getNext();
				}
				out.println(prevNode.getValue() + printDelim + counter);
				prevNode = prevNode.getNext();
			}
			else{
				prevNode = prevNode.getNext();
			}
		}
	} // end of print()
	
	private boolean checkCopies(Node currNode){
		Node focus = mHead;
		while(currNode != focus){
			if(currNode.getValue().equals(focus.getValue())){
				return false;
			}
			focus = focus.getNext();
		}
		return true;
	}
	private class Node {
		protected String Nvalue;
		protected Node Nnext;
		int number;
		
		public Node(String value){
			Nvalue = value;
			Nnext = null;
			number =1 ;
		}
		
		 public int getNumber() {
	            return number;
	        }

	        public String getValue() {
	            return Nvalue;
	        }

	        public Node getNext() {
	            return Nnext;
	        }

	        public void setNext(Node next) {
	            this.Nnext = next;
	        }
	        
	        public void NumberAdd() {
	            number++;
	        }

	        public void NumberSubtract() {
	            number--;
	        }
	}
	
} // end of class SortedLinkedListMultiset