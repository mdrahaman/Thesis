package de.fhpotsdam.unfolding.examples.multi;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame

{

    private ImageIcon icon;
    private static Container container;
    private Font font;

    private JLabel label1;
    private JLabel label2;

    private JTextField userText1;
    private JPasswordField passwordText;

    private JButton loginButton;
    private JButton clearButton;


    LoginFrame()
    {
	initContainer();
    }


    private void initContainer()
    {

	container = this.getContentPane();
	container.setBackground(Color.GRAY);
	container.setLayout(null);
	font = new Font("Serif", Font.PLAIN, 18);

	icon = new ImageIcon(getClass().getResource("EyeTrack.png"));
	this.setIconImage(icon.getImage());

	label1 = new JLabel("Username");
	label1.setBounds(20, 20, 200, 30);
	container.add(label1);
	label1.setFont(new Font("Serif", Font.BOLD, 28));

	label2 = new JLabel("Password");
	label2.setBounds(20, 70, 200, 30);
	container.add(label2);
	label2.setFont(new Font("Serif", Font.BOLD, 28));

	userText1 = new JTextField("Enter Username");
	userText1.setBounds(250, 20, 200, 30);
	container.add(userText1);

	passwordText = new JPasswordField("Password");
	passwordText.setBounds(250, 70, 200, 30);
	container.add(passwordText);
	passwordText.setEchoChar('*');

	loginButton = new JButton("Login");
	loginButton.setBounds(250, 150, 100, 30);
	container.add(loginButton);
	
	loginButton.addActionListener(new ActionListener()
		{

		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		String userName = userText1.getText();
		String password = passwordText.getText();

		if (userName.equals("rsaied@yahoo.com") && password.equals("rsaied1988"))
		{
		    JOptionPane.showMessageDialog(null, "Welcome to our system");

		    // jump to other window / frame

		    dispose(); // hide login frame
		    Window window = new Window();
		    window.setVisible(true);

		} else
		{
		    JOptionPane.showMessageDialog(null, "Incorect Credential");
		}
			
		    }
	    
	    
		}
		
		);
	

	clearButton = new JButton("Clear");
	clearButton.setBounds(350, 150, 100, 30);
	container.add(clearButton);

	clearButton.addActionListener(new ActionListener()
	{

	    @Override
	    public void actionPerformed(ActionEvent arg0)
	    {
		userText1.setText("");
		passwordText.setText("");

	    }
	});

    }


    public static void main(String[] args)
    {

	LoginFrame loginframe = new LoginFrame();
	loginframe.setVisible(true);

	/* without constructor we can do like below */

	loginframe.setBounds(0, 0, 500, 350);
	// frame.setSize(1920, 1040);
	loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	loginframe.setLocale(null);
	loginframe.setLocationRelativeTo(null);
	loginframe.setResizable(false);
	loginframe.setTitle("Welcome to Gaze Exploration");
	loginframe.setBackground(Color.GREEN);

	// JMenuBar menubar = new JMenuBar();
	// menubar.setBounds(0, 0, 800, 30);
	// container.add(menubar);
	//
	// JMenu file = new JMenu("File");
	// file.setFont(new Font("serif", Font.BOLD, 22));
	// file.setForeground(Color.MAGENTA);
	// menubar.add(file);
	//
	//
	// JMenuItem open = new JMenuItem("Open");
	// open.setFont(new Font("serif", Font.BOLD, 15));
	// file.add(open);
	//
	// JMenuItem exit = new JMenuItem("Exit");
	// open.setFont(new Font("serif", Font.BOLD, 15));
	// file.add(exit);
    }

}
