package kolos;

public class p1 {
	static int[] dictionary; 
	// Get Fibonacci with Memoization
	public static int getFibWithMem(int n) {
	    if (dictionary == null) {
	        dictionary = new int[n];
	    }

	    if (dictionary[n - 1] == 0) {
	        if (n <= 2) {
	            dictionary[n - 1] = n - 1;
	        } else {
	            dictionary[n - 1] = getFibWithMem(n - 1) + getFibWithMem(n - 2);
	        }
	    }

	    return dictionary[n - 1];
	}

	public static void printFibonacci()
	{
	    for (int i =0; i<dictionary.length;i++) {
	    	System.out.print("F[" + (i+1) + "]:" + dictionary[i] + ", ");
	    }
	}
	
	public static void main(String[] args) {
		getFibWithMem(8);
		printFibonacci();
	}
}
