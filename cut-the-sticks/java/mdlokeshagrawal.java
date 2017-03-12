import java.util.Scanner;
public class Solution {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		int n=s.nextInt();
		int a[]=new int[1001];
		
		for(int i=0;i<n;i++){
			int x=s.nextInt();
			a[x]++;
		}
		long sum=0;
		for(int i=1;i<a.length;i++){
			sum+=a[i];
		}
		System.out.println(sum);
		for(int i=0;i<a.length;i++){
			if(a[i]!=0){
				sum=sum-a[i];
				if(sum>0)
				System.out.println(sum);
			}
		}
	}
}