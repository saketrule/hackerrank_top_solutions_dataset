import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static int[][] ncr;
    
    public static void main(String[] args) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        ncr = new int[42][42];
        for (int i = 0; i <= 40; i++) ncr[i][0] = ncr[i][i] = 1;
        for (int i = 1; i <= 40; i++) {
            for (int j = 1; j < i; j++) {
                ncr[i][j] = ncr[i-1][j-1]+ncr[i-1][j];
            }
        }
        
        // sieve      
        boolean isPrime[] = new boolean[4000001];
        java.util.Arrays.fill(isPrime, true);
        isPrime[1] = false;
        for (int i = 2; i*i <= 4000000; i++) {
            if (isPrime[i]) {
                for (int j = i; i*j <= 4000000; j++) {
                    isPrime[i*j] = false;
                }
            }
        }
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader in = new BufferedReader(new FileReader("july01.in"));
        int T = Integer.parseInt(in.readLine());
        for (int i = 0; i < T; i++) {
            int x = ways(Integer.parseInt(in.readLine()));
            int primes = 0;
            for (int j = 2; j <= x; j++) {
                if (isPrime[j]) {
                	primes++;
                }
            }
            System.out.println(primes);
        }
    }
    
    private static int ways(int n) {
        int ans = 0;
        for (int i = 0; 4*i <= n; i++) {
            int j = n-4*i;
            ans += ncr[i+j][j];
        }     
        return ans;
    }
}