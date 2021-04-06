package interview.problems;

import java.util.Scanner;

public class MaxSumSubrray {
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = s.nextInt();
		}
 
		int max = arr[0];
		for (int i = 0; i < n; i++) {
			int sum = 0;
			for (int j = i; j < n; j++) {
				sum = sum + arr[j];
				if (sum > max) {
					max = sum;
				}
			}
		}
		
		System.out.println(max);
	}

}
