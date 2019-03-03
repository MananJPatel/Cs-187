package structures;

import java.util.Comparator;
import java.util.Iterator;

public class StudentArrayHeap<P, V> extends AbstractArrayHeap<P, V> {

	protected StudentArrayHeap(Comparator<P> comparator) {
		super(comparator);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int getLeftChildOf(int index) {
		// TODO Auto-generated method stub
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		}
		int newindex = (index * 2) + 1;
		return newindex;
	}

	@Override
	protected int getRightChildOf(int index) {
		// TODO Auto-generated method stub
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		}
		int newindex = (index * 2) + 2;
		return newindex;
	}

	@Override
	protected int getParentOf(int index) {
		// TODO Auto-generated method stub
		if (index < 1) {
			throw new IndexOutOfBoundsException();
		}
		int newindex = (index - 1) / 2;
		return newindex;
	}

	@Override
	protected void bubbleUp(int index) {
		// TODO Auto-generated method stub
		int ivalue;
		Entry<P,V> somevalue;
		if(index == 0) {
			return;
		}
		
		while (comparator.compare(heap.get(this.getParentOf(index)).getPriority(),
				heap.get(index).getPriority()) < 0) {
			
			ivalue = this.getParentOf(index);
			somevalue = heap.get(ivalue);
			heap.set(ivalue, heap.get(index));
			heap.set(index,somevalue);
			index = ivalue;
			
			if(index == 0) {
				return;
			}
		}
	}

	@Override
	protected void bubbleDown(int index) {
		// TODO Auto-generated method stub
		int ivalue;
		Entry<P,V> somevalue;
		if(heap.size() <= 1) {
			return;
		}
		
		while(true) {
			if (getLeftChildOf(index) >= heap.size()) {
				return;
			}
			if (getRightChildOf(index) >= heap.size()) {
				if (comparator.compare(heap.get(this.getLeftChildOf(index)).getPriority(),
					heap.get(index).getPriority()) > 0) {
					ivalue = getLeftChildOf(index);
				} 
				else {
					return;
				}
			} 
			else if (comparator.compare(heap.get(this.getRightChildOf(index)).getPriority(), 
					heap.get(index).getPriority()) > 0 ||
					comparator.compare(heap.get(this.getLeftChildOf(index)).getPriority(),  
					heap.get(index).getPriority()) > 0) {
				
				if (comparator.compare( heap.get(this.getLeftChildOf(index)).getPriority(),  
					heap.get(this.getRightChildOf(index)).getPriority()) >= 0) {
					ivalue = this.getLeftChildOf(index);
				} 
				else {
					ivalue = this.getRightChildOf(index);
				}
			} 
			else {
				return;
			}
			somevalue = heap.get(ivalue);
			heap.set(ivalue, heap.get(index));
			heap.set(index,somevalue);
			index = ivalue;
		}
		
		
	}

	public Iterator<Entry<P, V>> iterator() {
		// TODO Auto-generated method stub
		return heap.iterator();
	}
}

