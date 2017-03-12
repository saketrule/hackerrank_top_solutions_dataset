import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    final static int C = 4;
	
	public static long factorial(int n) {
		long counter = 1;
		if (n == 0) {
			return counter;
		}
		for (int i = 1; i <= n; i++) {
			counter *= i;
		}
		return counter;
	}
	public static long choose(int fours, int ones) {
		if (fours == 0 || ones == 0) return 1;
		int dif = 0;
		int rem = 0;
		if (fours > ones) {
			dif = fours;
			rem = ones;
		} else {
			dif = ones;
			rem = fours;
		}
		long counter = 1;
		for (int i = fours + ones; i > dif; i--) {
			counter *= i;
		}
		return counter / factorial(rem);
	}
	
	public static long find(int n) {
		int reps = n / C;
		int M = 0;
		for (int i = 0; i <= reps; i++) {
			M += choose(i, n - 4 * i);
		}
		return M;
	}
    
    public static boolean isPrime(int n) {
        if (n == 0 || n == 1 || n == 4)
            return false;
        if (n == 2 || n == 3)
            return true;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if ( (n % i) == 0 ) {
                return false;
            }
        }
        return true;
    }
    
    public static int countPrimes(long n) {
        int primes = 0;
        for (int i = 0; i <= n; i++) {
            if (isPrime(i)) {
                primes++;
            }
        }
        return primes;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int times = Integer.parseInt(line);
        for (int i = 0; i < times; i++) {
            int n = Integer.parseInt(br.readLine());
            long ans = countPrimes(find(n));
            System.out.println(ans);
        }
    }
}