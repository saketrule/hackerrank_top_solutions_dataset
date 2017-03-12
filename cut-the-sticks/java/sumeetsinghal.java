import java.util.Scanner;

class Solution {

	public static void main(String[] args)  {
    	Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        
        int[] array = new int[N];
        int[] countArray = new int[1001];
        for (int i = 0; i < N; i++) {
        	array[i] = s.nextInt();
            countArray[array[i]]++;
        }
        printNumberOfCuts(countArray, N);
    }
    
    public static void printNumberOfCuts(int[] countArray, int remCount) {
    	for (int i = 1; i <= 1000; i++) {
        	if (countArray[i] > 0) {
            	System.out.println(remCount);
            }
            remCount = remCount - countArray[i];
        }
    }

}