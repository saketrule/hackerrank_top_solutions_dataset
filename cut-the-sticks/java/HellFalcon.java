import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in), 128 << 10));
		int n = in.nextInt();
		int[] counts = new int[1001];
		for (int i = 0; i < n; i++) counts[in.nextInt()]++;
		for (int i = 0; i <= 1000; i++) {
			if (counts[i] > 0) {
				System.out.println(n);
				n -= counts[i];
			}
		}
	}

}