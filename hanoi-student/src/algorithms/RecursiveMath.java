package algorithms;

/**
 * A class that implements some basic mathematical functions using recursion
 *
 * @author jcollard
 */
public class RecursiveMath {

	/**
	 * Returns {@code true} if {@code val} is even and {@code false} otherwise.
	 * A number is even if it is zero or if its predecessor is an odd number. A
	 * negative number is even if its negation is even.
	 *
	 * @param val
	 * @return {@code true} if {@code val} is even and {@code false} otherwise.
	 */
	public boolean isEven(int val) {
    // Without recursion this could be: return val % 2 == 0;
		if (val == 0 ) {
			return true;
		}
		else if (val < 0) {
			return isEven(val * -1);
		}
		return isOdd(val -1);
	}

	/**
	 * Returns {@code true} if {@code val} is odd and {@code false} otherwise.
	 * Zero is not odd, but one is odd, as is any number whose predecessor is
	 * even. A negative number is odd if its negation is odd.
	 * 
	 * @param val
	 * @return {@code true} if {@code val} is odd and {@code false} otherwise.
	 */
	public boolean isOdd(int val) {
    // Without recursion this could be: return val % 2 == 1;
    //return false;
		
		if (val == 0 ) {
			return false;
		}
		else if (val < 0) {
			return isOdd(val * -1);
		}
		return isEven(val-1);
		
	}

	/**
	 * Returns the sum of all values between 1 and n.
	 * 
	 * @param n
	 * @return the sum of all values between 0 and n.
	 * @throws IllegalArgumentException
	 *             if n is less than 0.
	 */
	public int sumN(int n) {
		if (n < 0)
			throw new IllegalArgumentException();
		if (n == 0)
			return n;
		return n + sumN(n - 1);
	}

	/**
	 * Returns the multiplication of all values between 1 and n.
	 * 
	 * @param n
	 * @return the multiplication of all values between 1 and n.
	 * @throws IllegalArgumentException
	 *             if n is less than 0.
	 */
	public int factorial(int n) {
    //return -1;
		if (n < 0)
			throw new IllegalArgumentException();
		if (n == 1)
			return n;
		return n * factorial(n - 1);
	}

	/**
	 * Returns 2 to the nth power. (2^n)
	 * 
	 * @param n
	 * @return 2 to the nth power.
	 * @throws IllegalArgumentException
	 *             if n is less than 0.
	 */
	public int biPower(int n) {
		if (n < 0)
			throw new IllegalArgumentException();
		else if(n==0)
			 return 1;
		
		return 2 * biPower(n - 1);
        //return -1;
	}
}
