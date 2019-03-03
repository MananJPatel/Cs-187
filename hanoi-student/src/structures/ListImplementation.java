package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An {@code ListImplementation} is a Linked List that wraps {@link Node}s and
 * provides useful operations.
 * 
 * @author jcollard
 * 
 */
public class ListImplementation<T> implements ListInterface<T> {
	private Node<T> top;
	private int size = 0;

	
	public ListImplementation() {
	}

	/**
	 * Returns the number of nodes in this list.
	 */
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
        return size == 0;
	}

	/**
	 * Appends {@code elem} to the end of this {@code ListImplementation} and
	 * returns itself for convenience.
	 */
	@Override
	public ListImplementation<T> append(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		else {
			top = new Node<T>(elem,top);
			size++;
		}
                return this;
	}

	/**
	 * Gets the {@code n}th element from this list.
	 */
	@Override
	public T get(int n) {
		if(n > size-1 || n < 0) {
			throw new NoSuchElementException();
		}
		else {
			int count = size;
			Node<T> current = top;
			while(count > n+1) {
				current = current.getNext();
				count--;
			}
			return current.getData(); 
		}
	}

	/**
	 * Returns an iterator over this list. The iterator does not support the
	 * {@code remove()} method.
	 */
	@Override
	public Iterator<T> iterator() {
		
        return new ListIterator();
	}
	
	class ListIterator implements Iterator<T>
	{
		int length=size;
		int count=0;
	
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if( length > 0 && count < length) {
				return true;
			}
			else{
				return false;
			}
		}
		

		@Override
		public T next() {
			// TODO Auto-generated method stub
			
			if(count<length)
			{
				  T elem=get(count);
				  count++;
				  return elem;
			}
			
			throw new NoSuchElementException();
		}
		
	}
	
}

