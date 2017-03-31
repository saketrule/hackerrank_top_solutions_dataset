import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

	 private static class BinaryIndexedTree{
			private final int[] a;
			private final int maxIndex;
			BinaryIndexedTree(int maxIndex){
				this.maxIndex = maxIndex ;
				a = new int[maxIndex +1];
			}
			/**
			 * Add value to bucket of index idx.
			 * @param val
			 * @param idx
			 */
			void add(int val, int idx){
				while(idx <= maxIndex){
					a[idx] +=val;
					idx += (idx & -idx);
				}
			}
			/**
			 * Get the sum of sub array from 0 to idx (inclusive)
			 * @param idx
			 * @return
			 */
			int getSum(int idx){
				idx = Math.min(idx, maxIndex);
				idx = Math.max(idx, 0);
				int sum = 0;
				while(idx !=0){
					sum += a[idx];
					idx -= (idx & -idx);
				}
				return sum;
			}
	        /**
			 * Get the sum of sub array from i to j (exclusive)
			 * @param idx
			 * @return
			 */
			int getSum(int i, int j){
				return getSum(j -1) - getSum(i -1);
			}
		}
	    
	    static int best(int[] a){
	        BinaryIndexedTree b = new BinaryIndexedTree(a.length);
	        long total = 0;
	        for(int i = 0;i<a.length;i++){
//	            b.add(a[i],i +1);
	            total += a[i];
	        }
	        return best(b, a, 0, a.length,total);
	        
	    }
	    
	    
	    static int best(BinaryIndexedTree b , int[] a, int from, int to,long total){
	        if((to - from) <= 1){
	            return 0;
	        }

	        int mid = from +1;
	        
	        long right = a[from]; long left = total - right; 
	        while(mid < to){
	            if(right == left){
	                return Math.max(best(b,a, from,mid, right),best(b,a,mid,to,left)) + 1;
	            }
	            right += a[mid];
	            left -= a[mid];
	            mid++;
	        }
	        return 0;
	    }

    public static void main(String[] args) {
         Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t > 0){
            int N = in.nextInt();
            int[] a = new int[N];
            for(int i =0;i<N;i++){
                a[i] = in.nextInt();
            }
            System.out.println(best(a));
            t--;
        }
    }
}