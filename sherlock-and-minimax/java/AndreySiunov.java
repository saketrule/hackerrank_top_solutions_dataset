import java.io.*;
import java.math.BigInteger;
import java.util.*;

@SuppressWarnings("unused")
public class Solution {
    public static void main(String[] args) throws IOException {
        boolean isDebugMode = Arrays.asList(args).contains("DEBUG_MODE");

        InputStream inputStream;
        OutputStream outputStream;

        if (isDebugMode) {
//            inputStream = new ConsoleInputStream();
            inputStream = new FileInputStream();

//            outputStream = new FileOutputStream();
            outputStream = new ConsoleOutputStream();
        } else {
            inputStream = new ConsoleInputStream();
            outputStream = new ConsoleOutputStream();
        }

        inputStream.open();
        outputStream.open();

        new Solution().run(inputStream, outputStream, isDebugMode);

        outputStream.close();
        inputStream.close();
    }

    @SuppressWarnings("FieldCanBeLocal")
    private InputStream in;
    private OutputStream out;
    private boolean isDebugMode;
    private Timer timer = new Timer();

    private void printInDebug(String s) throws IOException {
        if (isDebugMode) {
            out.println(s);
            out.flush();
        }
    }

    private void printTimer(String mark) throws IOException {
        printInDebug(mark + ": " + timer.getMillisAndReset() + " ms.");
    }

    private static String formatDouble(double n, int precision) {
        return String.format(Locale.ENGLISH, "%." + precision + "f", n);
    }

    private void run(InputStream in, OutputStream out, boolean isDebugMode) throws IOException {
        this.in = in;
        this.out = out;
        this.isDebugMode = isDebugMode;

//        int t = in.nextInt();
//        for (int i = 0; i < t; i++) {
        solve();
//            out.flush();
//        }
    }

    int n, p, q;
    int[] a;

    private void solve() throws IOException {
        n = in.nextInt();
        a = in.nextIntArray(n);
        p = in.nextInt();
        q = in.nextInt();

        Arrays.sort(a);

        ans = 0;
        anss = a[0];

        check(p);
        check(q);
        for (int i = 1; i < n; i++) {
            check((a[i] + a[i - 1]) / 2);
            check((a[i] + a[i - 1]) / 2 - 1);
            check((a[i] + a[i - 1]) / 2 + 1);
        }

        out.println(anss);
    }

