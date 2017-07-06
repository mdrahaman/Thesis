package de.fhpotsdam.unfolding.examples.multi;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Application {

public static void main(String[] args) {

		final JFrame frame = new JFrame("PApplet in java application");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		JPanel buttonPanel = new JPanel();

		final MyApplet2 applet = new MyApplet2();

		applet.init();

		JButton buttonCreate = new JButton("create new ball");
		buttonCreate.setToolTipText("creates a ball");
		buttonCreate.setActionCommand("create ball");

		JButton buttonLoad = new JButton("Load file");
		buttonLoad.setToolTipText("load background Image");

		buttonCreate.addActionListener(applet);

		buttonLoad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser chooser = new JFileChooser();

				int returnVal = chooser.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("you choose to open this file : " + chooser.getSelectedFile().getName());

					applet.loadBgImage(chooser.getSelectedFile());

				}
			}
		});

		buttonPanel.add(buttonCreate);
		buttonPanel.add(buttonLoad);

		panel.add(applet);
		panel.add(buttonPanel);
		frame.add(panel);
		//frame.setSize(applet.getSize().width, applet.getSize().height);
		frame.setSize(1000, 600);
		frame.setVisible(true);

	}

}
