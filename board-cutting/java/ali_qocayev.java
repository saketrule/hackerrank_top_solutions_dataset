
import java.util.Arrays;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ali
 */
enum CutType {

    Horizontal, Vertical
}

class Cost implements Comparable {

    long amount;
    CutType type; /*0-h, 1-vertical*/


    @Override
    public int compareTo(Object o) {
        Cost c = (Cost) o;
        if (c.amount > amount) {
            return 1;
        } else if (c.amount == amount) {
            return 0;
        } else {
            return -1;
        }
    }
}

public class Solution {

    /**
     * @param args the command line arguments
     */
    

    private static long modulo = 1000000000 + 7;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

       //read test case count
        int testCount = in.nextInt();
        for (int i = 0; i < testCount; i++) {
            int m = in.nextInt();

            int horizonalCount = 1;
            int n = in.nextInt();

            int verticalCount = 1;
            Cost costArray[] = new Cost[n + m - 2];
            for (int j = 0; j < m - 1; j++) {
                costArray[j] = new Cost();
                costArray[j].amount = in.nextLong();
                costArray[j].type = CutType.Horizontal;
            }
            for (int j = 0; j < n - 1; j++) {
                costArray[m - 1 + j] = new Cost();
                costArray[m - 1 + j].amount = in.nextLong();
                costArray[m - 1 + j].type = CutType.Vertical;
            }
            Arrays.sort(costArray);
            long sum = 0;
            for(int j=0; j <costArray.length; j++){
                int coef = 0;
                if (costArray[j].type==CutType.Horizontal){
                    coef=horizonalCount;
                }else coef = verticalCount;
                sum+=costArray[j].amount * coef%modulo;
                if (costArray[j].type==CutType.Horizontal){
                    verticalCount++;
                }else horizonalCount++;
                        
            }
            System.out.println(sum% modulo);

        }

    }

}
