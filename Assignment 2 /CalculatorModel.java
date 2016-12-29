/*
*File Name: CalculatorModel.java
*Author: Lucas Pazelo Vargas de Oliveira
*Course: CST8221 – JAP, Lab Section: 302
*Assignment: 2
*Date: February 06
*Professor: Svillen Ranev
*Purpose: This class is responsible for doing the calculations of
*the calculator and return its result
*/

/*
 * This class is responsible for doing the calculations of
 * the calculator and return its result
 * @author Lucas Pazelo Vargas de Oliveira
 * @version 1.0
 * @since 1.8_25
 */
public class CalculatorModel {
	private String op1;					//Operand 1 in the operation
	private String op2;					//Operand 2 in the operation
	private String operation;		//Variable that represents the operation
	private String opMode;			//Operation Mode -- Int or Float
	private String errorState;	//True for error or false otherwise
	private String result;			//Variable for the result of the operation
	private String equal;				//Variable is "equal" when the equal button is clicked
	private int floatPrecision; //Float Precision: 1-->.0   2-->.00  3-->Sci
	private int moreLessTag; 		//Determine which variable is being displayed: 1-->op1  2-->op2  3-->result
	
	/*
	 * Constructor of the Calculator Model
	 */
	private CalculatorModel(){ 
		op1 = null;
		op2 = null;
		operation = null;
		opMode = "Float";
		result = null;
		floatPrecision = 2;
		equal = "notEqual";
		moreLessTag = 4;
	}
	
	//Instantiation of the Calculator Model object
	private static CalculatorModel calcModel = new CalculatorModel();
	
	/*Returns the instance of the Calculator Model object
	 * @return calcModel Address of the Calculator Model object
	 */
	public static CalculatorModel getInstance(){
		return calcModel;
	}
	
	/*Set the Operand1 with a value
	 * @param op1 String of the Opreand1
	 * @throws CalculatorException Check to see if op1 will not cause an error
	 */
	public void setOperand1(String op1) throws CalculatorException{
		this.op1 = op1;
		validateData(op1);
	}
	
	/*Return the value of Operand1
	 * @return op1 String of the Opreand1
	 */
	public String getOperand1(){
		this.moreLessTag = 1;
		return this.op1;
	}
	
	/*Set the Operand2 with a value
	 * @param op2 String of the Opreand1
	 * @throws CalculatorException Check to see if op2 will not cause an error
	 */
	public void setOperand2(String op2) throws CalculatorException{
		this.op2 = op2;
		validateData(op2);
	}
	
	/*Return the value of Operand2
	 * @return op2 String of the Opreand2
	 */
	public String getOperand2(){
		this.moreLessTag = 2;
		return this.op2;
	}
	
	/*Set the Operation with a value
	 * @param operation String of the Operation
	 */
	public void setOperation(String operation){
		this.operation = operation;
	}
	
	/*Return the value of Operation
	 * @return operation String of the Operation
	 */
	public String getOperation(){
		this.moreLessTag = 4;
		return this.operation;
	}
	
	/*Set the Operation Mode with a value
	 * @param opMode String of the Operation Mode
	 */
	public void setOpMode(String opMode){
		this.opMode = opMode;
	}
	
	/*Return the value of Operation Mode
	 * @return opMode String of the Operation Mode
	 */
	public String getOpMode(){
		return this.opMode;
	}
	
	/*Set the Result with a value
	 * @param result String of the Result
	 * @throws CalculatorException Check to see if result will not cause an error
	 */
	public void setResult(String result) throws CalculatorException{		
		this.result = result;
		validateData(result);
	}
	
	/*Return the value of Result
	 * @return result String of the Result
	 */
	public String getResult(){
		this.moreLessTag = 3;
		return result;
	}
	
	/*Set the Float Precision with a value
	 * @param floatPrecision int of the Float Precision
	 */
	public void setFloatPrecision(int fPrecision){
		this.floatPrecision = fPrecision;
	}
	
	/*Return the value of Float Precision
	 * @return floatPrecision int of the Float Precision
	 */
	public int getFloatPrecision(){
		return this.floatPrecision;
	}
	
	/*Set the Error State with a value
	 * @param errorState String of the Error State
	 */
	public void setErrorState(String errorState){
		this.errorState = errorState;
	}
	
