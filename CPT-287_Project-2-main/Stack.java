import java.io.*;
import java.util.*;

public class Stack<T> implements basicStack {
	
	/* * 
	 * * Creates Node Object
	 * */
	
	private class Node {
		T data;
		Node next;
		
		Node (T someData) {
			data = someData;
		}
	}
	/* *
	 * * Private Variables
	 * */
	private Node top;
	private int numOfItems;
	
	/* *
	 * * @return: Number of Items in Stack
	 * */
	
	public int size() {
		return numOfItems;
	}
	
	/* *
	 * * @return: False if size == 0; True if size != 0
	 * */
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/* *
	 * * @return: Top's data 
	 * * Does Not Remove Top from Stack
	 * */
	
	public Object peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Accessing empty stack");
		}
		return top.data;
	}
	
	/* *
	 * * @return: second to Top's data 
	 * * Does Not Remove Top from Stack
	 * */
	
	public T peekNext() {
		if (isEmpty()) { 
			throw new NoSuchElementException("Accessing empty stack"); 
		}else if (size()<2) {
			throw new NoSuchElementException("there is no second element");
		} else {
        return top.next.data;// returns the second to last item pushed without removing it from the queue
		}
	}
	
	/* *
	 * * @return: Top's data
	 * * Removes Top from Stack, and decrements numOfItems
	 * */
	
	public Object pop() {
		if (isEmpty()) {
			throw new NoSuchElementException("Accessing empty stack");
		}
		T toBeRemoved = top.data;
		top = top.next;
		numOfItems--;
		return toBeRemoved;
	}
	
	/* *
	 * * Adds element to top of Stack
	 * * Increments numOfItems
	 * * Type cast input Value to Generic Object T
	 * */
	
	public void offer(Object value) { // Had to type cast to (T)
		Node newTop = new Node((T) value);
		newTop.next = top;
		top = newTop;
		numOfItems++;
	}
	
	/* *
	 * * @param: Stack nums (The Stack storing the numbers of the equation
	 * * Used to test what numbers are in the stack at any given point
	 * * TESTING PURPOSES ONLY
	 */
	
	public static void printNums(Stack nums) {
		int[] numHolder = new int[nums.size()];
		
		for (int num : numHolder) {
			num = (int) nums.pop();
			System.out.println(num);
		}
		
		for (int num : numHolder) {
			nums.offer(num);
		}
	} // End printNums
	
	/* *
	 * * @param: Stack operands (The Stack storing the operands of the equation
	 * * Used to test what operands are in the stack at any given point
	 * * TESTING PURPOSES ONLY
	 */
	
	public static void printOps(Stack operands) { // TESTING PURPOSES
		String[] opHolder = new String[operands.size() - 1];
		
		for (int i = 0; i < opHolder.length; i++) {
			opHolder[i] = (String) operands.pop();
			System.out.println(opHolder[i]);
		}
		
		for (String opperand : opHolder) {
			operands.offer(opperand);
		}
	} // End printOpps
} // End class Stack
