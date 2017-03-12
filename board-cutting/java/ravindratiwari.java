import java.io.*;
import java.util.*;
import java.math.*;

public class Solution
{
	public static long MOD = 1000000007L;
	public static void main(String[] args) 
	{
		Scan scan = new Scan(System.in);
		PrintWriter out = new PrintWriter(System.out);
				
		int tc = scan.nextInt();
		for (int t = 0; t < tc; t++)
		{
			int M = scan.nextInt();
			int N = scan.nextInt();
			
			long[] arr1 = new long[M - 1];
			for (int i = 0; i < M - 1; i++)
				arr1[i] = scan.nextInt();
			
			long[] arr2 = new long[N - 1];
			for (int i = 0; i < N - 1; i++)
				arr2[i] = scan.nextInt();
			
			Arrays.sort(arr1);
			Arrays.sort(arr2);
			
			int p1 = M - 2, p2 =  N - 2;
			int c1 = 1, c2 = 1;
			
			long sum = 0L;
			
			while (p1 >= 0 && p2 >= 0)
			{
				boolean op = false;				
				if (arr1[p1] > arr2[p2])
					op = true;
				else if (arr1[p1] == arr2[p2] && arr1[p1] * c1 < arr2[p2] * c2)
					op = true;
				
				if (op)
				{
					sum += (arr1[p1] * c1);
					sum %= MOD;
					p1--;
					c2++;
				}
				else
				{
					sum += (arr2[p2] * c2);
					sum %= MOD;
					p2--;
					c1++;
				}		
			}
			while (p1 >= 0)
			{
				sum += (arr1[p1] * c1);
				sum %= MOD;
				p1--;
				c2++;				
			}
			while (p2 >= 0)
			{
				sum += (arr2[p2] * c2);
				sum %= MOD;
				p2--;
				c1++;
			}
			out.println(sum);								
		}
		out.close();		
	}	
}

final class Scan
{
	private InputStream in = null;
	private int pos,count;
	private byte[] buf = new byte[1<<16];
	
	public Scan(InputStream in)
	{
		this.in = in;
		pos = 0;
		count = 0;
	}
	public int nextInt()
	{
		int c=read(), sign = 1;
		while (c <= ' ')
		    c = read();
		if (c == '-')
		{
			sign = -1;
			c = read();
		}
		int n = c - '0';
		while((c = read() - '0') >= 0)
		    n = n * 10 + c;
		return n * sign;		
	}	
	public String next()
	{
		StringBuilder sb = new StringBuilder();
		int c = read();
		while (c <= ' ')
		    c = read();
		while (c >= 33)
		{
			sb.append((char) c );
			c = read();
		}
		return sb.toString();
	}
	public long nextLong()
	{
		int c = read(), sign = 1;
		while (c <= ' ')
		    c = read();
		if (c == '-')
		{
			sign = -1;
			c = read();
		}		
		long n = 1L * (c - '0');
		while((c = read() - '0') >= 0)
		    n = n * 10 + c;
		return n * sign;		
	}
	public int read()
	{
		if( pos == count)
		   fillBuffer();
		   
		return buf[pos++];		
	}
	private void fillBuffer()
	{
		try
		{
			count = in.read(buf, pos = 0 , buf.length);
			if (count == -1)
				buf[0] = -1;
		}
		catch(Exception e)
		{
			
		}
	}
}