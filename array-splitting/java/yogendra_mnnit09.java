import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 
	static int totalWay;
    public static void main(String[] args) throws FileNotFoundException {
    	//System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);
        
        int T,N,arr[];
        
        T = sc.nextInt();
        for(int tc=0;tc<T;tc++){
        	N = sc.nextInt();
        	arr = new int[N+1];
        	totalWay=0;
        	for(int i=1;i<=N;i++){
        		arr[i]=sc.nextInt()+arr[i-1];
        		
        	}
        	
        	
        	System.out.println(solve(arr,1,N));
        	
        }
        
    }

	private static int solve(int[] arr, int i, int n) {
		
		for(int k=i;k<n;k++){
			if(arr[k]*2==arr[n]+arr[i-1]){
				return Math.max(solve(arr,i,k), solve(arr,k+1,n))+1;
			}
		}
		return 0;
	}
}