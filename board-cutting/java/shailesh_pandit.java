import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            Integer[] yi = new Integer[m-1];
            Integer[] xi = new Integer[n-1];
            
            for(int j=0;j<m-1;j++){
                yi[j]= sc.nextInt();
            }
            for(int j=0;j<n-1;j++){
                xi[j]= sc.nextInt();
            }
            Arrays.sort(yi,Collections.reverseOrder());
            Arrays.sort(xi,Collections.reverseOrder());
            
            int ny=1,nx=1;
            long c=0;
            
            while(ny<m || nx<n) {
                if(ny<m && (nx>=n || yi[ny-1]>xi[nx-1])) {
                    c= (c + ((long)nx*(long)yi[ny-1])%1000000007)%1000000007;
                    ny++;
                } else if(nx<n && (ny>=m || xi[nx-1]>=yi[ny-1])) {
                    c= (c + ((long)ny*(long)xi[nx-1])%1000000007)%1000000007;
                    nx++;
                }
            }
            
            System.out.println(c);
        }
    }
}