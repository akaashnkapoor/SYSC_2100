import java.util.*;
public class Assignment4 {

    //Helper method that returns the index with the largest object T for selection sort.
    private static <T extends Comparable<? super T>> int findMax(T[] theArray, int size) {

        int indexSoFar = 0;
        for (int currIndex = 1; currIndex < size; ++currIndex) {
            if (theArray[currIndex].compareTo(theArray[indexSoFar]) > 0) {
                indexSoFar = currIndex;
            }//end if
        }//end for
        return indexSoFar;
    }//end method

    //Question 1: Recursive Selection Sort using the findMax helper method.
    public static <T extends Comparable<? super T>> void recursiveSelectionSort(T[] theArray, int n) {

        int largest = findMax(theArray, n); //finds the index with the largest object.

        //Perform the swap of the array contents.
        T temp = theArray[largest];
        theArray[largest] = theArray[n - 1];
        theArray[n - 1] = temp;

        if (n == 1) {
            return;//if n is 1 then the array is sorted.
        } else {
            recursiveSelectionSort(theArray, n - 1); //recursive call
        }//end if

    }//end method

    //Helper method for bubble sorting the array.
    private static <T extends Comparable<? super T>> void Swap(T[] theArray, int size) {

        for (int i = 0; i < size - 1; i++) {
            //Determining if the object of current index is greater than next index. If so swap the contents.
            if (theArray[i].compareTo(theArray[i + 1]) > 0) {
                T temp = theArray[i];
                theArray[i] = theArray[i + 1];
                theArray[i + 1] = temp;
            }//end if
        }//end for
    }//end method

    //Question 1: Recursive Selection Sort using Swap as helper method.
    public static <T extends Comparable<? super T>> void recursiveBubbleSort(T[] theArray, int n) {

        Swap(theArray, n);//call the swap method
        if (n == 1) {
            return; //if n is 1 then the array is sorted.
        } else {
            recursiveBubbleSort(theArray, n - 1);//perform recursive call
        }
    }

    //Language Recognition Method to determine if a word is a palindrome.
    public static boolean isInLanguage(String str) {

        Stack<Character> stack = new Stack<>(); //declares stack
        Queue<Character> queue = new LinkedList<>(); //declares queue.

        boolean inLanguage; //checks if the string input is in the language.
        char charStr[] = str.toCharArray();
        int index = 0;

        //pushes all characters onto the stack until the $ is reached.
        while (charStr[index] != '$'){
            stack.push(charStr[index]);
            index++;//increment the index position.
        }//end while

        index++; //skips the dollar sign symbol.

        //add the rest of the characters to the queue.
        while (index < str.length()){
            queue.add(charStr[index]);
            index++;
        }//end while

        inLanguage = true;
        while (inLanguage){
            try{
                Character stackTop = stack.pop();
                Character queueFront = queue.poll();
                if(stackTop != queueFront){ //checks if the top of stack is equal to the front of the queue. If not, its not in the language.
                    inLanguage = false;
                }
            } catch (EmptyStackException e){ //checks for empty stack exception.
                break;
            }//end catch
        }//end while

        if(inLanguage && stack.isEmpty() && queue.isEmpty()){ //Everything needs to be empty in order to be in the langugage.
            return true;
        } else {
            return false;
        }

    }//end method

    //Exercise 3: converting to integer number from an input string.
    public static int convertToNumber(String str){

        Queue<Character> queue = new LinkedList<>();//creates a new character
        Iterator<Character> it = queue.iterator();//utilizes the iterator to iterate through the queue.
        StringBuilder num = new StringBuilder(); //used to rebuild the characters without the spaces.

        char [] charStr = str.toCharArray(); //turn input string into an array of characters.
        for(char ch: charStr){
            if(ch != ' ') { //if there is no whitespace add it to the queue.
                queue.add(ch);
            }//end if
        }//end for

        while(it.hasNext()){ //while the queue has an filled index position, append the character to the string builder.
            num.append(queue.poll());
        }//end while

        //convert the string builder into a string and into an integer.
        return Integer.parseInt(num.toString());
    }//end method

}//end class