package hanoi;

/**
 * A {@link ArrayBasedHanoiBoard} is a simple implementation of
 * {@link HanoiBoard}
 * 
 * @author jcollard
 * 
 */
public class ArrayBasedHanoiBoard implements HanoiBoard {


	/**
	 * Creates a {@link ArrayBasedHanoiBoard} with three empty {@link HanoiPeg}s
	 * and {@code n} rings on peg 0.
	 */
	StackBasedHanoiPeg[] hanoiboard;
	public ArrayBasedHanoiBoard(int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		
		hanoiboard = new StackBasedHanoiPeg[3];
		hanoiboard[0] = new StackBasedHanoiPeg();
		hanoiboard[1] = new StackBasedHanoiPeg();
		hanoiboard[2] = new StackBasedHanoiPeg();
		for (int i = n; i > 0; i--) {
			hanoiboard[0].addRing(new HanoiRing(i));
		}
	}

	@Override
	public void doMove(HanoiMove move) throws IllegalHanoiMoveException {
		if (!isLegalMove(move)) {
			throw new IllegalHanoiMoveException("can't perform wrong move");
		}
		HanoiRing hanring = hanoiboard[move.getFromPeg()].remove();
		hanoiboard[move.getToPeg()].addRing(hanring);

	}

	@Override
	public boolean isSolved() {
		System.out.println(!hanoiboard[0].hasRings() + " " + !hanoiboard[0].hasRings());
		return !hanoiboard[0].hasRings() && !hanoiboard[1].hasRings();
	}

	/**
	 * <p>
	 * A {@link HanoiMove} is not legal if either is true:
	 * 
	 * <ul>
	 * <li>The from peg has no rings</li>
	 * <li>The to peg has rings AND the ring to be moved has a size larger than
	 * the topmost ring on the to peg.</li>
	 * </ul>
	 * 
	 * Otherwise, the move is legal.
	 * </p>
	 */
	@Override
	public boolean isLegalMove(HanoiMove move) {
		if (move == null) {
			throw new NullPointerException();
		}
		if (!hanoiboard[move.getFromPeg()].hasRings()) {
			return false;
		}
		if (!hanoiboard[move.getToPeg()].hasRings()) {
			return true;
		}
		if (move.getToPeg() == move.getFromPeg()) {
			return false;
		}
		if (hanoiboard[move.getFromPeg()].getTopRing().getSize() > hanoiboard[move.getToPeg()].getTopRing().getSize()) {
			return false;
		}
		
		return true;
	}
}
