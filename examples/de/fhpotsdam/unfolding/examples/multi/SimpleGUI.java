package de.fhpotsdam.unfolding.examples.multi;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimpleGUI extends JFrame{
	
	
	

	

		
		public static void main(String[] args) {	
			
			JFrame win= new JFrame("test");
			win.setSize(1000, 600);			
			win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
			JPanel panel = new JPanel();
			
			
			
			SimpleAplet applet = new SimpleAplet();
			applet.init();
			
			
			final JButton btn1 = new JButton("button1");
			btn1.setVisible(true);
			
			JButton btn2 = new JButton("button2");
			btn2.setVisible(true);
			
			
			
		
			panel.add(btn1);
			panel.add(btn2);
			win.add(panel);
			panel.add(applet);
			//frame.setSize(applet.getSize().width, applet.getSize().height);
		
			win.setVisible(true);
			
		
		

	}

}
