import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        
        
        Scanner scanner = new Scanner(System.in);
        
        int t = scanner.nextInt();
        
        for(int k=0;k<t;k++){
            
            int n = scanner.nextInt();
            int[] ar = new int[n];
            for(int i=0;i<n;i++){
                ar[i] = scanner.nextInt();
            }
            //
            long sum =0;
            for(int i=0;i<n;i++){
                sum += ar[i];
                
            }
            System.out.println(calc(0, n-1,ar, sum));
            
        }
    }
    
    static int calc(int left, int right, int[] ar, long sum){
        if (right <= left) return 0;
        
        if (sum%2 != 0)
            return 0;
        
        long half = sum / 2;
        // find mid point
        int sL = left;
        long sumL = ar[sL];
        //
        while(sumL < half && sL < right) {
        	sumL += ar[++sL];
        }
        int pnt = 0;
        if (sumL == half) {
        	pnt = 1;
            int leftPnt=0; 
            int rightPnt =0;
        
            leftPnt = calc(left, sL ,ar , sum/2);
            rightPnt = calc(sL + 1, right, ar,sum/2);
            //
            if (leftPnt>rightPnt)
                pnt+=leftPnt;
            else pnt+=rightPnt;
        }
        return pnt;
    }
}