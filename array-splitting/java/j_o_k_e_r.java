import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner src= new Scanner (System.in);
        int t = src.nextInt();
        for(int r =0;r<t;r++)
        {
            int n  = src.nextInt();
            long[] a = new long[n];
            int f =0;
            for(int i =0;i<n;i++)
            {
                a[i]=src.nextLong();
                if(a[i]!=0)
                    f=1;
            }
            if(f==0)
                System.out.println(n-1);
            else
            {
                int ans = divide(a,0,n);
                System.out.println(ans);
            }
        }
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
    public static int divide(long[] a , int p , int r)
    {
        long sum =0;
        for(int i=p;i<r;i++)
            sum = sum +a[i];
        int count=0;
        if(sum%2!=0)
            count =0;
        else
        {
            //int q = -1;
            int[] q = new int[2];
            q = findpartition(a,p,r,sum);
            if(q[0]!=-1) 
            {
                count  =  Math.max(q[1]+divide(a,p,q[0]+1),q[1]+divide(a,q[0]+1,r));
            } 

        }
        return count;
    }

    public static int[] findpartition(long[] a, int p, int r,long sum)
    {
        int[] q=new int[2];
        q[0]=-1;
        q[1]=0;
        long sum1=0;
        for(int i = p;i<r;i++)
            {
            sum = sum-a[i];
            sum1 = sum1+a[i];
          //  System.out.println(sum+" "+sum1);
            if(sum ==sum1)
                {
                q[0] = i;
               // System.out.println(q[0]);
                q[1]=1;
                break;
            }
        }
        return q;
    }

}