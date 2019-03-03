package structures;

public class DoublyCircularLinkedListImplementation<T extends Comparable<T>> implements
		DoublyCicularLinkedList<T> {

	protected DLLNode<T> first;
	protected DLLNode<T> last;
	protected DLLNode<T> atpoint;
	protected DLLNode<T> atpoint1;
	protected int size;	
	protected boolean hasfound;
	
	public DoublyCircularLinkedListImplementation() {
            // TODO
		first = null;
		last = null;
		size = 0;
		atpoint = null;
		atpoint1 = null;
	}
	
	//additional methods start
	private boolean isEmpty() {
		return (first == null);
	}
	
	protected void find(T t)
	{
	      atpoint = first;
	      hasfound = false;
	      if(!isEmpty()){ 	  
	    	  do { 
	    		  
	    		  if (atpoint.getInfo().equals(t)) {  
	    			  hasfound = true;
	    			  return;
	    		  }
	    		  
	    		  else {
	    			  atpoint = atpoint.getForward();
	    		  }
	    		  
	          	}
	    	  	while(atpoint != last.getForward());
	      	}
	   }

	//additional methods end
	
	@Override
	public int size() {
            // TODO
            return size;
	}
	
	@Override
	public void add(T element) {
            // TODO
		DLLNode<T> node = new DLLNode<T>(element);  
	   	 if (isEmpty()) {
	   		 first = node;
	   	     last = node;   	    
	   	     first.setBack(last);
	   	     last.setForward(first);	   	 
	   	 }
	   	 else {
	   		 last.setForward(node);
	   		 node.setBack(last);
	   	     last = node;
	   	     last.setForward(first);
	   	 }
	   	 size++;
	}

	@Override
	public boolean remove(T element) {
            // TODO
		find(element);
	      if (hasfound){ 
	    	if(atpoint == first && size() == 1) {
	    		first = null;
	    		last = null;
	    		
	    	}
	    	else if (atpoint == first) {   		
	    		first = first.getForward(); 
	    		first.setBack(last);
	            last.setForward(first);    
	        }
	    	
	    	else if(atpoint == last) {
	        	last = last.getBack();
	            last.setForward(first);
	            first.setBack(last); 
	        }
	    	
	        else{						 
	        	atpoint.getBack().setForward(atpoint.getForward());  
	        	atpoint.getForward().setBack(atpoint.getBack());  	
	        }
	    	
	        size--;
	      }
	      return hasfound;     
	}

	
	@Override
	public boolean contains(T element) {
            // TODO
		 find(element);
		 return hasfound;
	}

	@Override
	public T get(T element) {
            // TODO
		find(element);    
	    if (hasfound)
	      return atpoint.getInfo();
	    else
	      return null;
		}
            

	@Override
	public void reset() {
            // TODO
		atpoint = first;
		atpoint1 = last;
	}

	@Override
	public T getNext() {
            // TODO
		T next = atpoint.getInfo();
		atpoint=atpoint.getForward();
            return next;
	}

	@Override
	public T getPrevious() {
            // TODO
		T previous = atpoint1.getInfo();
		atpoint1=atpoint1.getBack();
		
		
            return previous;
	}
}
