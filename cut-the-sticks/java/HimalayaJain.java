import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        int N;
        int[] s=new int[1001];
        Scanner in=new Scanner(System.in);
        N=in.nextInt();
        for(int i=0;i<N;++i){
            s[in.nextInt()]++;
        }
        System.out.println(N);
        for(int i=1;i<1001;++i){
            if(N==s[i])
                break;
            if(s[i]>0){
                N-=s[i];
                System.out.println(N);
            }
        }
    }
}