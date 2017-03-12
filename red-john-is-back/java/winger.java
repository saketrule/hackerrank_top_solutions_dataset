import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;

public class Solution {

    public static void solve(Input in, PrintWriter out) throws IOException {
        final int maxn = 40;
        int[] d = new int[maxn + 1];
        d[0] = 1;
        for (int i = 1; i <= maxn; ++i) {
            d[i] = d[i - 1];
            if (i >= 4) {
                d[i] += d[i - 4];
            }
        }
        int[] ans = new int[maxn + 1];
        boolean[] prime = new boolean[d[maxn] + 1];
        Arrays.fill(prime, true);
        for (int i = 2; i < prime.length; ++i) {
            if (!prime[i]) {
                continue;
            }
            for (int j = 0; j <= maxn; ++j) {
                if (i <= d[j]) {
                    ans[j]++;
                }
            }
            for (int j = 2 * i; j < prime.length; j += i) {
                prime[j] = false;
            }
        }
        int tests = in.nextInt();
        for (int test = 0; test < tests; ++test) {
            out.println(ans[in.nextInt()]);
        }
    }

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(System.out);
        solve(new Input(new BufferedReader(new InputStreamReader(System.in))), out);
        out.close();
    }

    static class Input {
        BufferedReader in;
        StringBuilder sb = new StringBuilder();

        public Input(BufferedReader in) {
            this.in = in;
        }

        public Input(String s) {
            this.in = new BufferedReader(new StringReader(s));
        }

        public String next() throws IOException {
            sb.setLength(0);
            while (true) {
                int c = in.read();
                if (c == -1) {
                    return null;
                }
                if (" \n\r\t".indexOf(c) == -1) {
                    sb.append((char)c);
                    break;
                }
            }
            while (true) {
                int c = in.read();
                if (c == -1 || " \n\r\t".indexOf(c) != -1) {
                    break;
                }
                sb.append((char)c);
            }
            return sb.toString();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
    }
}
