
public class Operand {
	//field
		private String operator;
		
		//constructor
		public Operand(String theOperator) {
			operator = theOperator;
		}
		
		//Methods
		public String getOperator() {
			return operator;
		}
		
		public void setOperator(String newOp) {
			operator = newOp;
		}
		
		/**
		 * returns the precedence of the operands operator
		 * @return: 1-7 number of precedence
		 */
		public int precidence() {
			switch (this.getOperator()) {
				case "||":
					return 1;
				case "&&":
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
					return -1;// returns -1 as precedence should result in an error
			}
			
			
		}
	}
