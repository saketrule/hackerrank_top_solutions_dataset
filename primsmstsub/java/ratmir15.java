import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Set<Long> set = new HashSet<Long>();
        for (int i=0;i<m;i++) {
            long fr = (long)sc.nextInt();
            long to = (long)sc.nextInt();
            long w = (long)sc.nextInt();
            //System.out.println(fr<<12);
            //System.out.println(to<<24);
            //System.out.println(w);
            set.add(w+(fr<<20)+(to<<32));
        }
        int z = 1;
        Set<Long> vs = new HashSet<Long>();
        vs.add(1l);
        long sum = 0;
        while (z<n) {
            long min = Integer.MAX_VALUE;
            long min_fr = -1;
            long min_to = -1;
            Set<Long> toRemove = new HashSet<Long>();
            for (long l:set) {
                long fr = (l>>20)%(1<<12);
                long to = l>>32;
                long w = l%(1<<20);
                //System.out.println(l+" "+fr+" "+to+" "+w);
                if (vs.contains(fr) && vs.contains(to)) {
                    toRemove.remove(l);
                } else {
                 if (vs.contains(fr) || vs.contains(to)) {
                        if (w<min) {
                            min = w;
                            min_fr = fr;
                            min_to = to;
                        }    
                    }
                }
            }
            vs.add(min_fr);
            vs.add(min_to);
            sum += min;
            set.removeAll(toRemove);
            //System.out.println(sum);
            z++;
        }
        System.out.println(sum);
    }
}