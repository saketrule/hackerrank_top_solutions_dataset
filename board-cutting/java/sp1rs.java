import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.*;
public class Solution {

    public static void main(String[] args) throws IOException{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        long MOD=1000000007;
        while(t-->0){
            String s=br.readLine();
            String str[]=s.split(" ");
            int m=Integer.parseInt(str[0]);
            int n=Integer.parseInt(str[1]);
        //    int k=0;
            long y[]=new long[m];
            long x[]=new long[n];
            s=br.readLine();
            str=s.split(" ");
            for(int i=1;i<m;i++){
                y[i]=Long.parseLong(str[i-1]);
                
            }
            s=br.readLine();
            str=s.split(" ");
            for(int i=1;i<n;i++){
                x[i]=Long.parseLong(str[i-1]);
                
            }
            Arrays.sort(x);
            Arrays.sort(y);
            long hori_y=1;
            long vert_x=1;
            int k=1;
            int i=m-1;
            int j=n-1;
            long ans=0;
            while(k<=n+m-2){
                
                if(i>=1 && j>=1){
                //  System.out.println(i);
                    if(y[i]>=x[j]){
                        ans=(ans+y[i]*vert_x)%MOD;
                        hori_y+=1;
                        i-=1;
                    }else{
                        ans=(ans+x[j]*hori_y)%MOD;
                        vert_x+=1;
                        j-=1;
                        
                    }
                    
                }else if(i>=1){
                    ans=(ans+y[i]*vert_x)%MOD;
                    hori_y+=1;
                    i-=1;
                    
                }else if(j>=1){
                    ans=(ans+x[j]*hori_y)%MOD;
                    vert_x+=1;
                    j-=1;
                }
                k+=1;
                
            }
            System.out.println(ans);
        }
    }
}