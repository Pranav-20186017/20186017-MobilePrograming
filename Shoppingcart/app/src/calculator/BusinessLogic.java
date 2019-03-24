
package calculator;
import java.util.Arrays;
import java.math.*;

/**
 * <p> Title: BusinessLogic Class. </p>
 * 
 * <p> Description: The code responsible for performing the calculator business logic functions. 
 * This class performs the business logic of the calculator by using three CalculatorValues and 
 * performs actions with and on them.  The class expects data from the User Interface to arrive
 * as Strings and returns Strings to it.  This class calls the CalculatorValue class to do the 
 * computations and this class knows nothing about the actual representation of CalculatorValues,
 * that is the responsibility of the CalculatorValue class and the classes it calls.  Hiding the 
 * representation of calculator values from the business logic means that this code does not have
 * to change when there is a change in the representation changes. Similarly, the this class does
 * not need to change if aspects of the user interface changes.</p>
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

public class BusinessLogic {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	// These are the major calculator values 
	private CalculatorValue operand1 = new CalculatorValue("0", "0", "");
	private CalculatorValue operand2 = new CalculatorValue("0", "0", "");
	private CalculatorValue result = new CalculatorValue("0","0", "");
	private String operand1ErrorMessage = "";
	private boolean operand1Defined = false;
	private String operand2ErrorMessage = "";
	private boolean operand2Defined = false;
	private String operand1ETErrorMessage = "";
	private String operand2ETErrorMessage = "";
	private boolean errorTerm1Defined = false;
	private boolean errorTerm2Defined = false;
	private String resultErrorMessage = "";
	private unit unitObj;
	private boolean checkBoxFlag1 = false;
	private boolean checkBoxFlag2 = false;
	private boolean operand1mverror = false;
	private boolean operand2mverror = false;

	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/
	
	/**********
	 * This method initializes all of the elements of the business logic aspect of the calculator.
	 * There is no special computational initialization required, so the initialization of the
	 * attributes above are all that are needed.
	 */
	public BusinessLogic() {
	}

	/**********************************************************************************************

	Getters and Setters
	
	**********************************************************************************************/
	
	/**********
	 * This public setter takes an input String, checks to see if there is a non-empty input string.
	 * If so, it uses it to create a new CalculatorValue and places it into operand1, any associated
	 * error message is placed into operand1ErrorMessage, and sets the defined flag accordingly.
	 * 
	 * @param mv measured value
	 * @param et error term
	 * @return	True if the set did not generate an error; False if there was invalid input
	 */
	public boolean setOperand1(String mv, String et, String unit) {
		operand1Defined = false;						 // Assume the operand will not be defined
		errorTerm1Defined = false;  
		operand1mverror = false;
		if (mv.length() <= 0 && et.length() <= 0) {		// See if the input is empty. If so no error
			operand1ErrorMessage = "";					// message, but the operand is not defined.
			return true;								// Return saying there was no error.
		}
		operand1 = new CalculatorValue(mv,et, unit);             //Intialize the operand1 Calculator value Object
		operand1ErrorMessage = operand1.getErrorMessage();       //See if there is any error for measured value
		operand1ETErrorMessage = operand1.getETErrorMessage();   //and Error term.
		System.out.println(operand1ErrorMessage);
		System.out.println(operand1ETErrorMessage);
		if (operand1ErrorMessage.length() > 0) {                 //If there are errors present in either measured
			operand1mverror = true;                              //value or error value, return false
			return false;
		}
		if(operand1ETErrorMessage.length() > 0) {
			if(operand1ErrorMessage.equals("Input is empty")) {
				operand1mverror = true;
			}
//			operand1mverror = false;
			return false;                                        //Return false, if there is error for error term 
		}
		operand1mverror = false;
		errorTerm1Defined = true;                                //Set operand1Defined boolean to true    
		operand1Defined = true;						
		return true;										     // signal that the set worked
	}

	
	/**********
	 * This public setter takes an input String, checks to see if there is a non-empty input string.
	 * If so, it uses it to create a new CalculatorValue and places it into operand2, any associated
	 * error message is placed into operand2ErrorMessage, and sets the defined flag accordingly.
	 * 
	 * The logic of this method is the same as that for operand1 above.
	 * 
	 * @param mv measured value
	 * @param et error term
	 * @return	True if the set did not generate an error; False if there was invalid input
	 */
	public boolean setOperand2(String mv, String et, String unit) {			
		
		// The logic of this method is exactly the
		// same as that for operand1, above.
		
		operand2Defined = false;							
		errorTerm2Defined = false;
		operand2mverror = false;
		if (mv.length() <= 0 && et.length() <= 0) {
			operand2ErrorMessage = "";
			return true;
		}

		operand2 = new CalculatorValue(mv,et, unit);
		operand2ErrorMessage = operand2.getErrorMessage();
		operand2ETErrorMessage = operand2.getETErrorMessage();
		if (operand2ErrorMessage.length() > 0) {
			operand2mverror = true;
			return false;               //return false, if there is error in measured value term
		}
		if(operand2ETErrorMessage.length() > 0) {
			return false;              //return false, if there is error in error term
		}
		errorTerm2Defined = true;
		operand2Defined = true;
		return true;
	}
	
	
	/**********
	 * This public setter takes an input String, checks to see if there is a non-empty input string.
	 * If so, it uses it to create a new CalculatorValue and places it into result and any 
	 * associated error message is placed into resultErrorMessage.
	 * 
	 * The logic of this method is similar to that for operand1 above. (There is no defined flag.)
	 * 
	 * @param mv Measured Value
	 * @param et Error Term
	 * @return	True if the set did not generate an error; False if there was invalid input
	 */
	public boolean setResult(String mv, String et, String unit) {			
		
		// The logic of this method is similar to
		// that for operand1, above.
		
		if (mv.length() <= 0) {							
			operand2ErrorMessage = "";
			return true;
		}
		result = new CalculatorValue(mv,et, unit);
		resultErrorMessage = operand2.getErrorMessage();
		if (operand2ErrorMessage.length() > 0)
			return false;
		return true;
	}
	
	/**********
	 * This method is called when we need to set a default error term value based on
	 * the measured value term. This is called when one of the error term is defined
	 * and the other error term is not defined when we are performing operations
	 * @param mv This method takes measured value string as input
	 * @return String returns error term string after certain operations
	 */
	public String seterrorTerm(String mv) {
		if(mv.contains(".")) {                               //checks if the string contains '.'
			String[] mvArray = mv.split("\\.");              //If no, append '.0' to the string
			if(mvArray.length == 1) {                        //If it is not a decimal number,
				return "0.5";                                //return 0.5
			}                                                  
			int length = mvArray[1].length();                //else
			String et = "";                                  //create a new string
			if(!mvArray[1].equals("0")) {                    //check for the number of digits after 
				for(int i = 0; i < length; i++) {            //decimal point and add those number of zeros
					et += "0";                               //to the string
				} 
			} 
			return "0." + et + "5";                          //append 0. and 5 character at the start and end of 
			                                                 //string and return the string
		} 
		return "0.5";
	}
	
	/**********
	 * This public setter sets the String explaining the current error in operand1.
	 * 
	 * @param   m
	 * return	This method returns nothing, but the operand1ErrorMessage has been set
	 */
	public void setOperand1ErrorMessage(String m) {
		operand1ErrorMessage = m;
		return;
	}
	
	/**********
	 * This public setter sets the boolean explaining the current state of operand1
	 * checkbox, if it is selected or not selected
	 * 
	 * @param  temp
	 * return	This method returns nothing, but the checkboxFlag1 variable is set
	 */
	public void setCheckBoxFlag1(boolean temp) {
		checkBoxFlag1 = temp;
	}
	
	/**********
	 * This public setter sets the boolean explaining the current state of operand2
	 * checkbox, if it is selected or not selected
	 * 
	 * @param  temp
	 * return	This method returns nothing, but the checkboxFlag2 variable is set
	 */
	public void setCheckBoxFlag2(boolean temp) {
		checkBoxFlag2 = temp;
	}
	
	/**********
	 * This public setter sets the String explaining the current error in operand1 Error Term.
	 * 
	 * @param   m
	 * return	This method returns nothing, but the operand1ETErrorMessage has been set
	 */
	public void setOperand1ETErrorMessage(String m) {
		operand1ETErrorMessage = m;
		return;
	}
	
	/**********
	 * This public getter fetches the String explaining the current error in operand1, it there is one,
	 * otherwise, the method returns an empty String.
	 * 
	 * return an error message or an empty String if there was no error
	 * @return error message
	 */
	public String getOperand1ErrorMessage() {
		return operand1ErrorMessage;
	}
	
	/**********
	 * This public getter fetches the String explaining the current error in operand1Error Term, it there is one,
	 * otherwise, the method returns an empty String.
	 * 
	 * return an error message or an empty String if there was no error
	 * @return error message
	 */
	public String getOperand1ETErrorMessage() {
		return operand1ETErrorMessage;
	}
	
	/**********
	 * This public getter fetches the boolean explaining the presence of error in operand1 Measuredvalue. If there
	 * is error, it returns true, else it will return false
	 * 
	 * returns true if there was no error
	 * @return state of measured value error
	 */
	public boolean getOperand1ErrorPresent() {
		return operand1mverror;
	}
	
	/**********
	 * This public getter fetches the boolean explaining the presence of error in operand2 Measuredvalue. If there
	 * is error, it returns true, else it will return false
	 * 
	 * returns true if there was no error
	 * @return state of measured value error
	 */
	public boolean getOperand2ErrorPresent() {
		return operand2mverror;
	}
	
	
	/**********
	 * This public getter fetches the String explaining the current error in operand2 Error Term, it there is one,
	 * otherwise, the method returns an empty String.
	 * 
	 * return an error message or an empty String if there was no error
	 * @return error message
	 */
	public String getOperand2ETErrorMessage() {
		return operand2ETErrorMessage;
	}
	
	/**********
	 * This public setter sets the String explaining the current error into operand2.
	 * 
	 * @param m value
	 * return	This method returns nothing, but the operand2ErrorMessage has been set
	 */
	public void setOperand2ErrorMessage(String m) {
		operand2ErrorMessage = m;
		return;
	}
	
	/**********
	 * This public getter fetches the String explaining the current error in operand2, it there is one,
	 * otherwise, the method returns an empty String.
	 * 
	 * @return error
	 * return an error message or an empty String if there was no error
	 */
	public String getOperand2ErrorMessage() {
		return operand2ErrorMessage;
	}
	
	/**********
	 * This public setter sets the operand1 error term to zero
	 * 
	 * return	This method returns nothing, but the operand1ErrorTerm has been set
	 */
	public void setOperand1ErrorTerm() {
		operand1.errorTerm = new UNumber();
	}
	/**********
	 * This public setter sets the operand2 error term to zero
	 * 
	 * return	This method returns nothing, but the operand2ErrorTerm has been set
	 */
	public void setOperand2ErrorTerm() {
		operand2.errorTerm = new UNumber();
	}
	
	/**********
	 * This public setter sets the String explaining the current error in the result.
	 * 
	 * @param m
	 * return	This method returns nothing, but the resultErrorMessage has been set
	 */
	public void setResultErrorMessage(String m) {
		resultErrorMessage = m;
		return;
	}
	
	/**********
	 * This public getter fetches the String explaining the current error in the result, it there is one,
	 * otherwise, the method returns an empty String.
	 * 
	 * @return and error message or an empty String if there was no error
	 */
	public String getResultErrorMessage() {
		return resultErrorMessage;
	}
	
	/**********
	 * This public getter fetches the defined attribute for operand1. You can't use the lack of an error 
	 * message to know that the operand is ready to be used. An empty operand has no error associated with 
	 * it, so the class checks to see if it is defined and has no error before setting this flag true.
	 * 
	 * @return true if the operand is defined and has no error, else false
	 */
	public boolean getOperand1Defined() {
		return operand1Defined;
	}
	
	/**********
	 * This public getter fetches the defined attribute for operand2. You can't use the lack of an error 
	 * message to know that the operand is ready to be used. An empty operand has no error associated with 
	 * it, so the class checks to see if it is defined and has no error before setting this flag true.
	 * 
	 * @return true if the operand is defined and has no error, else false
	 */
	public boolean getOperand2Defined() {
		return operand2Defined;
	}
	
	/**********
	 * This public getter fetches the defined attribute for operand1 Error Term. You can't use the lack of an error 
	 * message to know that the operand is ready to be used. An empty operand has no error associated with 
	 * it, so the class checks to see if it is defined and has no error before setting this flag true.
	 * 
	 * @return true if the operand is defined and has no error, else false
	 */
	public boolean getErrorterm1Defined() {
		return errorTerm1Defined;
	}
	
	/**********
	 * This public getter fetches the defined attribute for operand2 Error Term. You can't use the lack of an error 
	 * message to know that the operand is ready to be used. An empty operand has no error associated with 
	 * it, so the class checks to see if it is defined and has no error before setting this flag true.
	 * 
	 * @return true if the operand is defined and has no error, else false
	 */
	public boolean getErrorterm2Defined() {
		return errorTerm2Defined;
	}
	
	/**********
	 * This public getter fetches the String representation of the operand1. This method returns a string concatination of
	 * measuredvalue, error term
	 * 
	 * @return String representation of operand1
	 */
	public String getOperand1() {
		String mv = operand1.measuredValue.toDecimalString();
		String et = operand1.errorTerm.toDecimalString();
		return mv + " " + et;
	}
	
	/**********
	 * This public getter fetches the String representation of the operand2. This method returns a string concatination of
	 * measuredvalue, error term
	 * 
	 * @return String representation of operand2
	 */
	public String getOperand2() {
		String mv = operand2.measuredValue.toDecimalString();
		String et = operand2.errorTerm.toDecimalString();
		return mv + " " + et;
	}
	

	/**********************************************************************************************

	The toString() Method
	
	**********************************************************************************************/
	
	/**********
	 * This toString method invokes the toString method of the result type (CalculatorValue is this 
	 * case) to convert the value from its hidden internal representation into a String, which can be
	 * manipulated directly by the BusinessLogic and the UserInterface classes.
	 */
	public String toString() {
		return result.toString();
	}
	
	/**********
	 * This public toString method is used to display all the values of the BusinessLogic class in a
	 * textual representation for debugging purposes.
	 * 
	 * @return a String representation of the class
	 */
	public String debugToString() {
		String r = "\n******************\n*\n* Business Logic\n*\n******************\n";
		r += "operand1 = " + operand1.toString() + "\n";
		r += "     operand1ErrorMessage = " + operand1ErrorMessage+ "\n";
		r += "     operand1Defined = " + operand1Defined+ "\n";
		r += "operand2 = " + operand2.toString() + "\n";
		r += "     operand2ErrorMessage = " + operand2ErrorMessage+ "\n";
		r += "     operand2Defined = " + operand2Defined+ "\n";
		r += "result = " + result.toString() + "\n";
		r += "     resultErrorMessage = " + resultErrorMessage+ "\n";
		r += "*******************\n\n";
		return r;
	}

	/**********************************************************************************************

	Business Logic Operations (e.g. addition)
	
	**********************************************************************************************/
	
	/**********
	 * This public method computes the sum of the two operands using the CalculatorValue class method 
	 * for addition. The goal of this class is to support a wide array of different data representations 
	 * without requiring a change to this class, user interface class, or the Calculator class.
	 * 
	 * This method assumes the operands are defined and valid. It replaces the left operand with the 
	 * result of the computation and it leaves an error message, if there is one, in a String variable
	 * set aside for that purpose.
	 * 
	 * This method does not take advantage or know any detail of the representation!  All of that is
	 * hidden from this class by the ClaculatorValue class and any other classes that it may use.
	 * 
	 * @return a String representation of the result
	 * @throws Exception 
	 */
	public String addition() throws Exception {
		//Initialize and declare objects for result, operand1, operand2
		//Arguments for result are same as operand1
		//Also declare unit class object for checking units
		result = new CalculatorValue(operand1.measuredValue.toDecimalString(), operand1.errorTerm.toDecimalString(), operand1.unit);
		CalculatorValue temp1 = new CalculatorValue(operand1.measuredValue.toDecimalString(), operand1.errorTerm.toDecimalString(), operand1.unit);
		CalculatorValue temp2 = new CalculatorValue(operand2.measuredValue.toDecimalString(), operand2.errorTerm.toDecimalString(), operand2.unit);
		unitObj = new unit(operand1.unit, operand2.unit, "add");
		String[] resTemp = unitObj.start().split(" ");                 //split the output of the units class operation
		System.out.println(resTemp[0] + "bypass");
		if(resTemp[0].equals("Incompatible-units")) {                  //If units are incompatible
			resultErrorMessage = "Incompatible-units";                 //change the error
			return "";                                                 //return empty string
		}
		if(resTemp.length == 1) {                                      //If there is only unit in the ouput
			result.unit = resTemp[0];
		} else {
			String mfTemp = unitObj.getMF();                           //else store the multiplication factor in a
			if(!(mfTemp.contains("."))) {                              //string and append '.0' if not present
				mfTemp = mfTemp +".0";
			}
			String[] MF = mfTemp.split("\\.");                         //split using '.'
			String resUnit = unitObj.getResUnit();                     //store the result unit in a string   
			UNumber temp;
			temp = new UNumber(mfTemp, MF[0].length(), true);          //create a temp unumber object for multiplication
			if(resUnit.equals(operand1.unit)) {                        //factor and now check which operand doesn't match 
				operand2.measuredValue.mpy(temp);                      //with resUnit and multiply that value with 
			} else {                                                   //multiplication factor

				operand1.measuredValue.mpy(temp);
			}
			System.out.println(resUnit);
			result.unit = resUnit;
			result.measuredValue = operand1.measuredValue;
		}
			result.add(operand2);                                     //Perform addition operation   
			operand1 = temp1;
			operand2 = temp2;
			return result.toString();
	}
	
	/**********
	 * The following methods are method stubs that need to be implemented.
	 * This public method computes the subtraction of the two operands using the CalculatorValue class method 
	 * for subtraction. The goal of this class is to support a wide array of different data representations 
	 * without requiring a change to this class, user interface class, or the Calculator class.
	 * 
	 * This method assumes the operands are defined and valid. It replaces the left operand with the 
	 * result of the computation and it leaves an error message, if there is one, in a String variable
	 * set aside for that purpose.
	 * 
	 * This method does not take advantage or know any detail of the representation!  All of that is
	 * hidden from this class by the ClaculatorValue class and any other classes that it may use.
	 * 
	 * @return a String representation of the result
	 */
	public String subtraction() throws Exception{
		
		//Initialize and declare objects for result, operand1, operand2
		//Arguments for result are same as operand1
		//Also declare unit class object for checking units
		
		result = new CalculatorValue(operand1.measuredValue.toDecimalString(), operand1.errorTerm.toDecimalString(), operand1.unit);
		CalculatorValue temp1 = new CalculatorValue(operand1.measuredValue.toDecimalString(), operand1.errorTerm.toDecimalString(), operand1.unit);
		CalculatorValue temp2 = new CalculatorValue(operand2.measuredValue.toDecimalString(), operand2.errorTerm.toDecimalString(), operand2.unit);
		unitObj = new unit(operand1.unit, operand2.unit, "sub");
		
		String[] resTemp = unitObj.start().split(" ");                        //split the output of the units class operation
		if(resTemp[0].equals("Incompatible-units")) {                         //If units are incompatible                        
			resultErrorMessage = "Incompatible-units";                        //change the error 
			return "";                                                        //return empty string
		}
		
		//Logic is same as addition
		
		if(resTemp.length == 1) {
			result.unit = resTemp[0];
		} else {
			String mfTemp = unitObj.getMF();
			if(!(mfTemp.contains("."))) {
				mfTemp = mfTemp +".0";
			}
			String[] MF = mfTemp.split("\\.");
			String resUnit = unitObj.getResUnit();
			UNumber temp;
			temp = new UNumber(mfTemp, MF[0].length(), true);
			if(resUnit.equals(operand1.unit)) {
				operand2.measuredValue.mpy(temp);
			} else {

				operand1.measuredValue.mpy(temp);
			}
			result.unit = resUnit;
			result.measuredValue = operand1.measuredValue;
		}
		result.sub(operand2);                                                 //Perform Subtraction
		operand1 = temp1;
		operand2 = temp2;
		return result.toString();
	}
	/**********
	 * This public method computes the multiplication of the two operands using the CalculatorValue class method 
	 * for multiplication. The goal of this class is to support a wide array of different data representations 
	 * without requiring a change to this class, user interface class, or the Calculator class.
	 * 
	 * This method assumes the operands are defined and valid. It replaces the left operand with the 
	 * result of the computation and it leaves an error message, if there is one, in a String variable
	 * set aside for that purpose.
	 * 
	 * This method does not take advantage or know any detail of the representation!  All of that is
	 * hidden from this class by the ClaculatorValue class and any other classes that it may use.
	 * 
	 * @return a String representation of the result
	 * @throws Exception 
	 */
	public String multiplication() throws Exception {
		
		//Initialize and declare objects for result, operand1, operand2
		//Arguments for result are same as operand1
		//Also declare unit class object for checking units
		
		result = new CalculatorValue(operand1.measuredValue.toDecimalString(), operand1.errorTerm.toDecimalString(), operand1.unit);
		CalculatorValue temp1 = new CalculatorValue(operand1.measuredValue.toDecimalString(), operand1.errorTerm.toDecimalString(), operand1.unit);
		CalculatorValue temp2 = new CalculatorValue(operand2.measuredValue.toDecimalString(), operand2.errorTerm.toDecimalString(), operand2.unit);
		
		unitObj = new unit(operand1.unit, operand2.unit, "multi");      //create a unit class object
		String[] resTemp = unitObj.start().split(" ");                  //split the output with space
		String mfTemp = unitObj.getMF();                                //store both multiplication factor
	    String resUnit = unitObj.getResUnit();                          //and result unit into different
	    
		if(resTemp[0].equals("Incompatible-units")) {                   //check for incompatibility
			resultErrorMessage = "Incompatible-units";
			return "";
		}
		
		//Check if any of the operands are zero, if so, return both terms as zero
		
		if(operand1.measuredValue.toDecimalString().equals("0.") || (operand2.measuredValue.toDecimalString().equals("0."))) {
			return "0.0 0.0 "+ resUnit;
		} 
			
		if(!(mfTemp.contains("."))) {                                   //variables and append '.0' for 
			mfTemp = mfTemp +".0";                                      //multi factor if not present
	    }
		String[] MF = mfTemp.split("\\.");                              //split using '.'
		UNumber temp;
		if(MF.length == 2) {                                            //check for presence of error term
			temp = new UNumber(mfTemp, MF[0].length(), true);           //after splitting and create UNumber
		}                                                               //accordingly
		else
			temp = new UNumber(mfTemp, MF[0].length(), true);
		result.mpy(operand2);                                          //perform multiplication operation
		result.measuredValue.mpy(temp);                                //multiply multi factor to result value
		result.unit = resUnit;
		if(temp1.unit.equals(temp2.unit)) {                            //check the two units if same or not
			result.unit += "^2";
		}
		if(temp1.unit.equals(temp2.unit) && temp1.unit.equals("No-unit-selected")) {       //if no unit is selected
			result.unit = "No-unit-selected";
		} 
		operand1 = temp1;
	    operand2 = temp2;
		return result.toString();
	}
	/**********
	 * This public method computes the division of the two operands using the CalculatorValue class method 
	 * for division. The goal of this class is to support a wide array of different data representations 
	 * without requiring a change to this class, user interface class, or the Calculator class.
	 * 
	 * This method assumes the operands are defined and valid. It replaces the left operand with the 
	 * result of the computation and it leaves an error message, if there is one, in a String variable
	 * set aside for that purpose.
	 * 
	 * This method does not take advantage or know any detail of the representation!  All of that is
	 * hidden from this class by the ClaculatorValue class and any other classes that it may use.
	 * 
	 * @return a String representation of the result
	 */
	public String division() throws Exception {
		
		//Initialize and declare objects for result, operand1, operand2
		//Arguments for result are same as operand1
		//Also declare unit class object for checking units
				
		result = new CalculatorValue(operand1.measuredValue.toDecimalString(), operand1.errorTerm.toDecimalString(), operand1.unit);
		CalculatorValue temp1 = new CalculatorValue(operand1.measuredValue.toDecimalString(), operand1.errorTerm.toDecimalString(), operand1.unit);
		CalculatorValue temp2 = new CalculatorValue(operand2.measuredValue.toDecimalString(), operand2.errorTerm.toDecimalString(), operand2.unit);
		
		unitObj = new unit(operand1.unit, operand2.unit, "div");      //create a unit class object
		String[] resTemp = unitObj.start().split(" ");                //split using space
		String mfTemp = unitObj.getMF();                              //Store them in variables
	    String resUnit = unitObj.getResUnit();
	    if(resTemp[0].equals("Incompatible-units")) {                 //Check for incompatibility
			resultErrorMessage = "Incompatible-units";
			return "";
		}
	    
	    //Check if first operand is zero or not, if so, return both result values as zero
		if(operand1.measuredValue.toDecimalString().equals("0.")) {
			return "0.0 0.0 " + resUnit;
		}
		//If second operand is zero and first operand is not zero, return division by zero error
		if (operand2.measuredValue.toDecimalString().equals("0.")) {
			resultErrorMessage = "Division by Zero Error";
			return "";
		}
	    if(!(mfTemp.contains("."))) {        //check for '.', if not present, append '.0' to the value
			mfTemp = mfTemp +".0";
		}
		String[] MF = mfTemp.split("\\.");    //split using '.'
		UNumber temp;
		if(MF.length == 2) {                  //Declare UNumber variable according to the presence of error term
			temp = new UNumber(mfTemp, MF[0].length(), true);
		}
		else
			temp = new UNumber(mfTemp, MF[0].length(), true);
		result.unit = resUnit;
		
		if(temp1.unit.equals("No-unit-selected") && temp2.unit.equals("No-unit-selected")) {        //If both units are not selected, return 
			result.unit = "No-unit-selected";                                                       //Unit not selected
		} 
		
		if(temp1.unit.equals(temp2.unit) && !(temp1.unit.equals("No-unit-selected") && temp2.unit.equals("No-unit-selected"))) {        //If both units are same 
			result.unit = "no-unit";                                         //return no unit
		} 
		result.div(operand2);                           //Perform division
		result.measuredValue.mpy(temp);                 //multiply multiplication factor
		operand1 = temp1;
		operand2 = temp2;
		return result.toString();                       //return result string
	}

	
	/**********
	 * This public method computes the square root of the operand using the CalculatorValue class method 
	 * for square root. The goal of this class is to support a wide array of different data representations 
	 * without requiring a change to this class, user interface class, or the Calculator class.
	 * 
	 * This method assumes the operands are defined and valid. It replaces the left operand with the 
	 * result of the computation and it leaves an error message, if there is one, in a String variable
	 * set aside for that purpose.
	 * 
	 * This method does not take advantage or know any detail of the representation!  All of that is
	 * hidden from this class by the ClaculatorValue class and any other classes that it may use.
	 * 
	 * @return a String representation of the result
	 */
	public String squareroot() {
		//declare result object and Unumber zero
		result = new CalculatorValue(operand1.measuredValue.toDecimalString(), operand1.errorTerm.toDecimalString(), operand1.unit);
		UNumber zero = new UNumber(0);
		if(operand1.measuredValue.lessThan(zero)) {  //check if the operand value is negative 
			resultErrorMessage  = "Square root of negative number impossible"; //if so print error message
			return "";
		}
		else {
			String[] arr;
			if(operand1.errorTerm.toString().equals("+0.E+0")) {  //If the error term is zero
				String temp = seterrorTerm(new BigDecimal(operand1.measuredValue.toString()).toPlainString()); //set the error term to default value
				BigDecimal bg = new BigDecimal(temp);
				temp = bg.toPlainString();
				if(temp.contains(".")) {     //Check for presence of '.'
					arr= temp.split("\\.");
					UNumber tem = new UNumber(arr[0]+arr[1],1, true);
					result.errorTerm = tem;
				}
			}
			
		result.sqrt();        //Perform Square root operation
		resultErrorMessage = result.getErrorMessage();
		if(checkBoxFlag1) {   //Check for the selection of checkbox, if yes, don't round the value
			return result.measuredValue + " "  + result.unit; 
		}
		return result.toString();     //return result string
		}
	}
	
}
