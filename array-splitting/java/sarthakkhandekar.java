import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw=new PrintWriter(System.out);
        int t=Integer.parseInt(br.readLine().trim());
        while(t-->0){
            int n=Integer.parseInt(br.readLine().trim());
            String s[]=br.readLine().trim().split(" ");
            long sum=0;
            int a[]=new int[n];
            for(int i=0;i<n;i++){
                a[i]=Integer.parseInt(s[i]);
                sum+=a[i];
            }
            pw.println(r(a,n,0,n,sum));
            
        }
        pw.close();
    }
    static long r(int a[],int n,int start, int end,long sum){
        if(sum==0){
            return end-start-1;
        }
        if(sum%2==1)
            return 0;
        int be=n;
        long temp=0;
        for(int i=start;i<end;i++){
            temp+=a[i];
            if(temp==sum/2){
                be=i;
                break;
            }
        }
        
        if(be<n){
            return 1+Math.max(r(a,n,start,be+1,sum/2),r(a,n,be+1,end,sum/2));
        }
        return 0;
    }
}