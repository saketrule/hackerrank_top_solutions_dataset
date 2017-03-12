//https://www.hackerrank.com/contests/feb14/challenges/board-cutting



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution {
    public static final int PRIME = 1000000007;
    private BufferedReader in;
    private int M;
    private int N;
    public void run() throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        int tests = Integer.parseInt(in.readLine());	

        while(tests > 0){
            String[] params = in.readLine().split("\\s+");
         M = Integer.parseInt(params[0]);
         N = Integer.parseInt(params[1]);

        String[] Numbers = in.readLine().split("\\s+");
        long[] hNumbers = new long[M-1];
        for (int i = 0; i < M-1; i++) {
            hNumbers[i] = Long.parseLong(Numbers[i]);
        }

        Numbers = in.readLine().split("\\s+");
        long[] vNumbers = new long[N-1];
        for (int i = 0; i < N-1; i++) {
            vNumbers[i] = Long.parseLong(Numbers[i]);
        }

        Arrays.sort(hNumbers);
        Arrays.sort(vNumbers);
        System.out.println(getMinBoardValue(vNumbers, hNumbers));
            tests--;
        }



        in.close();
    }
    private long getMinBoardValue(long[] vNumbers, long[] hNumbers) {

        //System.out.println(vNumbers);
        //System.out.println(hNumbers);
        long hori = 0;
        long vert = 0;
        long val = 0;
        int i=M-2, j=N-2;
        while(i>=0 && j>=0){
            if(hNumbers[i] > vNumbers[j]){
                val+= ((hNumbers[i--]%PRIME)*((vert+1)%PRIME))%PRIME;
                hori++;
                hori = hori%PRIME;

            } else if(hNumbers[i] < vNumbers[j]){
                val+= ((vNumbers[j--]%PRIME)*((hori+1)%PRIME))%PRIME;
                vert++;
                vert = vert%PRIME;
            } else {
                if(hori < vert){
                    val+= ((vNumbers[j--]%PRIME)*((hori+1)%PRIME))%PRIME;
                    vert++;
                    vert = vert%PRIME;
                }else if(hori > vert){
                    val+= ((hNumbers[i--]%PRIME)*((vert+1)%PRIME))%PRIME;
                    hori++;
                    hori = hori%PRIME;
                }else{
                    val+= ((hNumbers[i--]%PRIME)*((vert+1)%PRIME))%PRIME;
                    hori++;
                    hori = hori%PRIME;
                }
            }
            val = val%PRIME;
        }

        while(i>=0){
            val+= ((hNumbers[i--]%PRIME)*((vert+1)%PRIME))%PRIME;
            hori++;
            hori = hori%PRIME;
            val = val%PRIME;
        } 
        while(j>=0){
            val+= ((vNumbers[j--]%PRIME)*((hori+1)%PRIME))%PRIME;
            vert++;
            vert = vert%PRIME;
            val = val%PRIME;
        }
        return val;
    }
    public static void main(String[] args) throws IOException {
        new Solution().run();
    }

}