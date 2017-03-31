import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        List<int []> input = new ArrayList<>();
        for(int i = 0; i < t; i++) {
            int n = in.nextInt();
            int arr[] = new int[n];
            for(int j = 0; j < n; j++) {
                arr[j] = in.nextInt();
            }
            input.add(arr);
        }
        for(int []arr : input) {
            System.out.println(maxScore(arr, 0, arr.length - 1));
        }
    }
    
    public static int maxScore(int []arr, int start, int end){
        if(start >= end) {
            return 0;
        }
        int i = start;
        int j = end;
        long lSum = arr[i];
        long rSum = arr[j];
        while(j-i > 1) {
            if(lSum < rSum) {
                i++;
                lSum += arr[i];
            }
            else {
                j--;
                rSum += arr[j];
            }
        }
        
        if(j-i == 1 && lSum == rSum) {
            return 1 + max(maxScore(arr, start, i), maxScore(arr, j, end));
        }
        return 0;
    }
    
    private static int max(int i, int j) {
        return i > j ? i : j;
    }
}