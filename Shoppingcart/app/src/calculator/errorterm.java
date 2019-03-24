package calculator;
import java.util.regex.*;

/*******
 * <p> Title: errorterm Class. </p>
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
public class errorterm {
	static String resultval = "";
	/*****
	 * In the constructor, start method has been called
	 * @param val it is of string type
	 */
	errorterm(String val){
		start(val);
	}
	/*****
	 * Get the current result ErrorValue of a calculator value
	 * @return String value
	 */
	public String getValue() {
		return resultval;
	}
	/*****
	 * To return N number of zeros string
	 * @param input number of zeros
	 * @return zerostring which of string type
	 */
	public static String zerosstring(int inputval) {
		String zeros = "";
		for (int i = 0; i < inputval; i++) {
			zeros = zeros + "0";
		}
		return zeros;
	}
	/*****
	 * To modify the error Value according to the requirements i.e Rounding off error Value
	 * @param doubleval which is of string type
	 * @return modified string after updating
	 */
	public static String valuemodification(String doubleval) {
		if(doubleval.equals("+0.")){   //check if it zero or not
			return "";
		}
		if(doubleval.charAt(0) == '+' || doubleval.charAt(0) == '-') { //Check for the '+' or '-' characters at the start
			doubleval = doubleval.substring(1);
		}
		
		if(!doubleval.contains(".")) {      //check for the presence of '.'  
			doubleval = doubleval + ".0";   //If no, append '.0' at the end
		}
		String[] splitval = doubleval.split("\\.");    //Split using '.'
		String mainpart = splitval[0];
		String decimalpart = splitval[1];
		boolean matchingstatus = Pattern.matches("^0*$", mainpart);   //Check if the left part of decimal point is zero
		if (matchingstatus) {                   //If yes, check for the index of first non-zero character
			String regexp = "[1-9]"; 
			Pattern pattern = Pattern.compile(regexp);
			Matcher matcher = pattern.matcher(decimalpart);
			if (matcher.find()) {
				int startindex = matcher.start();
				if(startindex == decimalpart.length() - 1) {      //If only one significant digit, return as it is
					return doubleval;
				}
				int decimalfirstval = Integer.parseInt(decimalpart.charAt(startindex) + "");   //else append according
				if (decimalfirstval == 9) {                        //to the first non-zero
					if (startindex == 0) {                     //If it is 0.9
						String finalmain = "1.0";
						return finalmain;
					} else {
						String zeros = zerosstring(startindex - 1);    //Else append one less than index number of zeros
						String finalmain = mainpart + "." + zeros + "1";
						return finalmain;
					}
				} else {
					String zeros = zerosstring(startindex);             //Else append index number of zeros to incremented decimal character
					decimalfirstval = decimalfirstval + 1;
					String finalmain = mainpart + "." + zeros + decimalfirstval;
					return finalmain;
				}
			}
		} else {                                          //If it is not zero
			boolean decimalstatus = Pattern.matches("^0*$", decimalpart);        //Check if the decimal part is zero
			boolean mainsubstatus = Pattern.matches("^0*$", mainpart.substring(1)); //Check if mainpart contains one significant digit
			if(mainsubstatus && decimalstatus) {    //If yes, return as it is
				return doubleval;
			}
			int firstval = Integer.parseInt(mainpart.charAt(0) + "");    //Else, increment the first character and append one less than main part
			firstval++;                                                  //length of zeros and return the string
			String zeros = zerosstring(mainpart.length() - 1);
			String finalmain = firstval + zeros;
			return finalmain;
		}
		return "";
	}
	/*****
	 * To launch the execution of rounding off error Value
	 * @param str String type
	 * return nothing
	 */
	public static void start(String str) {
		String doubleval = str;
		if (doubleval.contains("e") || doubleval.contains("E")) {
			if(doubleval.contains("e")) {
			    String[] splits = doubleval.split("e");
				resultval = valuemodification(splits[0]) + "e" +splits[1];	
			} else {
				String[] splits = doubleval.split("E");
				resultval = valuemodification(splits[0]) + "E" +splits[1];
			}	
		} else {
			resultval = valuemodification(doubleval);
		}
	}
}
