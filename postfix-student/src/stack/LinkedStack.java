package stack;
import stack.LLNode;
/**
 * A {@link LinkedStack} is a stack that is implemented using a Linked List structure
 * to allow for unbounded size.
 *
 * @param <T> the elements stored in the stack
 */
public class LinkedStack<T> implements StackInterface<T> {
	
	LLNode<T> top;
	int length=0;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T pop() throws StackUnderflowException {
    
		LLNode<T> remove;
		if(!isEmpty())
		{
			remove=top;
			remove.setData(top.getData());
			top=top.getNext();
			length--;
		}
		else
			throw new StackUnderflowException("Push attempted to empty stack");
		
	   // if(top!=null)
		return remove.getData();
	    //else
	    //	return null;
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T top() throws StackUnderflowException {
    // TODO
		if(!isEmpty())
		{
			return top.getData();
		}
		else
			throw new StackUnderflowException("top attempted on empty stack");
		
     //return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
    // TODO
		  if(top==null)
			  return true;
		  else
			  return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
    // TODO
    return length;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void push(T elem) {
		
		LLNode <T> newnode = new LLNode<T>(elem);
		newnode.setNext(top);
		top= newnode;
		length++;
	}

}
