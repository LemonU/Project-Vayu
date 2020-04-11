package cas.vayu.disasterarea.builder;

import java.util.Iterator;

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
     * returns number of values in the queue
     * @return integer for number of values in the queue
     */
    public int size(){
        return numberOfValues;
    }
    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){
            return current != null;
        }
        public void remove(){}
        public Item next() {
            Item output = current.item;
            current = current.next;
            return output;
        }

    }

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
