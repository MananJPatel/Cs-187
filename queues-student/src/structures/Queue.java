package structures;

import java.util.NoSuchElementException;

public class Queue<T> implements UnboundedQueueInterface<T> {
	
	Node<T> first, last;
	int size = 0;

	public Queue() {		
            // TODO 1
    }
	
	public Queue(Queue<T> other) {
            // TODO 2
		if (other == null) {
			return;
		}
		Node<T> currentPosition = other.first;
		
		while (currentPosition != null){
			this.enqueue(currentPosition.getData());
			currentPosition = currentPosition.getNext();
		}
	}
	
	@Override
	public boolean isEmpty() {
            // TODO 3
            return first == null && last == null;
	}

	@Override
	public int size() {
            // TODO 4
            return size;
	}

	@Override
	public void enqueue(T element) {
            // TODO 5
		if (element == null) {
			return;
		}
		
		if (first == null && last == null){
			last = new Node<T>(element);
			first = last;
		} 
		else {
			last.setNext(new Node<T>(element));
			last = last.getNext();
		}
		size++;

	}

	@Override
	public T dequeue() throws NoSuchElementException {
            // TODO 6
            if (first == null && last == null) {
    			throw new NoSuchElementException();
            }
            
    		else {
    			size--;
    			T t = first.getData();
    			first = first.getNext();
    			if (first == null) {
    				last = null;
    			}
    			return t;
    		}
	}

	@Override
	public T peek() throws NoSuchElementException {
            // TODO 7
		if (first == null && last == null) {
			throw new NoSuchElementException();
		}
		return first.getData();

	}

	@Override
	public UnboundedQueueInterface<T> reversed() {
            // TODO 8
		LinkedStack<T> s = new LinkedStack<T>();
		Node<T> currentPosition = first;
		while (currentPosition != null){
			s.push(currentPosition.getData());
			currentPosition = currentPosition.getNext();
		}
		Queue<T> t = new Queue<T>();
		while (!s.isEmpty()) t.enqueue(s.pop());
		return t;
	}
}
