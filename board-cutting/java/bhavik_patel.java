import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Solution {
	
	public static void main(String [] args){
		InputReader in = new InputReader(System.in);
		int T = in.readInt();
		for(int i=0;i<T;i++){
			int M = in.readInt();
			int N = in.readInt();
			int [] X = new int[M-1];
			int [] Y = new int[N-1];
			for(int j=0;j<M-1;j++)
				X[j]=in.readInt();
			for(int j=0;j<N-1;j++)
				Y[j]=in.readInt();
			
			X = radixSort(X);
			Y = radixSort(Y);
			int H =1;
			int V =1;
			int p = M-2;
			int q = N-2;
			long cost =0;
			while(!(p==-1 && q==-1)){
				if(p==-1){
					cost = (cost + ((long)Y[q])*H)%1000000007;
					V++;
					q--;
				}
				else if(q==-1){
					cost = (cost + ((long)X[p])*V)%1000000007;
					H++;
					p--;
				}
				else if(X[p]>=Y[q]){
					cost = (cost + ((long)X[p])*V)%1000000007;
					H++;
					p--;
				}
				else{
					cost = (cost + ((long)Y[q])*H)%1000000007;
					V++;
					q--;
				}
				
			}
			System.out.println(cost%1000000007);
		}
	}
	public static int[] radixSort(int[] a) {
        int[] b = null;
        for (int p = 0; p < 16; p++) {
            int c[] = new int[1<<2];
            // the next three for loops implement counting-sort
            b = new int[a.length];
            for (int i = 0; i < a.length; i++)
                c[(a[i] >> 2*p)&((1<<2)-1)]++;
            for (int i = 1; i < 1<<2; i++)
                c[i] += c[i-1];
            for (int i = a.length-1; i >= 0; i--)
                b[--c[(a[i] >> 2*p)&((1<<2)-1)]] = a[i];
            a = b;
        }
        return b;
    }

}
class InputReader {
    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    
    public InputReader(InputStream stream) {
    this.stream = stream;
    }
    
    public int read() {
    if (numChars == -1)
    throw new InputMismatchException();
    if (curChar >= numChars) {
    curChar = 0;
    try {
    numChars = stream.read(buf);
    } catch (IOException e) {
    throw new InputMismatchException();
    }
    if (numChars <= 0)
    return -1;
    }
    return buf[curChar++];
    }
    
    public int readInt() {
    int c = read();
    while (isSpaceChar(c))
    c = read();
    int sgn = 1;
    if (c == '-') {
    sgn = -1;
    c = read();
    }
    int res = 0;
    do {
    if (c < '0' || c > '9')
    throw new InputMismatchException();
    res *= 10;
    res += c - '0';
    c = read();
    } while (!isSpaceChar(c));
    return res * sgn;
    }
    
    public String readString() {
    int c = read();
    while (isSpaceChar(c))
    c = read();
    StringBuffer res = new StringBuffer();
    do {
    res.appendCodePoint(c);
    c = read();
    } while (!isSpaceChar(c));
    return res.toString();
    }
    public boolean isSpaceChar(int c) {
    return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }
    public boolean isNewLineChar(int c) {
    return c == '\n';
    }
    public String next() {
    return readString();
    }
 }