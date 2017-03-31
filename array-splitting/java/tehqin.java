
import java.util.*;
import java.io.*;

public class B
{
   public static void main(String[] args) throws Exception
   {
      FastScanner in = new FastScanner(System.in);
      PrintWriter out = new PrintWriter(System.out);
      int T = in.nextInt();
      while (T-->0) new B(in, out);
      out.close();
   }
   int N;
   int[] vs;
   int go(int i, int j)
   {
      long sum = 0;
      for (int x=i; x<=j; x++)
         sum += vs[x];
      if (sum % 2 != 0) return 0;
      sum /= 2;

      long ss = 0;
      for (int x=i; x<j; x++)
      {
         ss += vs[x];
         if (ss == sum)
            return 1 + Math.max(go(i, x), go(x+1, j));
      }
      return 0;
   }

   public B(FastScanner in, PrintWriter out)
   {
      N = in.nextInt();
      vs = new int[N];
      for (int i=0; i<N; i++)
         vs[i] = in.nextInt();

      long res = go(0, N-1);
      out.println(res);
   }
}

class FastScanner{
   private InputStream stream;
   private byte[] buf = new byte[1024];
   private int curChar;
   private int numChars;

   public FastScanner(InputStream stream)
   {
      this.stream = stream;
   }

   int read()
   {
      if (numChars == -1)
         throw new InputMismatchException();
      if (curChar >= numChars){
         curChar = 0;
         try{
            numChars = stream.read(buf);
         } catch (IOException e) {
            throw new InputMismatchException();
         }
         if (numChars <= 0)
            return -1;
      }
      return buf[curChar++];
   }

   boolean isSpaceChar(int c)
   {
      return c==' '||c=='\n'||c=='\r'||c=='\t'||c==-1;
   }
   
   boolean isEndline(int c)
   {
      return c=='\n'||c=='\r'||c==-1;
   }

   int nextInt()
   {
      return Integer.parseInt(next());
   }
   
   long nextLong()
   {
      return Long.parseLong(next());
   }

   double nextDouble()
   {
      return Double.parseDouble(next());
   }

   String next(){
      int c = read();
      while (isSpaceChar(c))
         c = read();
      StringBuilder res = new StringBuilder();
      do{
         res.appendCodePoint(c);
         c = read();
      }while(!isSpaceChar(c));
      return res.toString();
   }
   
   String nextLine(){
      int c = read();
      while (isEndline(c))
         c = read();
      StringBuilder res = new StringBuilder();
      do{
         res.appendCodePoint(c);
         c = read();
      }while(!isEndline(c));
      return res.toString();
   }
}
