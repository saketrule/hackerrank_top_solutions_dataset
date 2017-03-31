import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static long calculate(int start,int end,long[] array){
        if(start==end){
            return 0;
        }
        else{
            long sumf=array[start],sumb=array[end];
            int l=end-1,i=start+1;;
            while(i<=l){
                if(sumf<sumb){
                    sumf=sumf+array[i];
                    i=i+1;
                }
                else{
                    sumb=sumb+array[l];
                    l=l-1;
                }
            }
            if(sumf!=sumb){
                return 0;
            }
            else{
                return 1+Math.max(calculate(start,i-1,array),calculate(l+1,end,array));
            }
        }
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner input=new Scanner(System.in);
        int T=input.nextInt();
        for(int t=0;t<T;t++){
            int n=input.nextInt();
            long[] a=new long[n];
            for(int i=0;i<n;i++){
                a[i]=input.nextLong();
                
                
            }
            long answer=calculate(0,n-1,a);
            System.out.println(answer);
        }
    }
}