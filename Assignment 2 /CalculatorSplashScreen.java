/*
File name: CalculatorSplashScreen.java
Author: Lucas Pazelo Vargas de Oliveira 040 799 663
Course: CST8221 – JAP, Lab Section: 302
Assignment: 1
Date: February 13, 2015
Professor: Svillen Ranev
Purpose: The splash screen will show up on the screen
*/

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/*
 * Class that will show the screen
 * @author Lucas Pazelo Vargas de Oliveira
 * @version 2.0
 * @since 1.8_25
 */
public class CalculatorSplashScreen extends JWindow{
	
	private static final long serialVersionUID = 6248477390124803341L;
	
	private int periodTime;		//period of time that the splash screen will be on the screen
	
	/*Constructor of the CalculatorSplashScreen class
	 * @duration int Duration of Splash Screen in the computer Screen
	 */
	public CalculatorSplashScreen(int duration){
		this.periodTime = duration;
	}

	/*
	 * Show splash screen
	 */
	public void showSplashWindow() {
		
		JPanel content = new JPanel(new BorderLayout());
		content.setBackground(Color.BLACK);
	    
	    int width =  290+10;		//width of splash screen
	    int height = 256+10;		//height of splash screen
	    
	    Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (s.width-width)/2;
	    int y = (s.height-height)/2;
	    setBounds(x,y,width,height); 
	    	    
	    JLabel calc = new JLabel(new ImageIcon("calculator-icon.png")); /*image that will show up*/
	    JLabel name = new JLabel("Lucas Pazelo Vargas de Oliveira 040 799 663", JLabel.CENTER); /*label*/
	    name.setFont(new Font("Sans Serif", Font.PLAIN, 14)); /*font of the table*/
	    name.setForeground(Color.GREEN);
	    content.add(calc, BorderLayout.CENTER); 
	    content.add(name, BorderLayout.SOUTH);
	    setContentPane(content);
	    setVisible(true);

	    try {	
	    	 Thread.sleep(periodTime); }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	    dispose(); 
	  }
}
