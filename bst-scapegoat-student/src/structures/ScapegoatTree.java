package structures;

import java.util.Iterator;

public class ScapegoatTree<T extends Comparable<T>> extends
		BinarySearchTree<T> {
	private int upperBound;

	
	@Override
	public void add(T t) {
		// TODO
		if (t == null) {
			throw new NullPointerException();
		}
		upperBound++;
		
		BSTNode<T> BSTN = new BSTNode<T>(t, null, null);
		root = addToSubtree(root,BSTN);
		
		if (height() > Math.log(upperBound) / Math.log((double)3/2)) {
			BSTNode<T> BSTNC = BSTN;
			BSTNode<T> BSTNP = BSTN.parent;
			
			while ((double)2/3 > (double)subtreeSize(BSTNC)/ subtreeSize(BSTNP)) {
				BSTNC = BSTNC.parent;
				BSTNP = BSTNP.parent;
			}
			
			ScapegoatTree<T> SGT = new ScapegoatTree<T>();
			SGT.root = BSTNP;
			BSTNode<T> SGTP = BSTNP.parent;
			SGT.balance();
			
			if (SGTP.getLeft() == BSTNP) {
				SGTP.setLeft(SGT.root);
			}
			else {
				SGTP.setRight(SGT.root);
			}
		}
	}
		
	@Override
	public boolean remove(T element) {
		// TODO
		if (element == null) {
			throw new NullPointerException();
		}
		boolean finalAnswer = contains(element);
		
		if (finalAnswer) {
			root = removeFromSubtree(root, element);
		}
		if (upperBound > 2*size()) {
			balance();
			upperBound = size();
		}
		return finalAnswer;
	}
}
