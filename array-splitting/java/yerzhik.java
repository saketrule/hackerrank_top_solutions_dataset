import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    private static long solve(int data[], int low, int high) {
        if (low >= high) {
            return 0;
        }
        long sum = 0;
        for (int i = low; i <= high; ++i) {
            sum += data[i];
        }
        
        long lsum = 0;
        for (int i = low; i <= high; ++i) {
            lsum += data[i];
            sum -= data[i];
            if (sum == lsum) {
                long res1 = solve(data, low, i);
                long res2 = solve(data, i + 1, high);
                
                return 1 + Math.max(res1, res2);
            } else if (sum < lsum) {
                return 0;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i1 = 0 ; i1 < t; ++i1) {
            int n = in.nextInt();
            int []data = new int[n];
            for (int i = 0 ; i < n; ++i) {
                data[i] = in.nextInt();
            }
            
            System.out.println(solve(data, 0, n - 1));
        }
    }
}