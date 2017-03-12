import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static final int MOD = (int)Math.pow(10.0, 9.0) + 7;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int i = 0; i < t; i++){
            int row = sc.nextInt();
            int col = sc.nextInt();

            int[] horz = new int[row-1];
            for(int j = 0; j < row-1; j++)
                horz[j] = sc.nextInt();

            int[] vert = new int[col-1];
            for(int j = 0; j < col-1; j++)
                vert[j] = sc.nextInt();

            Arrays.sort(horz);
            Arrays.sort(vert);
            minimumCost(horz, vert);
        }
    }
    
    public static void minimumCost(int[] horz, int[] vert){
        long cutH = 0;
        long cutV = 0;
        long cost = 0;
        int indexH = horz.length-1;
        int indexV = vert.length-1;
        
        long temp = 0;
        while(indexH > -1 && indexV > -1){
            if(horz[indexH] >= vert[indexV]){
                temp = (horz[indexH]%MOD) * ((cutV+1)%MOD) %MOD;
                cost = ((cost%MOD) + (temp%MOD)) %MOD;
                //cost = cost + horz[indexH] * cut;
                indexH--;
                cutH++;
            }
            else{
                temp = (vert[indexV]%MOD) * ((cutH+1)%MOD) %MOD;
                cost = ((cost%MOD) + (temp%MOD)) %MOD;
                //cost = cost + (vert[indexV] * cut);
                indexV--;
                cutV++;
            }
        }
        
        while(indexH > -1){
            temp = (horz[indexH]%MOD) * ((cutV+1)%MOD) %MOD;
            cost = ((cost%MOD) + (temp%MOD)) %MOD;
            //cost = cost + (horz[indexH] * cut);
            indexH--;
            cutH++;
        }
        while(indexV > -1){
            temp = (vert[indexV]%MOD) * ((cutH+1)%MOD) %MOD;
            cost = ((cost%MOD) + (temp%MOD)) %MOD;
            //cost = cost + (vert[indexV] * cut);
            indexV--;
            cutV++;
        }
        
        System.out.println(cost);
    }
}