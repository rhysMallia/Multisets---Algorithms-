import java.io.PrintStream;
import java.util.*;



public class BstMultiset<T> extends Multiset<T>
{
	/* Setting variables for tree */
	protected Node rootNode;
	
	/* Setting default state of BST */
	public BstMultiset() {
		rootNode = null;
	} // end of BstMultiset()

	public void add(T item) {
		Node focus = rootNode;
		Node previous = null;
		Node subject = new Node(item);
		Boolean check = false;
		if(rootNode == null) {
			rootNode = subject;
			rootNode.add(1);
			return;
		}
		//Beginning of loop to go through the binary tree
		while(!check){
			previous = focus;
			//if the current node is the same as item, increase count
			if((focus.getKeyS()).compareTo((String)item) == 0) {
				focus.add(1);
				check = true;
				break;
			}
			//if smaller, continue left!
			else if((focus.getKeyS().compareTo((String)item) > 0)) {
				focus = focus.getLeftChild();
				//if the node has no children
				if(focus == null) {
					previous.setLeftChild(subject);
					previous.getLeftChild().add(1);
					check = true;
				}
			}
			//if larger, continue right!
			else if(focus.getKeyS().compareTo((String)item) < 0) {
				focus = focus.getRightChild();
				if(focus == null) {
					previous.setRightChild(subject);
					previous.getRightChild().add(1);
					check = true;
				}
			}
		}

		return;
	} // end of add()

	public int search(T item) {
		Node focus = rootNode;
		int counter = 0;
		if(rootNode == null) {
			return 0;
		}
		//while the current node is valid
		while(focus != null) {
			// if equal, return value!
			if(focus.getKeyS().compareTo((String) item) == 0) {
				counter = focus.getCount();
				break;
			}
			//if smaller, go left!
			else if(focus.getKeyS().compareTo((String) item) > 0) {
				//continues leftward
				focus = focus.getLeftChild();
			}
			//if larger, go right!
			else{
				//continues rightward
				focus = focus.getRightChild();
			}
		}
		if(focus == null){
			return 0;
		}
		return counter;
	} // end of add()
	

	public void removeOne(T item) {
		Node focus = rootNode;
		Node previous = null;
		Node current = null;
		Boolean check = false;
		Boolean found = false;
		//if rootnode is empty
		if(rootNode == null){
			return;
		}
		
		while(!check){
			previous = focus;
			//if less, go left
			if(focus.getKeyS().compareTo((String) item) > 0){
				focus = focus.getLeftChild();
				//breaking
				if(focus == null){
					check = true;
					break;
				}
				//if found, set marker to true
				else if(focus.getKeyS().compareTo((String)item) == 0){
					found = true;
					break;
				}
			}
			else if(focus.getKeyS().compareTo((String)item) == 0){
				found = true;
				break;
			}
			//if more, go right!
			else{
				focus = focus.getRightChild();
				if(focus == null) {
					check = true;
					break;
				}
				else if(focus.getKeyS().compareTo((String)item) == 0) {
					found = true;
					break;
				}
			}
		}
		
		if(found) {
			if(focus.getCount() > 1){
				focus.remove(1);
			}
			else{
				//if node has left children
				if(focus.hasNextLeft() && !focus.hasNextRight()) {
					//if the focus is root
					if(focus == rootNode) {
						rootNode = rootNode.getLeftChild();
					}
					else if(previous.getLeftChild() == focus) {
						previous.setLeftChild(focus.getLeftChild());
					}
					else if(previous.getRightChild() == focus) {
						previous.setRightChild(focus.getLeftChild());
					}
				}
				//if node has right children
				else if(!focus.hasNextLeft() && focus.hasNextRight()) {
					if(focus == rootNode) {
						rootNode = rootNode.getRightChild();
					}
					else if(previous.getRightChild() == focus) {
						previous.setRightChild(focus.getRightChild());
					}
					else if(previous.getRightChild() == focus) {
						previous.setLeftChild(focus.getRightChild());
					}
				}
				//if node has no children
				else if(!focus.hasNext()) {
					if(focus == rootNode) {
						rootNode = null;
					}
					else if(previous.getLeftChild() == focus) {
						previous.setLeftChild(null);
					}
					else if(previous.getRightChild() == focus) {
						previous.setRightChild(null);
					}
				}
				//if node has both children!
				else {
					current = focus;
					focus = focus.getRightChild();
					while(focus.hasNextLeft()) {
						focus = focus.getLeftChild();
					}
					
					//if root
					if(rootNode == current) {
						focus.setLeftChild(rootNode.getLeftChild());
						focus.setRightChild(rootNode.getRightChild());
						rootNode = focus;
					}
					else if(previous.getLeftChild() == current) {
						focus.setLeftChild(current.getLeftChild());
						focus.setRightChild(current.getRightChild());
						previous.setLeftChild(focus);
					}
					else {
						focus.setLeftChild(current.getLeftChild());
						focus.setRightChild(current.getRightChild());
						previous.setRightChild(focus);
					}
				}
			}
		}
	} // end of removeOne()
	
