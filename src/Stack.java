
import java.io.*;
import java.util.*;

public class Stack<T> implements basicStack {
	
	private class Node {
		T data;
		Node next;
		
		Node (T someData) {
			data = someData;
		}
	}
	
	private Node top;
	private int numOfItems;
	
	public int size() {
		return numOfItems;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public Object peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Accessing empty stack");
		}
		return top.data;
	}

	public Object pop() {
		if (isEmpty()) {
			throw new NoSuchElementException("Accessing empty stack");
		}
		T toBeRemoved = top.data;
		top = top.next;
		numOfItems--;
		return toBeRemoved;
	}

	public void offer(Object value) { // Had to type cast to (T)
		Node newTop = new Node((T) value);
		newTop.next = top;
		top = newTop;
		numOfItems++;
	}
	
	public static void printNums(Stack nums) { // TESTING PURPOSES
		int[] numHolder = new int[nums.size()];
		
		//System.out.println(nums.size());
		
		for (int num : numHolder) {
			num = (int) nums.pop();
			System.out.println(num);
		}
		
		for (int spot : numHolder) {
			nums.offer(spot);
		}
	} // End printNums
	
	public static void printOpps(Stack opperands) { // TESTING PURPOSES
		String[] oppHolder = new String[opperands.size() - 1];
		
		for (int i = 0; i < oppHolder.length; i++) {
			oppHolder[i] = (String) opperands.pop();
			System.out.println(oppHolder[i]);
		}
		
		for (String spot : oppHolder) {
			opperands.offer(spot);
		}
	} // End printOpps
} // End class Stack
