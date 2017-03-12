
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Solution {

	int M;
	int N;
	Long[] arrayM;
	Long[] arrayN;
	long Horizontalcuts = 0;
	long VerticalCuts = 0;
	double cost = 0;
	static double modVal = ( Math.pow(10, 9) + 7);

	public Solution(int m, int n, Scanner sc) {
		this.M = m;
		this.N = n;

		arrayM = new Long[M-1];
		for (int i = 0; i < this.M-1 ; i++) {
			arrayM[i] = sc.nextLong();
		}

		arrayN = new Long[N-1];
		for (int i = 0; i < this.N-1 ; i++) {
			arrayN[i] = sc.nextLong();
		}

		Arrays.sort(arrayM, new compareValues());
		Arrays.sort(arrayN, Collections.reverseOrder());
	}

	public void Caluculate() {
		int lowM = 0;
		int lowN = 0;
		while (lowM < arrayM.length && lowN < arrayN.length) {
			if (arrayM[lowM] > arrayN[lowN]) {
				VerticalCuts++;
				addValues(arrayM[lowM++], 0);
			} else {
				Horizontalcuts++;
				addValues(arrayN[lowN++], 1);
			}
		}

		while (lowM < arrayM.length) {
			VerticalCuts++;
			addValues(arrayM[lowM++], 0);
		}

		while (lowN < arrayN.length) {
			Horizontalcuts++;
			addValues(arrayN[lowN++], 1);
		}
	}

	private void addValues(long i, long j) {
		if (j == 0) {
			cost += mul(Horizontalcuts + 1, i);
		} else {
			cost += mul(VerticalCuts + 1, i);
		}

		if(cost > modVal){
			cost = cost % modVal;
		}
	}

	private double mul(long val, long a) {

		return (val*a) % modVal;
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int TestCases = sc.nextInt();

		for (int i = 0; i < TestCases; i++) {
			/*
			 * String mn = sc.next(); String[] mnArray = mn.split(" "); int m =
			 * new Integer(mnArray[0]); int n = new Integer(mnArray[1]);
			 */
			int m = sc.nextInt();
			int n = sc.nextInt();
			Solution s = new Solution(m, n, sc);
			s.Caluculate();
			System.out.println((long)(s.cost % modVal));
		}

	}

	public class compareValues implements Comparator<Long> {

		@Override
		public int compare(Long arg0, Long arg1) {

			return arg1.compareTo(arg0);
		}
	}

}
