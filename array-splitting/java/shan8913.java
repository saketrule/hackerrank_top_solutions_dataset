import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    static int search(long [] A,int i, int j,long sum){
        int start =i;
        int end =j;
        
        while(start<end){
            int mid = start+(end-start)/2;
            
            if(A[mid]==sum)
                return mid;
            if(A[mid]>sum)
                end--;
            else
                start++;
        }
        return -1;
    }
    static long calculateMax(int i,int j,long [] A){
        if(i>=j){
            return 0;
        }
        //System.out.println("Hello");
        //System.out.println("Hello2");
        long totSum = i>0?A[j]-A[i-1]:A[j];
        if(totSum%2!=0){
            return 0;
        }
        //System.out.println("Hello3");
        long searchSum = totSum/2;
        if(i>0){
            searchSum+=A[i-1];
        }
        int index = search(A,i,j,searchSum);
        //System.out.println(index);
        
        if(index == -1){
            return 0;
        }
        long max =0;
        
        max=1+Math.max(calculateMax(i,index,A),calculateMax(index+1,j,A));
        return max;
            
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        int T;
        Scanner scanner = new Scanner(System.in);
        T = scanner.nextInt();
        while(T>0){
            T--;
            int N = scanner.nextInt();
            long [] A = new long[N];
            long temp;
            for(int i=0;i<N;i++){
                temp = scanner.nextLong();
                if(i==0){
                    A[i]=temp;
                }
                else{
                    A[i]=A[i-1]+temp;
                }
            }
            
            if(A[N-1]==0)
                System.out.println(N-1);
            else
                System.out.println(calculateMax(0,N-1,A));
        }
    }
}