package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedNodeIterator<T> implements Iterator<T> {
	private Node<T> nodeOne;

	public LinkedNodeIterator(Node<T> top) {
		nodeOne = top;
	}

	@Override
	public boolean hasNext() { 
		// TODO Auto-generated method stub
		if(nodeOne != null){
			return true;	
		}
		return false;
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		if(!hasNext()){
			  throw new NoSuchElementException();
		  }else {
			  T nodeTwo = nodeOne.getData();
			  nodeOne = nodeOne.getNext();
			  return nodeTwo;
		  }
	}
	public void remove(){
		throw new UnsupportedOperationException();
	}

}
