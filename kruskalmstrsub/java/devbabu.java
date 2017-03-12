import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Node{
    int nodeId;
    int setId;
}

public class Solution {

    int N;
    int M;
    int S;
    Node []nodeArr;
    Integer [][]edgeList;
    
    void input(){
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        M = s.nextInt();
        nodeArr = new Node[N];
        for(int i = 0; i < N; i++){
            Node n = new Node();
            n.nodeId = i+1;
            n.setId = i+1;
            nodeArr[i] = n;
        }
        edgeList = new Integer[M][3];
        
        for(int i = 0; i < M; i++){
            int e1 = s.nextInt();
            int e2 = s.nextInt();
            int r = s.nextInt();
            if(e1 < e2){
                edgeList[i][0] = e1;
                edgeList[i][1] = e2;                
            }
            else{
                edgeList[i][0] = e2;
                edgeList[i][1] = e1;                
            }

            edgeList[i][2] = r;
        }
        
        S = s.nextInt();
        Arrays.sort(edgeList, new Comparator<Integer[]>()
    {
    @Override
    public int compare(Integer[] int1, Integer[] int2)
    {
        Integer numOfKeys1 = int1[2];
        Integer numOfKeys2 = int2[2];
        return numOfKeys1.compareTo(numOfKeys2);
    }
});
    }
    
    void kruskal(){
        long totalWeight = 0;
        for(int i = 0; i < M; i++){
            int e1 = edgeList[i][0];
            int e2 = edgeList[i][1];
            int r = edgeList[i][2];
            int set1 = nodeArr[e1-1].setId;
            int set2 = nodeArr[e2-1].setId;
            if(set1 != set2){
               //System.out.println(e1+" "+e2+" "+r+" "+set1+" "+set2);
                for(int j = 0; j < N;j++){
                    Node n = nodeArr[j];
                    if(n.setId == set2){
                        n.setId = set1;
                    }
                }
                nodeArr[e2-1].setId = set1;
                totalWeight = totalWeight + r;
            }
        }
        System.out.println(totalWeight);
    }
    
    public static void main(String[] args) {
        Solution s = new Solution();
        s.input();
        s.kruskal();
    }
}