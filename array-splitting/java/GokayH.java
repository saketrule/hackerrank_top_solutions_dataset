import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    private static int score(long[] nums, int left, int right, long sum) {
        if (sum == 0) // all zero array
            return right-left-1; 
        if (left==right) 
            return 0; // empty array
        if (sum%2 == 1)
            return 0; // cannot be split into two equal parts
        if (left == right-1)
            return 0; // one element array. cannot be split
        /*else if (left == right-2) {
            if (nums[left] == nums[right-1])
                return 1;
            else 
                return 0;
        }*/
        long halfSum = 0;
        int i = left;
        for ( ; i < right && halfSum < sum/2; i++) {
            halfSum += nums[i];    
        }
        //System.out.println(sum + ": " + left + " " + i + " " + right);
        if (halfSum*2 != sum) {
            //System.out.println("returning zero for sum: " + sum);      
            return 0;
        }
            
        int curScore = Math.max(score(nums, left, i ,halfSum), score(nums, i, right, halfSum)) + 1;
        //System.out.println("curScore: " + curScore);
        return curScore;
        
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-->0) {
            int N = sc.nextInt();
            long[] nums = new long[N];
            long sum = 0;
            for (int i = 0; i < N; i++) {
                nums[i] = sc.nextLong();
                sum += nums[i];
                //System.out.print(nums[i] + " ");
            }
            System.out.println(score(nums, 0, N, sum));
        }
    }
}