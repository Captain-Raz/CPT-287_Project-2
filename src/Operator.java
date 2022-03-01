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
		
		String zero = "()";
		String three = "!==";
		String four = ">=<=";
		String five = "+-";
		String six = "*/%";
		
		if (zero.contains(this.toString())) {
			return 0;
		} else if (this.toString().equals("|")) {
			return 1;
		} else if (this.toString().equals("&")) {
			return 2;
		} else if (three.contains(this.toString())) {
			return 3;
		} else if (four.contains(this.toString())) {
			return 4;
		} else if (five.contains(this.toString())) {
			return 5;
		} else if (six.contains(this.toString())) {
			return 6;
		} else if (this.toString().equals("^")) {
			return 7;
		} else {
			System.out.println("Unsupported Operator: " + this.toString());
			return -1;
		}
	}


	//get operator string
	public String toString() {
		return operator;
	}
	
}