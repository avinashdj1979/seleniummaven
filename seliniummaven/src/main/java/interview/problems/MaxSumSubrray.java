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

/*
Problem statement -
find the sum of all the contigous sub array and return the greatest of them
 example - for example say the array is {0, 3, 1, 3, 2} - sub arrays are (0} {3} {1} {3} {2} and {0,3} , { 3,1} {1,3}, {3,2} and  {0, 3, 1}, {3, 1, 3}, {1,3,2} and {0, 3, 1 ,3}. {3, 1, 3, 2} and the array itself - sub array with max sum was  {0, 3, 1, 3, 2}
Another example - {-1 -6, -7} max sum sub array is {-1} becuase matha sub array ellam {-1}=> -1 , {-6} => -6, {-7} => -7,  {-1, -6} => -7 {-6 -7} +> -13  {-1, -6, -7} -=> -14
*/