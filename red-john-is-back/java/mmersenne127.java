import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private static class Binomial implements Comparable<Binomial>{
        private int n, k;
        public Binomial(int n, int k) {
            this.n = n;
            this.k = k;
        }
        public int compareTo(Binomial t) {
            if(n == t.n)return k - t.k;
            return n - t.n;
        }
        public String toString() {
            return n + "," + k;
        }
    }
    private static TreeMap<Binomial, Long> table = new TreeMap<Binomial, Long>();
    static long solve(int n){
        long sum = 0;
        int t = 0;
        while(n >= 4*t){
            sum+= combinations(t+n-4*t, n-4*t);
            t++;
        }
        return primes((int)(sum));
    }
    static long primes(int n){
        boolean[] set = new boolean[n+1];
        set[0] = false;
        set[1] = false;
        for(int i = 2; i<n+1; i++){
            set[i] = true;
        }
        for(int i = 2; i*i <= n; i++){
            if(set[i]){
                for(int j = i*i; j<n+1; j+=i){
                    set[j] = false;
                }
            }
        }
        long sum = 0;
        for(int i = 0; i<n+1; i++){
            if(set[i])sum++;
        }
        return sum;
    }
static long combinations(int n, int k) {
        if(n == k || k == 0)return 1;
        if(k == 1 || k == n-1)return n;
        if(n == 0)return 0;
        if(k<n/2)k=n-k;
        Binomial key = new Binomial(n, k);
        if(table.get(key) != null)return table.get(key);
        long out = combinations(n-1,k-1) + combinations(n-1,k);
        table.put(key, out);
        return out;
	}
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int i = 0; i<t; i++){
            System.out.println(solve(in.nextInt()));
        }
    }
}