    void check(int num) {
        if (num < p || num > q) return;
        int mn = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            mn = Math.min(mn, Math.abs(a[i] - num));
        }
        if (mn > ans || (mn == ans && anss > num)) {
            ans = mn;
            anss = num;
        }
    }

    int ans = 0;
    int anss = 0;

    
    /*
     * Template classes
     * Author: Andrey Siunov
     * Date: 29.08.2013
     * Note: all classes are inner, because some testing servers do not run code with several classes
     */

    private static class SegmentTree {
        public static interface MergeFunction {
            int calcValue(int first, int second);
        }

        public static interface UpdateFunction {
            int calcValue(int value);
        }

        private MergeFunction mergeFunction;
        private int n;
        private int[] values;

        public SegmentTree(int[] collection, MergeFunction mergeFunction) {
            this.mergeFunction = mergeFunction;
            n = collection.length;
            values = new int[n * 4];
            build(collection, 1, 0, n - 1);
        }

        private void build(int[] collection, int v, int tl, int tr) {
            if (tl == tr) {
                values[v] = collection[tl];
            } else {
                int tm = (tl + tr) >> 1;
                build(collection, v << 1, tl, tm);
                build(collection, (v << 1) + 1, tm + 1, tr);
                values[v] = mergeFunction.calcValue(values[v << 1], values[(v << 1) + 1]);
            }
        }

        private UpdateFunction updateFunction;

        public void update(int pos, UpdateFunction updateFunction) {
            this.updateFunction = updateFunction;
            update(1, 0, n - 1, pos);
        }

        public void update(int v, int tl, int tr, int pos) {
            if (tl == tr) {
                values[v] = updateFunction.calcValue(values[v]);
            } else {
                int tm = (tl + tr) >> 1;
                if (pos <= tm) {
                    update(v << 1, tl, tm, pos);
                } else {
                    update((v << 1) + 1, tm + 1, tr, pos);
                }
                values[v] = mergeFunction.calcValue(values[v << 1], values[(v << 1) + 1]);
            }
        }

        public int getValue(int index) {
            return getValue(index, index);
        }

        public int getValue(int l, int r) {
            return getValue(1, 0, n - 1, l, r);
        }

        private Integer getValue(int v, int tl, int tr, int l, int r) {
            if (l > r) {
                return null;
            }
            if (l == tl && r == tr) {
                return values[v];
            }
            int tm = (tl + tr) >> 1;
            Integer leftValue = getValue(v << 1, tl, tm, l, Math.min(r, tm));
            Integer rightValue = getValue((v << 1) + 1, tm + 1, tr, Math.max(l, tm + 1), r);
            return leftValue == null ? rightValue :
                    (rightValue == null ? leftValue : mergeFunction.calcValue(leftValue, rightValue));
        }
    }


    private static class Pair<K, V> {
        private K key;
        private V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        K getKey() {
            return key;
        }

        V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            return !(key != null ? !key.equals(pair.key) : pair.key != null) && !(value != null ? !value.equals(pair.value) : pair.value != null);
        }

        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    private static class Timer {
        private long lastTime = 0;

        private Timer() {
            lastTime = System.currentTimeMillis();
        }

        public void reset() {
            lastTime = System.currentTimeMillis();
        }

        public long getMillisAndReset() {
            long current = System.currentTimeMillis();
            long result = current - lastTime;
            lastTime = current;
            return result;
        }
    }

    // IO template {
    private static class FileInputStream extends InputStream {
        private String inputFileName;

        public FileInputStream() throws IOException {
            this("input.txt");
        }

        public FileInputStream(String inputFileName) throws IOException {
            this.inputFileName = inputFileName;
        }

        @Override
        protected Reader getReader() throws IOException {
            return new FileReader(inputFileName);
        }
    }

    private static class ConsoleInputStream extends InputStream {
        @Override
        protected Reader getReader() throws IOException {
            return new InputStreamReader(System.in);
        }
    }

    private static abstract class InputStream {
        private static String DELIMITERS = " \t\n\r\f";
        private BufferedReader in;

        public InputStream open() throws IOException {
            in = new BufferedReader(getReader());
            return this;
        }

        private class Line {
            private Line(String inputLine) {
                this.inputLine = inputLine;
                stringTokenizer = new StringTokenizer(this.inputLine, DELIMITERS);
                readCharacters = 0;
            }

            private int readCharacters;
            private String inputLine = null;
            private StringTokenizer stringTokenizer = null;

            public String nextToken() {
                String result = stringTokenizer.nextToken();
                readCharacters += result.length();
                return result;
            }

            boolean hasNextToken() {
                return stringTokenizer.hasMoreTokens();
            }

            String getLineRest() {
                int position = 0;
                for (int remain = readCharacters; remain > 0; position++) {
                    if (DELIMITERS.indexOf(inputLine.charAt(position)) < 0) {
                        remain--;
                    }
                }
                return inputLine.substring(position);
            }
        }

        private Line currentLine = null;

        abstract protected Reader getReader() throws IOException;

        /**
         * Note: may be incorrect behavior if use this method with hasNextToken method
         */
        public String nextLine() throws IOException {
            setInputLine();
            if (currentLine == null) {
                return null;
            }
            String result = currentLine.getLineRest();
            currentLine = null;
            return result;
        }

        public boolean hasNextLine() throws IOException {
            setInputLine();
            return currentLine != null;
        }

        public String nextToken() throws IOException {
            return hasNextToken() ? currentLine.nextToken() : null;
        }

        /**
         * Note: may be incorrect behavior if use this method with nextLine method
         */
        public boolean hasNextToken() throws IOException {
            while (true) {
                setInputLine();
                if (currentLine == null || currentLine.hasNextToken()) {
                    break;
                } else {
                    currentLine = null;
                }
            }
            return currentLine != null;
        }

        public int nextInt() throws IOException {
            return Integer.valueOf(this.nextToken());
        }

        public long nextLong() throws IOException {
            return Long.valueOf(this.nextToken());
        }

        public double nextDouble() throws IOException {
            return Double.valueOf(this.nextToken());
        }

        public BigInteger nextBigInteger() throws IOException {
            return new BigInteger(this.nextToken());
        }


        public String[] nextTokensArray(int n) throws IOException {
            String[] result = new String[n];
            for (int i = 0; i < n; i++) {
                result[i] = this.nextToken();
            }
            return result;
        }

        public int[] nextIntArray(int n) throws IOException {
            int[] result = new int[n];
            for (int i = 0; i < n; i++) {
                result[i] = this.nextInt();
            }
            return result;
        }

        public long[] nextLongArray(int n) throws IOException {
            long[] result = new long[n];
            for (int i = 0; i < n; i++) {
                result[i] = this.nextLong();
            }
            return result;
        }

        public void close() throws IOException {
            currentLine = null;
            in.close();
        }

        private void setInputLine() throws IOException {
            if (currentLine == null) {
                String line = in.readLine();
                if (line != null) {
                    currentLine = new Line(line);
                }
            }
        }
    }

    private static class FileOutputStream extends OutputStream {
        private String outputFileName;

        public FileOutputStream() throws IOException {
            this("output.txt");
        }

        public FileOutputStream(String outputFileName) throws IOException {
            this.outputFileName = outputFileName;
        }

        @Override
        protected Writer getWriter() throws IOException {
            return new FileWriter(outputFileName);
        }
    }

    private static class ConsoleOutputStream extends OutputStream {
        @Override
        protected Writer getWriter() throws IOException {
            return new OutputStreamWriter(System.out);
        }
    }

    private static abstract class OutputStream {
        private PrintWriter out;

        public OutputStream open() throws IOException {
            out = new PrintWriter(getWriter());
            return this;
        }

        abstract protected Writer getWriter() throws IOException;

        public void print(Object... s) {
            for (Object token : s) {
                out.print(token);
            }
        }

        public void println(Object... s) {
            print(s);
            out.println();
        }

        public void println() {
            out.println();
        }

        public void flush() throws IOException {
            out.flush();
        }

        public void close() throws IOException {
            out.flush();
            out.close();
        }
    }
    // } IO template
}