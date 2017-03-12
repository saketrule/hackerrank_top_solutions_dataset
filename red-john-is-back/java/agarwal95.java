import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        int f[]=new int[45];
        f[1]=1;
        f[2]=1;
        f[3]=1;
        f[4]=2;
        f[5]=3;
        for(int i=6;i<45;i++){
            f[i]=f[i-1]+f[i-4];
        }
        
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        for(int i=1;i<=n;i++){
            System.out.println(getNoPrimes(f[s.nextInt()]));
        }
    }
    
    static int getNoPrimes(int n){
        if(n==1) return 0;
        int p=0;
        for(int i=1;i<=n;i++){
            if(isPrime(i)){
                p++;
            }
        }
        return p;
    }
    
    static boolean isPrime(int n){
        if(n%2==0) return false;
        for(int i=3;i<=Math.sqrt(n);i=i+2){
            if(n%i==0) return false;
        }
        
        return true;
    }
}
