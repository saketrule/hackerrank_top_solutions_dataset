import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws Exception{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.valueOf(br.readLine());
        for (int n=0;n<t;n++) {
            String params[] = br.readLine().split(" ");
        String y[] = br.readLine().split(" ");
        String x[] = br.readLine().split(" ");
        Long yval[] = new Long[y.length];
        Long xval[] = new Long[x.length];
        for (int i = 0;i<y.length;i++) {
            yval[i] = Long.valueOf(y[i]);
        }
        for (int i = 0;i<x.length;i++) {
            xval[i] = Long.valueOf(x[i]);
        }
        Arrays.sort(yval, Collections.reverseOrder());
        Arrays.sort(xval, Collections.reverseOrder());
        int yindx=0;
        int xindx=0;
        long cost=0;
        while (yindx<yval.length && xindx<xval.length) {
            if (yval[yindx] > xval[xindx]) {
                cost+=yval[yindx]*(xindx+1);
                yindx+=1;
            } else {
                cost+=xval[xindx]*(yindx+1);
                xindx+=1;
            }
        }
        while (yindx<yval.length) {
            cost+=yval[yindx]*(xindx+1);
            yindx+=1;
        }
        while (xindx<xval.length) {
            cost+=xval[xindx]*(yindx+1);
            xindx+=1;
        }
        System.out.println((cost % (1000000000+7)));
        }
    }
}