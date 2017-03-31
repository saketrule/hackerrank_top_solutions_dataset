import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static int game(int[] ar) {
        int N = ar.length;
        if (N == 1) {
            return 0;
        }

        int sumA = ar[0];
        int sumB = 0;
        int count = 1;
        for (int i = 1; i < N; i++) {
            sumB += ar[i];
        }
        for (int i = 1; i < N; i++) {
            if (sumA == sumB) {
                break;
            }
            sumA += ar[i];
            sumB -= ar[i];
            count ++;
        }
        
        if (sumA == sumB) {
            int[] arA = new int[count];
            int[] arB = new int[N - count];
            for (int i = 0; i < count; i++) {
                arA[i] = ar[i];
            }
            int index = 0;
            for (int i = count; i < N; i++) {
                arB[index++] = ar[i];
            }
            int gameA = game(arA);
            int gameB = game(arB);
            if (gameA > gameB) {
                return gameA + 1;
            } else {
                return gameB + 1;
            }
        } else {
            return 0;
        }
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i =0; i <T;i++) {
            int N = in.nextInt();
            int[] ar = new int[N];
            for(int j=0;j<N;j++){
                ar[j]=in.nextInt();
            }
            System.out.println(game(ar));
        }
    }
}