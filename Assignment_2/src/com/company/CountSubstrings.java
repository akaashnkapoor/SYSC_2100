package com.company;

import java.text.DecimalFormat;
import java.util.*; //mainly user the Scanner, Arraylist, and Linkedlist library.
import java.io.*; //used for catching exceptions.

public class CountSubstrings {

    //Instance variables
    private static Scanner userInput = new Scanner(System.in); //reads user input
    private static Scanner inputStream1 = null; //creates an input stream for the file. Two streams declared, one for ArrayList and one for LinkedList.
    private static Scanner inputStream2 = null;
    private static List<Character> listTextAL = new ArrayList<>(); //ArrayList declaration for the text file
    private static List<Character> listPatternAL = new ArrayList<>();//Arraylist declaration for the pattern the user seeks.
    private static List<Character> listTextLL = new LinkedList<>();//Linkedlist declaration for the text file.
    private static List<Character> listPatternLL = new LinkedList<>();//Linkedlist declaration for the patter the user seeks.
    private static DecimalFormat df = new DecimalFormat("##0.0");

    /*
   Returns the lowest index at which substring pattern begins in text (or else -1)
    */
    private static int findBrute(List<Character> text, List<Character> pattern) {
        int n = text.size();
        int m = pattern.size();
        for (int i = 0; i <= n - m; i++) { //try every starting index within text.
            int k = 0; // k is index into pattern
            while (k < m && text.get(i + k) == pattern.get(k))
                // kth character of pattern matches
                k++;
            if (k == m) // if we reach the end of the pattern,
                return i; // substring text[i..i+m-1] is a match
        } //end for
        return -1;// search failed
    }

    /*
    Returns the number of occurrences the substring appears in the text file.
     */
    private static int matchFunction(Scanner inputStream, List<Character> text, List<Character> pattern) {

        int countPattern = 0;//Keeps track of the successful occurrences.

        while (inputStream.hasNext()) { //checks for every new line of the text file.

            String[] line = inputStream.nextLine().split(" "); //Splits the line into tokens.


            for(String word: line){
                //For every token analyzed, turn every token into an array of characters and store it in the list.
                for(int i =0 ; i < word.length(); i++){
                    text.add(word.charAt(i));
                }//end for

                int result = findBrute(text, pattern); //returns the index where the substring is found.

                if (result != -1) { //if it returns anything but -1, substring was found in the token.
                    countPattern++; //increment for every matching substring found.
                }//end if

                text.clear(); //clears the token from the list. loops back again for the next token.
            }//end for

        }//end while
        inputStream.close(); //closes input stream.
        return countPattern;
    }

    /*
     * Main Program.
     */
    public static void main(String[] args) {
        //local variables
        String filename; //name of the file
        String pattern; //pattern the user seeks within the file.
        long timerStart; //timer start and ends using System.currentTimeMillis() function
        long timerEnd;
        int result; //result of the number of substrings found within text.

        //Reads in input from the user, asking for filename and pattern search.
        System.out.print("Please enter a path for the input file: ");
        filename = userInput.nextLine();
        System.out.print("Enter the pattern to look for: ");
        pattern = userInput.nextLine();

        //Checks for a file exception.
        try {
            inputStream1 = new Scanner(new File(filename));
            inputStream2 = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println("Error Opening File! ");
            System.exit(0);
        }

        //Stores characters from the pattern string into the character list for both arraylist and linkedlist.
        for (int i = 0; i < pattern.length(); i++) {
            listPatternAL.add(pattern.replace(" ","").charAt(i));
            listPatternLL.add(pattern.replace(" ","").charAt(i));
        }

        //Testing for ArrayList.
        timerStart = System.currentTimeMillis(); //starts the timer.
        result = matchFunction(inputStream1, listTextAL, listPatternAL);
        timerEnd = System.currentTimeMillis(); //ends the timer
        long timeElapsed = timerEnd - timerStart;
        System.out.println("Using ArrayList: " + result + " matches, derived in " + df.format(timeElapsed) + " milliseconds.");



        //Testing for Linked List.
        timerStart = System.currentTimeMillis(); //starts the timer.
        result = matchFunction(inputStream2, listTextLL, listPatternLL);
        timerEnd = System.currentTimeMillis();
        timeElapsed = timerEnd-timerStart;//ends the timer
        System.out.println("Using LinkedList: " + result + " matches, derived in " + df.format(timeElapsed) + " milliseconds.");

    }


}
