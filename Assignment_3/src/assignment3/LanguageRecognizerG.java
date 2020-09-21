package assignment3;

public class LanguageRecognizerG {

    //Instance variables
    private static String word;

    //Constructor to set up the language recognizer.
    public LanguageRecognizerG(String input) {
        word = input;
    }

    //Returns true if the input of letters meets the parameters of the language.
    public boolean recursiveRecogG(String input) {
        int lengthOfInput = input.length(); //Sets the length of the input string.

        //Evaluates if the first character is an empty string.
        if (input.isEmpty()) {
            return true;

        } else if (lengthOfInput == 1) {
            //Evaluates if the first character is <E>.
            return input.equals("&") || input.equals("#");

        } else { //If the string has a length of two or more, then evaluate set conditions.

            //Evaluates if two characters are in the order of <V><E>.
            if ((input.startsWith("W") || input.startsWith("A")) && ((input.endsWith("&") || input.endsWith("#"))) && lengthOfInput == 2) {
                return true;
                //Evaluates <E><G><W>.
            } else if ((input.startsWith("&") || input.startsWith("#")) && ((input.endsWith("W") || input.endsWith("A")))) {
                return recursiveRecogG((input.substring(1, lengthOfInput - 1))); //Do a recursive call if <E><"empty string"><V>.
            }
        }
        return false;
    }


    /*
        This method prints if the input string is part of the language.
     */
    public void recursivePrintG() {

        if (recursiveRecogG(word)) {
            System.out.println("'" + word + "' IS a word in the G language.");
        } else {
            System.out.println("'" + word + "' IS NOT in the G language.");
        }

    }


}
