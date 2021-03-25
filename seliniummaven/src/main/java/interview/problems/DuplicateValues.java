package interview.problems;

public class DuplicateValues {

	public static void main(String[] args) {
		int[] myArray = {1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 4};
		
		int i = 0;
		int j = i +1;
		int dupeCounter = 1;
		while(i < (myArray.length - 1)) {
			if((j < myArray.length) && (myArray[i] == myArray[j])) {
				dupeCounter = dupeCounter + 1;
				j = j + 1;
			} else {
				if(dupeCounter > 1) {
					System.out.printf("%d appears %d times\n", myArray[i], dupeCounter);
				}
				dupeCounter = 1;
				i = j;
				j = j + 1;
			}
		}
	}
}
