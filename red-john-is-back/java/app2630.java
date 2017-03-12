import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
		int T = Integer.parseInt(in.nextLine());
		int[] a = new int[40];
		for (int i = 0; i < 3; i++)
			a[i] = 1;
		a[3] = 2;
		for (int i = 4; i < 40; i++) {
			a[i] = a[i - 1] + a[i - 4];
		}

		int[] p = new int[a[39] + 1];
		p[2] = 1;
		p[3] = 2;
		List<Integer> primes = new ArrayList<Integer>();
		primes.add(2);
		primes.add(3);
		for (int i = 4; i <= a[39]; i++) {
			boolean flag = false;
			for (int j : primes) {
				if (i % j == 0) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				primes.add(i);
				p[i] = p[i - 1] + 1;
			} else
				p[i] = p[i - 1];
		}
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(in.nextLine());

			System.out.println(p[a[N-1]]);
		}
    }
}