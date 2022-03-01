public class Operand implements Item {

	//field
	private String num;
	
	//constructors
	public Operand () {}
	
	public Operand (String value) {
		num = value;
	}
	
	/**
	 * Sets the Operands number to a new number
	 * @param number: new number value
	 */
	public void setNum(String number) {
		num = number;
		
	}

	//get num value
	@Override
	public String toString() {
		return num;
	}

}