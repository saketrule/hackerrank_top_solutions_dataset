import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

public class Solution {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";
	
	static void solve()
	{
		int n = ni(), m = ni();
		int[] from = new int[m];
		int[] to = new int[m];
		for(int i = 0;i < m;i++){
			from[i] = ni()-1;
			to[i] = ni()-1;
		}
		int[][] g = packU(n, from, to);
		int[][] pars = parents(g, 0);
		int[] par = pars[0];
		int[] ord = pars[1];
		
		// 0
		int[][] dp = new int[n][2];
		for(int i = n-1;i >= 0;i--){
			int c = ord[i];
			int u = g[c].length + (i == 0 ? 1 : 0);
			int[][] ldp = new int[u][2];
			ldp[0][0] = -9999999;
			ldp[0][1] = 0;
			int p = 0;
			for(int e : g[c]){
				if(par[c] != e){
					{
						int v = ldp[p][0] + dp[e][0] + 1;
//						v = Math.max(v, ldp[p][0] + dp[e][1] + 1);
						v = Math.max(v, ldp[p][1] + dp[e][1]);
						ldp[p+1][0] = v;
					}
					{
						int v = ldp[p][1] + dp[e][0] + 1;
//						v = Math.max(v, ldp[p][1] + dp[e][1] + 1);
						v = Math.max(v, ldp[p][0] + dp[e][1]);
						ldp[p+1][1] = v;
					}
					p++;
				}
			}
			dp[c] = ldp[u-1];
		}
		out.println(dp[0][0]);
	}
	
	public static int[][] parents(int[][] g, int root) {
		int n = g.length;
		int[] par = new int[n];
		Arrays.fill(par, -1);

		int[] q = new int[n];
		q[0] = root;
		for(int p = 0, r = 1;p < r;p++){
			int cur = q[p];
			for(int nex : g[cur]){
				if(par[cur] != nex){
					q[r++] = nex;
					par[nex] = cur;
				}
			}
		}
		return new int[][] { par, q };
	}
	
	static int[][] packU(int n, int[] from, int[] to) {
		int[][] g = new int[n][];
		int[] p = new int[n];
		for(int f : from)
			p[f]++;
		for(int t : to)
			p[t]++;
		for(int i = 0;i < n;i++)
			g[i] = new int[p[i]];
		for(int i = 0;i < from.length;i++){
			g[from[i]][--p[from[i]]] = to[i];
			g[to[i]][--p[to[i]]] = from[i];
		}
		return g;
	}
	
	public static void main(String[] args) throws Exception
	{
		long S = System.currentTimeMillis();
		is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);
		
		solve();
		out.flush();
		long G = System.currentTimeMillis();
		tr(G-S+"ms");
	}
	
	static boolean eof()
	{
		try {
			is.mark(1000);
			int b;
			while((b = is.read()) != -1 && !(b >= 33 && b <= 126));
			is.reset();
			return b == -1;
		} catch (IOException e) {
			return true;
		}
	}
		
	static int ni()
	{
		try {
			int num = 0;
			boolean minus = false;
			while((num = is.read()) != -1 && !((num >= '0' && num <= '9') || num == '-'));
			if(num == '-'){
				num = 0;
				minus = true;
			}else{
				num -= '0';
			}
			
			while(true){
				int b = is.read();
				if(b >= '0' && b <= '9'){
					num = num * 10 + (b - '0');
				}else{
					return minus ? -num : num;
				}
			}
		} catch (IOException e) {
		}
		return -1;
	}
	
	static void tr(Object... o) { if(INPUT.length() != 0)System.out.println(Arrays.deepToString(o)); }
}
