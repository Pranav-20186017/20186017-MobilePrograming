package calculator;
import java.math.*;
import java.util.Arrays;
import java.util.Scanner;


/**
 * <p> Title: CalculatorValue Class. </p>
 *
 * <p> Description: A component of a JavaFX demonstration application that performs computations </p>
 *
 * <p> Copyright: Lynn Robert Carter © 2019 </p>
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

public class CalculatorValue {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	// These are the major values that define a calculator value
	UNumber measuredValue = new UNumber(0);
	UNumber errorTerm = new UNumber(0);
	String errorMessage = "";
	String errorMessageET = "";
	String unit = "";
	boolean negative = false;
	boolean flag = true;
	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	/*****
	 * This is the default constructor
	 */
	public CalculatorValue() {
	}

	/*****
	 * This copy constructor creates a duplicate of an already existing calculator value
	 * 
	 * @param v calculator value object
	 */
	public CalculatorValue(CalculatorValue v) {
		measuredValue = v.measuredValue;
		errorTerm = v.errorTerm;
		errorMessage = v.errorMessage;
		errorMessageET = v.errorMessageET;
	}

	/*****
	 * This constructor creates a calculator value from a string... Due to the nature
	 * of the input, there is a high probability that the input has errors, so the 
	 * routine returns the value with the error message value set to empty or the string 
	 * of an error message.
	 * 
	 * @param mv measured value
	 * @param et error term
	 * @param unitTerm
	 */
	public CalculatorValue(String mv, String et, String unitTerm) {
		if (mv.length() == 0) {								// If there is nothing there,
			errorMessage = "Input is empty";	// signal an error	
			return;												
		}
		this.unit = unitTerm;
		// If the first character is a plus sign, ignore it.
		int start = 0;										// Start at character position zero
		negative = false;							// Assume the value is not negative
		flag = true;
		if (mv.charAt(start) == '+')							// See if the first character is '+'
			 start++;										// If so, skip it and ignore it
		
		// If the first character is a minus sign, skip over it, but remember it
		else if (mv.charAt(start) == '-'){					// See if the first character is '-'
			start++;										// if so, skip it
			negative = true;								// but do not ignore it
			flag = false;
		}
		//See if there is substring or not and perform the operations
		if(mv.substring(start).length() > 0) {
			errorMessage = MeasuredValueRecognizer.checkMeasureValue(mv.substring(start)); //Error message as a whole is given to errorMessage
			if (errorMessage != "") {							                           // See if the error message is empty
				return;										
			}

			String str = mv.substring(start);   //Store substring into a variable
			String[] temp = mv.split("\\.");    //split using '.'
			if(str.contains("e")||str.contains("E")) {  //check if substring contains 'e' or 'E'
				BigDecimal bg = new BigDecimal(str);
				str = bg.toPlainString();               //convert to plain notation
			}
			String input[] = str.split("\\.");          //Split again with '.'
			if (input.length == 1) {                    //If it is not decimal or not
				measuredValue = new UNumber(input[0], input[0].length(), flag);
			}
			else {
				int count = 0;
				for(int i = 0; i <input[1].length(); i++){     //check for the number of zeros after 
				    if(input[1].charAt(i) == '0'){             //decimal point
				         count++;
				    }
				}
				if(input[0].equals("0") || str.charAt(0) == '.') {      //See if the first character is zero or '.'
				    measuredValue = new UNumber(input[1].substring(count),-count,flag);  //create measured value accordingly
				}
				else {
				    measuredValue = new UNumber(input[0]+input[1], input[0].length(), flag);
				}
		   }
		}
		if(!et.equals("")) {                 //If the error term is not empty
			errorMessageET = ErrorTermRecognizer.checkErrorTerm(et); //Error message as a whole is given to errorMessage
			if (errorMessageET != "") {							// See if the error message is empty
				return;
			}
			String[] temp1 = et.split("\\.");     //Split using '.'
			if (temp1.length == 1 && !(et.contains("E") || et.contains("e"))) {   //if it is not decimal number
				errorTerm = new UNumber(temp1[0], temp1[0].length(), flag);       //and not contains 'e' or 'E'
			}
			else if(temp1.length == 1 && (et.contains("E") || et.contains("e"))) {  //if not decimal number and
				String[] str1;                                                      //contains 'e' or 'E'
				   if(et.contains("E")){
				        str1 = et.split("E");
				   }else {
				        str1 = et.split("e");
				   }
				    errorTerm = new UNumber(str1[0], Integer.parseInt(str1[1])+str1[0].length(),flag);
			}
			   
			else if(temp1.length == 2 && !(et.contains("E") || et.contains("e"))) {   //If decimal and not contains
			    int count = 0;                                                        //'e' or 'E'
			    for(int i = 0; i <temp1[1].length(); i++){
			        if(temp1[1].charAt(i) == '0'){
			            count++;
			        }
			    }
			    if(temp1[0].equals("0") || et.charAt(1) == '.')
			        errorTerm = new UNumber(temp1[1].substring(count),-count,flag);
			    else
			        errorTerm = new UNumber(temp1[0]+temp1[1], temp1[0].length(), flag);
			}
			else if(temp1.length == 2 && (et.contains("E") || et.contains("e"))) {   //If contains 'e' or 'E'
			    int count = 0;
			    for(int i = 0; i <temp1[1].length(); i++){
			        if(temp1[1].charAt(i) == '0'){
			            count++;
			        }
			    }
			    String str1[];
			    if(et.contains("E")){
			        str1 = et.split("E");
			    }else {
			        str1 = et.split("e");
			    }
			    if(str1[0].equals("0") || et.charAt(1) == '.'){
			        errorTerm = new UNumber(temp1[1].substring(count),-count+Integer.parseInt(str1[1]),flag);
			    }else{
			        errorTerm = new UNumber(temp1[0]+temp1[1], temp1[0].length()+Integer.parseInt(str1[1]),flag);
			    }

			}
			
		}		 
	}

	/**********************************************************************************************

	Getters and Setters
	
	**********************************************************************************************/
	
	/*****
	 * This is the start of the getters and setters
	 * 
	 * Get the error message
	 * 
	 * @return errormessage return
	 */
	public String getErrorMessage(){
		return errorMessage;
	}
	
	/*****
	 * Set the current value of a calculator value to a specific double
	 * 
	 * @param mv measured value
	 * @param et error term
	 */
	public void setValue(String mv, String et){
		String[] temp = mv.split("\\.");
		measuredValue = new UNumber(mv, temp[0].length(), true);
		String[] temp1 = et.split("\\.");
		errorTerm = new UNumber(et, temp1[0].length(), true);
	}
	
	/*****
	 * Set the current value of a calculator error message to a specific string
	 * 
	 * @param m error message
	 */
	public void setErrorMessage(String m){
		errorMessage = m;
	}
	
	/*****
	 * Set the current value of a calculator value to the value of another (copy)
	 * 
	 * @param v calculator object
	 */
	public void setValue(CalculatorValue v){
		measuredValue = v.measuredValue;
		errorTerm = v.errorTerm;
		errorMessage = v.errorMessage;
		errorMessageET = v.errorMessageET;
	}
	/*****
	 * This is the start of the getters and setters
	 * 
	 * Get the error message
	 * 
	 * @return String error message of error term
	 */
	public String getETErrorMessage(){
		return errorMessageET;
	}
	
	/**********************************************************************************************

	The toString() Method
	
	**********************************************************************************************/
	
	/*****
	 * This is the default toString method
	 * 
	 * When more complex calculator values are creating this routine will need to be updated
	 * 
	 * @return string to print
	 */
	public String toString() {
		if(this.errorTerm.toString().equals("+0.E+0")) {
			return MeasureScientific(rounding(measuredValue + "", ""))+ " " + this.unit;
		}
		String er = roundErrorTerm(this.errorTerm.toString());
		String mv = rounding(measuredValue + "", er);
		
		return  MeasureScientific(mv)+ " " + er + " " + this.unit;
	}
	
	/*****
	 * This is the debug toString method
	 * 
	 * When more complex calculator values are creating this routine will need to be updated
	 * 
	 * @return debugging string
	 */
	public String debugToString() {
		return "measuredValue = " + measuredValue + " " +"errorTerm = "+ errorTerm +"\nerrorMessage = " + errorMessage + "\n" ;
	}
	
	
	/*************************************************************************************************
	 * Rounding the Measured value terms
	 * @param mv for measured value
	 * @param et for error term
	 */
	
	static String rounding(String mv, String et) {
		if(et.equals("")) {     //if error term is empty
			return mv;
		}
		String measured_value = "";
		String error_value = "";
		if (mv.contains("E") || et.contains("E")) {   //If measured value contains 'e' or 'E'
			BigDecimal val = new BigDecimal(mv);
			BigDecimal val1 = new BigDecimal(et);
			measured_value = val.toPlainString();
			error_value = val1.toPlainString();
		} else {
			measured_value = mv;
			error_value = et;
		}
		int precision_len = 0;
		String[] tokens = measured_value.split("\\.");
		String[] errtokens = error_value.split("\\.");
		char ch = error_value.charAt(0);
		if(ch =='0') {
			precision_len = error_value.substring(2).length();
			BigDecimal value = new BigDecimal(measured_value);
			value = value.setScale(precision_len, RoundingMode.HALF_UP).stripTrailingZeros();
			return value.toPlainString();
		} else if(errtokens[0].length() == 1) {
			BigDecimal value = new BigDecimal(measured_value);
			value = value.setScale(0, RoundingMode.CEILING);
			return value.toPlainString();
		}
		else {
			String[] error_tokens = error_value.split("\\.");
			precision_len = error_tokens[0].length();
			BigDecimal value  = new BigDecimal(tokens[0]);
			MathContext m = new MathContext((tokens[0].length() + 1) - precision_len);
			BigDecimal b = value.round(m);
			return b.toPlainString();
		}

	}
	/***** 
	 * To check if the string contains all zero characters or not
	 * @param str1
	 * @return boolean true or false
	 */
	public static boolean containsAllChars(String str1){
    	for(int i = 0 ; i<str1.length(); i++) {
    		if(str1.charAt(i) != '9') {
    			return false;
    		}
    	}
    	return true;
	}
	/*************************************************************************************************
	 * Helper function to round the terms
	 * @param mv for measured value
	 * @param et for error term
	 */
	public String roundingFunctionality(String mv, String et) {
		String res = rounding(mv, et);
		if (!res.contains(".")) {
			res += ".0";
		}
		return res;
	}
	/*************************************************************************************************
	 * To check the range of error term and convert it into scientific notation accordingly
	 * 
	 * @param input
	 * 
	 */
	public String checkErrorTerm(String input){
		int num = 0;
		String[] arr;
		UNumber nine = new UNumber("9000", 4, true);
		UNumber one = new UNumber("001", 0, true);
		UNumber inp;
		UNumber samp;
		if(input.contains(".")) {
			num = input.indexOf('.');
			arr = input.split("\\.");
			inp = new UNumber(arr[0]+arr[1], num, true);
		} else {
			num = input.length();
			inp = new UNumber(input, num, true);
		}
		//If the range is between 1 and 9000
        if(inp.lessThan(nine) && inp.greaterThan(one) ){
            return inp.toDecimalString()+ "0";
        }else if(!(inp.lessThan(nine) && inp.greaterThan(one)) ){
        	samp = new UNumber(inp.toDecimalString());
            return samp.toString();
        }
        return "";
    }
	/*************************************************************************************************
	 * To convert string to numbered format
	 * 
	 * @param num 
	 * 
	 */
    public String toNumber(String num){
        return new BigDecimal(num).toPlainString();
    }
    /*************************************************************************************************
	 * To check for the range of Measured value terms and convert to scientific notation accordingly
	 * 
	 * @param input for measured value
	 * 
	 */
	public String MeasureScientific(String input) {
		boolean inputFlag = false;
		BigDecimal b = new BigDecimal(input);
		input = b.toPlainString();
		int num = 0;
		String[] arr;
		UNumber nine = new UNumber("10000000", 8, true);
		UNumber one = new UNumber("00001", 0, true);
		UNumber inp;
		UNumber samp;
		if(input.charAt(0)=='-') {    //If the number is negative or not
			inputFlag = true;
		}
		if(input.contains(".")) {    //If it contains '.' or not
			num = input.indexOf('.');
			arr = input.split("\\.");
			inp = new UNumber(arr[0]+arr[1], num, true);
		} else {
			num = input.length();
			inp = new UNumber(input, num, true);
		}
        if(inp.lessThan(nine) && inp.greaterThan(one) ){
        	if(inputFlag) {
        		return "-"+inp.toDecimalString().substring(1)+ "0";
        	}
        	else {
        		return inp.toDecimalString()+ "0";
        	}
        }else if(!(inp.lessThan(nine) && inp.greaterThan(one)) ){
        	samp = new UNumber(inp.toDecimalString());
        	if(inputFlag) {
        		return "-" + samp.toString().substring(1);
        	} else {
        		return samp.toString();
        	}
        }
        return "";
	}
	/**
	 * To round off the error term to one significant digit.
	 * @param err error term
	 * 
	 * @return return error term
	 */
	public String roundErrorTerm(String err) {
		String zerostr = new UNumber().toDecimalString();
		if(err.equals("0.0")) {
			return "";
		}
		errorterm obj = new errorterm(err);
		String temp = obj.getValue();
		BigDecimal bg = new BigDecimal(temp);
		String res = checkErrorTerm(bg.toPlainString());
		return res;
	}

	
	/**********************************************************************************************

	The computation methods
	
	**********************************************************************************************/
	

	/**********************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume
	 * that the caller has verified that things are okay for the operation to take place.  These
	 * methods understand the technical details of the values and their reputations, hiding those
	 * details from the business +logic and user interface modules.
	 * 
	 * Since this is addition, there are no exception handling cases and units are handled at
	 * business logic class
	 * 
	 * @param v operand to add
	 */
	public void add(CalculatorValue v) {
		measuredValue.add(v.measuredValue);
		if(errorTerm.toDecimalString().equals("0.") && v.errorTerm.toDecimalString().equals("0.") ) {
			return;
		}else {
		errorTerm.add(v.errorTerm);
		}
		errorMessage = "";
	}
	/**********************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume
	 * that the caller has verified that things are okay for the operation to take place.  These
	 * methods understand the technical details of the values and their reputations, hiding those
	 * details from the business +logic and user interface modules.
	 * 
	 * Since this is Subtraction, there are no exception handling cases and units are handled at
	 * business logic class
	 * 
	 * @param v operand to sub
	 */
	public void sub(CalculatorValue v) {
		measuredValue.sub(v.measuredValue);
		if(errorTerm.toDecimalString().equals("0.") && v.errorTerm.toDecimalString().equals("0.") ) {
			return;
		}
		errorTerm.add(v.errorTerm);
		errorMessage = "";
	}
	/**********************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume
	 * that the caller has verified that things are okay for the operation to take place.  These
	 * methods understand the technical details of the values and their reputations, hiding those
	 * details from the business +logic and user interface modules.
	 * 
	 * Since this is multiplication, there are exception handling cases like if any of the operand terms is zero which is
	 * handled at business logic class and units are handled at business logic class
	 * 
	 * @param v operand to multiply
	 */
	public void mpy(CalculatorValue v) {
		if(errorTerm.toDecimalString().equals("0.") && v.errorTerm.toDecimalString().equals("0.") ) {
			this.measuredValue.mpy(v.measuredValue);
			errorMessage = "";
			return;
		}
		UNumber m1 = new UNumber(this.measuredValue);
		UNumber m2 = new UNumber(v.measuredValue);
		UNumber e1 = new UNumber(this.errorTerm);
		UNumber e2 = new UNumber(v.errorTerm);
		e1.mpy(m2);
		e2.mpy(m1);
		e1.add(e2);
		m1.mpy(m2);
		measuredValue = m1;
		errorTerm = e1;
		errorMessage = "";
	}
	/**********************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume
	 * that the caller has verified that things are okay for the operation to take place.  These
	 * methods understand the technical details of the values and their reputations, hiding those
	 * details from the business +logic and user interface modules.
	 *
	 * Since this is Division we have one case that is handled. i.e Division by Zero Error
	 * @param v operand to divide
	 */
	public void div(CalculatorValue v) {
		if(v.measuredValue.toDecimalString().equals("0.")) {
			errorMessage = "Cannot divide by zero";
			return;
		} 
		if(errorTerm.toDecimalString().equals("0.") && v.errorTerm.toDecimalString().equals("0.") ) {
			measuredValue.div(v.measuredValue);
			errorMessage = "";
			return;
		}
		else {
			this.errorTerm.div(this.measuredValue);
			v.errorTerm.div(v.measuredValue);
			measuredValue.div(v.measuredValue);	
			this.errorTerm.add(v.errorTerm);
			this.errorTerm.mpy(this.measuredValue);
			errorMessage = "";
		}
		
	}
	/**********************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume
	 * that the caller has verified that things are okay for the operation to take place.  These
	 * methods understand the technical details of the values and their reputations, hiding those
	 * details from the business +logic and user interface modules.
	 *
	 * Since this is Squareroot, we have one case that is handled. i.e Negative numbers not allowed
	 */
	public void sqrt() {
		UNumber zero = new UNumber("0",0,true);
		if(this.measuredValue.lessThan(zero)) {
			errorMessage = "Square root of negative number impossible";			
		} else {
			if(this.unit.equals("km^2")) {
				this.unit = "km";
			} else if(this.unit.equals("sec^2")) {
				this.unit = "sec";
			}
			UNumber errorFraction = new UNumber("05", 1, true);	
			this.errorTerm.div(this.measuredValue);
			DemoSquareRootWithUNumberWIthMissingCode temp = new DemoSquareRootWithUNumberWIthMissingCode(this.measuredValue.toDecimalString());
			this.measuredValue = temp.res;
			this.errorTerm.mpy(errorFraction);
			this.errorTerm.mpy(this.measuredValue);
			errorMessage = "";
		}
		
	}
}
