import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc=new Scanner(System.in);
        int V=sc.nextInt();
        int E=sc.nextInt();
        int[] edges_src=new int[E];
        int[] edges_dest=new int[E];
        int[] edges_wt=new int[E];
        for(int i=0;i<E;i++){
            edges_src[i]=sc.nextInt()-1;
            edges_dest[i]=sc.nextInt()-1;
            edges_wt[i]=sc.nextInt();
        }
        int start=sc.nextInt()-1;
        System.out.println(Krushkals(edges_src,edges_dest,edges_wt,V));
        
    }
    
    private static int Krushkals(int[] src, int[] dest, int[] wt, int V){
        int l=0;
        while(l<wt.length-1){
            if(wt[l]>wt[l+1])
                break;
            l++;
        }
        if(l!=wt.length)
        qsort(wt,0,wt.length-1,src,dest);
        
        int sum=0;
        int[] parent=new int[V];
        int[] rank=new int[V];
        for(int i=0;i<V;i++){
           parent[i]=i;
           rank[i]=0;
        }
        
        for(int i=0;i<wt.length;i++){
            int x=find(parent,src[i]);
            int y=find(parent,dest[i]);
            if(x!=y){
               sum+=wt[i];
               union(parent,rank,x,y);
            }
        }
        
        return sum;
        
    }
    
    private static int find(int[] parent,int x){
        if(parent[x]!=x)
            parent[x]=find(parent, parent[x]);
        return parent[x];
    }
    
    private static void union(int[] parent, int[] rank, int x, int y){
         x=find(parent,x);
         y=find(parent,y);
        if(x==y)
            return;
        if(rank[x]>rank[y])
            parent[y]=x;
        else if(rank[y]>rank[x])
            parent[x]=y;
        else{
            parent[y]=x;
            rank[x]++;
        }
    }
    
    private static void qsort(int[] wt, int start, int end, int[] src, int[] dest){
        
            if(start>=end)
                return;                
            int p=partition(wt,start,end,src,dest);
            qsort(wt,start,p-1,src,dest);
            qsort(wt,p+1,end,src,dest);
        
    }
    
    private static int partition(int[] arr, int start, int end,int[] src, int[] dest){
        int index=start;
        int i=start;
        int j=end;
        while(i<j){
            while(i<=end && arr[i]<=arr[index])
                i++;
            while(j>=0 && arr[index]<arr[j])
                j--;
            if(i<=j){
            int temp_wt=arr[i];
            int temp_src=src[i];
            int temp_dest=dest[i];
            
            arr[i]=arr[j];
            src[i]=src[j];
            dest[i]=dest[j];
            
            arr[j]=temp_wt;
            src[j]=temp_src;
            dest[j]=temp_dest;
            
            i++;
            j--;
            }
        }
        i--;
        if(index<i){
        int temp_wt=arr[i];
        int temp_src=src[i];
        int temp_dest=dest[i];
            
        arr[i]=arr[index];
        src[i]=src[index];
        dest[i]=dest[index];
            
        arr[index]=temp_wt;
        src[index]=temp_src;
        dest[index]=temp_dest;
        }
       return i; 
    } 
}