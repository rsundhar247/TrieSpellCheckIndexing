package sadden.dictionary.op;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.CENTER;
import static javax.swing.GroupLayout.Alignment.LEADING;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import app.TrieUtil;

public class Admin extends JFrame{

	public TrieUtil trieUtil;
	
	public Admin(TrieUtil trie)
	{
		this.trieUtil = trie;
		
        setBounds(150, 250, 600, 200);
		//Initial all the swing
		JLabel L_name = new JLabel("NAME:");
		JLabel L_pass = new JLabel("PASSWORD:");
		final JPasswordField password_field = new JPasswordField();
		final JTextField name_field = new JTextField();
		
		JButton B_ok = new JButton("LOGIN");
		
		 B_ok.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	String name = name_field.getText();
	            	String password = password_field.getText();
	            	if(name.equals("Sadden") && password.equals("123"))
	            	{
	            		System.out.println("Success");
	            		Manipulate man = new Manipulate(trieUtil);
	            		dispose();
	            		
	            	}
	            	else
	            	{
	            		name_field.setText("");
	            		password_field.setText("");
	            	}
	            }
	         });
		
		//add the layout management
		GroupLayout layout = new GroupLayout(getContentPane()); 
        getContentPane().setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(CENTER)
            		
        		.addGroup(layout.createParallelGroup(LEADING)
        				.addComponent(L_name)
        				.addComponent(name_field)
                		.addComponent(password_field)
        				.addComponent(L_pass))
        				
        		.addComponent (B_ok))
        );
       
//        layout.linkSize(SwingConstants.HORIZONTAL, name_field, password_field);

        layout.setVerticalGroup(layout.createSequentialGroup()
           
                .addComponent(L_name)   
                .addComponent(name_field)
                .addComponent(L_pass)
                .addComponent(password_field)
                .addComponent(B_ok)
            
           
        );

//        layout.linkSize(SwingConstants.VERTICAL, name_field, password_field);
        setTitle("Login");
//        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
 
		
	}
}
