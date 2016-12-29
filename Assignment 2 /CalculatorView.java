/*
File name: CalculatorView.java
Author: Lucas Pazelo Vargas de Oliveira 040 799 663
Course: CST8221 – JAP, Lab Section: 302
Assignment: 1
Date: February 13, 2015
Professor: Svillen Ranev
Purpose: Create the layout of the calculator
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.Component;

/*
 * This class is responsible for creating the calculator view
 * @author Lucas Pazelo Vargas de Oliveira
 * @version 2.0
 * @since 1.8_25
 */
public class CalculatorView extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JTextField d;			 	//calculator display
	private JLabel e; 					//error message
	private JButton dButton; 		//decimal point display
	JCheckBox intButton;				//int button
	JRadioButton zero;					//.0 button
	JRadioButton dot00;					//.00 button
	JButton aButton;						//any button
	JButton backspace;					//backspace button
	JPanel secondGroup;					//second group of panel
	JButton equal;							//equal button
	Component[] components;			//array of components
	
	String[] numbers = {"7", "8", "9", "\u00f7", 
			"4", "5", "6", "*", 
			"1", "2", "3", "-", 
			"\u00b1", "0", ".", "+"};
	
	/*
	 * Constructor of the Calculator View that creates the calculator
	 */
	CalculatorView(){

		Controller c = new Controller(); 
		NumbersController numController = new NumbersController();
		CController cController = new CController();
		OperationsController oController = new OperationsController();
		DotController dController = new DotController();
		BoxFloatPrecisionController boxFloatPrecision = new BoxFloatPrecisionController();
		IntButtonController intButtonController = new IntButtonController();
		MoreLessController moreLessController = new MoreLessController();
		BackSpaceController backSpaceController = new BackSpaceController();
		
		setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		setLayout(new BorderLayout());
		
		e = new JLabel("F", JLabel.CENTER);			
		e.setPreferredSize(new Dimension(25, 25)); 	
		e.setBackground(Color.YELLOW);
		e.setOpaque(true);  
		
		d = new JTextField("0.00", 16);
		d.setPreferredSize(new Dimension(d.getX(),30));
		d.setHorizontalAlignment(JTextField.RIGHT);
		d.setBackground(Color.WHITE);
		d.setEditable(false);
		
		backspace = new JButton("\u2190");
		backspace.setPreferredSize(new Dimension(25,25));
		backspace.setForeground(Color.RED);
		backspace.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
		backspace.setContentAreaFilled(false);
		backspace.setToolTipText("Backspace(Alt-B)");
		backspace.setMnemonic('B');
		backspace.setActionCommand("backspace");
		backspace.addActionListener(backSpaceController); 
		
		JPanel border = new JPanel(new BorderLayout());
			
		JPanel top = new JPanel(new FlowLayout());
		top.setBackground(Color.WHITE);
		top.add(e);
		top.add(d);
		top.add(backspace);
		
		JPanel bottom = new JPanel(new FlowLayout());
		bottom.setBackground(Color.BLACK);
		
		intButton = new JCheckBox();
		intButton.setText("Int");
		intButton.setBackground(Color.GREEN);
		intButton.addActionListener(intButtonController);
				
		zero = new JRadioButton( ".0", false );
		zero.setBackground(Color.YELLOW);
		zero.addActionListener(boxFloatPrecision);
		
		dot00 = new JRadioButton( ".00", true );
		dot00.setBackground(Color.YELLOW);
		dot00.addActionListener(boxFloatPrecision);
				
		JRadioButton sciButton = new JRadioButton("Sci", false);
		sciButton.setBackground(Color.YELLOW);
		sciButton.addActionListener(boxFloatPrecision);
		
		ButtonGroup group = new ButtonGroup();
		Box box = Box.createHorizontalBox();
		group.add(zero);
		group.add(dot00);
		group.add(sciButton);
		box.add(zero);
		box.add(dot00);
		box.add(sciButton);
		box.setBackground(Color.YELLOW);
				
		bottom.add(intButton);
		bottom.add(box);
		
		border.add(top, BorderLayout.NORTH);
		border.add(bottom, BorderLayout.SOUTH);
		
		JPanel border2 = new JPanel(new BorderLayout());
		
		aButton = createButton("C", "C", Color.BLACK, Color.RED, c);
		aButton.addActionListener(cController);
		border2.add(aButton, BorderLayout.NORTH);
		
		secondGroup = new JPanel(new GridLayout(4,4,5,5));
		int count = 1;
			
		for(String index: numbers){
			if(count%4 == 0){
				aButton = createButton(index, index, Color.YELLOW, Color.BLUE, c);
				aButton.addActionListener(oController);
				secondGroup.add(aButton);
			}else
				if(index.compareTo(".") == 0){
					dButton = createButton(index, index, Color.BLACK, Color.BLUE, c);
					dButton.addActionListener(dController);
					secondGroup.add(dButton);
				}else if(index.compareTo("\u00b1")==0) {
					aButton = createButton(index, index, Color.BLACK, Color.BLUE, c);
					aButton.addActionListener(moreLessController);
					secondGroup.add(aButton);
				}else {
					aButton = createButton(index, index, Color.BLACK, Color.BLUE, c);
					aButton.addActionListener(numController);
					secondGroup.add(aButton);
					
				}	
				count++;
			}
		
		components = secondGroup.getComponents();  //????????????????????????????????????
			
		EmptyBorder emptyBorder = new EmptyBorder(5, 3, 5, 3);
		secondGroup.setBorder(emptyBorder);
		border2.add(secondGroup, BorderLayout.CENTER);
		
		equal = createButton("=", "=", Color.BLACK, Color.YELLOW, c);
		equal.addActionListener(oController);
		border2.add(equal, BorderLayout.SOUTH);
		
		add(border, BorderLayout.NORTH);
		add(border2, BorderLayout.CENTER);		
	} 

	/*Create the number buttons
	 * @param text String Text of the button
	 * @param ac String Action Command
	 * @param fg Color Foreground color
	 * @param bg Color Background color 
	 */
	private JButton createButton (String text, String ac, Color fg, Color bg,
			 ActionListener handler){
		
		JButton button = new JButton(text);
		if(ac!=null){
			
			button.setActionCommand(ac);
		}
		button.setForeground(fg);		
		button.setBackground(bg);	
		button.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		button.addActionListener(handler);
		
		return button;
	}
	
	/*Buttons receive commands*/
	private class Controller implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			
			
			
			
		}
	}
	
	/*
	 * This class is responsible for performing some action when a number is clicked
	 * @author Lucas Pazelo Vargas de Oliveira
	 * @version 1.0
	 * @since 1.8_25
	 */
	private class NumbersController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			
			try{
			
				CalculatorModel calc = CalculatorModel.getInstance();
				
				/*if(calc.getEqual().equals("equal") == true){
					calc.setEqual("notEqual");
				}*/
				
				if(calc.getOperation() == null){
					if(calc.getOperand1() == null){
						calc.setOperand1(event.getActionCommand().toString());
						d.setText(calc.getOperand1());
					}else{
						calc.setOperand1(calc.getOperand1().concat(event.getActionCommand().toString()));
						d.setText(calc.getOperand1());
					}
				}else{
					if(calc.getOperand2() == null){
						calc.setOperand2(event.getActionCommand().toString());
						d.setText(calc.getOperand2());
					}else{
						if(calc.getEqual().equals("equal") == true && calc.getOperand2().equals("-") != true){
							calc.setOperand2(event.getActionCommand());
							d.setText(calc.getOperand2());
							calc.setEqual("notEqual");
						}else{
							calc.setOperand2(calc.getOperand2().concat(event.getActionCommand().toString()));
							d.setText(calc.getOperand2());
						}
					}
				}
				
			}catch(CalculatorException ex){
				d.setText(ex.getMessage());
				e.setText("E");
				e.setBackground(Color.RED);
				backspace.setEnabled(false);
				equal.setEnabled(false);
				dButton.setEnabled(false);
				for(int i = 0; i < components.length; i++){
					((JComponent) components[i]).setEnabled(false);
				}
			}
			
		}
	}
	
	/*
	 * This class is responsible for performing some action when the "C" button is clicked
	 * @author Lucas Pazelo Vargas de Oliveira
	 * @version 1.0
	 * @since 1.8_25
	 */
	private class CController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			
			CalculatorModel calc = CalculatorModel.getInstance();
			
			try{
				
				calc.setOperand1(null);
				calc.setOperand2(null);
				calc.setOperation(null);
				calc.setResult(null);
				calc.setEqual("notEqual");
				
				e.setText("F");
				e.setBackground(Color.YELLOW);
				backspace.setEnabled(true);
				equal.setEnabled(true);
				dButton.setEnabled(true);
				for(int i = 0; i < components.length; i++){
					((JComponent) components[i]).setEnabled(true);
				}
				
			}catch(CalculatorException ex){
				d.setText(ex.getMessage());
				e.setText("E");
				e.setBackground(Color.RED);
				backspace.setEnabled(false);
				equal.setEnabled(false);
				dButton.setEnabled(false);
				for(int i = 0; i < components.length; i++){
					((JComponent) components[i]).setEnabled(false);
				}
			}
			
			if(calc.getOpMode().equals("Int") == true)
				d.setText("0");
			else if(calc.getOpMode().equals("Float") == true)
				d.setText("0.00");
			
		}
	}
	
	/*
	 * This class is responsible for performing some action when an operation button is clicked
	 * @author Lucas Pazelo Vargas de Oliveira
	 * @version 1.0
	 * @since 1.8_25
	 */
	private class OperationsController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			
			try{
				
				CalculatorModel calc = CalculatorModel.getInstance();
			
				if(event.getActionCommand().equals("=") == true){
					calc.setEqual("equal");
					if(calc.getOperand2() == null)
						calc.setOperand2(calc.getOperand1());
					calc.performCalculations();				
					d.setText(calc.getResult());
					calc.setOperand1(calc.getResult());
				}else{
					String str = event.getActionCommand();
					if(str.equals("-") == true){
						if(calc.getOperand1() == null && calc.getOperand2() == null){
							calc.setOperand1(str);
							d.setText(calc.getOperand1());
						}else{
							calc.setOperation("+");
							calc.setOperand2(str);
							d.setText(calc.getOperand2());
						}
					}else{
						calc.setOperation(str);
						d.setText(str);
					}
				}
			
			}catch(CalculatorException ex){
				d.setText(ex.getMessage());
				e.setText("E");
				e.setBackground(Color.RED);
				backspace.setEnabled(false);
				equal.setEnabled(false);
				dButton.setEnabled(false);
				for(int i = 0; i < components.length; i++){
					((JComponent) components[i]).setEnabled(false);
				}
			}
				
		}
	}
	
	/*
	 * This class is responsible for performing some action when the "." button is clicked
	 * @author Lucas Pazelo Vargas de Oliveira
	 * @version 1.0
	 * @since 1.8_25
	 */
	private class DotController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			
			try{
			
				CalculatorModel calc = CalculatorModel.getInstance();
				
				if(calc.getOperand2() == null){
					if(calc.getOperand1().substring(calc.getOperand1().length()-1, calc.getOperand1().length()).equals(".") != true)
						calc.setOperand1(calc.getOperand1().concat("."));
				}else{
					if(calc.getOperand2().substring(calc.getOperand2().length()-1, calc.getOperand2().length()).equals(".") != true)
						calc.setOperand2(calc.getOperand2().concat("."));
				} 
				
			}catch(CalculatorException ex){
				d.setText(ex.getMessage());
				e.setText("E");
				e.setBackground(Color.RED);
				backspace.setEnabled(false);
				equal.setEnabled(false);
				dButton.setEnabled(false);
				for(int i = 0; i < components.length; i++){
					((JComponent) components[i]).setEnabled(false);
				}
			}
				
		}
	}
	
	/*
	 * This class is responsible for performing some action when a float precision button is clicked
	 * @author Lucas Pazelo Vargas de Oliveira
	 * @version 1.0
	 * @since 1.8_25
	 */
	private class BoxFloatPrecisionController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			
			CalculatorModel calc = CalculatorModel.getInstance();
			
			if(event.getActionCommand().equals(".0") == true)
				calc.setFloatPrecision(1);
			else if(event.getActionCommand().equals(".00") == true)
				calc.setFloatPrecision(2);
			else if(event.getActionCommand().equals("Sci") == true)
				calc.setFloatPrecision(3);
			
		}
	}
	
	/*
	 * This class is responsible for performing some action when the "Int" button is clicked
	 * @author Lucas Pazelo Vargas de Oliveira
	 * @version 1.0
	 * @since 1.8_25
	 */
	private class IntButtonController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			
			try{
			
				CalculatorModel calc = CalculatorModel.getInstance();
				
				if(intButton.isSelected()){
					calc.setOpMode("Int");
					dButton.setEnabled(false);
					dButton.setBackground(Color.GRAY);
					e.setText("I");
					e.setBackground(Color.GREEN);
					int count = 0;
					if(calc.getOperand1() != null){
						while(calc.getOperand1().substring(count, count+1).equals(".") != true && count < calc.getOperand1().length())
							count++;
						calc.setOperand1(calc.getOperand1().substring(0, count));
						if(calc.getMoreLessTag() == 1)
							d.setText(calc.getOperand1());
					}
					if(calc.getOperand2() != null){
						while(calc.getOperand2().substring(count, count+1).equals(".") != true && count < calc.getOperand2().length())
							count++;
						calc.setOperand2(calc.getOperand2().substring(0, count));
						if(calc.getMoreLessTag() == 1)
							d.setText(calc.getOperand2());
					}
				}else {
					calc.setOpMode("Float");
					dButton.setEnabled(true);
					dButton.setBackground(Color.BLUE);
					e.setText("F");
					e.setBackground(Color.YELLOW);
				}
				
			}catch(CalculatorException ex){
				d.setText(ex.getMessage());
				e.setText("E");
				e.setBackground(Color.RED);
				backspace.setEnabled(false);
				equal.setEnabled(false);
				dButton.setEnabled(false);
				for(int i = 0; i < components.length; i++){
					((JComponent) components[i]).setEnabled(false);
				}
			}
				
		}
	}
	
	/*
	 * This class is responsible for performing some action when the "+/-" button is clicked
	 * @author Lucas Pazelo Vargas de Oliveira
	 * @version 1.0
	 * @since 1.8_25
	 */
	private class MoreLessController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			
			try{
			
				CalculatorModel calc = CalculatorModel.getInstance();
				
				if(calc.getMoreLessTag() == 1){
					if(calc.getOperand1().substring(0, 1).equals("-") == true){
						calc.setOperand1(calc.getOperand1().substring(0, 0) + calc.getOperand1().substring(1));
						d.setText(calc.getOperand1());
					}else{
						StringBuilder sb1 = new StringBuilder(calc.getOperand1()).insert(0, "-");
						calc.setOperand1(sb1.toString());
						d.setText(calc.getOperand1());
					}
				}
				if(calc.getMoreLessTag() == 2){
					if(calc.getOperand2().substring(0, 1).equals("-") == true){
						calc.setOperand2(calc.getOperand2().substring(0, 0) + calc.getOperand2().substring(1));
						d.setText(calc.getOperand2());
					}else{
						StringBuilder sb2 = new StringBuilder(calc.getOperand2()).insert(0, "-");
						calc.setOperand2(sb2.toString());
						d.setText(calc.getOperand2());
					}
				}
				if(calc.getMoreLessTag() == 3){
					if(calc.getResult().substring(0, 1).equals("-") == true){
						calc.setResult(calc.getResult().substring(0, 0) + calc.getResult().substring(1));
						d.setText(calc.getResult());
					}else{
						StringBuilder sb3 = new StringBuilder(calc.getResult()).insert(0, "-");
						calc.setResult(sb3.toString());
						d.setText(calc.getResult());
					}
				}
				
			}catch(CalculatorException ex){
				d.setText(ex.getMessage());
				e.setText("E");
				e.setBackground(Color.RED);
				backspace.setEnabled(false);
				equal.setEnabled(false);
				dButton.setEnabled(false);
				for(int i = 0; i < components.length; i++){
					((JComponent) components[i]).setEnabled(false);
				}
			}
				
		}
	}
	
	/*
	 * This class is responsible for performing some action when the backspace button is clicked
	 * @author Lucas Pazelo Vargas de Oliveira
	 * @version 1.0
	 * @since 1.8_25
	 */
	private class BackSpaceController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			
			try{
			
				CalculatorModel calc = CalculatorModel.getInstance();
				
				if(calc.getMoreLessTag() == 1 && calc.getOperand1() != null){
					StringBuffer buf = new StringBuffer(calc.getOperand1().length() -1);
	        buf.append(calc.getOperand1().substring(0, calc.getOperand1().length() -1)).append(calc.getOperand1().substring(calc.getOperand1().length()));
	        calc.setOperand1(buf.toString());
	        d.setText(calc.getOperand1());
	        if(calc.getOperand1().length() == 0 || calc.getOperand1().equals("-") == true){
	        	calc.setOperand1(null);
	        	if(calc.getOpMode().equals("Int") == true)
	        		d.setText("0");
	        	else
	        		d.setText("0.00");
	        }
				}else if(calc.getMoreLessTag() == 2 && calc.getOperand2() != null){
					StringBuffer buf = new StringBuffer(calc.getOperand2().length() -1);
	        buf.append(calc.getOperand2().substring(0, calc.getOperand2().length() -1)).append(calc.getOperand2().substring(calc.getOperand2().length()));
	        calc.setOperand2(buf.toString());
	        d.setText(calc.getOperand2());
	        if(calc.getOperand2().length() == 0 || calc.getOperand2().equals("-") == true){
	        	calc.setOperand2(null);
	        	if(calc.getOpMode().equals("Int") == true)
	        		d.setText("0");
	        	else
	        		d.setText("0.00");
	        }
				}else if(calc.getMoreLessTag() == 3 && calc.getResult() != null){
					StringBuffer buf = new StringBuffer(calc.getResult().length() -1);
	        buf.append(calc.getResult().substring(0, calc.getResult().length() -1)).append(calc.getResult().substring(calc.getResult().length()));
	        calc.setResult(buf.toString());
	        d.setText(calc.getResult());
	        if(calc.getResult().length() == 0 || calc.getResult().equals("-") == true){
	        	calc.setResult(null);
	        	if(calc.getOpMode().equals("Int") == true)
	        		d.setText("0");
	        	else
	        		d.setText("0.00");
	        }
				}
				
			}catch(CalculatorException ex){
				d.setText(ex.getMessage());
				e.setText("E");
				e.setBackground(Color.RED);
				backspace.setEnabled(false);
				equal.setEnabled(false);
				dButton.setEnabled(false);
				for(int i = 0; i < components.length; i++){
					((JComponent) components[i]).setEnabled(false);
				}
			}
			
		}
	}
	
}
