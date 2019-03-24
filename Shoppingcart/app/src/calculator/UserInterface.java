package calculator;
//import java.util.ArrayList;
import java.util.*;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import calculator.BusinessLogic;
import javafx.scene.control.CheckBox;
//import javafx.scene.control.skin.ComboBoxListViewSkin;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;

/**
 * <p> Title: UserInterface Class. </p>
 * 
 * <p> Description: The Java/FX-based user interface for the calculator. The class works with 
 * String objects and passes work to other classes to deal with all other aspects of the 
 * computation.</p>
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

public class UserInterface {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	/* Constants used to parameterize the graphical user interface.  We do not use a layout manager
	   for this application. Rather we manually control the location of each graphical element for
	   exact control of the look and feel. */
	
	// These set the width and positioning of buttons
	private final double BUTTON_WIDTH = 60;
	private final double BUTTON_OFFSET = BUTTON_WIDTH / 2;

	// These are the application values required by the user interface
	private Label label_IntegerCalculator = new Label("UNumber Calculator");
	
	//Label and Text fields for Operand1
	private Label label_Operand1 = new Label("First operand");
	private TextField text_Operand1 = new TextField();			// No initial value
	private Label label_ErrOperand1 = new Label("First Error term");
	private TextField text_Operand1ErrorTerm = new TextField();  //No initial value
	private Label label_plusminus_operand1 = new Label("\u00B1");
	private boolean checkBoxFlag1 = false;
	
	//Combobox to select units for the first operand 1
	private Label labelSelectAUnit1 = new Label("Select a unit:");
    ComboBox <String> comboboxSelectUnit1 = new ComboBox <String>(); //Combobox for 1st operand
	
    
	//Label and Text fields for Operand2
	private Label label_Operand2 = new Label("Second operand");
	private TextField text_Operand2 = new TextField();			// No initial value
	private Label label_ErrOperand2 = new Label("Second Error term");
	private TextField text_Operand2ErrorTerm = new TextField();
	private Label label_plusminus_operand2 = new Label("\u00B1");
	private Label label_plusminus_result = new Label("\u00B1");
	private boolean checkBoxFlag2 = false;
	
	//Combobox to select units for the first operand 2
	private Label labelSelectAUnit2 = new Label("Select a unit:");
	ComboBox <String> comboboxSelectUnit2 = new ComboBox <String>(); //Combobox for 2nd operand
	
	
	//Label and Text fields for Result
	private Label label_Result = new Label("Result");
	private TextField text_Result = new TextField();			// No initial value
	private Label label_ErrResult = new Label("Result Error term");
	private TextField text_ResultErrorTerm = new TextField();   //No initial value
	private Label labelResultUnit = new Label("Resultant unit");
	private TextField text_ResultUnit = new TextField();
	
	//Label for the disable precision checkbox
	private Label label_disable_error = new Label("Disable Precision");
	
	
	
	// These are the buttons that perform the calculator operations when pressed
	private Button button_Add = new Button("+");
	private Button button_Sub = new Button("-");
	private Button button_Mpy = new Button("\u00D7");				
	private Button button_Div = new Button("\u00F7");				
	private Button button_sqrt = new Button("\u221A");
	// If the multiplication and/or division symbols do not display properly, replace the 
	// quoted strings used in the new Button constructor call with the <backslash>u00xx values
	// shown on the same line. This is the Unicode representation of those characters and will
	// work regardless of the underlying hardware being used. (e.g. new Button("\u00d7"); and 
	// new Button("\u00F78"); )
	
	// These are the labels that are used to display error messages. Since they are empty, nothing
	// shows on the user interface.
	private Label label_errOperand1 = new Label("");   //For Operand1 Measured Value Error
	private Label label_errETOperand1 = new Label(""); //For Operand1 Error Term Error
	private TextFlow errMeasuredValue1;   //For Operand1 Measured Value Text field
	private TextFlow errErrorTerm;        //For Operand1 Error Term value Text field
	
	
	//For Text flow below the Operand1 fields
	private Text errMVPart1 = new Text();  //For displaying correct part of the code in black color
	private Text errMVPart2 = new Text();  //For displaying arrow pointer in red color
	
	//For Text flow below the Operand1 Error fields
    private Text errETPart1 = new Text();  //For displaying correct part of the code in black color
    private Text errETPart2 = new Text();  //For displaying arrow pointer in red color
	
	private Label label_errOperand2 = new Label("");  //For Operand2 Measured Value Error
	private Label label_errETOperand2 = new Label(""); //For Operand2 Error Term Error
	private TextFlow errMeasuredValue2; //For Operand2 Measured Value Text field
	 private TextFlow errErrorTerm2;    //For Operand2 Error Term Value Text field
	
	
	//For Text flow below the Operand2 fields
	private Text errMV2Part1 = new Text(); //For displaying correct part of the code in black color 
    private Text errMV2Part2 = new Text(); //For displaying arrow pointer in red color
    
    //For Text flow below the Operand2 Error Term fields
    private Text errET2Part1 = new Text(); //For displaying correct part of the code in black color
    private Text errET2Part2 = new Text(); //For displaying arrow pointer in red color
    
    
	private Label label_errResult = new Label("");  //For Result Value Error
	
    //Array for storing all units that are required for the calculator computations
    String [] units = {"No-unit-selected", 
    		"km","km^2","km^3", "m", "ft",
    		"gms", "kg", "pounds",
    		 "hr","min","sec","sec^2",
    		 "m/sec", "km/hr",
    		 "Newton", "dyne",
    		 "m/sec^2", "km/hr^2", "km^3/sec^2"
    		};
    
	private double buttonSpace;		// This is the white space between the operator buttons.
	
	/* This is the link to the business logic */
	public BusinessLogic perform = new BusinessLogic();
	 boolean UserInterfaceIsReady = false;
	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	/**********
	 * This constructor initializes all of the elements of the graphical user interface. These
	 * assignments determine the location, size, font, color, and change and event handlers for
	 * each GUI object.
	 * 
	 * @param theRoot root param
	 */
	public UserInterface(Pane theRoot) {
		
				
		// There are five gaps. Compute the button space accordingly.
		buttonSpace = Calculator.WINDOW_WIDTH / 6;
		
		// Label theScene with the name of the calculator, centered at the top of the pane
		setupLabelUI(label_IntegerCalculator, "Arial", 24, Calculator.WINDOW_WIDTH, Pos.CENTER, 0, 10);
		
		// Label the first operand just above it, left aligned
		setupLabelUI(label_Operand1, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 35, 60);
		
		// Label the first operand just above it, left aligned
		setupLabelUI(label_ErrOperand1, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 680, 60);
		
		// Establish the first text input operand field and when anything changes in operand 1,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1, "Arial", 18, Calculator.WINDOW_WIDTH/2 - 50 , Pos.BASELINE_LEFT, 35, 90, true);
		text_Operand1.textProperty().addListener((observable, oldValue, newValue) -> { setOperand1(); });
		
		//For Checkboxes to enable or disable the error term text fields
		CheckBox errorcheckboxOP1 = new CheckBox("Disable Precision");
		CheckBox errorcheckboxOP2 = new CheckBox("Disable Precision");
		
		//To adjust the fonts of the checkboxes
		//For checkbox 1
		errorcheckboxOP1.setFont(Font.font("Arial", FontPosture.REGULAR, 12));
		
		//For checkbox 2
		errorcheckboxOP2.setFont(Font.font("Arial", FontPosture.REGULAR, 12));
				
		//+/- symbol in the user interface between two text boxes. For Operand1
		label_plusminus_operand1.setFont(Font.font("Arial", FontPosture.REGULAR, 20));
		label_plusminus_operand1.setLayoutX(Calculator.WINDOW_WIDTH/2);
		label_plusminus_operand1.setLayoutY(90);
		
		// Establish the Error Term textfield for the first operand.  If anything changes, process
		// all fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1ErrorTerm, "Arial", 18, 300, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/2 + 19, 90, true);
		text_Operand1ErrorTerm.textProperty().addListener((observable, oldValue, newValue) -> {setOperand1(); });
				
		// Move focus to the first operand error term when the user presses the enter (return) key
		text_Operand1.setOnAction((event) -> { text_Operand1ErrorTerm.requestFocus(); });
		
		// Move the focus to the Combo box 1 term when the user presses the enter (return) key
		text_Operand1ErrorTerm.setOnAction((event) -> { comboboxSelectUnit1.requestFocus(); });
		
		// Move the focus to the Operand 2 term when the user presses the enter (return) key
		comboboxSelectUnit1.setOnAction((event) -> { text_Operand2.requestFocus(); });
		
				
		// Establish an error message for the first operand just above it with, left aligned
		setupLabelUI(label_errOperand1, "Arial", 18, Calculator.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT,35, 145);
		label_errOperand1.setTextFill(Color.RED);
		
		// Establish an error message for the first operand just above it with, left aligned
		setupLabelUI(label_errETOperand1, "Arial", 18, Calculator.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT,680, 145);
		label_errETOperand1.setTextFill(Color.RED);
				
		// Label the second operand just above it, left aligned
		setupLabelUI(label_Operand2, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 35, 170);
		
		// Label the error term second operand just above it, left aligned
		setupLabelUI(label_ErrOperand2, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 680, 170);
		
		// Establish the second text input operand field and when anything changes in operand 2,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand2, "Arial", 18, Calculator.WINDOW_WIDTH/2 - 50, Pos.BASELINE_LEFT, 35, 200, true);
		text_Operand2.textProperty().addListener((observable, oldValue, newValue) -> {setOperand2(); });
		
		// Move the focus to the Operand2 Error term when the user presses the enter (return) key
		text_Operand2.setOnAction((event) -> { text_Operand2ErrorTerm.requestFocus(); });
		
		//+/- symbol in the user interface between two text boxes.
		label_plusminus_operand2.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
		label_plusminus_operand2.setLayoutX(Calculator.WINDOW_WIDTH/2);
		label_plusminus_operand2.setLayoutY(200);
		
		// Establish the Error Term textfield for the second operand.  If anything changes, process
		// all fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand2ErrorTerm, "Arial", 18, 300, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/2 + 19, 200, true);
		text_Operand2ErrorTerm.textProperty().addListener((observable, oldValue, newValue) -> {setOperand2(); });
		
		// Move the focus to the Combo box 2 when the user presses the enter (return) key
		text_Operand2ErrorTerm.setOnAction((event) -> { comboboxSelectUnit2.requestFocus(); });
		
		// Move the focus to the Result term when the user presses the enter (return) key
		comboboxSelectUnit2.setOnAction((event) -> { text_Result.requestFocus(); });
		
		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_errOperand2, "Arial", 18, Calculator.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT, 46, 260);
		label_errOperand2.setTextFill(Color.RED);
		
		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_errETOperand2, "Arial", 18, Calculator.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT, 680, 260);
		label_errETOperand2.setTextFill(Color.RED);
		
		// Label the result just above the result output field, left aligned
		setupLabelUI(label_Result, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 35, 290);
		
		setupLabelUI(label_ErrResult, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 680, 290);
		
		// Establish the result output field.  It is not editable, so the text can be selected and copied, 
		// but it cannot be altered by the user.  The text is left aligned.
		setupTextUI(text_Result, "Arial", 18, Calculator.WINDOW_WIDTH/2 - 50 , Pos.BASELINE_LEFT, 35, 320, false);
		
		// Move the focus to the Result Error term when the user presses the enter (return) key
		text_Result.setOnAction((event) -> { text_ResultErrorTerm.requestFocus(); });
		
		// Move the focus to the Result unit term when the user presses the enter (return) key
		text_ResultErrorTerm.setOnAction((event) -> { text_ResultUnit.requestFocus(); });
		
		//+/- symbol in the user interface between two text boxes.
		label_plusminus_result.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
		label_plusminus_result.setLayoutX(Calculator.WINDOW_WIDTH/2);
		label_plusminus_result.setLayoutY(320);
		
		setupTextUI(text_ResultErrorTerm, "Arial", 18, 300, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/2 + 19, 320, false);
		
		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_errResult, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 300, 290);
		label_errResult.setTextFill(Color.RED);
		
		// Establish the ADD "+" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Add, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 1 * buttonSpace-BUTTON_OFFSET, 400);
		button_Add.setOnAction((event) -> { try {
			addOperands();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} });
		
		// Establish the SUB "-" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sub, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 2 * buttonSpace-BUTTON_OFFSET, 400);
		button_Sub.setOnAction((event) -> { try {
			subOperands();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} });
		
		
		//For event handling of checkbox Error Term 1		
		errorcheckboxOP1.setOnAction((event) -> {
			if(errorcheckboxOP1.isSelected() ) {
				System.out.println("Entered");
				checkBoxFlag1 = true;
				text_Operand1ErrorTerm.setDisable(true);
				text_Operand1ErrorTerm.setText("");
			} else {
				text_Operand1ErrorTerm.setDisable(false);
				checkBoxFlag1 = false;
			}
			perform.setCheckBoxFlag1(checkBoxFlag1);
	    });	
				
		//For event handling of checkbox Error Term 2		
		errorcheckboxOP2.setOnAction((event) -> {
			if(errorcheckboxOP2.isSelected()) {
				checkBoxFlag2 = true;
				text_Operand2ErrorTerm.setDisable(true);
				text_Operand2ErrorTerm.setText("");
			} else {
				checkBoxFlag2 = false;
				text_Operand2ErrorTerm.setDisable(false);
			}
			perform.setCheckBoxFlag2(checkBoxFlag2);
		});		
		
		
		// Establish the MPY "*" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Mpy, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 3 * buttonSpace-BUTTON_OFFSET, 400);
		button_Mpy.setOnAction((event) -> { try {
			mpyOperands();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} });
		
		// Establish the DIV "/" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Div, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 4 * buttonSpace-BUTTON_OFFSET, 400);
		button_Div.setOnAction((event) -> { try {
			divOperands();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} });
		
		// Establish the sqrt button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_sqrt, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 5 * buttonSpace-BUTTON_OFFSET, 400);
		button_sqrt.setOnAction((event) -> { sqrtOperands(); });
		
		// Error Message for the Measured Value for operand 1
		errMVPart1.setFill(Color.BLACK);
	    errMVPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errMVPart2.setFill(Color.RED);
	    errMVPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errMeasuredValue1 = new TextFlow(errMVPart1, errMVPart2);
		errMeasuredValue1.setMinWidth(Calculator.WINDOW_WIDTH-10); 
		errMeasuredValue1.setLayoutX(45);  
		errMeasuredValue1.setLayoutY(120);
		
		
		// Error Message for the Measured Value for operand 2
		errMV2Part1.setFill(Color.BLACK);
	    errMV2Part1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errMV2Part2.setFill(Color.RED);
	    errMV2Part2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errMeasuredValue2 = new TextFlow(errMV2Part1, errMV2Part2);
		errMeasuredValue2.setMinWidth(Calculator.WINDOW_WIDTH-10); 
		errMeasuredValue2.setLayoutX(45);  
		errMeasuredValue2.setLayoutY(230);
		
		// Error Message for the Error Term for operand 1
	    errETPart1.setFill(Color.BLACK);
	    errETPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errETPart2.setFill(Color.RED);
	    errETPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errErrorTerm = new TextFlow(errETPart1, errETPart2);
		// Establish an error message for the second operand just above it with, left aligned
		errErrorTerm.setMinWidth(Calculator.WINDOW_WIDTH-10); 
		errErrorTerm.setLayoutX(692);  
		errErrorTerm.setLayoutY(120);
		
		// Error Message for the Error Term for operand 1
	    errET2Part1.setFill(Color.BLACK);
	    errET2Part1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errET2Part2.setFill(Color.RED);
	    errET2Part2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errErrorTerm2 = new TextFlow(errET2Part1, errET2Part2);
		// Establish an error message for the second operand just above it with, left aligned
		errErrorTerm2.setMinWidth(Calculator.WINDOW_WIDTH-10); 
		errErrorTerm2.setLayoutX(690);  
		errErrorTerm2.setLayoutY(230);
		
		
		//For checkbox Error Term OP1
		errorcheckboxOP1.setLayoutX(160);  
		errorcheckboxOP1.setLayoutY(62);
				
		//For checkbox Error Term OP2
		errorcheckboxOP2.setLayoutX(180);  
		errorcheckboxOP2.setLayoutY(172);
		
		//For operand 1 Label and Combo Box fields 
		loadComboBox1(units, comboboxSelectUnit1);
		setupLabelUI(labelSelectAUnit1, "Arial", 18, 210, Pos.BASELINE_LEFT, 1000, 60);
        setupComboBoxUI(comboboxSelectUnit1, "Arial", 18, 220, 1000, 90); 
        comboboxSelectUnit1.getSelectionModel().selectedItemProperty()
        	.addListener( (ObservableValue<? extends String> observable, String oldvalue, String newValue) -> {
        		setOperand1();
        		if(this.checkBoxFlag1) {
        			return;
        		}
        	});
        
        //For operand 2 Label and Combobox box fields
        loadComboBox1(units, comboboxSelectUnit2);
        setupLabelUI(labelSelectAUnit2, "Arial", 18, 210, Pos.BASELINE_LEFT, 1000, 170);
        setupComboBoxUI(comboboxSelectUnit2, "Arial", 18, 220, 1000, 200); 
        comboboxSelectUnit2.getSelectionModel().selectedItemProperty()
        	.addListener( (ObservableValue<? extends String> observable, String oldvalue, String newValue) -> {
        		setOperand2();
        		if(this.checkBoxFlag2) {
        			return;
        		}
        	});
		
        //For resultant unit Labels and Text fields
        setupLabelUI(labelResultUnit, "Arial", 18, 210, Pos.BASELINE_LEFT, 1000, 290);
        setupTextUI(text_ResultUnit, "Arial", 18, 220 , Pos.BASELINE_LEFT, 1000, 320, false);
        
		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(label_IntegerCalculator, label_Operand1, text_Operand1,
				label_errOperand1, label_Operand2, text_Operand2, label_errOperand2, label_Result, text_Result, label_errResult,
				button_Add, button_Sub, button_Mpy, button_Div,errMeasuredValue1, errMeasuredValue2, button_sqrt, text_Operand1ErrorTerm,
				text_Operand2ErrorTerm,text_ResultErrorTerm, errErrorTerm, label_errETOperand1,errErrorTerm2,label_errETOperand2,label_ErrOperand1,
				label_ErrOperand2,label_ErrResult,label_plusminus_operand1,label_plusminus_operand2,label_plusminus_result, 
				labelSelectAUnit1, comboboxSelectUnit1, labelSelectAUnit2, comboboxSelectUnit2, labelResultUnit, text_ResultUnit, errorcheckboxOP1, errorcheckboxOP2);

	}
	
	/**
	 * The action item selection lists in the ComboBoxes are dynamic. They can
	 * be changed by the program.
	 * 
	 * The buildingComboBox flag is used to signal the rest of this screen that
	 * a ComboBox is in the process of being updated. Changes to the ComboBox
	 * whether brought about by the user or by code, results in change events. 
	 * We do not want change events that come from defining any ComboBox select
	 * list via code to generate update events so we can treat all of those as
	 * coming from the user.
	 * 
	 * This routine assumes that the order of the action items in the array is
	 * precisely the correct order for display in the ComboBox. Sorting must be
	 * done elsewhere.
	 * 
	 * @param names
	 *            String[] - This is the array of names for the select list
	 * 
	 */
	
	/**********
	 * Private local method to initialize the standard fields for a label
	 * 
	 * @param l		The Label object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the label
	 * @param p		The alignment (e.g. left, centered, or right)
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);		
	}
		
	/**********
	 * Private local method to initialize the standard fields for a text field
	 * 
	 * @param t		The TextField object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the text field
	 * @param p		The alignment (e.g. left, centered, or right)
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 * @param e		true if the field should be editable, else false
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);		
		t.setEditable(e);
	}
	
	/**********
	 * Private local method to initialize the standard fields for a button
	 * 
	 * @param b		The Button object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the Button
	 * @param p		The alignment (e.g. left, centered, or right)
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 */
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);		
	}
	
	/**********
	 * Private local method to initialize the standard fields for a ComboBox
	 * 
	 * @param c		The ComboBox object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the ComboBox
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 */
	private void setupComboBoxUI(ComboBox <String> c, String ff, double f, double w, double x, double y){
		c.setStyle("-fx-font: " + f + " " + ff + ";");
		c.setMinWidth(w);
		c.setLayoutX(x);
		c.setLayoutY(y);
	}
	/**********************************************************************************************

	User Interface Actions
	
	**********************************************************************************************/

	/**********
	 * Private local method used to set the value of the first operand given a text value. The 
	 * method uses the business logic class to perform the work of checking the string to see it is
	 * a valid value and if so, saving that value internally for future computations. If there is 
	 * an error when trying to convert the string into a value, the called business logic method 
	 * returns false and actions are taken to display the error message appropriately.
	 */
	private void setOperand1() {
		//If checkbox flag is true, return without doing nothing
		text_Result.setText("");							// Any change of an operand probably
		text_ResultErrorTerm.setText("");                   // invalidates the result, so we clear
		text_ResultUnit.setText("");                        // the old result and the error
		label_Result.setText("Result");						// message
		label_Result.setTextFill(Color.BLACK);				 
		label_errResult.setText("");
		errMVPart1.setText("");								
		errMVPart2.setText("");					
		errETPart1.setText("");
		errETPart2.setText("");
		if(text_Operand1.getText().equals("-")) {
			text_Result.setText("");
			label_Result.setText("Result");
			label_errOperand1.setText("Negitive numbers are not allowed for square root");
			return;
		}
		if (perform.setOperand1(text_Operand1.getText(), text_Operand1ErrorTerm.getText(), this.units[comboboxSelectUnit1.getSelectionModel().getSelectedIndex()])) {	
			if(text_Operand1ErrorTerm.getText().equals("") && checkBoxFlag1) {
				text_Operand1ErrorTerm.setDisable(true);
			} else {
				text_Operand1ErrorTerm.setDisable(false);       // Enable the error term as there is no error in Measured Value
				label_errOperand1.setText("");					// Set the operand and see if there was
				label_errETOperand1.setText("");                // an error. If no error, clear this
				if (text_Operand2.getText().length() == 0)	    // operands error. If the other operand 
					label_errOperand2.setText("");				// is empty, clear its error as well.
			}
			
		}
		else {		
			String[] errmsg = perform.getOperand1ErrorMessage().split("\u21EB");	//Split with the unicode arrow.
			String[] eterrmsg = perform.getOperand1ETErrorMessage().split("\u21EB");
			//If there is error present in the Measured Value,
			//Disable the error term so that user
			//will be able to enter the error field only
			//after entering right measured Value
			if(perform.getOperand1ErrorPresent()) {
				text_Operand1ErrorTerm.setText("");
				text_Operand1ErrorTerm.setDisable(true);      //Disable the Error term field
			} else {
				text_Operand1ErrorTerm.setDisable(false);
			}
			
			if(errmsg.length == 1) {                                                //Check if the array length is 1
				if(errmsg[0].equals("") || errmsg[0].equals("Input is empty")) {
					if(errmsg[0].equals("Input is empty")) {
						label_errOperand1.setText("Input is empty");
						text_Operand1ErrorTerm.setDisable(false);
					} else {
						label_errOperand1.setText("");
						text_Operand1ErrorTerm.setDisable(false);
					}
				} else {
					errMVPart1.setText("");                                                                    
					errMVPart2.setText("\u21EB");
					label_errOperand1.setText(perform.getOperand1ErrorMessage());		//If 1 then set the error message to string.
				}
				
			} else {																
				errMVPart1.setText(errmsg[0]);										//Assign part 1 part to errMVPart1
				errMVPart2.setText("\u21EB");										//Assign the arrow to errMVpart2
				label_errOperand1.setText(errmsg[1]);								//Assign the error message to label errOperand1
			}
			
			//Do the above step for error term as well
			if(eterrmsg.length == 1 ) {												//Check if the array length is 1
				if(eterrmsg[0].equals("")) {
					label_errETOperand1.setText("");	
				} else {
					errETPart1.setText("");                                                                    
					errETPart2.setText("\u21EB");
					label_errETOperand1.setText(perform.getOperand1ETErrorMessage());	//If 1 then set the error message to string.
				}
				
			} else {																
				errETPart1.setText(eterrmsg[0]);									//Assign the part 1 part to errETPart1
				errETPart2.setText("\u21EB");										//Assign the arrow to errETpart2
				label_errETOperand1.setText(eterrmsg[1]);							//Assini the error message to label errETOperand1
			}
		}
	}								
	
	
	/**********
	 * Private local method to set the value of the second operand given a text value. The logic is
	 * exactly the same as used for the first operand, above.
	 */
	private void setOperand2() {
		// See setOperand1's comments.
		//The logic is same!
		text_Result.setText("");
		text_ResultUnit.setText("");
		text_ResultErrorTerm.setText("");
		label_Result.setText("Result");						
		label_Result.setTextFill(Color.BLACK);
		label_errResult.setText("");
		errMV2Part1.setText("");
		errMV2Part2.setText("");
		errET2Part1.setText("");
		errET2Part2.setText("");
		if (perform.setOperand2(text_Operand2.getText(), text_Operand2ErrorTerm.getText(), units[comboboxSelectUnit2.getSelectionModel().getSelectedIndex()])) {
			if(text_Operand2ErrorTerm.getText().equals("") && checkBoxFlag2) {
				text_Operand2ErrorTerm.setDisable(true);
			} else {
				text_Operand2ErrorTerm.setDisable(false);   //Disable the Error Term2 as there is no error in Measured Value 2
				label_errOperand2.setText("");
				label_errETOperand2.setText("");
				if (text_Operand2.getText().length() == 0)
					label_errOperand2.setText("");
			}
		}
		else {
			String[] errmsg2 = perform.getOperand2ErrorMessage().split("\u21EB");
			String[] eterrmsg = perform.getOperand2ETErrorMessage().split("\u21EB");
			if(perform.getOperand2ErrorPresent()) {
				text_Operand2ErrorTerm.setText("");
				text_Operand2ErrorTerm.setDisable(true);
			} else {
				text_Operand2ErrorTerm.setDisable(false);
			}
			if(errmsg2.length == 1) {                                            //Check if the array length is 1
				if(errmsg2[0].equals("") || errmsg2[0].equals("Input is empty")) {
					if(errmsg2[0].equals("Input is empty")) {
						label_errOperand2.setText("Input is empty");
						text_Operand2ErrorTerm.setDisable(false);
					} else {
						label_errOperand2.setText("");
						text_Operand2ErrorTerm.setDisable(false);
					}
						
				} else {
					errMV2Part1.setText("");                                                                    
					errMV2Part2.setText("\u21EB");
					label_errOperand2.setText(perform.getOperand2ErrorMessage());    //If 1 then set the error message to string.
				}
			} else {
				errMV2Part1.setText(errmsg2[0]);                                 //Assign part 1 part to errMV2Part1
				errMV2Part2.setText("\u21EB");                                   //Assign arrow to errMV2Part1
				label_errOperand2.setText(errmsg2[1]);                           //Assign the error message to label errOperand2
			}
			
			if(eterrmsg.length == 1) {												//Check if the array length is 1
				if(eterrmsg[0].equals("")) {
					label_errETOperand2.setText("");
				} else {
					errET2Part1.setText("");                                                                    
					errET2Part2.setText("\u21EB");
					label_errETOperand2.setText(perform.getOperand2ETErrorMessage());	//If 1 then set the error message to string.
				}
				
			} else {																
				errET2Part1.setText(eterrmsg[0]);									//Assign the part 1 part to errET2Part1
				errET2Part2.setText("\u21EB");										//Assign the arrow to errET2part2
				label_errETOperand2.setText(eterrmsg[1]);							//Assign the error message to label errETOperand2
			}
		}
	}

	
	
	/**********
	 * This method is called when an binary operation button has been pressed. It assesses if there 
	 * are issues with either of the binary operands or they are not defined. If not return false 
	 * (there are no issues)
	 * 
	 * @return	True if there are any issues that should keep the calculator from doing its work.
	 */
	private boolean binaryOperandIssues() {
		label_Result.setText("Result");
		label_Result.setTextFill(Color.BLACK);					    // Assume no errors
		String errorMessage1 = perform.getOperand1ErrorMessage();	// Fetch the error messages, if
		String errorMessage2 = perform.getOperand2ErrorMessage();   // any, from the two operands
		if (errorMessage1.length() > 0) {						    // Check the first.  If not empty
			label_errOperand1.setText(errorMessage1);			    // there's an error, so display it.
			if (errorMessage2.length() > 0) {					    // Do the same with the 2nd operand
				label_errOperand2.setText(errorMessage2);
				return true;									    // Return true if both have errors
			}
			else {
				return true;									    // Return true if only the first
			}													    // has an error
		}
		else if (errorMessage2.length() > 0) {					    // No error with the first, so check
			label_errOperand2.setText(errorMessage2);			    // the second operand the same way
			return true;										    // Return true if only the 2nd has
		}														    // an error.
		
		// If the code reaches here, neither the first nor the second has an error condition. The
		// following code check to see if the operands are defined.
		System.out.println(perform.getErrorterm1Defined() + "perform");
		if (!perform.getOperand1Defined() && !perform.getErrorterm1Defined()) {		 // Is first operand defined? If not,
			label_errOperand1.setText("No value found");		                     // it is an issue for this operator
			if (!perform.getOperand2Defined()) {				                     // Check the second operand. If it
				label_errOperand2.setText("No value found");	                     // is not defined, two messages 
				return true;									                     // should be displayed. Signal there
			}													                     // are issues by returning true.
			return true;
		} else if (!perform.getOperand2Defined() && !perform.getErrorterm2Defined()) {	// If the first is defined, check the
			label_errOperand2.setText("No value found");		                        // second. Both operands must be
			return true;										                        // defined. Signal there are issues
		}
		
		return false;											// No issues, so return false
	}
	
	/*****
	 * Checking the operand is valid or not before performing Square root Operation
	 * @return boolean for execution
	 */
	private boolean sqrtIssues() {
		label_Result.setText("Result");
		label_Result.setTextFill(Color.BLACK);					    // Assume no errors
		String errorMessage1 = perform.getOperand1ErrorMessage();	// Fetch the error messages, if
		if (errorMessage1.length() > 0) {                           //First Operand has any errors
			return true;		}
		
		// If the code reaches here, the first has no error condition. The
		// following code check to see if the operand is defined.
		
		if (!perform.getOperand1Defined()) {					
			label_errOperand1.setText("No value found");
			return true;                                  //If not defined return true after setting error text
		}
	return false;		 //No issues, so return false
		
	}
	
	/*****
	 * Checking the operands Units are defined or not before performing Arithmetic Operations
	 * @return boolean for execution
	 */
	private boolean  unitIssues() {
		int index1 = comboboxSelectUnit1.getSelectionModel().getSelectedIndex();
		int index2 = comboboxSelectUnit2.getSelectionModel().getSelectedIndex();
		if(index1 == 0 || index2 == 0) {       //If index is zero, it means no unit is selected
			return true;
		}
		return false;
	}

	/**********************************************************************************************
	 * This portion of the class defines the actions that take place when the various calculator
	 * buttons (add, subtract, multiply, and divide) are pressed.
	 */

	/**********
	 * This add routine is called when the user pressed the add button. It calls the business logic
	 * class to do the actual work. Notice that this method is really more about doing things with
	 * the user interface. That is, it interacts with the user and delegates all of the computation
	 * work to the business logic class and the other classes that it uses. This class and its 
	 * methods are work with Strings.
	 * @throws Exception 
	 */
	private void addOperands() throws Exception{
		text_ResultUnit.setText("");
		// Check to see if both operands are defined and valid
		if (binaryOperandIssues()) 								// If there are issues, return 
			return;												// without doing anything
		
		// If the operands are defined and valid, request the business logic method to do the 
		// addition and return the result as a String. If there is a problem with the actual 
		// computation, an empty string is returned. 
	
		String[] operand1 = perform.getOperand1().split(" ");
		String[] operand2 = perform.getOperand2().split(" ");
		
		//If one of the error term is defined and the other is not defined and also precision is not checked, 
		//we set the not defined error term to a value based on the measured value
		
		if(text_Operand1ErrorTerm.getText().equals("") && !text_Operand2ErrorTerm.getText().equals("") && !checkBoxFlag1) {
			text_Operand1ErrorTerm.setText(perform.seterrorTerm(operand1[0]));
		} else if(!text_Operand1ErrorTerm.getText().equals("") && text_Operand2ErrorTerm.getText().equals("") && !checkBoxFlag2){
			text_Operand2ErrorTerm.setText(perform.seterrorTerm(operand2[0]));
		}
		
		String theAnswer = perform.addition();					// The business logic does the add
		String[] answer = theAnswer.split(" ");
		label_errResult.setText("");							 // Reset the result error messages
		if (theAnswer.length() > 0) {							 // See if a result was returned
			label_Result.setText("Addition");                    // title of the field to "Addition"
			text_Result.setText(answer[0]);						 // If so, display it and change the
			if(answer.length == 2) {                             //values  
				text_ResultUnit.setText(answer[1]);
			}
			if(answer.length > 2) {
				text_ResultErrorTerm.setText(answer[1]);
				text_ResultUnit.setText(answer[2]);
			}
			
		}
		else {													// There is no result.
			text_Result.setText("");						    // Do not display a result.
			text_ResultErrorTerm.setText("");
			text_ResultUnit.setText("");
			label_Result.setText("Result");						        // Reset the result label.
			label_errResult.setText(perform.getResultErrorMessage());	// Display error message.
		}
	}

	/**********
	 * This is the subtract routine
	 * This subtraction routine is called when the user pressed the subtract button.
	 * It calls the business logic
	 * class to do the actual work. Notice that this method is really more about doing things with
	 * the user interface. That is, it interacts with the user and delegates all of the computation
	 * work to the business logic class and the other classes that it uses. This class and its 
	 * methods are work with Strings.
	 * @throws Exception 
	 */
	private void subOperands() throws Exception{
		text_ResultUnit.setText("");
		if (binaryOperandIssues()) 								// If there are issues, return 
			return;												// without doing anything
		
		// If the operands are defined and valid, request the business logic method to do the 
		// subtraction and return the result as a String. If there is a problem with the actual 
		// computation, an empty string is returned. 
		String[] operand1 = perform.getOperand1().split(" ");
		String[] operand2 = perform.getOperand2().split(" ");
		
		//If one of the error term is defined and the other is not defined and also precision is not checked, 
		//we set the not defined error term to a value based on the measured value
		if(text_Operand1ErrorTerm.getText().equals("") && !text_Operand2ErrorTerm.getText().equals("") && !checkBoxFlag1) {
			text_Operand1ErrorTerm.setText(perform.seterrorTerm(operand1[0]));
		} else if(!text_Operand1ErrorTerm.getText().equals("") && text_Operand2ErrorTerm.getText().equals("") && !checkBoxFlag2){
			text_Operand2ErrorTerm.setText(perform.seterrorTerm(operand2[0]));
		}
		
		String theAnswer = perform.subtraction();			    // The business logic does the subtraction
		String[] answer = theAnswer.split(" ");
		label_errResult.setText("");							// Reset the result error messages
		if (theAnswer.length() > 0) {							// See if a result was returned
			label_Result.setText("Difference");                // title of the field to "Difference"

			text_Result.setText(answer[0]);						// If so, display it and change the value
			if(answer.length == 2) {
				text_ResultUnit.setText(answer[1]);
			}
			if(answer.length > 2) {
			text_ResultErrorTerm.setText(answer[1]);
			text_ResultUnit.setText(answer[2]);
			}
			
		}
		else {													         // There is no result.
			text_Result.setText("");							         // Do not display a result.
			text_ResultErrorTerm.setText("");
			label_Result.setText("Result");						        // Reset the result label.
			label_errResult.setText(perform.getResultErrorMessage());	// Display error message.
		}
		
	}

	/**********
	 * This is the multiply routine
	 * This multiplication routine is called when the user pressed the multiply button. It calls the business logic
	 * class to do the actual work. Notice that this method is really more about doing things with
	 * the user interface. That is, it interacts with the user and delegates all of the computation
	 * work to the business logic class and the other classes that it uses. This class and its 
	 * methods are work with Strings.
	 * @throws Exception 
	 */
	private void mpyOperands() throws Exception{
		text_ResultUnit.setText("");
		if (binaryOperandIssues()) 								// If there are issues, return 
			return;												// without doing anything
		
		// If the operands are defined and valid, request the business logic method to do the 
		// multiplication and return the result as a String. If there is a problem with the actual 
		// computation, an empty string is returned. 
		
		String[] operand1 = perform.getOperand1().split(" ");
		String[] operand2 = perform.getOperand2().split(" ");
		
		//If one of the error term is defined and the other is not defined and also precision is not checked, 
		//we set the not defined error term to a value based on the measured value
		
		if(text_Operand1ErrorTerm.getText().equals("") && !text_Operand2ErrorTerm.getText().equals("") && !checkBoxFlag1) {
			text_Operand1ErrorTerm.setText(perform.seterrorTerm(operand1[0]));
		} else if(!text_Operand1ErrorTerm.getText().equals("") && text_Operand2ErrorTerm.getText().equals("") && !checkBoxFlag2){
			text_Operand2ErrorTerm.setText(perform.seterrorTerm(operand2[0]));
		}
		String theAnswer = perform.multiplication();					// The business logic does the multiply
		String[] answer = theAnswer.split(" ");
		if (theAnswer.length() > 0) {							// See if a result was returned
			label_Result.setText("Product");                    // title of the field to "Product"

			text_Result.setText(answer[0]);						// If so, display it and change the values
			if(answer.length == 2) {
				text_ResultUnit.setText(answer[1]);
			}
			if(answer.length > 2) {
			text_ResultErrorTerm.setText(answer[1]);
			text_ResultUnit.setText(answer[2]);
			}
			
		}
		else {													        // There is no result.
			text_Result.setText("");							        // Do not display a result.
			text_ResultErrorTerm.setText("");
			label_Result.setText("Result");						        // Reset the result label.
			label_errResult.setText(perform.getResultErrorMessage());	// Display error message.
		}			
	}

	/**********
	 * This is the divide routine.  If the divisor is zero, the divisor is declared to be invalid.
	 * This division routine is called when the user pressed the divide button. It calls the business logic
	 * class to do the actual work. Notice that this method is really more about doing things with
	 * the user interface. That is, it interacts with the user and delegates all of the computation
	 * work to the business logic class and the other classes that it uses. This class and its 
	 * methods are work with Strings.
	 * @throws Exception 
	 */
	private void divOperands() throws Exception{
		text_ResultUnit.setText("");
		if (binaryOperandIssues()) 								// If there are issues, return 
			return;												// without doing anything
		
		// If the operands are defined and valid, request the business logic method to do the 
		// division and return the result as a String. If there is a problem with the actual 
		// computation, an empty string is returned. 
		String[] operand1 = perform.getOperand1().split(" ");
		String[] operand2 = perform.getOperand2().split(" ");
		
		//If one of the error term is defined and the other is not defined and also precision is not checked, 
		//we set the not defined error term to a value based on the measured value
		
		if(text_Operand1ErrorTerm.getText().equals("") && !text_Operand2ErrorTerm.getText().equals("") && !checkBoxFlag1) {
			text_Operand1ErrorTerm.setText(perform.seterrorTerm(operand1[0]));
		} else if(!text_Operand1ErrorTerm.getText().equals("") && text_Operand2ErrorTerm.getText().equals("") && !checkBoxFlag2){
			text_Operand2ErrorTerm.setText(perform.seterrorTerm(operand2[0]));
		}
		String theAnswer = perform.division();				// The business logic does the divide
		String[] answer = theAnswer.split(" ");
		label_errResult.setText("");							// Reset the result error messages
		if (theAnswer.length() > 0) {							// See if a result was returned
			label_Result.setText("Quotient");                    // title of the field to "Quotient"
			text_Result.setText(answer[0]);						// If so, display it and change the values
			if(answer.length == 2) {
				text_ResultUnit.setText(answer[1]);
			}
			if(answer.length > 2) {
			text_ResultErrorTerm.setText(answer[1]);
			text_ResultUnit.setText(answer[2]);
			}
			
		}
		else {													// There is no result.
			text_Result.setText("");							// Do not display a result.
			text_ResultErrorTerm.setText("");
			label_Result.setText("Result");						// Reset the result label.
			label_errResult.setText(perform.getResultErrorMessage());	// Display error message.
		}			
	}

	
	/**********
	 * This is the square root routine.  
	 * This square root routine is called when the user pressed the square root button. It calls the business logic
	 * class to do the actual work. Notice that this method is really more about doing things with
	 * the user interface. That is, it interacts with the user and delegates all of the computation
	 * work to the business logic class and the other classes that it uses. This class and its 
	 * methods are work with Strings.
	 */
	private void sqrtOperands(){
		text_ResultUnit.setText("");
		text_ResultErrorTerm.setText("");
		// Check to see if first operand is defined and valid
		if (sqrtIssues()) 								// If there are issues, return 
			return;
		
		//If the operand value is zero, set the result value to zero and return nothing
		if(perform.getOperand1().equals("0. 0.")) {
			text_Result.setText("0.0");
			text_ResultUnit.setText(this.units[comboboxSelectUnit1.getSelectionModel().getSelectedIndex()]);
			return;
		}
		
		String[] operand1 = perform.getOperand1().split(" ");
		
		//If  the error term is not defined and precision is not checked, 
		//we set the not defined error term to a value based on the measured value
		
		
		if( text_Operand1ErrorTerm.getText().equals("") && checkBoxFlag1 == false) {
			text_Operand1ErrorTerm.setText(perform.seterrorTerm(operand1[0]));
		}
		
		String[] theAnswer = perform.squareroot().split(" ");               // The business logic does the square root
		label_errResult.setText("");							// Reset the result error messages
		if (theAnswer.length > 0 && perform.getResultErrorMessage() == "") {							// See if a result was returned
			label_Result.setText("Square Root");                    // title of the field to "Square Root"
			text_Result.setText(theAnswer[0]);						// If so, display it and change the
			if(theAnswer.length == 2) {
				text_ResultUnit.setText(theAnswer[1]);
			}
			if(theAnswer.length > 2) {
			if(checkBoxFlag1) {
				text_ResultErrorTerm.setText("");
			}
			else {
				text_ResultErrorTerm.setText(theAnswer[1]);
			}
			text_ResultUnit.setText(theAnswer[2]);
			}
		}
		else {													// There is no result.
			text_Result.setText("");
			text_ResultErrorTerm.setText("");                   // Do not display a result.				
			label_Result.setText("Result");						// Reset the result label.
			label_errResult.setText(perform.getResultErrorMessage());	// Display error message.
		}										// without doing anything
	}
	
	/**********
	 * This is the loadCombobox routine.  
	 * This loadCombobox routine is called when the user launches the Calculator.
	 * Internally a new Array list is created and we store the units in it and load
	 * it when we launch the calculator. Then this list is displayed in the form of
	 * drop down. User will be able to choose one of the values among the list and we
	 * identify the value based on the index
	 * This method returns nothing
	 */
	private void loadComboBox1(String[] names, ComboBox <String> comboboxSelectUnit) {
		// Define a new list of Units.
		List<String> list = new ArrayList<String>();
			for (int i = 0; i < names.length; i++) {
				list.add(names[i]);
			}
		
		comboboxSelectUnit.setItems(FXCollections.observableArrayList(list));
		comboboxSelectUnit.getSelectionModel().select(0);
        if (UserInterfaceIsReady) {
            // Force the skin to scroll to the same place as the selection.  I have no idea why
        	// one would think that we should have to do this!  The following web page helped me
        	// to get this to work: https://bugs.openjdk.java.net/browse/JDK-8091560
            ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>)comboboxSelectUnit1.getSkin();
            ( (ListView<?>) skin.getPopupContent()).scrollTo(0);
        }
	}
}