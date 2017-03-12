import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Solution {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskA solver = new TaskA();
		solver.solve(1, in, out);
		out.close();
	}
}

class TaskA {
    int dp[]=new int[42];
    int v=217290;
    int pd[]=new int[v];
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        Arrays.fill(dp,-1);
        primes();

        int t=in.nextInt();
         for(int i=0;i<t;i++){
             in.readLine();
             int n=in.nextInt();

             out.println(pd[go(n)]);
         }
    }


    int primes(){
        ArrayList<Integer> al=new ArrayList<Integer>(v/2);
        out:for(int i=2;i<v;i++){
            for(int j=0;j<al.size();j++){
                if(i%al.get(j)==0){
                    pd[i]=pd[i-1];
                    continue out;
                }
            }
            al.add(i);
            pd[i]=pd[i-1]+1;
        }
        return 0;
    }


    int go(int n){

        if(n<=0){
            return 1;
        }
        if(n<4)return 1;
        if(dp[n]!=-1)return dp[n];
        if(n==1){
            return 1;
        }
        else{
            return dp[n]=go(n-1)+go(n-4);
        }
    }

}

class InputReader {
    private BufferedReader br;
    private StringTokenizer st;
    public InputReader(InputStream in) {
        br=new BufferedReader(new InputStreamReader(in));
        try {
            st=new StringTokenizer(br.readLine());
        } catch (IOException ignored) {

        }
    }



    public boolean readLine() {
        try {
            st=new StringTokenizer(br.readLine());

        } catch (IOException e) {
            return false;
        }
        catch (NullPointerException e){
            return false;
        }
        return true;
    }
    public int nextInt(){
        return Integer.parseInt(st.nextToken());
    }


}

