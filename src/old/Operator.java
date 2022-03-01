

public class Operator implements Item {

	//field
	private String operator;
	
	//constructors
	public Operator () {}
	
	public Operator(String theOperator) {
		operator = theOperator;
	}
	
	//Methods
	public void setOperator(String newOp) {
		operator = newOp;
	}
	
	/**
	 * Calculates the Operators result given two ordered integer parameters
	 * @param leftNum:the number on the left side of the equation
	 * @param rightNum:the number on the right side of the equation
	 * @return: the result of calculating the left and right numbers with the Operators operator converted to a string
	 */
	public String calc(int leftNum, int rightNum) {
		int result = 0;
		boolean bool;// to convert "&" "|" ">" "<" "<=" ">=" "==" and "!=" case results into integers
		switch(this.toString()) {
		case "+":
			result = leftNum + rightNum;
			break;
		case "-":
			result = leftNum-rightNum;
			break;
		case "*":
			result = leftNum*rightNum;
			break;
		case "/":
			result = leftNum/rightNum;
			break;
		case "%":
			result = leftNum%rightNum;
			break;
		case "&":
			bool = (leftNum!=0) && (rightNum!=0);
			if (bool == true){
				result = 1;
			}else {
				result = 0;
			}
			break;
		case "|":
			bool = (leftNum!=0)||(rightNum!=0);
			if (bool == true){
				result = 1;
			}else {
				result = 0;
			}
			break;
		case "^":
			result = (int) Math.pow(leftNum, rightNum);
			break;
		case "<":
			bool = leftNum<rightNum;
			if (bool == true){
				result = 1;
			}else {
				result = 0;
			}
			break;
		case "<=":
			bool = leftNum<=rightNum;
			if (bool == true){
				result = 1;
			}else {
				result = 0;
			}
			break;
		case ">=":
			bool = leftNum>=rightNum;
			if (bool == true){
				result = 1;
			}else {
				result = 0;
			}
			break;
		case ">":
			bool = leftNum>rightNum;
			if (bool == true){
				result = 1;
			}else {
				result = 0;
			}
			break;
		case "!=":
			bool = leftNum!=rightNum;
			if (bool == true){
				result = 1;
			}else {
				result = 0;
			}
			break;
		case "==":
			bool = leftNum==rightNum;
			if (bool == true){
				result = 1;
			}else {
				result = 0;
			}
			break;
		default:
			System.out.println("Unsupported operator: " + this.toString());
		}
		return Integer.toString(result);//returns integer converted to string
	}
	
	/**
	 * returns the precedence of the operands operator
	 * @return: 1-7 number of precedence
	 */
	public int precidence() {
		switch (this.toString()) {
			case "(", ")":
				return 0;//Precedence of 0 prevents eval method from trying to calculate using '(' or ')' as Operators
			case "|":
				return 1;
			case "&":
				return 2;
			case "==", "!=":
				return 3;
			case ">", ">=", "<", "<=":
				return 4;
			case "+", "-":
				return 5;
			case "*", "/", "%":
				return 6;
			case "^":
				return 7;
			default:
				System.out.println("Unsupported Operator");
				return -1;
		}
	}


	//get operator string
	public String toString() {
		return operator;
	}
	
}
