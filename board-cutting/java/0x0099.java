import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t-- > 0) {
			int m = sc.nextInt() - 1;
			int n = sc.nextInt() - 1;
			Heap vertical = new Heap(m);
			Heap horizontal = new Heap(n);
			for(int i=0; i<m; i++) vertical.read(sc.nextInt());
			for(int i=0; i<n; i++) horizontal.read(sc.nextInt());
			vertical.heapify();
			horizontal.heapify();
			int verticalParts = 1, horizontalParts = 1;
			int verticalMax = vertical.pop();
			int horizontalMax = horizontal.pop();
			long total = 0;
			for(int i=1; i<m+n; i++) {
				if(verticalParts == m+1 || horizontalParts == n+1) break;
				if(verticalMax > horizontalMax) {
					long mul = horizontalParts;
					mul = mul * verticalMax;
					total = (total + mul) % MOD;
					verticalParts++;
					verticalMax = vertical.pop();
				} else if(verticalMax < horizontalMax) {
					long mul = verticalParts;
					mul = mul * horizontalMax;
					total = (total + mul) % MOD;
					horizontalParts++;
					horizontalMax = horizontal.pop();
				} else {
					if(m > n) {
						long mul = horizontalParts;
						mul = mul * verticalMax;
						total = (total + mul) % MOD;
						verticalParts++;
						verticalMax = vertical.pop();
					} else {
						long mul = verticalParts;
						mul = mul * horizontalMax;
						total = (total + mul) % MOD;
						horizontalParts++;
						horizontalMax = horizontal.pop();
					}
				}
			}
			while(verticalParts <= m) {
				long mul = horizontalParts;
				mul = mul * verticalMax;
				total = (total + mul) % MOD;
				verticalParts++;
				verticalMax = vertical.pop();
			}
			while(horizontalParts <= n) {
				long mul = verticalParts;
				mul = mul * horizontalMax;
				total = (total + mul) % MOD;
				horizontalParts++;
				horizontalMax = horizontal.pop();
			}
			System.out.println(total);
		}
	}
	
	static int MOD = 1000000007;

	static class Heap {
		int[] data;
		int size;
		int pos;

		Heap(int size) {
			this.data = new int[size + 1];
			this.size = size;
			this.pos = 1;
		}

		void read(int i) {
			data[pos++] = i;
		}

		void heapify() {
			int c = (size >>> 1) | 1;
			while (c > 0)
				heapify(c--);
		}

		void heapify(int b) {
			while (true) {
				int min = b;
				int lc = b << 1;
				int rc = lc | 1;
				if (lc <= size && data[lc] > data[min]) min = lc;
				if (rc <= size && data[rc] > data[min]) min = rc;
				if (min == b) break;
				int temp = data[b];
				data[b] = data[min];
				data[min] = temp;
				b = min;
			}
		}

		int pop() {
			int temp = data[1];
			data[1] = data[size];
			data[size--] = temp;
			heapify(1);
			return data[size + 1];
		}
	}
}