import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int n, arr[], i;
        long sum;
        while (t-- > 0){
            n = in.nextInt();
            arr = new int[n];
            sum = 0;
            for (i = 0 ; i < n; i++){
            	arr[i] = in.nextInt();
            	sum += arr[i];
            }
            System.out.println(f(arr, 0, n-1, sum, 0));
        }
        in.close();
    }
    
    private static int f(int[] arr, int start, int end, long sum, int score){
        if (start >= end)
            return score;
        long partSum = 0;
        int i = start;
        for (; i <= end; i++){
            partSum += arr[i];
            if (partSum == sum - partSum)
            	break;
        }
        if (i <= end)
        	return Math.max(f(arr, start, i, partSum, score + 1),
        			f(arr, i + 1, end, partSum, score + 1));
        return score;
    }
}