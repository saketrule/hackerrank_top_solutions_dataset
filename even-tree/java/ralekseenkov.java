import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Solution {

    // leave empty to read from stdin/stdout
    private static final String TASK_NAME_FOR_IO = "";

    // file names
    private static final String FILE_IN = TASK_NAME_FOR_IO + ".in";
    private static final String FILE_OUT = TASK_NAME_FOR_IO + ".out";

    BufferedReader in;
    PrintWriter out;
    StringTokenizer tokenizer = new StringTokenizer("");

    public static void main(String[] args) {
        new Solution().run();
    }

    int n;
    int[][] a;
    boolean[] was;
    int answer;

    private void solve() throws IOException {
        n = nextInt();
        a = new int[n][n];
        int m = nextInt();
        for (int k = 0; k < m; k++) {
            int u = nextInt() - 1;
            int v = nextInt() - 1;
            a[u][v] = 1;
            a[v][u] = 1;
        }

        answer = 0;
        was = new boolean[n];
        for (int i = 0; i < n; i++)
            if (!was[i]) {
                rec(i, -1);
            }

        out.print(answer);
    }

    private int rec(int u, int prevU) {
        was[u] = true;
        int cnt = 1;
        for (int v = 0; v < n; v++)
            if (a[u][v] > 0 && v != prevU) {
                cnt += rec(v, u);
            }

        if ((cnt & 1) == 0 && (prevU >= 0)) {
            answer++;
        }

        return cnt;
    }

    public void run() {
        long timeStart = System.currentTimeMillis();

        boolean fileIO = TASK_NAME_FOR_IO.length() > 0;
        try {

            if (fileIO) {
                in = new BufferedReader(new FileReader(FILE_IN));
                out = new PrintWriter(new FileWriter(FILE_OUT));
            } else {
                in = new BufferedReader(new InputStreamReader(System.in));
                out = new PrintWriter(new OutputStreamWriter(System.out));
            }

            solve();

            in.close();
            out.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        long timeEnd = System.currentTimeMillis();

        if (fileIO) {
            System.out.println("Time spent: " + (timeEnd - timeStart) + " ms");
        }
    }

    private String nextToken() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            String line = in.readLine();
            if (line == null) {
                return null;
            }
            tokenizer = new StringTokenizer(line);
        }
        return tokenizer.nextToken();
    }

    private int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    private BigInteger nextBigInt() throws IOException {
        return new BigInteger(nextToken());
    }

    private long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    private double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }

}