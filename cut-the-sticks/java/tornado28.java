import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	
	public static void main(String[] args) throws Exception{
		if(System.getProperty("os.name").equals("Mac OS X")){ //Local run
			InputStream is = new FileInputStream("/Users/brianbargh/Desktop/TestCase.txt");
    		System.setIn(is);
    		System.out.println("Local Test Run:");
		}
		
		Scanner s = new Scanner(System.in);
		int N = s.nextInt(); //Number of test cases
		
		int[] lengths = new int[N];
		
		for(int i=0; i<N; i++){
			lengths[i] = s.nextInt();
		}
		
		Arrays.sort(lengths);
		
		int index = 0;
		
		while(index  < N){
			System.out.println(N - index);
			
			int currentStickLength = lengths[index];
			while(index<N && lengths[index] == currentStickLength){
				index++;
			}
		}
		
	}

}