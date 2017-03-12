import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


class Edge {
    int source , destination , weight;
}
class SortedEdge implements Comparator<Edge>{
    public  int compare (Edge e1 , Edge e2){
        if (e1.weight != e2.weight){
            if (e1.weight > e2.weight )
                return 1;
            else
                return -1;
        }
        else {
            int firstSum  = e1.source + e1.destination + e1.weight;
            int secondSum = e2.source + e2.destination + e2.weight;
            if (firstSum > secondSum)
                return 1;
            else if (firstSum == secondSum)
                return 0;
            else
                return -1;
        }
    }
}
public class Solution {
    private static  int array[];
    private static int toInteger (String value){return Integer.parseInt(value);}
    private static long toLong (String value){return Long.parseLong(value);}

    public static void main (String args[]){
        Scanner scanner = new Scanner (System.in);
        String tokens[] = scanner.nextLine().split(" ");
        int numberOfNodes = toInteger( tokens[0] ), edgesCount = toInteger( tokens[1] );
        Edge[]  edges = new Edge[edgesCount];
        for (int i = 0 ; i < edgesCount ; i++){
            tokens = scanner.nextLine().split(" ");
            edges[i] = new Edge();
            edges[i].source = toInteger( tokens[0] ) - 1 ;
            edges[i].destination = toInteger( tokens[1] ) - 1;
            edges[i].weight = toInteger( tokens[2] );
        }
        Arrays.sort (edges, new SortedEdge());
        int startPoint = toInteger( scanner.nextLine()) - 1;
        boolean [] isVisited = new boolean[numberOfNodes];
        int sum = 0 , addedEdges = 0 ;
        HashMap<Integer ,  Integer> map = new HashMap<Integer, Integer>();
        int [] treeNumber = new int[numberOfNodes];
        for (int i = 0; i < numberOfNodes ; i++) {
            treeNumber [i] = -1;
        }
        int numberOfTrees = 0 ;
        for (int i = 0 ; i < edgesCount && addedEdges  <= numberOfNodes - 1;i++){
            int sourceTree = treeNumber[edges[i].source];
            int destTree = treeNumber[edges[i].destination];
            if (sourceTree!= -1 && destTree != -1 && sourceTree!= destTree){
                int sourceCount = map.get(sourceTree), destCount = map.get(destTree);

                for (int j= 0 ; j < numberOfNodes ; j++){
                    if (treeNumber[j] == sourceTree)
                        treeNumber[j] = destTree;
                }
                map.put(destTree , sourceCount + destCount);
                addedEdges ++;
                sum += edges[i].weight;
            }
            else if (sourceTree == -1 && destTree != -1){
                treeNumber[edges[i].source] = destTree;
                map.put (destTree , map.get(destTree) + 1);
                addedEdges ++;
                sum += edges[i].weight;
            }
            else if (sourceTree != -1 && destTree == -1){
                treeNumber[edges[i].destination] = sourceTree;
                map.put (sourceTree , map.get(sourceTree) + 1);
                addedEdges ++;
                sum += edges[i].weight;
            }
            else if (sourceTree == -1 && destTree == -1){
                treeNumber[edges[i].destination] = ++numberOfTrees;
                treeNumber[edges[i].source] = numberOfTrees;
                map.put(numberOfTrees , 2);
                sum += edges[i].weight;
                addedEdges  ++;
            }
        }
        System.out.println(sum);

    }
}
