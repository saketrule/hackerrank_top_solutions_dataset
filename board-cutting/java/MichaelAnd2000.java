import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
public class Solution {
	private static final long MODVAL = 1000000007;
	static BufferedReader in = new BufferedReader(new InputStreamReader(
			System.in));
	static StringBuilder out = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		int numT = Integer.parseInt(in.readLine());
		
		for(int t = 0; t < numT; t ++)
		{
			String line = in.readLine();
			String[] data = line.split("\\s+");
			int m = Integer.parseInt(data[0])-1;
			int n = Integer.parseInt(data[1])-1;
			long[] mList = new long[m];
			long[] nList = new long[n];
			
			line = in.readLine();
			data = line.split("\\s+");
			for(int i = 0; i < m; i ++)
			{
				mList[i] = Integer.parseInt(data[i]);
			}
			line = in.readLine();
			data = line.split("\\s+");
			for(int i = 0; i < n; i ++)
			{
				nList[i] = Integer.parseInt(data[i]);
			}
			
			Arrays.sort(mList);
			Arrays.sort(nList);
			
			int curM = m-1;
			int curN = n-1;
			
			int mMul = 1;
			int nMul = 1;
			
			long total = 0;
			
			while(curM>=0 || curN>=0)
			{
				if(curN<0 || (curM>=0 && mList[curM]>nList[curN]))
				{
					total += mMul*mList[curM];
					total %= MODVAL;
					curM--;
					nMul++;
				}
				else
				{
					total += nMul*nList[curN];
					total %= MODVAL;
					curN--;
					mMul++;
				}
			}
			
			out.append(total + "\n");
		}
		System.out.println(out);
	}
}
