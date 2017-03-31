import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static int findFirst(long[] nums, int from, int to, long target) {
		while (from <= to) {
			int m = (from + to) / 2;
			if (nums[m] >= target) to = m - 1;
			else from = m+1;
		}
		return from;
	}
    
    public static int search(long[] A, int from, int to, long low, long hi) {
        if ((hi-low)%2 != 0) return 0;
        if (from >= to) return 0;
        int pos = findFirst(A,from,to, (low+hi)/2);
        if (pos < to && A[pos] == (low+hi)/2) {
            return 1 + Math.max(search(A, from, pos, low, (low+hi)/2), search(A, pos+1, to, (low+hi)/2, hi));
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        while (T-->0) {
            int N = in.nextInt();
            long[] A = new long[N];
            A[0] = (long)in.nextInt();
            for (int i = 1; i < N; i++) {
                long x = (long)in.nextInt();
                A[i] = A[i-1] + x;
            }
            int result = search(A, 0, N-1, 0, A[N-1]);
            System.out.println(result);
        }
    }
}