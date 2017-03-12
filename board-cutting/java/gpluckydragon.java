import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in=new Scanner(System.in);
    	int T=in.nextInt();
    	for(int index=0;index<T;index++){
    		int M=in.nextInt();
    		int N=in.nextInt();
    		int[] x=new int[M-1];
    		int[] y=new int[N-1];
    		for(int i=0;i<M-1;i++){
    			x[i]=in.nextInt();
    		}
    		for(int i=0;i<N-1;i++){
    			y[i]=in.nextInt();
    		}
    		
    		
    		Arrays.sort(x);
    		Arrays.sort(y);
    		long c=0;
    		int xPiece=1;
    		int yPiece=1;
    		for(int i=0;i<x.length+y.length;i++){
    			if(xPiece==M){
    				c+=(long)y[y.length-yPiece]*(long)xPiece;
    				yPiece++;
    			}
    			else if(yPiece==N){
    				c+=(long)x[x.length-xPiece]*(long)yPiece;
    				xPiece++;
    			}
    			else{
    				if(x[x.length-xPiece]>y[y.length-yPiece]){
    					c+=(long)x[x.length-xPiece]*(long)yPiece;
        				xPiece++;
    				}
    				else{
    					c+=(long)y[y.length-yPiece]*(long)xPiece;
        				yPiece++;
    				}
    			}
    		}
    		System.out.println(c%1000000007);
    	}
    }
}