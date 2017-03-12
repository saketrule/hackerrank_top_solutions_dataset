import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

   
    private static int toInteger (String value){return Integer.parseInt(value);}
    private static long toLong (String value){return Long.parseLong(value);}
    public static void main (String args[]){
        Scanner scanner = new Scanner (System.in);
        String tokens[] = scanner.nextLine().split(" ");
        int numberOfNodes = toInteger( tokens[0] ) , edges = toInteger( tokens[1] );
        int adjMatrix [][] = new int[numberOfNodes][numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                adjMatrix[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0 ; i < edges ; i++){
            tokens = scanner.nextLine().split( " ");
            int u = toInteger( tokens[0] ) , v = toInteger( tokens[1] ) , weight = toInteger( tokens[2] );
            --u;--v;
            if (adjMatrix[u][v] > weight) {
                adjMatrix[u][v] = weight;
                adjMatrix[v][u] = weight;
            }
        }
        int source = toInteger( scanner.nextLine() );
        System.out.println(getSumOFMST(--source,adjMatrix));
    }
    //private static int getMin ()
    private  static  int getSumOFMST(int source , int[][] matrix){
        int sum = 0, nodes = matrix[0].length;
        boolean[] isVisited = new boolean[matrix[0].length];
        int dist[] = new int[nodes];
        for (int i = 0; i < nodes; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[source ] = 0;
        for (int i = 0 ; i < nodes ; i ++){
            int index = getMin (isVisited, dist);
            if (index != -1 ){
                isVisited[index] = true;
                for (int j = 0 ; j < nodes ; j++){
                    if ( !isVisited[j] && matrix[index][j] != Integer.MAX_VALUE && dist[j] >   matrix[index][j])
                        dist[j] = matrix[index][j];
                }
            }
        }
        for(int i = 0 ; i < nodes ; i++)
            sum += dist[i];
        return sum;
    }
    private static int getMin(boolean[] visited , int[] dist ){
        int min = Integer.MAX_VALUE , index = -1;
        for (int i = 0 ; i < visited.length ; i++){
            if (dist[i] < min && !visited[i]){
                min = dist[i];
                index = i;
            }
        }
        return index;
    }
}