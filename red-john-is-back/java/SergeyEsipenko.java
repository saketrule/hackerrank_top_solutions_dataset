/* HackerRank Template */

import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Arrays.fill;
import static java.util.Arrays.binarySearch;
import static java.util.Arrays.sort;

public class Solution {
	
	static long initTime;
	static final Random rnd = new Random(7777L);
	static boolean writeLog = false;
	
	public static void main(String[] args) throws IOException {
		initTime = System.currentTimeMillis();
		try {
			writeLog = "true".equals(System.getProperty("LOCAL_RUN_7777"));
		} catch (SecurityException e) {}
		new Thread(null, new Runnable() {
			public void run() {
				try {
					try {
						if (new File("input.txt").exists())
							System.setIn(new FileInputStream("input.txt"));
					} catch (SecurityException e) {}
					long prevTime = System.currentTimeMillis();
					new Solution().run();
					writeLog("Total time: " + (System.currentTimeMillis() - prevTime) + " ms");
					writeLog("Memory status: " + memoryStatus());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, "1", 1L << 24).start(); 
	}

	void run() throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		solve();
		out.close();
		in.close();
	}
	
	/*************************************************************** 
	 * Solution
	 **************************************************************/

	final int MAXN = 40;
	final int C = 4;
	final int[] dp = new int [MAXN + 1];
	
	final int MAXP = 217286;
	final boolean[] sieve = eratosphen(MAXP);
	
	void solve() throws IOException  {
		
		dp[0] = 1;
		for (int i = 1; i <= MAXN; i++) {
			dp[i] += dp[i - 1];
			if (i >= C)
				dp[i] += dp[i - C];
		}
		
		int[] count = new int [MAXP + 2];
		for (int i = 0; i <= MAXP; i++) {
			count[i + 1] += count[i];
			if (!sieve[i])
				count[i + 1]++;
		}
		
		for (int T = nextInt(); T --> 0; ) {
			int n = nextInt();
			out.println(count[dp[n] + 1]);
		}
		
	}
	
	boolean[] eratosphen(int N) {
		int NS = (int) sqrt(N) + 1;
		boolean[] sieve = new boolean [N + 1];
		int[] primes = new int [N];
		int psz = 0;
		primes[psz++] = 2;
		for (int i = 4; i <= N; i += 2)
			sieve[i] = true;
		for (int i = 3; i <= N; i += 2)
			if (!sieve[i]) {
				primes[psz++] = i;
				if (i <= NS)
					for (int j = i * i; j <= N; j += i)
						sieve[j] = true;
			}
		sieve[0] = sieve[1] = true;
		return sieve;
	}
	
	/*************************************************************** 
	 * Input 
	 **************************************************************/
	BufferedReader in;
	PrintWriter out;
	StringTokenizer st = new StringTokenizer("");
	
	String nextToken() throws IOException {
		while (!st.hasMoreTokens())
			st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}
	
	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}
	
	long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}
	
	double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
	
	int[] nextIntArray(int size) throws IOException {
		int[] ret = new int [size];
		for (int i = 0; i < size; i++)
			ret[i] = nextInt();
		return ret;
	}
	
	long[] nextLongArray(int size) throws IOException {
		long[] ret = new long [size];
		for (int i = 0; i < size; i++)
			ret[i] = nextLong();
		return ret;
	}
	
	double[] nextDoubleArray(int size) throws IOException {
		double[] ret = new double [size];
		for (int i = 0; i < size; i++)
			ret[i] = nextDouble();
		return ret;
	}
	
	String nextLine() throws IOException {
		st = new StringTokenizer("");
		return in.readLine();
	}
	
	boolean EOF() throws IOException {
		while (!st.hasMoreTokens()) {
			String s = in.readLine();
			if (s == null)
				return true;
			st = new StringTokenizer(s);
		}
		return false;
	}
	
	/*************************************************************** 
	 * Output 
	 **************************************************************/
	void printRepeat(String s, int count) {
		for (int i = 0; i < count; i++)
			out.print(s);
	}
	
	void printArray(int[] array) {
		if (array == null || array.length == 0)
			return;
		for (int i = 0; i < array.length; i++) {
			if (i > 0) out.print(' ');
			out.print(array[i]);
		}
		out.println();
	}
	
	void printArray(long[] array) {
		if (array == null || array.length == 0)
			return;
		for (int i = 0; i < array.length; i++) {
			if (i > 0) out.print(' ');
			out.print(array[i]);
		}
		out.println();
	}
	
	void printArray(double[] array) {
		if (array == null || array.length == 0)
			return;
		for (int i = 0; i < array.length; i++) {
			if (i > 0) out.print(' ');
			out.print(array[i]);
		}
		out.println();
	}
	
	void printArray(double[] array, String spec) {
		if (array == null || array.length == 0)
			return;
		for (int i = 0; i < array.length; i++) {
			if (i > 0) out.print(' ');
			out.printf(Locale.US, spec, array[i]);
		}
		out.println();
	}
	
	void printArray(Object[] array) {
		if (array == null || array.length == 0)
			return;
		boolean blank = false;
		for (Object x : array) {
			if (blank) out.print(' '); else blank = true;
			out.print(x);
		}
		out.println();
	}
	
	@SuppressWarnings("rawtypes")
	void printCollection(Collection collection) {
		if (collection == null || collection.isEmpty())
			return;
		boolean blank = false;
		for (Object x : collection) {
			if (blank) out.print(' '); else blank = true;
			out.print(x);
		}
		out.println();
	}
	
	/*************************************************************** 
	 * Utility
	 **************************************************************/
	static String memoryStatus() {
		return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() >> 20) + "/" + (Runtime.getRuntime().totalMemory() >> 20) + " MB";
	}
	
	static void checkMemory() {
		System.err.println(memoryStatus());
	}
	
	static long prevTimeStamp = Long.MIN_VALUE;
	
	static void updateTimer() {
		prevTimeStamp = System.currentTimeMillis();
	}
	
	static long elapsedTime() {
		return (System.currentTimeMillis() - prevTimeStamp);
	}
	
	static void checkTimer() {
		System.err.println(elapsedTime() + " ms");
	}
	
	static void chk(boolean f) {
		if (!f) throw new RuntimeException("Assert failed");
	}
	
	static void chk(boolean f, String format, Object ... args) {
		if (!f) throw new RuntimeException(String.format(format, args));
	}
	
	static void writeLog(String format, Object ... args) {
		if (writeLog) System.err.println(String.format(Locale.US, format, args));
	}
}
