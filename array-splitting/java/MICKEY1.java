import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in=new Scanner(System.in);
        int t=in.nextInt();
       
        while(t-->0)
            {
                 int n=in.nextInt();
                long[] a=new long[n];
               for(int i=0;i<n;i++)
                {
                    a[i]=in.nextLong();
                }
                System.out.println(solve(a,0,n-1));
            }
    }
    
    static int solve(long[] a,int start,int end)
       {
            if(start>=end)return 0; 
            int pos=0;
            boolean flag=false;
            long[] cum=new long[end-start+1];
            cum[0]=a[start];
            int k=0;
            
            for(int i=start;i<end;i++)
                {
                    cum[k+1]=a[i+1]+cum[k];
                k++;
                    
                }
            
            //for(long j:cum)System.out.print(j+" ");
            //System.out.println();
           for(int i=0;i<cum.length;i++)
                {
                       if(cum[i]==(cum[cum.length-1]-cum[i])) 
                     {
                           flag=true;
                           pos=i;
                           break;
                       }                         
                    
                }
        
            if(flag)
                {
                return 1+max(solve(a,start,start+pos),solve(a,start+pos+1,end));
                }
            else
                return 0;
            
        }    
       
    static int max(int a,int b)
        {
        if(a>b)return a;
        else return b;
    }
}