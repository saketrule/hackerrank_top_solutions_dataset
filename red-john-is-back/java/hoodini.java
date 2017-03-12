import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    final static int FIRST_COMP = 4;
	static int[] NUM_OF_PRIMES = new int[217287];
	static HashMap<Integer,BigInteger> factorialCache = new HashMap<Integer,BigInteger>();
	static int maxFactorialCache = 1;

	public static void main(String[] args) {
		factorialCache.put(0, BigInteger.ONE);
		factorialCache.put(1, BigInteger.ONE);
		populatePrimeCount();
		
		Scanner scin = new Scanner(System.in);
		int T = scin.nextInt();
		
		for (int i = 0; i < T; i++) {
			int N = scin.nextInt();
			
			solve(N);
		}
	}
	
	public static void solve(int N) {
		// Get the number of brick configurations
		long M = getNumOfConfig(N);
		
		// Get the number of primes <= M
		System.out.println(getNumOfPrimes((int)M));
		
	}
	
	public static int getNumOfConfig(int N) {
		if (N < 4)
			return 1;
		else {
			// All vertical is one config
			int configs = 1;
			// Number of horizontal areas
			int big_blocks = N / 4;
			
			// how many shifts of each number of big blocks
			for (int i = 1; i <= big_blocks; i++) {
				int free_small_blocks = N - (4*i);
				BigInteger tempBI = factorial(free_small_blocks + i).divide(factorial(i).multiply(factorial(free_small_blocks)));
				configs += tempBI.longValue();
			}
			
			return configs;
		}
	}
	
	public static int getNumOfPrimes(int M) {
		return NUM_OF_PRIMES[M];
	}
	
	public static void populatePrimeCount() {
		NUM_OF_PRIMES[0] = NUM_OF_PRIMES[1] = 0;
		NUM_OF_PRIMES[2] = 1;
		for (int i = 3; i < NUM_OF_PRIMES.length; i++) {
			NUM_OF_PRIMES[i] = NUM_OF_PRIMES[i-1] + isPrime(i);
		}
	}
	
	/** Returns 0 if not prime, 1 if prime */
	public static int isPrime(int n) {
		if (n < 2)
			return 0;
		if (n % 2 == 0)
			return 0;
		for (int i = 3; i*i <= n; i+=2) {
			if (n % i == 0)
				return 0;
		}
		return 1;
	}
	
	public static BigInteger factorial(int n) {
		BigInteger ret = BigInteger.ONE;
        
        if (n == 0) return BigInteger.ONE;
        if (n <= maxFactorialCache) return factorialCache.get(n);
        ret = factorialCache.get(maxFactorialCache);
        for (int i = maxFactorialCache+1; i <= n; i++, maxFactorialCache++) {
        	ret = ret.multiply(BigInteger.valueOf(i));
        	factorialCache.put(i, ret);
        }
        return ret;
    }
}