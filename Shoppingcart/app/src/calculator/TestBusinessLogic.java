package calculator;

/**
 * <p> Title: TestCharCode. </p>
 *
 * <p> Description: A component of the Calculator application </p>
 *
 * <p> Copyright: Lynn Robert Carter © 2017 </p>
 *
 * @author Lynn Robert Carter
 * @author Chaitanya Puritipati
 *
 * @version 4.00	2017-10-17 The JavaFX-based GUI for the implementation of a calculator
 * @version 4.01	2019-02-08 Minor documentation update.
 * @version 4.02    2019-02-13 Implemented Subtraction, Multiplication, Division methods and also handled Division by zero error
 * @version 4.03    2019-02-23 Implemented Square root by converting the Calculator into Double calculator
 * @version 4.04    2019-03-02 Implemented Error terms and also rounded off result values based on requirements
 * @version 4.05    2019-03-08 Implemented Units into the calculator and UNumber class
 *
 */


public class TestBusinessLogic {

	/**********
	 * This class roots the execution of the test of the BusinessLogic class.  The application
	 * tests the class by invoking the class methods and checking the result to see if the results
	 * are proper.
	 *
	 */

	/*********************************************************************************************/

	/**********
	 * The check method compares an Expected String to an Actual String and returns true if the
	 * Strings match and false otherwise.  In addition, the Strings are displayed to the console
	 * and a message is display stating whether or not there is a difference.  If there is a
	 * difference, the character at the point of the difference in the actual String is replaced
	 * with a "?" and both are displayed making it clear what character is the start of the
	 * difference
	 *
	 * @param Expected	The String object of the expected value
	 * @param Actual		The String object of the actual value
	 */
	private static boolean check(String expected, String actual) {
		// Display the input parameters
		System.out.println("***Expected String");
		System.out.println(expected);
		System.out.println("***Actual String");
		System.out.println(actual);

		// Check to see if there is a difference
		int lesserLength = expected.length();
		if (lesserLength > actual.length()) lesserLength = actual.length();
		int ndx = 0;
		while (ndx < lesserLength && expected.charAt(ndx) == actual.charAt(ndx))
			ndx++;

		// Explain why the loop terminated and if there is a difference make it clear to the user
		if (ndx < lesserLength || lesserLength < expected.length() || lesserLength < actual.length()) {
			System.out.println("*** There is a difference!\n" + expected.substring(0, ndx) + "? <-----");
			return false;
		}
		System.out.println("*** There is no difference!\n");
		return true;
	}

	/*********************************************************************************************/

