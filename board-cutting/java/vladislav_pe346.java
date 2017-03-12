import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int t = in.nextInt();
		for (int i = 0; i < t; i++) {
			int m = in.nextInt();
			int n = in.nextInt();
			CostAndIndex[] costs = new CostAndIndex[m + n - 2];
			for (int j = 0; j < m - 1; j++) {
				costs[j] = new CostAndIndex(in.nextInt(), true, j);
			}
			for (int j = m - 1; j < m + n - 2; j++) {
				costs[j] = new CostAndIndex(in.nextInt(), false, j - m + 1);
			}
			System.out.println(solve(m, n, costs));
		}
	}

	public static class CostAndIndex {
		private int cost;
		private boolean horizontal;
		private int index;

		public CostAndIndex(int cost, boolean horizontal, int index) {
			this.cost = cost;
			this.horizontal = horizontal;
			this.index = index;
		}

		public String toString() {
			return "Cost: " + cost + "; horizontal: " + horizontal
					+ "; index: " + index;
		}
	}

	private static BigInteger solve(int m, int n, CostAndIndex[] costs) {
		int verticalSegments = 1;
		int horizontalSegments = 1;
		Arrays.sort(costs, new Comparator<CostAndIndex>() {

			@Override
			public int compare(CostAndIndex o1, CostAndIndex o2) {
				if (o1.cost > o2.cost) {
					return -1;
				} else if (o1.cost < o2.cost) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		BigInteger result = BigInteger.ZERO;
		for (CostAndIndex cai : costs) {
			BigInteger cost = BigInteger.valueOf(cai.cost);
			cost = cost.multiply(cai.horizontal ? BigInteger
					.valueOf(verticalSegments) : BigInteger
					.valueOf(horizontalSegments));
			if (cai.horizontal) {
				horizontalSegments++;
			} else {
				verticalSegments++;
			}
			result = result.add(cost).mod(BigInteger.valueOf(1000000007));
		}
		return result;

	}
}