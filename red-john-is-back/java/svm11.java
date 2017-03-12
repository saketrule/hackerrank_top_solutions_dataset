import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);

        Task solver = new Task();
        int testCount = in.readInt();
        for (int i = 1; i <= testCount; ++i)
            solver.solve(i, in, out);

        out.close();
    }
}

class Task {
    final int MAXN = 41;

    int[] numberOfWays;
    boolean[] isPrime;
    ArrayList<Integer> primeList;

    public Task() {
        numberOfWays = new int[MAXN];
        numberOfWays[0] = numberOfWays[1] = numberOfWays[2] = numberOfWays[3] = 1;
        for (int i = 4; i < numberOfWays.length; ++i) {
            numberOfWays[i] = numberOfWays[i - 1] + numberOfWays[i - 4];
        }

        isPrime = new boolean[numberOfWays[numberOfWays.length - 1]];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i < isPrime.length; ++i) {
            if (isPrime[i]) {
                for (int j = i * i; j < isPrime.length; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        primeList = new ArrayList<Integer>();
        for (int i = 2; i < isPrime.length; ++i) {
            if (isPrime[i]) {
                primeList.add(i);
            }
        }
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.readInt();
        int m = numberOfWays[n];
        int res = Collections.binarySearch(primeList, m);
        if (res >= 0) {
            ++res;
        }
        else {
            res = -(res + 1);
        }
        out.println(res);
    }
}

class InputReader {
    private BufferedReader reader;
    private StringTokenizer tokenizer;

    public InputReader(InputStream inputStream) {
        reader = new BufferedReader(new InputStreamReader(inputStream));
        tokenizer = null;
    }

    public String readLine() {
        String res = null;
        try {
            res = reader.readLine();
        } catch (IOException e) {
            throw new InputMismatchException();
        }
        return res;
    }

    public String readString() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(readLine());
        }
        return tokenizer.nextToken();
    }

    public int readInt() {
        return Integer.parseInt(readString());
    }

    public long readLong() {
        return Long.parseLong(readString());
    }

    public double readDouble() {
        return Double.parseDouble(readString());
    }
}