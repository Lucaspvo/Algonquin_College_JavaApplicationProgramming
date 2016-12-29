/*
*File Name: CalculatorException.java
*Author: Lucas Pazelo Vargas de Oliveira
*Course: CST8221 – JAP, Lab Section: 302
*Assignment: 2
*Date: February 06
*Professor: Svillen Ranev
*Purpose: This class is responsible for displaying an error message in case of an error
*/

/*
 * This class is responsible for displaying an error message in case of an error
 * @author Lucas Pazelo Vargas de Oliveira
 * @version 1.0
 * @since 1.8_25
 */
@SuppressWarnings("serial")
public class CalculatorException extends Exception{
	
	/*
	 * Constructor of the Calculator Exception
	 */
	public CalculatorException(){
		super("Full name not correct formt");
	}
	
	/*
	 * Constructor of the Calculator Exception
	 * @param message String
	 */
	public CalculatorException(String message){
		super(message);
	}
	
	/*
	 * Constructor of the Calculator Exception
	 * @param message String
	 * @param cause Throwable
	 */
	public CalculatorException(String message, Throwable cause){
		super(message, cause);
	}
	
	/*
	 * Constructor of the Calculator Exception
	 * @param cause Throwable
	 */
	public CalculatorException(Throwable cause){
		super(cause);
	}
}
