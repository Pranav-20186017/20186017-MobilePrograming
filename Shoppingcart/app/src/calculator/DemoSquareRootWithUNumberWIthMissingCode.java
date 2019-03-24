package calculator;

/*******
 * <p> Title: DemoSquareRootWithUNumberMissingCode Class. </p>
 * 
 * <p> Description: A JavaFX demonstration application and baseline for a sequence of projects </p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2019 </p>
 * 
 * @author Lynn Robert Carter
 * @author Chaitanya Puritipati
 * 
 * @version 4.00	2017-10-16 The mainline of a JavaFX-based GUI implementation of a long integer calculator
 * @version 4.01	2019-02-08 Minor documentation updates.
 * @version 4.02    2019-02-13 Implemented Subtraction, Multiplication, Division methods and also handled Division by zero error
 * @version 4.03    2019-02-23 Implemented Square root by converting the Calculator into Double calculator
 * @version 4.04    2019-03-02 Implemented Error terms and also rounded off result values based on requirements
 * @version 4.05    2019-03-08 Implemented Units into the calculator and UNumber class 
 */

public class DemoSquareRootWithUNumberWIthMissingCode {
	
	private static int numSignificantDigits = 25;
	public UNumber res;
	DemoSquareRootWithUNumberWIthMissingCode(String st){
		res = start(st);
	}
	
	/*****
	 * This private method counts how many digits are the same between two estimates
	 */
	private static int howManyDigitsMatch(UNumber newGuess, UNumber oldGuess) {
		// If the characteristics is not the same, the digits in the mantissa do not matter
		if (newGuess.getCharacteristic() != oldGuess.getCharacteristic()) return 0;
		
		// The characteristic is the same, so fetch the mantissas so we can compare them
		byte[] newG = newGuess.getMantissa();
		byte[] oldG = oldGuess.getMantissa();
		
		// Computer the shorter of the two
		int size = newGuess.length();
		int otherOne = oldGuess.length();
		if (otherOne < size) size = otherOne;
		
		// Loop through the digits as long as they match
		for (int ndx = 0; ndx < size; ndx++)
			if (newG[ndx] != oldG[ndx]) return ndx;	// If the don't match, ndx is the result
		
		// If the loop completes, then the size of the shorter is the length of the match
		return size;
	}
	

	/*****
	 * This is the mainline 
	 * 
	 * @param args	The program parameters are ignored
	 */
	public static UNumber start(String st) {
		String input = st;
		UNumber newGuess = new UNumber(0.0);
		// As long as the length of the input String is positive, continue processing the input
		while (input.length() > 0) {
			// Does this input line consist of a value?
				double inputValue = Double.parseDouble(st);
				UNumber theValue =  new UNumber(inputValue);
				UNumber two = new UNumber(2.0);
				
				newGuess = new UNumber(theValue);				// Compute the estimate
				newGuess.div(two);
				UNumber oldGuess;										// Temporary value for determining when to terminate the loop
				int iteration = 0;										// Count the number of iterations
				int digitsMatch = 0;
				do {
					long start = System.nanoTime();
					iteration++;										// Next iteration
					oldGuess = newGuess;								// Save the old guess             
					newGuess = new UNumber(theValue, numSignificantDigits);       // Compute the new guess
					newGuess.div(oldGuess);
					newGuess.add(oldGuess);
					newGuess.div(two);
					long stop = System.nanoTime();
					digitsMatch = howManyDigitsMatch(newGuess, oldGuess);
					System.out.println("     " + iteration + " estimate " + newGuess.toString() + " with " + digitsMatch + " digits matching taking " + (stop-start)/1000000000.0 + " seconds" );		// Display the intermediate result
										
				} while (digitsMatch < numSignificantDigits);			// Determine if the old and the new guesses are "close enough"
				return newGuess;							// return the final result
		}
		return newGuess;              //return the final result
	}
}
