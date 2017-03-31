import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    private static int count(int[] a, int low, int high) {
        int[] sumLeft = new int[a.length];
        int[] sumRight = new int[a.length];
        sumLeft[low] = a[low];
        for (int i = low + 1; i <= high; i++) {
            sumLeft[i] = sumLeft[i - 1] + a[i];
        }
        sumRight[high] = a[high];
        for (int i = high - 1; i >= low; i--) {
            sumRight[i] = sumRight[i + 1] + a[i];
        }
        int count = 0;
        for (int i = low; i < high; i++) {
            if (sumLeft[i] == sumRight[i + 1]) {
               count += Math.max(count(a, low, i), count(a, i + 1, high)) + 1; 
               break; 
            }
        }
        return count;
    }  
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        for (int t = 0; t < tests; t++) {
            int n = sc.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            int res = count(a, 0, n - 1);
            System.out.println(res); 
        }        
   }
}