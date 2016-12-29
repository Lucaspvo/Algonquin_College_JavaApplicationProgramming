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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CalculatorView extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JTextField d; 	/*calculator display*/
	private JLabel e; 			/*error message*/
	private JButton dButton; 		/*decimal point display*/
	
	/*Create the Calculator*/
	CalculatorView(){

		Controller c = new Controller(); 
		setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		setLayout(new BorderLayout());
		
		e = new JLabel("F", JLabel.CENTER);			
		e.setPreferredSize(new Dimension(25, 25)); 	
		e.setBackground(Color.YELLOW);
		e.setOpaque(true);   
		d = new JTextField("0.0", 16);
		d.setPreferredSize(new Dimension(d.getX(),30));
		d.setHorizontalAlignment(JTextField.RIGHT);
		d.setBackground(Color.WHITE);
		d.setEditable(false);
		
		JButton backspace = new JButton("\u2190");
		backspace.setPreferredSize(new Dimension(25,25));
		backspace.setForeground(Color.RED);
		backspace.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
		backspace.setContentAreaFilled(false);
		backspace.setToolTipText("Backspace(Alt-B)");
		backspace.setMnemonic('B');
		backspace.setActionCommand("backspace");
		backspace.addActionListener(c); 
		
		JPanel border = new JPanel(new BorderLayout());
			
		JPanel top = new JPanel(new FlowLayout());
		top.setBackground(Color.WHITE);
		top.add(e);
		top.add(d);
		top.add(backspace);
		
		JPanel bottom = new JPanel(new FlowLayout());
		bottom.setBackground(Color.BLACK);
		
		JCheckBox intButton = new JCheckBox();
		intButton.setText("Int");
		intButton.setBackground(Color.GREEN);
		intButton.addActionListener(c);
				
		JRadioButton zero = new JRadioButton( "0", false );
		zero.setBackground(Color.YELLOW);
		zero.addActionListener(c);
		
		JRadioButton dot00 = new JRadioButton( ".00", true );
		dot00.setBackground(Color.YELLOW);
		dot00.addActionListener(c);
				
		JRadioButton sciButton = new JRadioButton("Sci", false);
		sciButton.setBackground(Color.YELLOW);
		sciButton.addActionListener(c);
		
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
		JButton aButton;
		
		aButton = createButton("C", "C", Color.BLACK, Color.RED, c);
		aButton.addActionListener(c);
		border2.add(aButton, BorderLayout.NORTH);
		
		String[] numbers = {"7", "8", "9", "\u00f7", 
												"4", "5", "6", "*", 
												"1", "2", "3", "-", 
												"\u00b1", "0", ".", "+"};
		JPanel secondGroup = new JPanel(new GridLayout(4,4,5,5));
		int count = 1;
			
		for(String index: numbers){
			if(count%4 == 0){
				aButton = createButton(index, index, Color.YELLOW, Color.BLUE, c);
				aButton.addActionListener(c);
				secondGroup.add(aButton);
			}else
				if(index.compareTo(".") == 0){
					dButton = createButton(index, index, Color.BLACK, Color.BLUE, c);
					dButton.addActionListener(c);
					secondGroup.add(dButton);
				}else {
					aButton = createButton(index, index, Color.BLACK, Color.BLUE, c);
					aButton.addActionListener(c);
					secondGroup.add(aButton);
				}	
				count++;
			}
			
		EmptyBorder emptyBorder = new EmptyBorder(5, 3, 5, 3);
		secondGroup.setBorder(emptyBorder);
		border2.add(secondGroup, BorderLayout.CENTER);
		
		JButton equal = createButton("=", "=", Color.BLACK, Color.YELLOW, c);
		equal.addActionListener(c);
		border2.add(equal, BorderLayout.SOUTH);
		
		add(border, BorderLayout.NORTH);
		add(border2, BorderLayout.CENTER);		
	} 

	/*Create the number buttons*/
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
			
			d.setText(event.getActionCommand().toString());		
		}
	}
}
