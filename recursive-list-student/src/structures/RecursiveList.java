package structures;

import java.util.Iterator;

public class RecursiveList<T> implements ListInterface<T>{
	
	protected int size;
	protected Node<T> linkedlist;
	
	public Iterator<T> iterator() {
		return new LinkedNodeIterator<T>(linkedlist);
	}

	public int size() {
		return size;
	}

	@Override
	public ListInterface<T> insertFirst(T elem) {
		// TODO Auto-generated method stub
		return insertAt(0, elem);
	}

	@Override
	public ListInterface<T> insertLast(T elem) {
		// TODO Auto-generated method stub
		return insertAt(size, elem);
	}

	@Override
	public ListInterface<T> insertAt(int index, T elem) {
		// TODO Auto-generated method stub
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (elem == null) {
			throw new NullPointerException();
		}
		Node<T> node = new Node<T>(elem);
		if (index == 0) {
			node.setNext(linkedlist);
			linkedlist = node;
		}
		else {
			insertHelper(linkedlist, index, node);		
		}
		size++;	
		return this;
	}

	@Override
	public T removeFirst() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			throw new IllegalStateException();
		}
		return removeAt(0);
	}

	@Override
	public T removeLast() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			throw new IllegalStateException();
		}
		return removeAt(size-1);
	}

	@Override
	public T removeAt(int i) {
		// TODO Auto-generated method stub
		if (i >= size || i < 0) {
			throw new IndexOutOfBoundsException();
		}
		T total;
		if (i == 0) {
			total = linkedlist.getData();
			linkedlist = linkedlist.getNext();
		}
		else {
			total = removeHelper(linkedlist,i);
		}
		size--;
		return total;
	}

	@Override
	public T getFirst() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			throw new IllegalStateException();
		}
		return get(0);
	}

	@Override
	public T getLast() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			throw new IllegalStateException();
		}
		return get(size-1);
	}

	@Override
	public T get(int i) {
		// TODO Auto-generated method stub
		if (i >= size || i < 0) {
			throw new IndexOutOfBoundsException();
		}
		return getHelper(linkedlist, i);
	}

	@Override
	public boolean remove(T elem) {
		// TODO Auto-generated method stub
		int index = indexOf(elem);
		if (index == -1) {
			return false;
		}
		removeAt(index);
		return true;
	}

	@Override
	public int indexOf(T elem) {
		if (elem == null) {
			throw new NullPointerException();
		}
		return indexOf(elem, linkedlist, 0);
	}
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return linkedlist == null;
	}
	
	// helper methods for insert remove and get
	
	private void insertHelper(Node<T> position,int index,Node<T> node) {
		if(index == 1 ) {
			node.setNext(position.getNext());
			position.setNext(node);
			}
		else {
			insertHelper(position.getNext(), index-1, node);
		}
	}
	
	private T removeHelper(Node<T> position, int index) {
		if (index == 1) {
			T elem = position.getNext().getData();
			position.setNext(position.getNext().getNext());
			return elem;
		}
		return removeHelper(position.getNext(), index-1);
	}
	
	private T getHelper(Node<T> position, int index) {
		if (index == 0) {
			return position.getData();
		}
		return getHelper(position.getNext(), index-1);
	}
	private int indexOf(T tf, Node<T> tc, int positionindex) {
		if (tc == null) return -1;
		if (tc.getData().equals(tf)) {
			return positionindex;
		}
		return indexOf(tf, tc.getNext(), positionindex+1);
		
	}
}
