import java.util.List;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.StringTokenizer;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author Lokesh Khandelwal
 */
public class Solution {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		OutputWriter out = new OutputWriter(outputStream);
		Bricks solver = new Bricks();
		solver.solve(1, in, out);
		out.close();
	}
}

class Bricks {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int answers[]=new int[41];
        int i,j;
        for(i=4;i<=40;i++)
        {
            answers[i]=recur(0,i);
        }
        boolean p[]= IntegerUtils.sieve(217287);
        int primes[]=new int[217287];
        for(i=2;i<=217286;i++)
        {
            primes[i]=primes[i-1];
            if(p[i]) primes[i]++;
        }
        int test=in.nextInt();
        while (test-->0)
        {
            out.printLine(primes[answers[in.nextInt()]]);
        }
    }
    public int recur(int x,int n) {
        if(x==n)
            return 1;
        if(x>n) return 0;
        return recur(x+1,n)+recur(x+4,n);
    }
}

class InputReader
{
    BufferedReader in;
    StringTokenizer tokenizer=null;

    public InputReader(InputStream inputStream)
    {
        in=new BufferedReader(new InputStreamReader(inputStream));
    }
    public String next()
    {
        try{
            while (tokenizer==null||!tokenizer.hasMoreTokens())
            {
                tokenizer=new StringTokenizer(in.readLine());
            }
            return tokenizer.nextToken();
        }
        catch (IOException e)
        {
            return null;
        }
    }
    public int nextInt()
    {
        return Integer.parseInt(next());
    }
    }

class OutputWriter {
	private final PrintWriter writer;

	public OutputWriter(OutputStream outputStream) {
		writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
	}

	public OutputWriter(Writer writer) {
		this.writer = new PrintWriter(writer);
	}

	public void print(Object...objects) {
		for (int i = 0; i < objects.length; i++) {
			if (i != 0)
				writer.print(' ');
			writer.print(objects[i]);
		}
	}

	public void printLine(Object...objects) {
		print(objects);
		writer.println();
	}

	public void close() {
		writer.close();
	}
}

class IntegerUtils {
    public static boolean [] sieve(int n)
    {
        boolean prime[]=new boolean[n+1];
        Arrays.fill(prime,true);
        prime[0]=false;
        prime[1]=false;
        int sqrt=(int)Math.sqrt(n);
        for(int i=2;i<=sqrt;i++)
            if(prime[i])
                for(int k=i*i;k<=n;k+=i)
                    prime[k]=false;
        return prime;
    }

    }

