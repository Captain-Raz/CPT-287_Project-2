
public class infixCalc {
	
	static Stack nums = new Stack(); // Stack for all numbers
	static Stack operands = new Stack(); // Stack for everything less numbers
	
	public static void main(String[] args) {
		
		nums.offer(1);
		nums.offer(3);
		nums.offer(2);
		nums.offer(54);
		nums.offer(11);
		nums.offer(31);
		nums.offer(21);
		nums.offer(5); // top
		
		Stack.printNums(nums);

	}

}
