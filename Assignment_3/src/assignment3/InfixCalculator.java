package assignment3;

public class InfixCalculator {

    private String input; //Variable for user input string.
    /*
     * Constructor for creating the infix calculator.
     */
    public InfixCalculator(String input) {
        this.input = input; //String for the user input.
    }

    /*
     * This helper method checks the precedence of the operators. + and - have a lower precedence than * and /.
     */
    public int checkPrecedence(char ch) {

        if (ch == '+' || ch == '-') {
            return 0;
        } else if (ch == '*' || ch == '/'){
            return 1;
        }else{
            return 2;
        }

    }
    /*
     * This method converts the infix expression from the user input into a postfix expression.
     */
    public String convertPostFix(String input) {
        StackListBased <Character> stack = new StackListBased<>(); //Creates a stack based object of Character type.

        char[] inputChar = input.toCharArray(); //Converts the user input into an array of characters.
        StringBuilder postFixExp = new StringBuilder(); //Allows you to add characters to the postfix expression.

        for (char ch : inputChar) { //Iterates through every character in the array.

            switch (ch) {
                //These cases checks if the character in the array is an operator.
                case '+':
                case '-':
                case '/':
                case '*':
                    //Checks if the stack is not empty, does not equal to the start of a bracket, and if the character has a lower precedence than the top of the stack.
                    //If the required conditions are met, it will pop the operations from the stack until a '(' is reached or when its empty or the top of the stack has a lower precedence than the character.
                    while (!stack.isEmpty() && !stack.peek().equals('(') && checkPrecedence(ch) <= checkPrecedence(stack.peek())) {
                        postFixExp.append(' '); //Extra space allocated before placing the character into the postfix string.
                        postFixExp.append(stack.pop());
                    }
                    postFixExp.append(' ');
                    stack.push(ch); //This saves the new operator and gets pushed onto the stack.
                    break;

                //This case determines if the character starts with an opening bracket. Pushes it onto the stack if it does.
                case '(':
                    stack.push(ch);
                    break;
                //This case determines if the character starts with a closing bracket.
                case ')':
                    while (!stack.peek().equals('(')) { //Iterates through the stack if the bracket has an opening bracket. Pops the stack into the postfix expression.
                        postFixExp.append(' ');
                        postFixExp.append(stack.pop());

                    }
                    stack.pop(); //Once the opening bracket is reached, remove the bracket from the stack.
                    break;
                //This case determines if a white space has been encountered. If so, ignore the white space.
                case ' ':
                    break;
                //The default case analyzes all the operands available in the expression. They just get appended to the postfix string.
                default:
                    postFixExp.append(ch);
                    break;
            }//end switch

        }//end for

        //If the stack is still not empty, dump everything out of the stack and onto the postfix string.
        while (!stack.isEmpty()) {
            postFixExp.append(' ');
            postFixExp.append(stack.pop());

        }//end while

        return postFixExp.toString();

    }//end method

    public StringBuilder convertPrefix(String expression){
        String result;
        StringBuilder input = new StringBuilder(expression);
        input.reverse();

        char [] charsExp = new String(input).toCharArray();
        for (int i = 0; i < charsExp.length; i++) {

            if (charsExp[i] == '(') {
                charsExp[i] = ')';
                i++;
            }
            else if (charsExp[i] == ')') {
                charsExp[i] = '(';
                i++;
            }
        }

        String str = new String(charsExp);
        result = convertPostFix(str);
        return new StringBuilder(result).reverse();

    }


    /*
        This method evaluates the Infix expression generated from the user input. It first converts the infix to postfix then evaluates the expression.
     */
    public void evaluateInfix() {

        String postFixExpression = convertPostFix(input);
        StringBuilder preFixExpression = convertPrefix(input);
        System.out.println("The postfix equivalent expression is: " + postFixExpression);
        System.out.println("The prefix equivalent expression is: " + preFixExpression);
        System.out.println("The result is: " + getPostFix(postFixExpression));

    }

    /*
        This method evaluates the postfix expression using a String LinkedList collection using Stack operations.
     */
    public int getPostFix(String postFixExp){

        //Local Variables
        StackListBased <String> stack = new StackListBased<>(); //Creates stack object of String type.
        int operand2;
        int operand1;

        String[] postFE = postFixExp.split(" ");//splits the string into an array of strings separated by whitespace.


        for(String str: postFE){
            boolean numeric = true; //checks if the string is numeric or not via try and catch.

            try{
                Integer.parseInt(str); //Tries to parse the token into an integer.
            } catch (NumberFormatException e) {
                numeric = false;
            }

            //If the string is numeric, then push the string onto the String LinkedList.
            if (numeric){
                stack.push(str);
            } else {
                operand2 = Integer.parseInt(stack.pop()); //Take the top operand and parse it into an int.
                operand1 = Integer.parseInt(stack.pop()); //Take the second most top operand and parse it into an int.

                //Evaluates all the available operations that can be performed on the operands. Pushes the result into the same LinkedList.
                switch(str){
                    case "+":
                        stack.push(Integer.toString(operand1 + operand2));
                        break;
                    case "-":
                        stack.push(Integer.toString(operand1 - operand2));
                        break;
                    case "*":
                        stack.push(Integer.toString(operand1 * operand2));
                        break;
                    case "/":
                        stack.push(Integer.toString(operand1 / operand2));
                        break;
                }//end switch

            }//end if

        }//end for

        return Integer.parseInt(stack.peek()); //returns the final value inside the LinkedList.

    }//end method

}//end class.
