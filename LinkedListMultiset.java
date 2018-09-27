import java.io.PrintStream;
import java.util.*;


public class LinkedListMultiset<T> extends Multiset<T>
{
	
	protected Node mHead;
	protected int mLength;
	
	public LinkedListMultiset() {
		// Implement me!
		mHead = null;
		mLength = 0;
		
	} // end of LinkedListMultiset()
	
	
	public void add(T item) {
		// Implement me!
		
		 Node newNode = new Node((String) item);
		 
		 //if the list is empty then the new node is now the list head.
	        if (mHead == null) 
	        {
	        	mHead = newNode;
	        }
	        
	        
	     //otherwise we set the current node as the list head, and the previous node as a null.   
	        else 
	        {
	            Node currNode = mHead;
	            Node prevNode = null;
	   
	     // we then cycle through the list until the current node is null, so we may add our new node at the end of list.
	            for (int i = 0; i < mLength; ++i) {
	            	
	            	// if the node we wish to enter is already present in the list then we add 1 to the counter of that node.
	                if (currNode.getValue().equals(newNode.getValue())) 
	                {
	                    currNode.NumberAdd();
	                    return;
	                }
	                
	                //set the previous node to the current and the current to the next(to cycle through the list).
	                prevNode = currNode;
	                currNode = currNode.getNext();
	            }
	            
	            //add the new node to the end of the list
	            prevNode.setNext(newNode);
	        }
	      
	        //increase the length of the list by 1.
	        mLength++;
		
	} // end of add()
	
	
	public int search(T item) 
	{
		// Implement me!		
		 Node currNode = mHead;

		 //cycle through nodes in the list again
		 for (int i = 0; i < mLength; ++i) {
	        
	        	
	        	//if the value of the search node exists in the list, then return that nodes ctr value(how many times that item is in the list)
	            if (currNode.getValue().equals((String) item)) 
	            {
	                return currNode.getNumber();
	            }
	            
	            currNode = currNode.getNext();
	        }

	        return 0;
		
		// default return, please override when you implement this method
		
	} // end of add()
	
	
	public void removeOne(T item)
	{
		// Implement me!
		 Node currNode = mHead;
	     Node prevNode = null;
	     
	     	//cycle through list again until we reach
	     for (int i = 0; i < mLength; ++i) {
	        
	        	
	        	//      to see if current node's value equals the value of the item to remove
	            if (currNode.getValue().equals((String) item)) 
	            {
	            	
	            	
	                currNode.NumberSubtract();
	                
	                //check and see if all of that particular item is over, if so then the node has to be removed from list
	                if (currNode.getNumber() == 0) 
	                {
	                
	                	
	                	//if that node was at the head of the list then set the head as the next node in the list
	                    if (currNode == mHead)
	                    {
	                    	mHead = currNode.getNext();
	                    }
	                    	
	                    //if the node was in the middle of the list then set the previous nodes next as the current nodes next.
	                   	else
	                   	{
	                        prevNode.setNext
	                                (currNode.getNext());
	                   	} 
	                    
	                    
	                    //decrease the length of the list by one.
	                    mLength--;
	                    
	                }
	                
	                return;
	            }
	            
	            prevNode = currNode;
	            currNode = currNode.getNext();
	        }
	} // end of removeOne()
	
	
	public void removeAll(T item) 
	{
		
		 Node currNode = mHead;
	     Node prevNode = null;
	     	
	     
	     //cycle through the list
	     for (int i = 0; i < mLength; ++i) {
	        	
	        	//if the value of the current node is the value of the item to be completely removed
	            if (currNode.getValue().equals((String) item)) 
	            {
	            	
	            	//if the node is at head of list then set head as the next node in list
	                if (currNode == mHead)
	                {
	                    
	                	mHead = currNode.getNext();
	                	
	                }
	                
	                //if the node is in the middle of the list, set the previous node's next as the current node's next.
	                else
	                {
	                	
	                    prevNode.setNext(currNode.getNext());
	                    
	                }
	              
	                //reduce list length by one.
	                mLength--;
	                
	                return;
	           
	            }
	           
	            prevNode = currNode;
	            currNode = currNode.getNext();
	        
	        }
	} // end of removeAll()
	
	
	public void print(PrintStream out) {
		
		  Node currNode = mHead;

		  //Cycle through list printing all the nodes and their counter values.
		  for (int i = 0; i < mLength; ++i) {
	            System.out.printf("%s | %d\n", currNode.getValue(), currNode.getNumber());
	            currNode = currNode.getNext();
	        }
		
	} // end of print()
	
	private class Node {
       
		 int number;
		
		protected String Nvalue;
       
        protected Node Nnext;

       

        public Node(String value) {
            Nvalue = value;
            Nnext = null;
            number = 1;
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
            Nnext = next;
        }
        
        public void NumberAdd() {
            number++;
        }

        public void NumberSubtract() {
            number--;
        }

    }
	
} // end of class LinkedListMultiset