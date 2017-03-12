import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class Solution {
	public static void main(String[] args) throws java.lang.Exception {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();

		TreeMap<Integer, Integer> map = new TreeMap<>();
		for(int i=0; i<N; i++) {
			int key = scanner.nextInt();
			if(map.containsKey(key))
				map.put(key, map.get(key) + 1);
			else
				map.put(key, 1);
		}
		scanner.close();
		
		int sticksLeft = N;
		
		while(!map.isEmpty()) {
			System.out.println(sticksLeft);
			sticksLeft -= map.pollFirstEntry().getValue();
		}
		
	}
}