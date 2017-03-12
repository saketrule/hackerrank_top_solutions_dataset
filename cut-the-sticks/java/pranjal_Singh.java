import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.TreeMap;

public class Solution {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputLine = br.readLine();
		int numbers = Integer.parseInt(inputLine);
		inputLine = br.readLine();
		String[] inputParams = inputLine.split(" ");
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		for(int i = 0; i < numbers; i++){
			int num = Integer.parseInt(inputParams[i]);
			int count = 1;
			if(map.containsKey(num)){
				count = map.get(num) + 1;
			}
			map.put(num, count);
		}
		
		for(Iterator<Integer> iter = map.keySet().iterator(); iter.hasNext(); ){
			int key = iter.next();
			System.out.println(numbers);
			numbers -= map.get(key);
		}
	}
}