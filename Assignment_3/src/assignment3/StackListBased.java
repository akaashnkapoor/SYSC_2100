package assignment3;
import java.util.*;

//List Based ADT stack. Can accept any type of stack (Character, String, Integer, etc).
public class StackListBased<E> {

    private E top; //Variable to indicate the top of the list.
    private LinkedList<E> items; //The construction of the list. Ensures LinkedList based implementation of the ADT list.

    public StackListBased(){ //constructor for the ADT List.
        this.items = new LinkedList<>();
        this.top = null;
    }

    //Creates the stack list of any Type.
    public void createStack(){
        this.items = new LinkedList<>();
    }

    //Removes all the list contents with no empty stack Exception since this is a Referenced Based ADT.
    public void popAll(){
        items.clear();
    }

    //Checks if the stack is empty.
    public boolean isEmpty(){
        return this.items.isEmpty();
    }


    //Adds to the Stack
    public void push(E top){
        this.items.add(0, top);
    } //There is no stack exception for push method for LinkedList stack implementation.


    //Removes from Stack with Exception.
    public E pop(){
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            E temp = this.items.get(0); //gets the object from the top of the stack  before removal.
            this.items.remove(0);
            return temp;
        }

    }

    //Looks at the top of the stack with Exception.
    public E peek(){
        if (isEmpty()){
            throw new EmptyStackException();
        } else {
            return this.items.get(0);
        }
    }

}
