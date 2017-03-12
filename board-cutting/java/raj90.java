
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Solution {
    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer stk;

    public static void main(String[] args) throws Exception {
        in = new BufferedReader(new InputStreamReader(System.in));
        //in = new BufferedReader(new FileReader("in.txt"));
        out = new PrintWriter(System.out);
        stk = new StringTokenizer(in.readLine());
        int i,j,k,l,m,n,t,x1,y1;long sum=0,x2,y2,x[],y[];
        t=ni();
        for(i=0;i<t;i++){
            m=ni();
            n=ni();
            x=new long[m-1];
            y=new long[n-1];
            for(j=0;j<m-1;j++)
                x[j]=ni();
            for(j=0;j<n-1;j++)
                y[j]=ni();
            Arrays.sort(x);
            Arrays.sort(y);
            sum=0;
            x1=m-2;y1=n-2;x2=1;y2=1;
            for(j=0;j<m+n-2;j++){
                if(x1>-1&&y1>-1){
                    if((x[x1])>(y[y1])){
                         sum+=(y2*x[x1]);
                          x2++;
                        x1--;
                    }
                    else{
                       sum+=(x2*y[y1]);
                    //System.out.println("y1= "+y1+"x2= "+x2);
                    y2++;
                    y1--; 
                    }
                }
                else if(x1<0){
                     sum+=(x2*y[y1]);
                    //System.out.println("y1= "+y1+"x2= "+x2);
                    y2++;
                    y1--; 
                }
                else if(y1<0){
                     sum+=(y2*x[x1]);
                          x2++;
                        x1--;
                }
                /*if(x1>-1){
                    if(y1>-1&&(x[x1])>(y[y1])){
                        sum+=(y2*x[x1]);
                        x2++;
                    x1--;
                    }
                    
                    else{
                        sum+=(y2*x[x1]);
                    System.out.println("x1= "+x1+"y2= "+y2);
                    x2++;
                    x1--;
                    }
                }
                else{
                    sum+=(x2*y[y1]);
                    System.out.println("y1= "+y1+"x2= "+x2);
                    y2++;
                    y1--;
                }*/
                sum=sum%1000000007;
            }
            out.println(sum);
        }
        out.flush();
}
static void printf(String format, Object... args) {
        System.out.printf(format, args);
        out.printf(format, args);
    }

    static int ni() throws Exception {
        while (!stk.hasMoreTokens()) {
            stk = new StringTokenizer(in.readLine());
        }
        return Integer.parseInt(stk.nextToken());
    }

    static long nl() throws Exception {
        while (!stk.hasMoreTokens()) {
            stk = new StringTokenizer(in.readLine());
        }
        return Long.parseLong(stk.nextToken());
    }

    static double nd() throws Exception {
        while (!stk.hasMoreTokens()) {
            stk = new StringTokenizer(in.readLine());
        }
        return Double.parseDouble(stk.nextToken());
    }

    static String ns() throws Exception {
        while (!stk.hasMoreTokens()) {
            stk = new StringTokenizer(in.readLine());
        }
        return stk.nextToken();
    }

    static int min(int a, int b) {
        return a < b ? a : b;
    }

    static int max(int a, int b) {
        return a > b ? a : b;
    }

    static long min(long a, long b) {
        return a < b ? a : b;
    }

    static long max(long a, long b) {
        return a > b ? a : b;
    }

    static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}


