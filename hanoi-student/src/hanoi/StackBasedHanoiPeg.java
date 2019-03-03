package hanoi;

import structures.LinkedStack;
import structures.StackInterface;

/**
 * A {@link StackBasedHanoiPeg} is a {@link HanoiPeg} backed by a
 * {@link LinkedStack}
 * 
 * @author jcollard
 *
 */
public class StackBasedHanoiPeg implements HanoiPeg {
	/**
	 * Creates a new {@link StackBasedHanoiPeg} that has no rings.
	 * 
	 */
	 LinkedStack <HanoiRing> stack;
	
	public StackBasedHanoiPeg() {
		stack=new LinkedStack<HanoiRing>();
	}

	@Override
	public void addRing(HanoiRing ring) throws IllegalHanoiMoveException {
		
		if(ring == null) {
			throw new NullPointerException();
		}
		else if(stack.isEmpty())
		{
			stack.push(ring);
		}
		else if(ring.getSize() >= stack.peek().getSize()){
			throw new IllegalHanoiMoveException("new added ring must be saller than the top most ring");
		}
		else {
			stack.push(ring);
		}
	}

	@Override
	public HanoiRing remove() throws IllegalHanoiMoveException {
		if(stack.isEmpty()) {
			throw new IllegalHanoiMoveException("can't perform opertion on empty stack");
		}
		else {
			return stack.pop();
		}
	}

	@Override
	public HanoiRing getTopRing() throws IllegalHanoiMoveException {
		if(stack.isEmpty()) {
			throw new  IllegalHanoiMoveException("can't perform opertion on empty stack");
		}
		else {
			return stack.peek();
		}
	}

	@Override
	public boolean hasRings() {
        if(stack.getSize()>0) {
        	return true;
        }
        return false;
	}
}