	public void removeAll(T item) {
		Node focus = rootNode;
		Node previous = null;
		Node current = null;
		Boolean check = false;
		Boolean found = false;
		//if rootnode is empty
		if(rootNode == null){
			return;
		}
		
		while(!check){
			previous = focus;
			//if less, go left
			if(focus.getKeyS().compareTo((String) item) > 0){
				focus = focus.getLeftChild();
				//breaking
				if(focus == null){
					check = true;
					break;
				}
				//if found, set marker to true
				else if(focus.getKeyS().compareTo((String)item) == 0){
					found = true;
					break;
				}
			}
			else if(focus.getKeyS().compareTo((String)item) == 0){
				found = true;
				break;
			}
			//if more, go right!
			else{
				focus = focus.getRightChild();
				if(focus == null) {
					check = true;
					break;
				}
				else if(focus.getKeyS().compareTo((String)item) == 0) {
					found = true;
					break;
				}
			}
		}
		
		if(found) {
				//if node has left children
				if(focus.hasNextLeft() && !focus.hasNextRight()) {
					//if the focus is root
					if(focus == rootNode) {
						rootNode = rootNode.getLeftChild();
					}
					else if(previous.getLeftChild() == focus) {
						previous.setLeftChild(focus.getLeftChild());
					}
					else if(previous.getRightChild() == focus) {
						previous.setRightChild(focus.getLeftChild());
					}
				}
				//if node has right children
				else if(!focus.hasNextLeft() && focus.hasNextRight()) {
					if(focus == rootNode) {
						rootNode = rootNode.getRightChild();
					}
					else if(previous.getRightChild() == focus) {
						previous.setRightChild(focus.getRightChild());
					}
					else if(previous.getRightChild() == focus) {
						previous.setLeftChild(focus.getRightChild());
					}
				}
				//if node has no children
				else if(!focus.hasNext()) {
					if(focus == rootNode) {
						rootNode = null;
					}
					else if(previous.getLeftChild() == focus) {
						previous.setLeftChild(null);
					}
					else if(previous.getRightChild() == focus) {
						previous.setRightChild(null);
					}
				}
				//if node has both children!
				else {
					current = focus;
					focus = focus.getRightChild();
					while(focus.hasNextLeft()) {
						focus = focus.getLeftChild();
					}
					
					//if root
					if(rootNode == current) {
						focus.setLeftChild(rootNode.getLeftChild());
						focus.setRightChild(rootNode.getRightChild());
						rootNode = focus;
					}
					else if(previous.getLeftChild() == current) {
						focus.setLeftChild(current.getLeftChild());
						focus.setRightChild(current.getRightChild());
						previous.setLeftChild(focus);
					}
					else {
						focus.setLeftChild(current.getLeftChild());
						focus.setRightChild(current.getRightChild());
						previous.setRightChild(focus);
					}
				}
		}
	} // end of removeAll()
	
	//recursive loop to go through every node from root!
	public void print(PrintStream out) 
	{
		Node focus = rootNode;
		printLoop(focus, out);
	}
	
	
	// self calling loop to print through each node inorder
	public void printLoop(Node focus, PrintStream out)
	{
		int counter = 0;
		if(focus != null){
			// Number of occurrence of the current node
			counter += focus.getCount();
			//prints to the PrintStream!
			out.println(focus.getKeyS() + " | " + counter);
			// prints out the left child first
			printLoop(focus.getLeftChild(), out);
			// prints out he right child
			printLoop(focus.getRightChild(), out);
			
		}
}
	//Creating the node class for BTS
	public class Node{
	// setting values for node
		private T keyValue;
		private int count;
		private Node leftChild;
		private Node rightChild;
	
		Node(T item){
			this.keyValue = item;
			count = 0;
			leftChild = null;
			rightChild = null;
		}
		//getters and setters
		public T getKey(){
			return keyValue;
		}
		public String getKeyS() {
			return (String)keyValue;
		}
		public void setKey(T item) {
			this.keyValue = item;
		}
		public Node getLeftChild() {
			return leftChild;
		}
		public void setLeftChild(Node item) {
			this.leftChild = item;
		}
		public Node getRightChild() {
			return rightChild;
		}
		public void setRightChild(Node item) {
			this.rightChild = item;
		}
		
		public boolean hasNext() {
			if(this.getLeftChild() != null || this.getRightChild() != null) {
				return true;
			}
			return false;
		}
		
		public boolean hasNextLeft() {
			if(this.getLeftChild() != null) {
				return true;
			}
			return false;
		}
		
		public boolean hasNextRight() {
			if(this.getRightChild() != null) {
				return true;
			}
			return false;
		}
		
		public void add(int amount) {
			this.count += amount;
			return;
		}
		
		public void remove(int amount) {
			this.count -= amount;
		}
		public int getCount() {
			return count;
		}
	}
}
