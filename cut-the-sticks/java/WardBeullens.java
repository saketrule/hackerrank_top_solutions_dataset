import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;


public class Solution {

	public static void main(String[] args) {
		Scanner In = new Scanner(System.in);
        int N = In.nextInt();
        TreeMap<Integer,Integer> list = new TreeMap<Integer,Integer>();
        for(int i=0 ; i<N ; i++)
        {
        	int A = In.nextInt();
        	if(list.containsKey(A))
        	{
        		list.put(A,list.get(A)+1);
        	}
        	else
        	{
        		list.put(A,1);
        	}
        }
        Iterator<Integer> it = list.keySet().iterator();
        int sum = 0;
        while(it.hasNext())
        {
        	System.out.println(N-sum);
        	sum+=list.get(it.next());
        }
        In.close();
	}

}
