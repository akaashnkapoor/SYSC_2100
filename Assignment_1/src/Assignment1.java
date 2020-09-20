/*
 * SYSC 2100 Assignment 1: Recursion
 * Name: Akaash Kapoor
 * Student Number: 101112895
 * 
 */

public class Assignment1 {
	
	//Question 1: Spock Question. 
	
	public static int c(int n, int k){
		
		if (k == n){ //Base case 1: Only one way to perform c(n,k) therefore function returns 1.
			System.out.println("c(" + n + "," + k + ") = 1");
			return 1;
		}
		
		if (k == 0){ //Base case 2: There will always be one group of 0 to choose from therefore function return 1.
			System.out.println("c(" + n + "," + k + ") = 1");
			return 1;
		}
		
		if (k > n){ //Base case 3: You can't choose an amount that is greater than the initial size, resulting in a return of 0. 
			System.out.println("c(" + n + "," + k + ") = 0");
			return 0;
		}
		
		System.out.print("c(" + n + "," + k + ") = ");
		System.out.println("c(" + (n-1) + "," + (k-1) + " + " + "c("+ (n-1) + "," + k + ")");
		return c(n-1, k-1) + c(n-1, k); //Recursive case
	}
	
	//Question 2: Parade Question
	public static int P(int n){
		//Base case 1: There are only two ways to organize the parade with one of either Band or Float. 
		if (n == 1){
            return 2;
        }
		//Base case 2: There are only three ways to organize the parade: BF.FB,FF. 
        if (n == 2) {
            return 3;
        }
        //Recursive case is defined here. 
        return P(n-1) + P(n-2);
	}
	
	
	//Question 3: Writes the line of characters and in the form of blocks.
	
	public static void writeLine (char ch, int n){
		
		//Base case 1: As n reaches 1, there will only be one character printed.
		if (n==1){
			System.out.print(ch);
			
		} else {
			//Recursive call and print the character. 
			writeLine(ch, n-1);
			System.out.print(ch);
		}
		
	}
	
	public static void writeBlock(char ch, int n, int m){
		
		//Base case 1: As m reaches 1, there will only be one line printed, creating another line to print on.
		if (m == 1){
			writeLine(ch,n);
			System.out.println("");
		} else {
			//Recursive calls while using the writeLine method.
			writeBlock(ch, n, m-1);
			writeLine(ch,n);
			System.out.println("");
		}
	}
	
	
	//Question 4: Reverse the Digits of a Decimal Number
	public static void reverseDigits(int number){
		//Base Case: When you divide number by 10, it will eventually print out the reversed number
		if (number < 10){
			System.out.print(number);
		} else {
			//This prints out the last digit of the number
			System.out.print(number % 10);
			//Recursive call to divide the number by 10
			reverseDigits(number/10);
		}
		
		
	}
	
	//Question 5: Performs a Binary Search on an Alphabetically sorted array. 
	public static int myBinarySearch(String[] anArray, int first, int last, String value){
		int index;
		
		//Base case 1: When the field of search becomes narrow enough, it will print a -1, indicating the string could not be found. 
		if (first > last){
			index = -1;
		}
		
		else {
			
			int mid = (first + last) / 2;
			//Base case 2: if the string is equal to the index value at the middle, then return that index position. 
			if (value == anArray[mid]){
				index = mid;
				return index;
			}
			
			//The compareTo function returns a negative integer if the string (at mid) found is greater than value, and vise versa for the string less than value. 
			if (value.compareTo(anArray[mid]) < 0){
				index = myBinarySearch(anArray, first, mid-1, value);//Recursive function call. Reduces the field of search by half. 
			} else {
				index = myBinarySearch(anArray, mid+1, last, value); //Recursive function call. Reduces the field of search by half. 
			}
		}
		return index;
	}
	

}
