import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Sandesh
 * Date: 7/27/13
 * Time: 10:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Solution {
    static long dp[];
    static HashSet<Integer> hs;
    public static void main(String arg[]){
        hs=new HashSet<Integer>();
        dp=new long[41];
        Arrays.fill(dp,-1);
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        setPrime();
        for (int j=0;j<N;j++){
            int val=sc.nextInt();
            int x=(int)(new Solution().get(val));
            int ans=0;
            for (int i=2;i<=x;i++){
                if(hs.contains(i))
                    ans++;
            }
            System.out.println(ans);
        }
    }
    public long get(int N){
        if(dp[N]!=-1)
            return dp[N];
        long ans=1;
        if(N<=3)
            ans=1;
        else {
            ans=get(N-1)+get(N-4);
        }
        dp[N]=ans;
        return ans;
    }
    static boolean isPrime(int a){
        for (int i=2;i*i<=a;i++){
            if(a%i==0)
                return false;
        }
        return true;
    }
    static void setPrime(){
        for (int i=2;i<=217286;i++){
            if(isPrime(i))
                hs.add(i);
        }

    }

}
