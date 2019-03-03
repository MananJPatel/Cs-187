package structures;

import comparators.IntegerComparator;

import java.util.Comparator;
import java.util.Iterator;

public class MinQueue<V> implements PriorityQueue<Integer, V> {
	
	IntegerComparator IC = new IntegerComparator();
	StudentArrayHeap<Integer,V> hMethod = new StudentArrayHeap<Integer,V>(IC);

	@Override
	public PriorityQueue<Integer, V> enqueue(Integer priority, V value) {
		// TODO Auto-generated method stub
		hMethod.add(priority, value);
		return this;
	}

	@Override
	public V dequeue() {
		// TODO Auto-generated method stub
		return hMethod.remove();
	}

	@Override
	public V peek() {
		// TODO Auto-generated method stub
		return hMethod.peek();
	}

	@Override
	public Iterator<Entry<Integer, V>> iterator() {
		// TODO Auto-generated method stub
		return hMethod.iterator();
	}

	@Override
	public Comparator<Integer> getComparator() {
		// TODO Auto-generated method stub
		return IC;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return hMethod.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return hMethod.isEmpty();

	}
}

