import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import personalCalculator.Stack;

public class infixCalc {
	
	static final String INPUT_FILE_TXT = "src/inputFile.txt";//path to the input file
	static Stack nums = new Stack(); // Stack for all numbers
	static Stack operands = new Stack(); // Stack for everything less numbers
	
	public static void main(String[] args) throws FileNotFoundException {
		
		nums.offer(1);
		nums.offer(3);
		nums.offer(2);
		nums.offer(54);
		nums.offer(11);
		nums.offer(31);
		nums.offer(21);
		nums.offer(5); // top
		
		Stack.printNums(nums);

		String infixEquation;// holds the equation read from one line of the .txt file
		Scanner fScan = new Scanner(new File(INPUT_FILE_TXT));
		
		while (fScan.hasNext()) {//loops through each line of the .txt file
			infixEquation = (fScan.nextLine().replaceAll(" ", ""));//removes the white space from the line before assigning it to infixEquation
			Stack stringStack =	parseEquation(convToStack(shortenAndOr(infixEquation)));//turns && and || into & and |, converts the string to a stack,
			                                                                            //parses that stack into a stack with integers and operators in the form of strings
			while (!stringStack.isEmpty()) {//Temporary code: tests parser
				System.out.print("" + stringStack.pop() + " ");
			}
			System.out.println();
		}
		fScan.close();
		
	}//end main
	
	/**
	 * Converts the equation from a string into a stack
	 * @param equationString: an equation read from the .txt file 
	 * @return: the original string converted to a stack (popping every value in the stack produces backwards equation**)
	 */
	public static Stack convToStack(String equationString) {//Converts the string to a stack **could be in stack class?**
		Stack inputStack = new Stack();
	
		for (int i = 0; i < equationString.length(); i++) {
			inputStack.offer(equationString.charAt(i));
		}
		return inputStack;
	}//end convToStack
	
	/**
	 * turns && into & and || into | to simplify parsing method
	 * @param equationString: an equation read from the .txt file 
	 * @return: the same equation with | and & replacing || and &&
	 */
	public static String shortenAndOr(String equationString) {
		StringBuilder noDouble = new StringBuilder();
		for (int i = 0; i < equationString.length(); i++ ) {
			if (equationString.charAt(i)=='&' || equationString.charAt(i)=='|') {
				noDouble.append(equationString.charAt(i));
				i++;
			} else {
				noDouble.append(equationString.charAt(i));
			}
		}
		return noDouble.toString();
	}//end shortenAndOr
	
	
	/**
	 * puts each operator and number in a string and adds it to a Stack (deals with == != <= and >= so they can be popped once)
	 * @param expression: a stack read from the .txt file with white space removed, and & and | converted
	 * @return: a Stack of strings containing operators and numbers from the expression stack (in backwards order) **restores the equations original order**
	 */
	public static Stack parseEquation(Stack expression) {
		Stack stringStack = new Stack();
		StringBuilder num = new StringBuilder();//holds multi digit numbers
		
		while (!expression.isEmpty()) {
			if (Character.isDigit((char)expression.peek())){//if the top is a number
				while (!expression.isEmpty() && Character.isDigit((char)expression.peek())) {//while it continues to be a number
					num.append(expression.pop());//and that number to the stringBuilder
				}
				stringStack.offer(num.toString());//add the stringBuilder to the other stack
				num.setLength(0);//reset the stringBuilder
			} else if ((char)expression.peek()=='=') {//if the top is = we know the next char is > < = or ! because the equation is backwards
				if ((char)expression.peekNext()=='=') {
					stringStack.offer("==");//if the next char is = add "==" operator to the other stack
					expression.pop();
					expression.pop();//pop twice to continue parsing past '=' and '=' nodes
				}else if ((char)expression.peekNext()=='<') {
					stringStack.offer("<=");//if the next char is < add "<=" operator to the other stack
					expression.pop();
					expression.pop();//pop twice to continue parsing past '=' and '<' nodes
				}else if ((char)expression.peekNext()=='>'){
					stringStack.offer(">=");//if the next char is > add ">=" operator to the other stack
					expression.pop();
					expression.pop();//pop twice to continue parsing past '=' and '>' nodes
				}else {//Only option left is "!="
					stringStack.offer("!=");
					expression.pop();
					expression.pop();//pop twice to continue parsing past '=' and '!' nodes
				}
			} else {
				stringStack.offer(expression.pop());//if the top is any operator other than '=' add it to the other stack
			}
		}
		
		return stringStack;
	}//end parseEquation
}
