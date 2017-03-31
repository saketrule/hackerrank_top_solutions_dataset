import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static int depth = 0;
    
    public static void findDepth(long[] arr, int lo, int hi, int curDepth) {
        if(hi <= lo)
            return;
        
        long rangeSum = (lo == 0) ? arr[hi] : (arr[hi] - arr[lo-1]);
        if(rangeSum % 2 == 1)
            return;
        
        boolean canPartition = false;
        int partitionIndex = 0;
        int l = lo, r = hi;
        int mid = l + (r - l)/2;
        while(l <= r) {
            mid = l + (r - l)/2;
            long midSum = (lo == 0) ? arr[mid] : (arr[mid] - arr[lo-1]);
            if(midSum == rangeSum/2) {
                canPartition = true;
                partitionIndex = mid;
                break;
            } else if(midSum < rangeSum/2) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }           
        }
        if(canPartition) {
            depth = Math.max(depth, curDepth+1);
            findDepth(arr, lo, partitionIndex, curDepth+1);
            findDepth(arr, partitionIndex+1, hi, curDepth+1);
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int k = 0; k < t; k++) {
            int n = in.nextInt();
            int[] input = new int[n];
            long[] prefixSum = new long[n];
            for(int i = 0; i < n; i++) {
                input[i] = in.nextInt();
                if(i > 0)
                    prefixSum[i] = prefixSum[i-1] + input[i];
                else
                    prefixSum[i] = input[i];
            }
            depth = 0;
            if(prefixSum[n-1] != 0)
                findDepth(prefixSum, 0, n-1, 0);
            else
                depth = n-1;
            System.out.println(depth);
            /*
            int[][] dp = new int[n][n+1];
            long firstSum, secondSum;
            for(int len = 2; len <= n; len++) {
                for(int from = 0; from + len - 1 < n; from++) {
                    for(int j = 1; j < len; j++) {
                        firstSum = (from == 0) ? (prefixSum[from+j-1]) : (prefixSum[from+j-1] - prefixSum[from-1]);
                        secondSum = prefixSum[from+len-1] - prefixSum[from+j-1];
                        if(firstSum == secondSum)
                            dp[from][len] = Math.max(dp[from][j], dp[from+j][len-j]) + 1;
                    }
                }
            }
            System.out.println(dp[0][n]);
            */
        }
        in.close();
    }
}