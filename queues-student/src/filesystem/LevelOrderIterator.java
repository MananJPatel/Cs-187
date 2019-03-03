package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;

import structures.Queue;


/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem. A level-order traversal is equivalent to a breadth-
 * first search.
 * 
 * @author liberato
 *
 */
public class LevelOrderIterator extends FileIterator<File> {
	Queue<File> file= new Queue<File>();
	Queue<File> fin= new Queue<File>();
	File[] t;
	int x;
	
	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * @param rootNode
	 * @throws FileNotFoundException if the rootNode does not exist
	 */
	public LevelOrderIterator(File rootNode) throws FileNotFoundException {
        	// TODO 1
		if(!rootNode.exists() || rootNode == null) {
			throw new FileNotFoundException();
		}
		file.enqueue(rootNode);
		//for(;!file.isEmpty();)
		while(!file.isEmpty()) {
			if(file.peek().isDirectory()) {
				File[] files1 = file.peek().listFiles();
				Arrays.sort(files1);
				for(File f : files1)
					file.enqueue(f);
			}
			fin.enqueue(file.dequeue());
			x++;
		}
	}
	
	@Override
	public boolean hasNext() {
        	// TODO 2
            return !fin.isEmpty();
	}

	@Override
	public File next() throws NoSuchElementException {
        	// TODO 3
		if(fin.isEmpty()) {
			throw new NoSuchElementException();
		}
            return fin.dequeue();
	}

	@Override
	public void remove() {
		// Leave this one alone.
		throw new UnsupportedOperationException();		
	}

}
