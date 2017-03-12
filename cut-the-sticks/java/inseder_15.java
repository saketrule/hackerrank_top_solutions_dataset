
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws NumberFormatException,
			IOException {

		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in), 1024 * 1024 * 2);

		int n = Integer.parseInt(br.readLine());
		int v[] = readArrayLine(br.readLine(), n);
		int x[] = new int[1001];
		for (int i = 0; i < n; i++) {
			x[v[i]]++;
		}
		StringBuilder sb = new StringBuilder();
		int val = n;
		for (int i = 0; i <= 1000; i++) {
			if (x[i] > 0) {
				sb.append(val + "\n");
				val -= x[i];
			}
		}
		System.out.println(sb.toString());
	}

	public static int[] readArrayLine(String line, int n) {
		int[] ret = new int[n];
		int start = 0;
		int end = line.indexOf(' ', start);
		for (int i = 0; i < n; i++) {
			if (end > 0)
				ret[i] = Integer.parseInt(line.substring(start, end));
			else
				ret[i] = Integer.parseInt(line.substring(start));
			start = end + 1;
			end = line.indexOf(' ', start);
		}
		return ret;
	}
}
