package sadden.dictionary.op;

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

import app.TrieUtil;

public class Manipulate extends JFrame{

	public TrieUtil trieUtil;
	
	public Manipulate(TrieUtil trie)
	{
		this.trieUtil = trie;
		
		
		setBounds(150, 250, 400, 200);
		//Initial all the swing
		
		
		final JTextField word_textfield = new JTextField();
		JButton insert = new JButton("insert");
		JButton delete = new JButton("delete");
		final JLabel label = new JLabel("------->>>");
		
		
		insert.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	//insert function
	            	String word = word_textfield.getText();
	            	if(!trieUtil.search(word))
	            	{
	            		trieUtil.insert(word);
	            		label.setText("insert successfully");
	            	}
	            	else
	            	{
	            		label.setText("The word already exists");
	            	}
	            	
	            }
	         });
		delete.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	//delete function
	            	
	            	String word = word_textfield.getText();
	            	if(trieUtil.search(word))
	            	{
	            		trieUtil.delete(word);
	            		label.setText("delete successfully");
	            	}
	            	else
	            	{
	            		label.setText("The word do not exist");
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
		            	.addComponent (word_textfield)
		            	.addComponent (label)
		        		.addGroup(layout.createSequentialGroup()
		        				.addComponent(insert)
		        				.addComponent(delete))
		        				)
		        );
		       
		        layout.linkSize(SwingConstants.HORIZONTAL, insert, delete);

		        layout.setVerticalGroup(layout.createSequentialGroup() 
		                .addComponent(word_textfield)
		                .addComponent (label)
		                .addGroup(layout.createParallelGroup(LEADING)
		        				.addComponent(insert)
		        				.addComponent(delete))
		        				
		            
		           
		        );

//		        layout.linkSize(SwingConstants.VERTICAL, name_field, password_field);
		        setTitle("insert or delete");
//		        pack();
		        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        setVisible(true);
		
		
		
		
		
	}
	
	
	
	
	
	
}