	/*Set the Equal with a value
	 * @param equal String of the Equal
	 */
	public void setEqual(String equal){
		this.equal = equal;
	}
	
	/*Return the value of Equal
	 * @return equal String of the Equal
	 */
	public String getEqual(){
		return this.equal;
	}
	
	/*Return the value of More or Less Tag
	 * @return moreLessTag int of the More or Less Tag
	 */
	public int getMoreLessTag(){
		return this.moreLessTag;
	}
	
	/*Return the value of Error State
	 * @return errorState String of the Error State
	 */
	public String getErrorState(){
		return this.errorState;
	}
	
	/*Make the chosen calculation by the user
	 * @throws CalculatorException To make sure that if there is an error, this will be handled
	 */
	public void performCalculations() throws CalculatorException{
		switch(operation){
			case "\u00f7":
				if(this.floatPrecision == 1 && this.opMode == "Float")
					this.setResult(String.format("%.01f", Float.parseFloat(op1) / Float.parseFloat(op2)));
				else if(this.floatPrecision == 2 && this.opMode == "Float")
					this.setResult(String.format("%.02f", Float.parseFloat(op1) / Float.parseFloat(op2)));
				else if(this.floatPrecision == 3 && this.opMode == "Float")
					this.setResult(String.format("%E", Float.parseFloat(op1) / Float.parseFloat(op2)));
				else
					this.setResult(Integer.toString(Integer.parseInt(op1) / Integer.parseInt(op2)));
				break;
			case "*":
				if(this.floatPrecision == 1 && this.opMode == "Float")
					this.setResult(String.format("%.01f", Float.parseFloat(op1) * Float.parseFloat(op2)));
				else if(this.floatPrecision == 2 && this.opMode == "Float")
					this.setResult(String.format("%.02f", Float.parseFloat(op1) * Float.parseFloat(op2)));
				else if(this.floatPrecision == 3 && this.opMode == "Float")
					this.setResult(String.format("%E", Float.parseFloat(op1) * Float.parseFloat(op2)));
				else
					this.setResult(Integer.toString(Integer.parseInt(op1) * Integer.parseInt(op2)));
				break;
			case "+":
				if(this.floatPrecision == 1 && this.opMode == "Float")
					this.setResult(String.format("%.01f", Float.parseFloat(op1) + Float.parseFloat(op2)));
				else if(this.floatPrecision == 2 && this.opMode == "Float")
					this.setResult(String.format("%.02f", Float.parseFloat(op1) + Float.parseFloat(op2)));
				else if(this.floatPrecision == 3 && this.opMode == "Float")
					this.setResult(String.format("%E", Float.parseFloat(op1) + Float.parseFloat(op2)));
				else
					this.setResult(Integer.toString(Integer.parseInt(op1) + Integer.parseInt(op2)));
				break;
			case "-":
				if(this.floatPrecision == 1 && this.opMode == "Float")
					this.setResult(String.format("%.01f", Float.parseFloat(op1) - Float.parseFloat(op2)));
				else if(this.floatPrecision == 2 && this.opMode == "Float")
					this.setResult(String.format("%.02f", Float.parseFloat(op1) - Float.parseFloat(op2)));
				else if(this.floatPrecision == 3 && this.opMode == "Float")
					this.setResult(String.format("%E", Float.parseFloat(op1) - Float.parseFloat(op2)));
				else
					this.setResult(Integer.toString(Integer.parseInt(op1) - Integer.parseInt(op2)));
				break;
		}
	}
	
	/*Function used to validate the data received from the calculator
	 * @param data String Value that needs to be handled
	 * @throws CalculatorException Calls CalculatorExceptionin case of an error
	 */
	private void validateData(String data) throws CalculatorException{
		if(data != null && data.length() != 0 && data.equals("-") != true){
			if(this.opMode.equals("Int") == true){
				System.out.println("entrou if int");
				if(Long.parseLong(data) > Integer.MAX_VALUE)
					throw new CalculatorException("--");
				if(this.op2 != null)
					if(this.op2.equals("0") == true)
						throw new CalculatorException("--");
			}
			if(this.opMode.equals("Float") == true){
				if(Double.parseDouble(data) > Float.MAX_VALUE)
					throw new CalculatorException("--");
				if(this.op2 != null)
					if(Float.parseFloat(op2) == 0)
						throw new CalculatorException("--");
			}
		}
		
	}
	
	
}
