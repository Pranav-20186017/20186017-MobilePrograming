package calculator;


import java.io.File;
import java.io.FileReader;

import org.json.simple.JSONArray;

//import java.io.FileReader;
//import java.util.Iterator;
//import java.util.Map;

//import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.util.Arrays;
import java.util.Map;

/**
 * <p> Title: unit class </p>
 * 
 * <p> Description: To check for the compatibility of units by using json parser</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2018 </p>
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
 * 
 */
public class unit
{
	//These are the main varaibles that are necessary
	String unit1 = "";
	String unit2 = "";
	String resultUnit = "";
	String type = "";
	String multiplicationFactor = "";
	/*****
	 * This is the unit class constructor
	 * @param u1 unit of operand 1
	 * @param u2 unit of operand 2
	 * @param operation to denote type of operation
	 */
	unit(String u1, String u2, String operation) throws Exception {
		this.unit1 = u1;
		this.unit2 = u2;
		this.type = operation;
		this.resultUnit = "";
		start();
	}
	/*****
	 * This method returns Multiplication factor
	 * @return String
	 */
	public String getMF() {
		return this.multiplicationFactor;
	}
	/*****
	 * This method returns Resultant Unit
	 * @return String
	 */
	public String getResUnit() {
		return resultUnit;
	}
	/*****
	 * This method starts the unit compatibility checking process
	 * It is invoked when the constructor is called
	 * @return String
	 */
	public String start() throws Exception
    {
    	if(this.unit1.equals(this.unit2)) {     //Check if the two units are same or not
    		this.resultUnit = this.unit1;       //If so, set the result unit to unit 1
    		this.multiplicationFactor = "1.0";  //Multiplication factor to 1.0
    		return resultUnit + " " + this.multiplicationFactor;  //return that string
    	}
    	Object obj;                 //Else initialize object of Object type
    	if(this.type.equals("add") || this.type.equals("sub")) {   //Read and parse json files based on operation
    		File file = new File("units.json");
    		String parent = file.getAbsoluteFile().getParent();
    		obj = new JSONParser().parse(new FileReader(parent + "/src/calculator/units.json"));
    	} else if(type.equals("multi")){
    		File file = new File("multiunitsdata.json");
    		String parent = file.getAbsoluteFile().getParent();
    		obj = new JSONParser().parse(new FileReader(parent + "/src/calculator/multiunitsdata.json"));
    	} else {
    		File file = new File("divisionunits.json");
    		String parent = file.getAbsoluteFile().getParent();
    		obj = new JSONParser().parse(new FileReader(parent + "/src/calculator/divisionunits.json"));
    	}

   
        JSONObject jo = (JSONObject) obj;     //Type to json object

		JSONObject first = (JSONObject) jo.get(this.unit1); //First unit value json object
		JSONArray arr = (JSONArray) first.get(this.unit2);  //Second unit value array after checking in the first unit
        if(arr != null) {      //If there is array, return the values
        this.multiplicationFactor = (String) arr.get(0);
        this.resultUnit = (String)  arr.get(1);
        return resultUnit + " " + this.multiplicationFactor;
        } else {                    //else return incompatible units string                 
        	return "Incompatible-units "; 
        }
    }
}
