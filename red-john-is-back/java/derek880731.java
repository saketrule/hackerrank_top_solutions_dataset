import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        Solution sol = new Solution();
        int n = in.nextInt();
        for(int i = 0; i < n; i++) {
            int N = in.nextInt();
            //System.out.println("N: " + N);
            System.out.println(sol.app(N));
        } 
        
    }
    private int ways = 0;
    public int app(int N) {
        int[] A = new int[2];
        A[0] = 1;
        A[1] = 4;
        ways = 0;
        getWays(A, N, 0);
        //System.out.println("ways: " + ways);
        return getPrime(ways);
    }
    
    private void getWays(int[] A, int N, int sum) {
        if(sum > N) {
            return;
        }
        if(sum == N) {
            ways++;
            return;
        }
        for(int i = 0; i < A.length; i++) {
            sum += A[i];
            //System.out.print(A[i]);
            getWays(A, N, sum);
            sum -= A[i];
        }
    }
    
    private int getPrime(int n) {

        boolean[] prime=new boolean[n+1];
        for(int i = 0; i <= n; i++) {
            prime[i] = true;
        }

        prime[0]=false;
        prime[1]=false;
        int m= (int)Math.sqrt(n);

        for (int i=2; i<=m; i++) {
            if (prime[i]) {
                for(int j = 2*i; j <= n; j += i) {
                    prime[j] = false;
                }
            }
        }
        int count = 0;
        for(int i = 0; i <= n; i++) {
            if(prime[i])
                count++;
        }
        return count;
    }
}