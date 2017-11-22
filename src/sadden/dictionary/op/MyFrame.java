package sadden.dictionary.op;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MyFrame extends JFrame{

	private Color background = new Color(0xf5f5f5);
	
	public MyFrame()
	{
		super("Check Your Spell");
		Container p = getContentPane();
		setBounds(200, 200, 900, 600);
		setVisible(true);
		p.setBackground(background);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		
		setResizable(true);
		
		AddSwig();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTheme();
		
		setVisible(true);
	}

	/**
	 * add every swig here
	 */
	private void AddSwig() {
		// TODO Auto-generated method stub

		JPanel root = new JPanel();
		//root.setLayout(new );
		root.add(new JButton("我是JButton"));
	      root.add(new JToggleButton("我是JToggleButton"));
	      root.add(new JLabel("我是JLabel"));
	      root.add(new JCheckBox("我是JCheckBox"));
	      root.add(new JRadioButton("我是JRadioButton"));
	      root.add(new JTextField("我是JTextField"));
	      root.add(new JPasswordField("我是JPasswordField"));
	      root.add(new JTextArea("我是JTextArea"));
	      add(root);
	}

	/**
	 * set system's themes
	 */
	private void setTheme() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
