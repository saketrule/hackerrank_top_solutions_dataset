/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package javaapplication1;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 *
 * @author weli
 */
public class Solution
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
        // TODO code application logic here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String sps[] = line.split(" ");
        int N = Integer.parseInt(sps[0]);
        int M = Integer.parseInt(sps[1]);
        int edges[][] = new int[N + 1][N + 1];
        int outputs[] = new int[N + 1];
        int manys[][] = new int[N + 1][N + 1];
        int infos[][] = new int[M][3];

        for (int i = 0; i < M; i++)
        {
            line = br.readLine();
            sps = line.split(" ");
            int start = Integer.parseInt(sps[0]);
            int end = Integer.parseInt(sps[1]);
            infos[i][0] = start;
            infos[i][1] = end;
            infos[i][2] = 1;
            edges[start][end] = 1;
            edges[end][start] = 1;
            outputs[start]++;
            outputs[end]++;
        }

//        for(int i=1;i<N+1;i++)
//        {
//            for(int j=1;j<N+1;j++)
//            {
//                System.out.print(edges[i][j]+" ");
//            }
//            System.out.println("\t"+outputs[i]);
//        }

        for(int i=1;i<N+1;i++)
        {
            if(edges[1][i]==1)
            {
                int num = compute(1,i,edges,outputs,N,manys);
                manys[1][i] = num;
                manys[i][1] = N-num;
            }
        }
        
        int sum = 0;
        for(int i=1;i<N+1;i++)
        {
            for(int j=1;j<N+1;j++)
            {
//                System.out.print(manys[i][j]+" ");
                if(manys[i][j]%2==0&&manys[i][j]!=0)
                    sum++;
            }
//            System.out.println();
        }
        

        System.out.println(sum/2);









    }

    public static int count(int removed_num, int infos[][], int manys[][], int M)
    {
        int maxRemove = removed_num;
        int cur_remove = removed_num;
//        System.out.println("now removed "+removed_num);
        for (int i = 0; i < M; i++)
        {
            if (infos[i][2] == 1)
            {
                int start = infos[i][0];
                int end = infos[i][1];
//                System.out.println(start + " " + end + " " + manys[start][end]);
                if (manys[start][end] % 2 == 0)
                {
                    infos[i][2] = 0;
                    cur_remove++;
//                    System.out.println("remove "+start+" "+end);
                    int m_remove = count(cur_remove, infos, manys, M);
                    if (m_remove > maxRemove)
                    {
                        maxRemove = cur_remove;
                    }
                    infos[i][2] = 1;
                }
            }
        }
        return maxRemove;
    }

    public static int compute(int start, int end, int edges[][], int outputs[], int N, int manys[][])
    {
        if (outputs[end] == 1)
        {
            return 1;
        }
        int sum = 1;
        for (int i = 1; i < N + 1; i++)
        {
            if (edges[end][i] == 1 && i != start)
            {
                int num = compute(end, i, edges, outputs, N, manys);
                manys[end][i] = num;
                manys[i][end] = N - num;
                sum += num;
            }
        }
        return sum;
    }

    
}