	/**********
	 * This main method roots the execution of this test.  The method ignores the program
	 * parameters.  After initializing several local variables, it performs a sequence of
	 * tests, displaying information accordingly and tallying the number of successes and
	 * failures.
	 *
	 * @param args	Ignored by this application.
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// Display the header message to the console and initialize local variables
		System.out.println("Test BusinessLogic Class\n");
		int numPassed = 0;
		int numFailed = 0;

		// 1. Perform a default constructor test
		BusinessLogic test = new BusinessLogic();		// Perform the test

		System.out.println("1. No input given");	    // No input that was given

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\n******************\n" +
				"*\n" +
				"* Business Logic\n" +
				"*\n" +
				"******************\n" +
				"operand1 = 0.0 \n" +
				"     operand1ErrorMessage = \n" +
				"     operand1Defined = false\n" +
				"operand2 = 0.0 \n" +
				"     operand2ErrorMessage = \n" +
				"     operand2Defined = false\n" +
				"result = 0.0 \n" +
				"     resultErrorMessage = \n" +
				"*******************\n\n", test.debugToString())) {

			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			//System.out.println(test.debugToString());
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		System.out.println("####################################################################################");

		// 2. Perform setOperand1 test
		test = new BusinessLogic();											// Set up for the test

		boolean flag = test.setOperand1("1234567890123456","10", "");					// Perform the test

		System.out.println("2. Input = \"1234567890123456\"");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\nReturned boolean = true\n******************\n" +
				"*\n" +
				"* Business Logic\n" +
				"*\n" +
				"******************\n" +
				"operand1 = +0.12345678901234600E+16 20.0 \n" +
				"     operand1ErrorMessage = \n" +
				"     operand1Defined = true\n" +
				"operand2 = 0.0 \n" +
				"     operand2ErrorMessage = \n" +
				"     operand2Defined = false\n" +
				"result = 0.0 \n" +
				"     resultErrorMessage = \n" +
				"*******************\n\n", "\nReturned boolean = " + flag + test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		System.out.println("####################################################################################");

		// 3. Perform setOperand2 test
		test = new BusinessLogic();											// Set up for the test

		flag = test.setOperand2("1234560123456789", "10", "");							// Perform the test

		System.out.println("3. Input = \"1234560123456789\"");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\nReturned boolean = true\n******************\n" +
				"*\n" +
				"* Business Logic\n" +
				"*\n" +
				"******************\n" +
				"operand1 = 0.0 \n" +
				"     operand1ErrorMessage = \n" +
				"     operand1Defined = false\n" +
				"operand2 = +0.12345601234567900E+16 20.0 \n" +
				"     operand2ErrorMessage = \n" +
				"     operand2Defined = true\n" +
				"result = 0.0 \n" +
				"     resultErrorMessage = \n" +
				"*******************\n\n", "\nReturned boolean = " + flag + test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		System.out.println("####################################################################################");

		// 4. Perform setResult test
		test = new BusinessLogic();											// Set up for the test

		flag = test.setResult("1234560123456789","10" , "");							// Perform the test

		System.out.println("4. Input = \"1234560123456789\"");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\nReturned boolean = true\n******************\n" +
				"*\n" +
				"* Business Logic\n" +
				"*\n" +
				"******************\n" +
				"operand1 = 0.0 \n" +
				"     operand1ErrorMessage = \n" +
				"     operand1Defined = false\n" +
				"operand2 = 0.0 \n" +
				"     operand2ErrorMessage = \n" +
				"     operand2Defined = false\n" +
				"result = +0.12345601234567900E+16 20.0 \n" +
				"     resultErrorMessage = \n" +
				"*******************\n\n", "\nReturned boolean = " + flag + test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		System.out.println("####################################################################################");

		// 5. Perform setOperand1ErrorMessage test
		test = new BusinessLogic();											// Set up for the test

		test.setOperand1ErrorMessage("Test message for operand1");				// Perform the test

		System.out.println("5. Input = \"Test message for operand1\"");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\n******************\n" +
				"*\n" +
				"* Business Logic\n" +
				"*\n" +
				"******************\n" +
				"operand1 = 0.0 \n" +
				"     operand1ErrorMessage = Test message for operand1\n" +
				"     operand1Defined = false\n" +
				"operand2 = 0.0 \n" +
				"     operand2ErrorMessage = \n" +
				"     operand2Defined = false\n" +
				"result = 0.0 \n" +
				"     resultErrorMessage = \n" +
				"*******************\n\n", test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		System.out.println("####################################################################################");

		// 6. Perform getOperand1ErrorMessage test
		test = new BusinessLogic();											// Set up for the test

		test.setOperand1ErrorMessage("Test message for operand1");				// Perform the test

		System.out.println("6. Input = \"Test message for operand1\"");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("Test message for operand1", test.getOperand1ErrorMessage())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		System.out.println("####################################################################################");

		// 7. Perform setOperand2ErrorMessage test
		test = new BusinessLogic();											// Set up for the test

		test.setOperand2ErrorMessage("Test message for operand2");				// Perform the test

		System.out.println("7. Input = \"Test message for operand2\"");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\n******************\n" +
				"*\n" +
				"* Business Logic\n" +
				"*\n" +
				"******************\n" +
				"operand1 = 0.0 \n" +
				"     operand1ErrorMessage = \n" +
				"     operand1Defined = false\n" +
				"operand2 = 0.0 \n" +
				"     operand2ErrorMessage = Test message for operand2\n" +
				"     operand2Defined = false\n" +
				"result = 0.0 \n" +
				"     resultErrorMessage = \n" +
				"*******************\n\n", test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		System.out.println("####################################################################################");

		// 8. Perform getOperand2ErrorMessage test
		test = new BusinessLogic();											// Set up for the test

		test.setOperand2ErrorMessage("Test message for operand2");				// Perform the test

		System.out.println("8. Input = \"Test message for operand2\"");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("Test message for operand2", test.getOperand2ErrorMessage())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		System.out.println("####################################################################################");


		// 9. Perform setResultErrorMessage test
		test = new BusinessLogic();											// Set up for the test

		test.setResultErrorMessage("Test message for result");					// Perform the test

		System.out.println("9. Input = \"Test message for result\"");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\n******************\n" +
				"*\n" +
				"* Business Logic\n" +
				"*\n" +
				"******************\n" +
				"operand1 = 0.0 \n" +
				"     operand1ErrorMessage = \n" +
				"     operand1Defined = false\n" +
				"operand2 = 0.0 \n" +
				"     operand2ErrorMessage = \n" +
				"     operand2Defined = false\n" +
				"result = 0.0 \n" +
				"     resultErrorMessage = Test message for result\n" +
				"*******************\n\n", test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		System.out.println("####################################################################################");

		//10. Perform getResultErrorMessage test
		test = new BusinessLogic();											// Set up for the test

		test.setResultErrorMessage("Test message for result");					// Perform the test

		System.out.println("10. Input = \"Test message for result\"");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("Test message for result", test.getResultErrorMessage())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		System.out.println("####################################################################################");

		//11. Perform addition test
		test = new BusinessLogic();											// Set up for the test

		test.setOperand1("12345678","0.001" , "");
		test.setOperand2("87654321","1" , "");
		String answer = null;
		try {
			answer = test.addition();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("11. Input = \n\"12345678\"\n\"87654321\"");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\nReturned string = +0.999999990E+8 2.0 \n******************\n" +
				"*\n" +
				"* Business Logic\n" +
				"*\n" +
				"******************\n" +
				"operand1 = +0.123456780E+8 0.0010 \n" +
				"     operand1ErrorMessage = \n" +
				"     operand1Defined = true\n" +
				"operand2 = +0.876543210E+8 1.0 \n" +
				"     operand2ErrorMessage = \n" +
				"     operand2Defined = true\n" +
				"result = +0.999999990E+8 2.0 \n" +
				"     resultErrorMessage = \n" +
				"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		System.out.println("####################################################################################");

		//12. Perform subtraction test
		test = new BusinessLogic();											// Set up for the test

		test.setOperand1("12345678","0.001" , "");
		test.setOperand2("87654321","1" , "");
		answer = test.subtraction();

		System.out.println("12. Input = \n\"12345678\"\n\"87654321\"");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\nReturned string = -0.753086430E+8 2.0 \n******************\n" +
				"*\n" +
				"* Business Logic\n" +
				"*\n" +
				"******************\n" +
				"operand1 = +0.123456780E+8 0.0010 \n" +
				"     operand1ErrorMessage = \n" +
				"     operand1Defined = true\n" +
				"operand2 = +0.876543210E+8 1.0 \n" +
				"     operand2ErrorMessage = \n" +
				"     operand2Defined = true\n" +
				"result = -0.753086430E+8 2.0 \n" +
				"     resultErrorMessage = \n" +
				"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		System.out.println("####################################################################################");

		//13. Perform multiplication test
		test = new BusinessLogic();											// Set up for the test

		test.setOperand1("12345678","0.001", "");
		test.setOperand2("87654321","1", "");
		answer = test.multiplication();

		System.out.println("13. Input = \n\"12345678\"\n\"87654321\"");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\nReturned string = +0.10821520000000000E+16 +0.200000000E+8 ^2\n******************\n" +
				"*\n" +
				"* Business Logic\n" +
				"*\n" +
				"******************\n" +
				"operand1 = +0.123456780E+8 0.0010 \n" +
				"     operand1ErrorMessage = \n" +
				"     operand1Defined = true\n" +
				"operand2 = +0.876543210E+8 1.0 \n" +
				"     operand2ErrorMessage = \n" +
				"     operand2Defined = true\n" +
				"result = +0.10821520000000000E+16 +0.200000000E+8 ^2\n" +
				"     resultErrorMessage = \n" +
				"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		System.out.println("####################################################################################");

		//14. Perform division test
		test = new BusinessLogic();											// Set up for the test

		test.setOperand1("12345678","0.001", "");
		test.setOperand2("87654321","1", "");
		answer = test.division();

		System.out.println("14. Input = \n\"12345678\"\n\"87654321\"");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\nReturned string = 0.140845060 0.0000000020 no-unit\n******************\n" +
				"*\n" +
				"* Business Logic\n" +
				"*\n" +
				"******************\n" +
				"operand1 = +0.123456780E+8 0.0010 \n" +
				"     operand1ErrorMessage = \n" +
				"     operand1Defined = true\n" +
				"operand2 = +0.876543210E+8 1.0 \n" +
				"     operand2ErrorMessage = \n" +
				"     operand2Defined = true\n" +
				"result = 0.140845060 0.0000000020 no-unit\n" +
				"     resultErrorMessage = \n" +
				"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		System.out.println("####################################################################################");

		//15. units test
		test = new BusinessLogic();											// Set up for the test

		test.setOperand1("12345678","0.001", "km");
		test.setOperand2("87654321","1", "m");
		answer = test.addition();

		System.out.println("15. Input = \n\"12345678\"\n\"87654321\"");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\nReturned string = +0.124333323210E+11 2.0 m\n******************\n" +
				"*\n" +
				"* Business Logic\n" +
				"*\n" +
				"******************\n" +
				"operand1 = +0.123456780E+8 0.0010 km\n" +
				"     operand1ErrorMessage = \n" +
				"     operand1Defined = true\n" +
				"operand2 = +0.876543210E+8 1.0 m\n" +
				"     operand2ErrorMessage = \n" +
				"     operand2Defined = true\n" +
				"result = +0.124333323210E+11 2.0 m\n" +
				"     resultErrorMessage = \n" +
				"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		System.out.println("####################################################################################");

		//16. units test
		test = new BusinessLogic();											// Set up for the test

		test.setOperand1("12345678","0.001", "gms");
		test.setOperand2("87654321","1", "m");
		answer = test.addition();

		System.out.println("16. Input = \n\"12345678\"\n\"87654321\"");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\nReturned string = \n******************\n" +
				"*\n" +
				"* Business Logic\n" +
				"*\n" +
				"******************\n" +
				"operand1 = +0.123456780E+8 0.0010 gms\n" +
				"     operand1ErrorMessage = \n" +
				"     operand1Defined = true\n" +
				"operand2 = +0.876543210E+8 1.0 m\n" +
				"     operand2ErrorMessage = \n" +
				"     operand2Defined = true\n" +
				"result = +0.123456780E+8 0.0010 gms\n" +
				"     resultErrorMessage = Incompatible-units\n" +
				"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		System.out.println("####################################################################################");

		//17. units test - multiplication
		test = new BusinessLogic();											// Set up for the test

		test.setOperand1("12345678","0.001", "km");
		test.setOperand2("87654321","1", "m");
		answer = test.multiplication();

		System.out.println("17. Input = \n\"12345678\"\n\"87654321\"");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\nReturned string = +0.10821520000000000000E+19 +0.200000000E+8 m^2\n******************\n" +
				"*\n" +
				"* Business Logic\n" +
				"*\n" +
				"******************\n" +
				"operand1 = +0.123456780E+8 0.0010 km\n" +
				"     operand1ErrorMessage = \n" +
				"     operand1Defined = true\n" +
				"operand2 = +0.876543210E+8 1.0 m\n" +
				"     operand2ErrorMessage = \n" +
				"     operand2Defined = true\n" +
				"result = +0.10821520000000000000E+19 +0.200000000E+8 m^2\n" +
				"     resultErrorMessage = \n" +
				"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		System.out.println("####################################################################################");

		//18. units test - division
		test = new BusinessLogic();											// Set up for the test

		test.setOperand1("12345678","0.001", "km");
		test.setOperand2("87654321","1", "m");
		answer = test.division();

		System.out.println("18. Input = \n\"12345678\"\n\"87654321\"");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\nReturned string = 140.845060 0.0000000020 no-units\n******************\n" +
				"*\n" +
				"* Business Logic\n" +
				"*\n" +
				"******************\n" +
				"operand1 = +0.123456780E+8 0.0010 km\n" +
				"     operand1ErrorMessage = \n" +
				"     operand1Defined = true\n" +
				"operand2 = +0.876543210E+8 1.0 m\n" +
				"     operand2ErrorMessage = \n" +
				"     operand2Defined = true\n" +
				"result = 140.845060 0.0000000020 no-units\n" +
				"     resultErrorMessage = \n" +
				"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		System.out.println("####################################################################################");

		//19. units test - square root
		test = new BusinessLogic();											// Set up for the test

		test.setOperand1("12345678","0.001", "km");
		//test.setOperand2("87654321","1", "m");
		answer = test.squareroot();

		System.out.println("19. Input = \n\"12345678\"\n\"87654321\"");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\nReturned string = 3513.641700570 0.00000010 km\n******************\n" +
				"*\n" +
				"* Business Logic\n" +
				"*\n" +
				"******************\n" +
				"operand1 = +0.123456780E+8 0.0010 km\n" +
				"     operand1ErrorMessage = \n" +
				"     operand1Defined = true\n" +
				"operand2 = 0.0 \n" +
				"     operand2ErrorMessage = \n" +
				"     operand2Defined = false\n" +
				"result = 3513.641700570 0.00000010 km\n" +
				"     resultErrorMessage = \n" +
				"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		System.out.println("####################################################################################");

		//20. units test - square root of negative numbers
		test = new BusinessLogic();			// Set up for the test

		test.setOperand1("-21","", "km");
		//test.setOperand2("87654321","1", "m");
		answer = test.squareroot();

		System.out.println("20. Input = \n\"12345678\"\n\"87654321\"");

		// Check the actual output against the expected.  If they match, the test has been passed and display the proper
		// message and tally the result
		if (check("\nReturned string = \n******************\n" +
				"*\n" +
				"* Business Logic\n" +
				"*\n" +
				"******************\n" +
				"operand1 = -21.0 km\n" +
				"     operand1ErrorMessage = \n" +
				"     operand1Defined = true\n" +
				"operand2 = 0.0 \n" +
				"     operand2ErrorMessage = \n" +
				"     operand2Defined = false\n" +
				"result = -21.0 km\n" +
				"     resultErrorMessage = Square root of negative number impossible\n" +
				"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
			numPassed++;
			System.out.println("\tPass");
		}
		// If they do not match, display that there was a failure and tally that result
		else {
			numFailed++;
			System.out.println("\tFail");
		}
		System.out.println();
		System.out.println("####################################################################################");

		//21. Homanah transfer- pi^2
			test = new BusinessLogic();	  // Set up for the test

			test.setOperand1("3.14159265358979","5e-15", "");
			test.setOperand2("3.14159265358979","5e-15", "");
			answer = test.multiplication();

			System.out.println("21. Input = pi ^2");

			// Check the actual output against the expected.  If they match, the test has been passed and display the proper
			// message and tally the result
			if (check("\nReturned string = 9.869604401089340 0.000000000000040 ^2\n******************\n" +
					"*\n" +
					"* Business Logic\n" +
					"*\n" +
					"******************\n" +
					"operand1 = 3.141592653589790 0.0000000000000050 \n" +
					"     operand1ErrorMessage = \n" +
					"     operand1Defined = true\n" +
					"operand2 = 3.141592653589790 0.0000000000000050 \n" +
					"     operand2ErrorMessage = \n" +
					"     operand2Defined = true\n" +
					"result = 9.869604401089340 0.000000000000040 ^2\n" +
					"     resultErrorMessage = \n" +
					"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
				numPassed++;
				System.out.println("\tPass");
			}
			// If they do not match, display that there was a failure and tally that result
			else {
				numFailed++;
				System.out.println("\tFail");
			}
			System.out.println();
			System.out.println("####################################################################################");

			//22. Homanah transfer- 4pi^2
			test = new BusinessLogic();											// Set up for the test

			test.setOperand1("9.869604401089340","4e-10", "");
			test.setOperand2("4","0.5", "");
			answer = test.multiplication();

			System.out.println("22. Input = 4pi ^2");

			// Check the actual output against the expected.  If they match, the test has been passed and display the proper
			// message and tally the result
			if (check("\nReturned string = 40.0 6.0 ^2\n******************\n" +
					"*\n" +
					"* Business Logic\n" +
					"*\n" +
					"******************\n" +
					"operand1 = 9.869604401090 0.00000000040 \n" +
					"     operand1ErrorMessage = \n" +
					"     operand1Defined = true\n" +
					"operand2 = 4.0 0.50 \n" +
					"     operand2ErrorMessage = \n" +
					"     operand2Defined = true\n" +
					"result = 40.0 6.0 ^2\n" +
					"     resultErrorMessage = \n" +
					"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
				numPassed++;
				System.out.println("\tPass");
			}
			// If they do not match, display that there was a failure and tally that result
			else {
				numFailed++;
				System.out.println("\tFail");
			}
			System.out.println();
			System.out.println("####################################################################################");


			//23. Homanah transfer- a^2
			test = new BusinessLogic();											// Set up for the test

			test.setOperand1("188760000","0.5", "km");
			test.setOperand2("188760000","0.5", "km");
			answer = test.multiplication();

			System.out.println("23. Input = a ^2");

			// Check the actual output against the expected.  If they match, the test has been passed and display the proper
			// message and tally the result
			if (check("\nReturned string = +0.356303376000000000E+17 +0.2000000000E+9 km^2\n******************\n" +
					"*\n" +
					"* Business Logic\n" +
					"*\n" +
					"******************\n" +
					"operand1 = +0.1887600000E+9 0.50 km\n" +
					"     operand1ErrorMessage = \n" +
					"     operand1Defined = true\n" +
					"operand2 = +0.1887600000E+9 0.50 km\n" +
					"     operand2ErrorMessage = \n" +
					"     operand2Defined = true\n" +
					"result = +0.356303376000000000E+17 +0.2000000000E+9 km^2\n" +
					"     resultErrorMessage = \n" +
					"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
				numPassed++;
				System.out.println("\tPass");
			}
			// If they do not match, display that there was a failure and tally that result
			else {
				numFailed++;
				System.out.println("\tFail");
			}
			System.out.println();
			System.out.println("####################################################################################");


			//24. Hohmann transfer- a^3
			test = new BusinessLogic();											// Set up for the test

			test.setOperand1("188760000","5e-15", "km");
			test.setOperand2("188760000","5e-15", "km");
			answer = test.multiplication();

			System.out.println("24. Input = a ^3");

			// Check the actual output against the expected.  If they match, the test has been passed and display the proper
			// message and tally the result
			if (check("\nReturned string = +0.356303376000000000E+17 0.0000020 km^2\n******************\n" +
					"*\n" +
					"* Business Logic\n" +
					"*\n" +
					"******************\n" +
					"operand1 = +0.1887600000E+9 0.0000000000000050 km\n" +
					"     operand1ErrorMessage = \n" +
					"     operand1Defined = true\n" +
					"operand2 = +0.1887600000E+9 0.0000000000000050 km\n" +
					"     operand2ErrorMessage = \n" +
					"     operand2Defined = true\n" +
					"result = +0.356303376000000000E+17 0.0000020 km^2\n" +
					"     resultErrorMessage = \n" +
					"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
				numPassed++;
				System.out.println("\tPass");
			}
			// If they do not match, display that there was a failure and tally that result
			else {
				numFailed++;
				System.out.println("\tFail");
			}
			System.out.println();
			System.out.println("####################################################################################");


			//25. Hohmann transfer- a^3
			test = new BusinessLogic();											// Set up for the test

			test.setOperand1("+0.356303376000000000E+17","2e-5", "km^2");
			test.setOperand2("188760000","5e-15", "km");
			answer = test.multiplication();

			System.out.println("25. Input = a ^3");

			// Check the actual output against the expected.  If they match, the test has been passed and display the proper
			// message and tally the result
			if (check("\nReturned string = +0.67255825253760000000000000000E+28 5000.0 km^3\n******************\n" +
					"*\n" +
					"* Business Logic\n" +
					"*\n" +
					"******************\n" +
					"operand1 = +0.356303376000000000E+17 0.000020 km^2\n" +
					"     operand1ErrorMessage = \n" +
					"     operand1Defined = true\n" +
					"operand2 = +0.1887600000E+9 0.0000000000000050 km\n" +
					"     operand2ErrorMessage = \n" +
					"     operand2Defined = true\n" +
					"result = +0.67255825253760000000000000000E+28 5000.0 km^3\n" +
					"     resultErrorMessage = \n" +
					"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
				numPassed++;
				System.out.println("\tPass");
			}
			// If they do not match, display that there was a failure and tally that result
			else {
				numFailed++;
				System.out.println("\tFail");
			}
			System.out.println();
			System.out.println("####################################################################################");

			//26. Hohmann transfer- 4pi^2 * a ^ 3
			test = new BusinessLogic();											// Set up for the test

			test.setOperand1("40.0","6", "km^2");
			test.setOperand2("+0.67255825253760000000000000000E+28","5e3", "km");
			answer = test.multiplication();

			System.out.println("26. Input = 4pi^2 * a ^3");

			// Check the actual output against the expected.  If they match, the test has been passed and display the proper
			// message and tally the result
			if (check("\nReturned string = +0.2690000000000000000000000000000000E+33 +0.500000000000000000000000000000E+29 km^3\n******************\n" +
					"*\n" +
					"* Business Logic\n" +
					"*\n" +
					"******************\n" +
					"operand1 = 40.0 6.0 km^2\n" +
					"     operand1ErrorMessage = \n" +
					"     operand1Defined = true\n" +
					"operand2 = +0.67255825253760000000000000000E+28 6000.0 km\n" +
					"     operand2ErrorMessage = \n" +
					"     operand2Defined = true\n" +
					"result = +0.2690000000000000000000000000000000E+33 +0.500000000000000000000000000000E+29 km^3\n" +
					"     resultErrorMessage = \n" +
					"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
				numPassed++;
				System.out.println("\tPass");
			}
			// If they do not match, display that there was a failure and tally that result
			else {
				numFailed++;
				System.out.println("\tFail");
			}
			System.out.println();
			System.out.println("####################################################################################");


			//27. Hohmann transfer- 4pi^2 * a ^ 3 / GM
			test = new BusinessLogic();											// Set up for the test

			test.setOperand1("0.2690000000000000000000000000000000E+33","5E-15", "km^3");
			test.setOperand2("1.327E+13","5e-15", "km");
			answer = test.division();

			System.out.println("27. Input = 4pi^2 * a ^3 / GM");

			// Check the actual output against the expected.  If they match, the test has been passed and display the proper
			// message and tally the result
			if (check("\nReturned string = +0.2027128862094951017332328560660E+20 0.0000000090 km^2\n******************\n" +
					"*\n" +
					"* Business Logic\n" +
					"*\n" +
					"******************\n" +
					"operand1 = +0.2690000000000000000000000000000000E+33 0.0000000000000050 km^3\n" +
					"     operand1ErrorMessage = \n" +
					"     operand1Defined = true\n" +
					"operand2 = +0.132700000000000E+14 0.0000000000000050 km\n" +
					"     operand2ErrorMessage = \n" +
					"     operand2Defined = true\n" +
					"result = +0.2027128862094951017332328560660E+20 0.0000000090 km^2\n" +
					"     resultErrorMessage = \n" +
					"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
				numPassed++;
				System.out.println("\tPass");
			}
			// If they do not match, display that there was a failure and tally that result
			else {
				numFailed++;
				System.out.println("\tFail");
			}
			System.out.println();
			System.out.println("####################################################################################");


			//28. Hohmann transfer- sqrt(4pi^2 * a ^ 3 / GM)
			test = new BusinessLogic();											// Set up for the test

			test.setOperand1("0.2027128862094951017332328560660E+20","9E-15", "sec^2");
			answer = test.squareroot();

			System.out.println("28. Input = sqrt(4pi^2 * a ^ 3 / GM)");

			// Check the actual output against the expected.  If they match, the test has been passed and display the proper
			// message and tally the result
			if (check("\nReturned string = +0.4502364780973327576431138396311019360E+10 0.00000000000000000000000090 sec\n******************\n" +
					"*\n" +
					"* Business Logic\n" +
					"*\n" +
					"******************\n" +
					"operand1 = +0.2027128862094951017332328560660E+20 0.0000000000000090 sec^2\n" +
					"     operand1ErrorMessage = \n" +
					"     operand1Defined = true\n" +
					"operand2 = 0.0 \n" +
					"     operand2ErrorMessage = \n" +
					"     operand2Defined = false\n" +
					"result = +0.4502364780973327576431138396311019360E+10 0.00000000000000000000000090 sec\n" +
					"     resultErrorMessage = \n" +
					"*******************\n\n", "\nReturned string = " + answer + test.debugToString())) {
				numPassed++;
				System.out.println("\tPass");
			}
			// If they do not match, display that there was a failure and tally that result
			else {
				numFailed++;
				System.out.println("\tFail");
			}
			System.out.println();
			System.out.println("####################################################################################");


		System.out.println("Number of tests passed: " + numPassed);
		System.out.println("Number of tests failed: " + numFailed);

	}
}
