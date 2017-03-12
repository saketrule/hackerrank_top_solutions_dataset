import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    void get()
    {
       Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int[][] arr=new int[n][n];
        int e=s.nextInt();
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
            arr[i][j]=Integer.MAX_VALUE;
        for(int i=0;i<e;i++)
        {
            int f=s.nextInt()-1;
            int ed=s.nextInt()-1;
            int l=s.nextInt();
            if(l<arr[f][ed])
            {
            arr[f][ed]=l;
            arr[ed][f]=l;
            }
        }
        int sum=0;
            
        //int min=999;
        int src=s.nextInt();
       boolean[] bl=new boolean[n];
        for(int i=0;i<n;i++)
            bl[i]=true;
        bl[src-1]=false;
        int ind=0;
        for(int k=1;k<n;k++)
        {    
            int min=Integer.MAX_VALUE;
            
            for(int i=0;i<n;i++)
            {  
                for(int j=0;j<n;j++)
                {
                   if(arr[i][j]<min && (!bl[i]) && bl[j] && i!=j ) 
                    {
                       min=arr[i][j];
                       ind=j;
                    }
                }
                
               
            }
             // System.out.println(""+sum); 
            if(min!=Integer.MAX_VALUE)
            { sum+=min;
                bl[ind]=false;
            }
            
            
        }
        
       System.out.println(""+sum); 
        
        
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Solution s=new Solution();
        s.get();
    }
}