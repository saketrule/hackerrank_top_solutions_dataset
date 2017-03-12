import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String [] args ) {
		try{
			String str;			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedOutputStream bos = new BufferedOutputStream(System.out);
			String eol = System.getProperty("line.separator");
			byte [] eolb = eol.getBytes();
			byte[] spaceb= " ".getBytes();
			str  = br.readLine();
			int t = Integer.parseInt(str);
			int [] dp = new int[41];
			dp[0] = 0;
			dp[1] = 1;
			dp[2] = 1;
			dp[3] = 1;
			dp[4] = 2;
			for(int i = 5 ; i < 41 ; i++) {
				dp[i] = dp[i-1] + dp[i-4];
			}
			boolean [] isPrime = new boolean[220000];
			for(int i = 0 ; i < 220000 ; i++) {
				isPrime[i] = true;
			}
			isPrime[0] = false;
			isPrime[1] = false;		
			int [] ans = new int[220000];
			ans[0] = 0;
			ans[1] = 0;
			int iter = 2;
			while(iter<220000) {
				if(isPrime[iter]) {
					for(int i = 2*iter ; i < 220000 ; i+=iter) {
						isPrime[i] = false;
					}
					ans[iter] = ans[iter-1] + 1;
				} else {
					ans[iter] = ans[iter-1];
				}
				iter++;
			}
			for(int i = 0 ; i < t ; i++) {
				str  = br.readLine();
				int n = Integer.parseInt(str);
				bos.write(new Integer(ans[dp[n]]).toString().getBytes());
				bos.write(eolb);
			}
			bos.flush();
		}  catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
