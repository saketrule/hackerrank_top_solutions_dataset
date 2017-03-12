import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) throws Exception{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader read=ri();
        int n=i(read.readLine());
        int a[]=new int[n];
        String s[]=read.readLine().split(" ");
        for(int i=0;i<n;i++)a[i]=i(s[i]);
        Arrays.sort(a);
        int diff=0,left=0;;
        while(a[n-1]>diff)
        {
            System.out.println(n-left);
            diff=a[left];
            while(left<n&&a[left]<=diff)left++;
            if(left>=n)break;
        }
        
    }
    
    static BufferedReader rf(String name) throws Exception
{
return new BufferedReader(new FileReader(name));
}
static BufferedWriter wf(String name)  throws Exception
{
return new BufferedWriter(new FileWriter(name));
}
static BufferedReader ri() throws Exception
{
return new BufferedReader(new InputStreamReader(System.in));
}
static int i(String s){return Integer.parseInt(s.trim());}
static long l(String s){return Long.parseLong(s.trim());}
static double d(String s){return Double.parseDouble(s.trim());}
}