import java.util.*;
import java.io.*;

public class Solution implements Runnable {
	public static final int MOD = 1000000007;
	public void solve() throws IOException {
		int t = nextInt();
                for(int i = 0; i < t; i++) solve2();
	}
        
        public void solve2() throws IOException{
                int M = nextInt(); int N = nextInt();
                long[] horizontalCost = new long[M - 1];
                long[] verticalCost = new long[N - 1];
                for(int i = 0; i < (M - 1); i++) horizontalCost[i] = nextInt();
                for(int i = 0; i < (N - 1); i++) verticalCost[i] = nextInt();
                 
                Arrays.sort(horizontalCost);
                //reverse
                int x = 0, y = M - 2;
                while(x < y){ long t = horizontalCost[x]; horizontalCost[x] = horizontalCost[y]; horizontalCost[y] = t; x++; y--;}
                
                Arrays.sort(verticalCost);
                //reverse
                x = 0; y = N - 2;
                while(x < y){long t = verticalCost[x]; verticalCost[x] = verticalCost[y]; verticalCost[y] = t; x++; y--;}
                
                int vSegments = 1;
                int hSegments = 1;
                int i = 0, j = 0;
                long ans = 0;
                while(i < horizontalCost.length && j < verticalCost.length){
                        if((horizontalCost[i]) >= (verticalCost[j])){
                                ans += ((horizontalCost[i] * hSegments) % MOD);
                                vSegments++;
                                i++;
                        }
                        else{
                                ans += ((verticalCost[j] * vSegments) % MOD);
                                hSegments++;
                                j++;
                        }
                        ans %= MOD;
                }
                
                while(i < horizontalCost.length){
                        ans += ((horizontalCost[i] * hSegments) % MOD);
                        ans %= MOD;        
                        i++;
                }
                
                while(j < verticalCost.length){
                        ans += ((verticalCost[j] * vSegments) % MOD);
                        ans %= MOD;
                        j++;
                }
                
                System.out.println(ans);
        }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//-----------------------------------------------------------
	public static void main(String[] args) {
		new Solution().run();
	}

        public void print1Int(int[] a){
                for(int i = 0; i < a.length; i++)
                        System.out.print(a[i] + " ");
                System.out.println();
        }
        
        public void print2Int(int[][] a){
                for(int i = 0; i < a.length; i++){
                        for(int j = 0; j < a[0].length; j++){
                                System.out.print(a[i][j] + " ");
                        }
                        System.out.println();
                }
        }
        
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			tok = null;
			solve();
			in.close();
		} catch (IOException e) {
			System.exit(0);
		}
	}

	public String nextToken() throws IOException {
		while (tok == null || !tok.hasMoreTokens()) {
			tok = new StringTokenizer(in.readLine());
		}
		return tok.nextToken();
	}

	public int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	public long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}

	public double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}

	BufferedReader in;
	StringTokenizer tok;
}