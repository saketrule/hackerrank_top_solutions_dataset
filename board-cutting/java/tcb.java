import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

	private static final BigInteger MODULO = BigInteger.valueOf(1000000007);
	
	public static void main(String[] args) throws IOException {
        Scanner scanner;
        if (args.length == 0) {
            scanner = new Scanner(new BufferedInputStream(System.in));
        }
        else {
            scanner = new Scanner(new BufferedInputStream(new FileInputStream(args[0])));
        }
        int t = scanner.nextInt();
        
        for (int i = 0; i < t; ++i) {
        	int m = scanner.nextInt();
        	int n = scanner.nextInt();
        	int [] y = new int [m - 1];
        	for (int j = 0; j < m - 1; ++j) {
        		y[j] = scanner.nextInt();
        	}
        	int [] x = new int [n - 1];
        	for (int j = 0; j < n - 1; ++j) {
        		x[j] = scanner.nextInt();
        	}
        	
        	BigInteger count = BigInteger.ZERO;
        	
        	int piecesY = 1;
        	int piecesX = 1;
        	
        	Arrays.sort(y);
        	Arrays.sort(x);
        	
        	int currentY = y.length - 1;
        	int currentX = x.length - 1;
        	while (currentY >= 0 && currentX >= 0) {
        		if (y[currentY] > x[currentX] || (y[currentY] == x[currentX] && piecesX < piecesY)) {
        			piecesY++;
        			count = count.add(
        					BigInteger.valueOf(piecesX).multiply(BigInteger.valueOf(y[currentY])));
        			currentY--;
        		} else {
        			piecesX++;
        			count = count.add(
        					BigInteger.valueOf(piecesY).multiply(BigInteger.valueOf(x[currentX])));
        			currentX--;
        		}
        	}
        	while (currentY >= 0) {
    			count = count.add(
    					BigInteger.valueOf(piecesX).multiply(BigInteger.valueOf(y[currentY])));
        		currentY--;
        	}
        	while (currentX >= 0) {
    			count = count.add(
    					BigInteger.valueOf(piecesY).multiply(BigInteger.valueOf(x[currentX])));
        		currentX--;
        	}
        	System.out.println(count.remainder(MODULO));
        }
	}
}