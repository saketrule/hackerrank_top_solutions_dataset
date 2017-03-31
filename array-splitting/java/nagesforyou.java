import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int t = sc.nextInt();
        
        while(t > 0){
            --t;
            int n = sc.nextInt();
            int[] a = new int[n];
            long[] c = new long[n];

            long pre = 0;
            
            for(int i=0; i<n; ++i) {
                a[i] = sc.nextInt();
                pre += a[i];
                c[i] = pre;
            }
        
            System.out.println(solve(a, c, 0, n-1));
        }
    }

    public static int solve(int[] a, long[] c, int s, int e){
        
        long sum = 0;
        
        if (s ==0){
            if (c[e]%2 == 1)
                return 0;
            else
                sum = c[e] / 2;
        }
        
        if ( s > 0){            
            if ((c[e] - c[s-1])%2 == 1)
                return 0;
            else {
                sum = (c[e] + c[s-1]) / 2;
            }
        }
        
        
        if (sum == 0){
        	return e - s;
        }
        	
        
        int mid = binarySearch(c, s, e, sum);
        
        if (mid < 0)
            return 0;
        else {
            return 1 + Math.max(solve(a, c, s, mid), solve(a, c, mid+1, e));
        }        
    }

    public static int binarySearch(long[] c, int s, int e, long val){
        
        if ( s == e){
            if (c[s] == val)
                return s;
            else
                return -1;
        }
        
        if ( s+1 == e){
            
            if (c[s] == val)
                return s;
            else if (c[e] == val)
                return e;
            else
                return -1;
        }
        
        int mid = (s+e) / 2;
        
        if (c[mid] == val)
            return mid;
        else if (c[mid] > val)
            return binarySearch(c, s, mid, val);
        else
            return binarySearch(c, mid, e, val);
    }
}