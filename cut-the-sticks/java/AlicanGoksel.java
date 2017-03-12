import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
		int testCases = input.nextInt();
		ArrayList<Integer> sticks = new ArrayList<Integer>();
		int cutIndex = 0;
		for(int i=0;i<testCases;i++){
			sticks.add(input.nextInt());
		}
		boolean print = true;
		Collections.sort(sticks);
		while(cutIndex != sticks.size()-1){
			
			System.out.println(sticks.size()-cutIndex);
			int min = sticks.get(cutIndex);
			boolean entered = false;
			for(int i=cutIndex+1;i<sticks.size();i++){
				if(sticks.get(i)>min){
					cutIndex = i;
					entered = true;
					break;
				}
			}
			
			if(!entered){
				print = false;
				break;
			}
		}
		if(print)
		System.out.println(sticks.size()-cutIndex);
    }
}