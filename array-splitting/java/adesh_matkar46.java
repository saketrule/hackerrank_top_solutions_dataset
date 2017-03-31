import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) 
    {
			int i,j;
			Solution sdp= new Solution();
			Scanner sc=new Scanner(System.in);
			//int []arr=new int[]{4,0,1,1,0,1,1}
			int t=sc.nextInt();
			//System.out.println(t);
			while(t > 0)
			{
				int s=sc.nextInt();
				//System.out.println("t:s "+t+":"+s);
				long []arr=new long[s];
				for(i=0;i<s;i++)
					arr[i]=sc.nextLong();
				int moves=sdp.goForPartition(arr,0,arr.length-1);
				
				System.out.println(moves);
				t=t-1;
         }
    }
    	private int goForPartition(long  []arr,int l,int h) 
		{
			//System.out.println(arr.length);
			int lmoves,rmoves,i=0;
			
			
			int sum=0,n=arr.length;
			lmoves=rmoves=0;
			if(l>=h)
				return 0;
			
			int splitIndex=findPartition(arr,l,h);
			if(splitIndex == -1)
				return 0;
			lmoves=goForPartition(arr,l,splitIndex)+1;
			rmoves=goForPartition(arr,splitIndex+1,h)+1;
				
			return Math.max(lmoves, rmoves);
				
		}
		

		private int findPartition(long[] arr,int l,int h)
		{
			int i=0;
			long sum=0,auxSum=0;
			for(i=l;i<=h;i++)
				sum+=arr[i];
			if(sum%2 !=0)
				return -1;
			for(i=l;i<=h;i++)
			{
				auxSum+=arr[i];
				if(auxSum==sum/2)
					break;
			}
			if(i>h)
				return -1;
			else return i;
			
		}
}