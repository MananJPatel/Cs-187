package structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements
		BSTInterface<T> {
	protected BSTNode<T> root;

	public boolean isEmpty() {
		return root == null;
	}

	public int size() {
		return subtreeSize(root);
	}

	protected int subtreeSize(BSTNode<T> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + subtreeSize(node.getLeft())
					+ subtreeSize(node.getRight());
		}
	}

	public boolean contains(T t) {
		// TODO
		return rec_contains(t, root);
	}

	public boolean remove(T t) {
		if (t == null) {
			throw new NullPointerException();
		}
		boolean result = contains(t);
		if (result) {
			root = removeFromSubtree(root, t);
		}
		return result;
	}

	protected BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
		// node must not be null
		int result = t.compareTo(node.getData());
		if (result < 0) {
			node.setLeft(removeFromSubtree(node.getLeft(), t));
			return node;
		} else if (result > 0) {
			node.setRight(removeFromSubtree(node.getRight(), t));
			return node;
		} else { // result == 0
			if (node.getLeft() == null) {
				return node.getRight();
			} else if (node.getRight() == null) {
				return node.getLeft();
			} else { // neither child is null
				T predecessorValue = getHighestValue(node.getLeft());
				node.setLeft(removeRightmost(node.getLeft()));
				node.setData(predecessorValue);
				return node;
			}
		}
	}

	private T getHighestValue(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getData();
		} else {
			return getHighestValue(node.getRight());
		}
	}

	private BSTNode<T> removeRightmost(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getLeft();
		} else {
			node.setRight(removeRightmost(node.getRight()));
			return node;
		}
	}

	public T get(T t) {
		// TODO
		return rec_get(t, root);
	}


	public void add(T t) {
		if (t == null) {
			throw new NullPointerException();
		}
		root = addToSubtree(root, new BSTNode<T>(t, null, null));
	}

	protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) {
		if (node == null) {
			return toAdd;
		}
		int result = toAdd.getData().compareTo(node.getData());
		if (result <= 0) {
			node.setLeft(addToSubtree(node.getLeft(), toAdd));
		} else {
			node.setRight(addToSubtree(node.getRight(), toAdd));
		}
		return node;
	}

	@Override
	public T getMinimum() {
		// TODO
		if (this.isEmpty()) {
			return null;
		}
		
		return rec_getMinimum(root);
	}


	@Override
	public T getMaximum() {
		// TODO
		if (this.isEmpty()) {
			return null;
		}
		
		return rec_getMaximum(root);
	}


	@Override
	public int height() {
		// TODO
		if (this.isEmpty()) {
			return -1;
		} else if (size() == 1) {
			return 0;
		}
		
		return rec_height(root) - 1;
	}


	public Iterator<T> preorderIterator() {
		// TODO
		Queue<T> queue = new LinkedList<T>();
		rec_preorder(queue, root);
		return queue.iterator();
	}


	public Iterator<T> inorderIterator() {
		Queue<T> q = new LinkedList<T>();
		rec_inorder(q, root);
		return q.iterator();
	}
	

	public Iterator<T> postorderIterator() {
		// TODO
		Queue<T> q = new LinkedList<T>();
		rec_postorder(q, root);
		return q.iterator();
	}


	@Override
	public boolean equals(BSTInterface<T> other) {
		// TODO
		return rec_equals(this.root, other.getRoot());
	}


	@Override
	public boolean sameValues(BSTInterface<T> other) {
		// TODO
		Iterator<T> one = this.inorderIterator();
		Iterator<T> two = other.inorderIterator();
		
		while (one.hasNext()) {
			if (!one.next().equals(two.next())) {
				return false;
			}
		}
		
		if (two.hasNext() || one.hasNext()) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean isBalanced() {
		// TODO
		return rec_isBalanced(root);
	}

	@Override
    public void balance() {
		// TODO
		if (this.isEmpty()) {
			return;
		}
		
		Iterator<T> recursion = this.inorderIterator();
		ArrayList<T> l = new ArrayList<T>();
		
		while (recursion.hasNext()) {
			l.add(recursion.next());
		}
		root = null;
		rec_balance(l);
	}


	@Override
	public BSTNode<T> getRoot() {
        // DO NOT MODIFY
		return root;
	}
	
	// additional methods starts because we are using recursive method.
	private boolean rec_contains(T point, BSTNode<T> binarysearchtree) {
		 if (binarysearchtree == null)
			 return false; 
		 else if (point.compareTo(binarysearchtree.getData()) > 0)
			 return rec_contains(point, binarysearchtree.getRight());
		 else if (point.compareTo(binarysearchtree.getData()) < 0)
			 return rec_contains(point, binarysearchtree.getLeft());  
		 else
			 return true; 
	 }
	
	private T rec_get(T point, BSTNode<T> binarysearchtree) {
		 if (binarysearchtree == null)
			 return null; 
		 else if (point.compareTo(binarysearchtree.getData()) > 0)
			 	return rec_get(point, binarysearchtree.getRight());
		 else if (point.compareTo(binarysearchtree.getData()) < 0)
			 return rec_get(point, binarysearchtree.getLeft());  
		 else
			 return binarysearchtree.getData(); 
	 }
	
	private T rec_getMinimum(BSTNode<T> BSTN) {
		if (BSTN.getLeft() == null) {
			return BSTN.getData();
		}
		return rec_getMinimum(BSTN.getLeft());
	}
	
	private T rec_getMaximum(BSTNode<T> BSTN) {
		if (BSTN.getRight() == null) {
			return BSTN.getData();
		}
		return rec_getMinimum(BSTN.getRight());
	}
	
	private int rec_height(BSTNode<T> binarysearchtree) {
		if (binarysearchtree == null) {
			return 0;
		} 
		else {
			if (rec_height(binarysearchtree.getLeft()) > rec_height(binarysearchtree.getRight())) {
				return rec_height(binarysearchtree.getLeft()) + 1;
			} 
			else {
				return rec_height(binarysearchtree.getRight()) + 1;
			}
		}
	}
	
	private void rec_preorder(Queue<T> q, BSTNode<T> BSTN) {
		if (BSTN != null) {
			rec_preorder(q, BSTN.getLeft());
			q.add(BSTN.getData());
			rec_preorder(q, BSTN.getRight());
		}
	}
	
	private void rec_inorder(Queue<T> q, BSTNode<T> BSTN) {
		if (BSTN != null) {
			rec_inorder(q, BSTN.getLeft());
			q.add(BSTN.getData());
			rec_inorder(q, BSTN.getRight());
		}
	}
	
	private void rec_postorder(Queue<T> q, BSTNode<T> BSTN) {
		if (BSTN != null) {
			rec_postorder(q, BSTN.getLeft());
			q.add(BSTN.getData());
			rec_postorder(q, BSTN.getRight());
		}
	}
	
	private boolean rec_equals(BSTNode<T> BSTN, BSTNode<T> BSTN1) {
		
		if (BSTN == BSTN1) {
			return true;
		} 
		else {
			if (BSTN == null || BSTN1 == null) {
				return false;
			} 
			else {
				boolean checkLeft = rec_equals(BSTN.getLeft(), BSTN1.getLeft());
				boolean checkRight = rec_equals(BSTN.getRight(), BSTN1.getRight());
				return BSTN.getData().equals(BSTN1.getData()) && checkLeft && checkRight;
			}
		}
	}
	
	private boolean rec_isBalanced(BSTNode<T> BSTN) {
		if (BSTN == null) {
			return true;
		} 
		else {
			if (rec_height(BSTN.getRight()) + 1 < rec_height(BSTN.getLeft()) ||
				rec_height(BSTN.getRight()) - 1 > rec_height(BSTN.getLeft())) {
				return false;
			} 
			else if (!rec_isBalanced(BSTN.getRight()) || !rec_isBalanced(BSTN.getLeft())) {
				return false;
			} 
			else {
				return true;
			}
		}
	}
	
	private void rec_balance(ArrayList<T> al) {
		
		if (al.size() == 1) {
			this.add(al.get(0));
			return;
		}
		
		if (al.isEmpty()) {
			return;
		}
		
		int TheMiddleOne = (al.size() - 1) / 2;
		this.add(al.get(TheMiddleOne));
		al.remove(TheMiddleOne);
		
		ArrayList<T> rs = new ArrayList<T>(al.subList((al.size() - 1) / 2, al.size()));
		ArrayList<T> ls = new ArrayList<T>(al.subList(0, (al.size() - 1) / 2));
		rec_balance(rs);
		rec_balance(ls);
		
		
	}
	
	
	/// additional methods ends here. 

	public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
		// header
		int count = 0;
		String dot = "digraph G { \n";
		dot += "graph [ordering=\"out\"]; \n";
		// iterative traversal
		Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
		queue.add(root);
		BSTNode<T> cursor;
		while (!queue.isEmpty()) {
			cursor = queue.remove();
			if (cursor.getLeft() != null) {
				// add edge from cursor to left child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getLeft().getData().toString() + ";\n";
				queue.add(cursor.getLeft());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}
			if (cursor.getRight() != null) {
				// add edge from cursor to right child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getRight().getData().toString() + ";\n";
				queue.add(cursor.getRight());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}

		}
		dot += "};";
		return dot;
	}

	public static void main(String[] args) {
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			BSTInterface<String> tree = new BinarySearchTree<String>();
			for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
				tree.add(s);
			}
			Iterator<String> iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.preorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.postorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();

			System.out.println(tree.remove(r));

			iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
		}

		BSTInterface<String> tree = new BinarySearchTree<String>();
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			tree.add(r);
		}
		System.out.println(tree.size());
		System.out.println(tree.height());
		System.out.println(tree.isBalanced());
		tree.balance();
		System.out.println(tree.size());
		System.out.println(tree.height());
		System.out.println(tree.isBalanced());
	}
}