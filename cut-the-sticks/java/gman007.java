import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++) a[i] = in.nextInt();
        Arrays.sort(a);
        int i = 0, sum = a.length;
        while(i < a.length){
            System.out.println(sum);
            int c = 0;
            do{
                i++;
                c++;
            }while(i < a.length && a[i-1] == a[i]);
            sum -= c;
        }
    }
}