package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class infixCalc static final String INPUT_FILE_TXT = "src/inputFile.txt";//path to the input file
static Stack nums = new Stack(); // Stack for all numbers
static Stack operands = new Stack(); // Stack for everything less numbers

public static void main(String[] args) throws FileNotFoundException {

	String infixEquation;// holds the equation read from one line of the .txt file
	Scanner fScan = new Scanner(new File(INPUT_FILE_TXT));
	
	while (fScan.hasNext()) {//loops through each line of the .txt file
		infixEquation = (fScan.nextLine().replaceAll(" ", ""));//removes the white space from the line before assigning it to infixEquation
		Stack stringStack =	parseEquation(convToStack(shortenAndOr(infixEquation)));//turns && and || into & and |, converts the string to a stack,
		                                                                            //parses that stack into a stack with integers and operators in the form of strings
		int x = eval(stringStack);
		System.out.println(x);
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
 * puts each operator and number in a Operator or Operand object and adds it to an itemStack (deals with == != <= and >= so they can be popped once)
 * @param expression: the equation as a stack of characters with no white space and '&' '|' instead of '&''&' '|''|'. popping this stack will result in the last char of the  expression
 * @return: a Stack of Items containing Operators and Operands from the expression stack in backwards order (restores the equations original order)
 */
public static Stack parseEquation(Stack expression) {
	Stack<Item> itemStack = new Stack<Item>();
	StringBuilder num = new StringBuilder();//holds multi digit numbers
	Operator symbol = new Operator();
	Operand number = new Operand();
	
	while (!expression.isEmpty()) {
		if (Character.isDigit((char)expression.peek())) {//if the top is a number
			while (!expression.isEmpty() && Character.isDigit((char)expression.peek())) {//while it continues to be a number
				num.insert(0, expression.pop());//and that number to the stringBuilder
			}
			number = new Operand(num.toString());// create a new Operand with the string builders value
			
			itemStack.offer(number);//add the new Operand to the itemStack
			num.setLength(0);//reset the stringBuilder
		} else if ((char)expression.peek()=='=') {//if the top is = we know the next char is > < = or ! because the equation is backwards
			if ((char)expression.peekNext()=='=') {
				symbol = new Operator("==");
				itemStack.offer(symbol);//if the next char is = add "==" operator to the itemStack
				expression.pop();
				expression.pop();//pop twice to continue parsing past '=' and '=' nodes
			}else if ((char)expression.peekNext()=='<') {
				symbol = new Operator("<=");
				itemStack.offer(symbol);//if the next char is < add "<=" operator to the itemStack
				expression.pop();
				expression.pop();//pop twice to continue parsing past '=' and '<' nodes
			}else if ((char)expression.peekNext()=='>'){
				symbol = new Operator(">=");
				itemStack.offer(symbol);//if the next char is > add ">=" operator to the itemStack
				expression.pop();
				expression.pop();//pop twice to continue parsing past '=' and '>' nodes
			}else {//Only option left is "!="
				symbol = new Operator("!=");
				itemStack.offer(symbol);
				expression.pop();
				expression.pop();//pop twice to continue parsing past '=' and '!' nodes
			}
		} else {//if the top is any operator other than '=' put it in a new Operator Object and add it to the itemStack
			symbol = new Operator(expression.pop().toString());
			itemStack.offer(symbol);
		}
	}
	
	return itemStack;
			
}//end parseEquation

/**
 * evaluates a stack of Items witch can be Operands or Operators
 * @param itemStk: a stack of Items witch can be Operands or Operators
 * @return: an integer result
 */
public static int eval(Stack<Item> itemStk) {
	Stack<Operand> operandStk = new Stack<Operand>();
	Stack<Operator> operatorStk = new Stack<Operator>();
	int leftNum;
	int rightNum;
	Operand newNum = new Operand();
	
	while (!itemStk.isEmpty()) {//while there are Operands and Operators in the itemStack
		if (Character.isDigit((itemStk.peek().toString()).charAt(0))) {//if the top is a Operand
				operandStk.offer(itemStk.pop());//put that Operand in the integers stack
			
		} else if (itemStk.peek().toString().equals("(")) {//if the top is a "(" Operator 
			operatorStk.offer(itemStk.pop());//put it on the operands stack
			
		} else if (itemStk.peek().toString().equals(")")) {//if the top is a ")" Operator
			while (!operatorStk.peek().toString().equals("(")) {//while the top of the operators stack is not a "("
				rightNum = Integer.parseInt(operandStk.pop().toString());
				leftNum = Integer.parseInt(operandStk.pop().toString());//pull the top two Operands of the integers stack
				newNum = new Operand(((Operator)operatorStk.pop()).calc(leftNum, rightNum));
				operandStk.offer(newNum);//calculates the result of leftNum (operand) rightNum and pushes it onto the integers stack
			}
			operatorStk.pop();//remove the "("
			itemStk.pop();//remove the ")"
			
		} else {//the top is any Operator other than "(" and ")"
			while (!operatorStk.isEmpty() && ((Operator)operatorStk.peek()).precidence()>=((Operator)itemStk.peek()).precidence()) {//if the itemStack's operator is of less or equal precedence to the operatorStk's operator
				rightNum = Integer.parseInt(operandStk.pop().toString());
				leftNum = Integer.parseInt(operandStk.pop().toString());
				newNum = new Operand(((Operator)operatorStk.pop()).calc(leftNum, rightNum));
				operandStk.offer(newNum);//calculate the operatorStk's next Operator with the integer stacks next two Operands. put the result in a Operand object and push it onto the operandStk
			}
			operatorStk.offer(((Operator)itemStk.pop()));//otherwise put the source stacks operator on the operatorStk
		}
		
	}//end sourceStk while
	
	while (!operatorStk.isEmpty()) {//while there are operators in the operatorStk
		rightNum = Integer.parseInt(operandStk.pop().toString());
		leftNum = Integer.parseInt(operandStk.pop().toString());
		newNum = new Operand(((Operator)operatorStk.pop()).calc(leftNum, rightNum));//pop two integers and a symbol and calculate the result
		operandStk.offer(newNum);//push result onto operandStk
	}
	
	return Integer.parseInt(operandStk.pop().toString());//return the last integer in the operandStk
}
}