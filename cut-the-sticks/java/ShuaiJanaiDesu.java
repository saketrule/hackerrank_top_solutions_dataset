import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] sticks = new int[n];
        for (int i = 0; i < n; i += 1) {
            sticks[i] = sc.nextInt();
        }
        Arrays.sort(sticks);
        int gone = 0;
        int cut = 0;
        for (int i = 0; i < n; i += 1) {
            if (cut < sticks[i]) {
                System.out.println(n - gone);
                cut = sticks[i];
            }
            gone += 1;
        }
    }
}