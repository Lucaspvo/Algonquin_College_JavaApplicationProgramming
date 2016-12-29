/*
File name: Calculator.java
Author: Lucas Pazelo Vargas de Oliveira 040 799 663
Course: CST8221 – JAP, Lab Section: 302
Assignment: 1
Date: February 13, 2015
Professor: Svillen Ranev
Purpose: File responsible for starting the calculator
*/

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;


/*
 * Class responsible for initializing the Calculator
 * @author Lucas Pazelo Vargas de Oliveira
 * @version 2.0
 * @since 1.8_25
 */
public class Calculator {
	
	/*Shows the calculator in its frame*/
	public static void main(String[] args){
		CalculatorSplashScreen s = new CalculatorSplashScreen(5000);
		s.showSplashWindow();
		
		/* Frame that contains Calculator*/
		EventQueue.invokeLater( new Runnable() {
			public void run(){
				JFrame calc = new JFrame();
				calc.setTitle("My calculator");
				calc.setMinimumSize(new Dimension(330, 400));
				calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
				calc.pack();
				calc.setLocationByPlatform(true);
				calc.setContentPane(new CalculatorView());
				calc.setVisible(true);	
			}
		});
	}
}
