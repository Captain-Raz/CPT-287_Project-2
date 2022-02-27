import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

		Scanner fScan;
		String infixEquation = "";
		fScan = new Scanner(new File(INPUT_FILE_TXT));
		while (fScan.hasNext()) {
			infixEquation = (fScan.nextLine().replaceAll(" ", ""));//removes the white space
			System.out.println(infixEquation);
		}
		fScan.close();
		
	}//end main

}
