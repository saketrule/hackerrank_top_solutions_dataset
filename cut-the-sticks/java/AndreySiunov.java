import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
//        InputStream inputStream = new FileInputStream().open();
        InputStream inputStream = new ConsoleInputStream().open();
//        OutputStream outputStream = new FileOutputStream().open();
        OutputStream outputStream = new ConsoleOutputStream().open();
        new Solution().run(inputStream, outputStream);
        outputStream.close();
        inputStream.close();
    }

    InputStream in;
    OutputStream out;

    protected void run(InputStream in, OutputStream out) throws IOException {
        this.in = in;
        this.out = out;

//        int t = in.nextInt();
//        for (int i = 0; i < t; i++) {
        solve();
//        }
    }

    private void solve() throws IOException {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        while (a.length > 0) {
            out.println(a.length);
            int mn = a[0];
            for (int i = 0; i < a.length; i++) {
                mn = Math.min(mn, a[i]);
            }
            List<Integer> aa = new LinkedList<Integer>();
            for (int i = 0; i < a.length; i++) {
                a[i] -= mn;
                if (a[i] > 0) aa.add(a[i]);
            }
            a = new int[aa.size()];
            int i = 0;
            for (Integer an : aa) {
                a[i] = an;
                i++;
            }
        }
    }
}

// template beginning
// Author: Andrey Siunov
// Date: 29.08.2013

class FileInputStream extends InputStream {
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

class ConsoleInputStream extends InputStream {
    @Override
    protected Reader getReader() throws IOException {
        return new InputStreamReader(System.in);
    }
}

abstract class InputStream {
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
        return new Integer(this.nextToken());
    }

    public long nextLong() throws IOException {
        return new Long(this.nextToken());
    }

    public double nextDouble() throws IOException {
        return new Double(this.nextToken());
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

class FileOutputStream extends OutputStream {
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

class ConsoleOutputStream extends OutputStream {
    @Override
    protected Writer getWriter() throws IOException {
        return new OutputStreamWriter(System.out);
    }
}

abstract class OutputStream {
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


class Pair<K, V> {
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

        if (key != null ? !key.equals(pair.key) : pair.key != null) return false;
        if (value != null ? !value.equals(pair.value) : pair.value != null) return false;

        return true;
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