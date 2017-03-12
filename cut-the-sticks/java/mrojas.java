import java.io.*;
import java.util.*;
public class Solution {
    public static void main(String[] args) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        short N = Short.parseShort(br.readLine());
        String[] temp = br.readLine().split(" ");
        short[] As = new short[N];
        for(short i = 0; i < N; ++i){
            As[i] = Short.parseShort(temp[i]);
        }
        Arrays.sort(As);
        short curLength = 0;
        for(short i = 0; i < N; ++i){
            if (As[i] > curLength){
                curLength = As[i];
                sb.append((N - i) + "\n");
            }
        }
        System.out.print(sb);
    }
}