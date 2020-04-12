package cas.vayu.disasterarea.builder;

import java.util.Iterator;
/**
 * Stack implemented using Linked List
 * @author Oussama Saoudi
 *
 * @param <Item> Generic type of the stack
 */
public class Stack<Item> implements Iterable<Item>{

    Node first;
    int numberOfValues;
    /**
     * Returns iterator for all values in the stack
     * @return Iterator of items for values in the stack
     */
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    /**
     * Returns true if stack is empty and false otherwise.
     * @return boolean if the stack is empty
     */
    public boolean isEmpty(){
        return numberOfValues == 0;
    }
    /**
     * returns number of values in the stack
     * @return integer for number of values in the stack
     */
    public int size(){
        return numberOfValues;
    }
    /**
     * Iterator for all the items that are within the stack
     * @author Oussama Saoudi
     *
     */
    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        /**
         * Checks if the Iterator has a next value
         */
        public boolean hasNext(){
            return current != null;
        }
        /**
         * Returns the next item in the iterator
         */
        public Item next() {
            Item output = current.item;
            current = current.next;
            return output;
        }

    }
    /**
     * Node class which stores generic item and next node
     * @author Oussama Saoudi
     *
     */
    private class Node{
        Item item;
        Node next;
    }
    /**
     * Pops item from the top of the stack and returns it
     * @return Returns item that was popped from top of the stack
     */
    public Item pop(){
        Item output = first.item;
        first = first.next;
        numberOfValues--;
        return output;
    }
    
    /**
     * Returns the top item in the stack without popping it
     * @return top Item in the stack
     */
    public Item top() {
    	return first.item;
    }
    /**
     * Pushes item to the top of the stack
     * @param item Adds item to the top of the stack 
     */
    public void push(Item item){
        Node pushed = new Node();
        pushed.item = item;
        pushed.next = first;
        first = pushed;
        numberOfValues++;
    }
}
