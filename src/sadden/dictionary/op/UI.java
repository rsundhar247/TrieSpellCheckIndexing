package sadden.dictionary.op;

import static javax.swing.GroupLayout.Alignment.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import app.SpellCheckFunction;
public class UI extends JFrame{

	public String res;
	public SpellCheckFunction cf;
	
	
	
	public UI(){
		res = null;
        //set the bound
        setBounds(150, 250, 900, 400);
		//Initial all the swing
		JLabel label_input = new JLabel("Input:");
		JLabel label_output = new JLabel("Output:");
		
        final JTextArea input = new JTextArea();
        final JTextArea output = new JTextArea();
        input.setEditable(true);
        output.setEditable(false);
        input.setLineWrap(true);
        output.setLineWrap(true);
        
        
        JButton button_check = new JButton("Check");
        JButton button_clear = new JButton("Clear");
        JButton button_admin = new JButton("Login as Admin");
//        button_check.setVerticalTextPosition(AbstractButton.CENTER);
//        button_check.setHorizontalTextPosition(AbstractButton.LEADING);
        button_check.setMnemonic(KeyEvent.VK_C);

      
        button_clear.setMnemonic(KeyEvent.VK_Z);
        
        button_check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String content = input.getText();
               cf = new SpellCheckFunction();
               res = cf.Check(content);
               if(res ==null)
               {
            	   output.setText("No Wrong Spelling found");
               }
               else
               {
            	   output.setText("Possible wrong spelling are below:\n"+res);
               }
               
               
            }
         });
        button_clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	output.setText("");
            	input.setText("");
            }
         });
        button_admin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("Now Login");
            	Dictionary dic = new Dictionary();
            	cf.trieUtil = dic.ReadDictionary(cf.trieUtil);
            	Admin admin = new Admin(cf.trieUtil);
            }
         });
        //new layout file
        GroupLayout layout = new GroupLayout(getContentPane()); 
        getContentPane().setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(LEADING)
                .addComponent(label_input)
                .addComponent(input))
            .addGroup(layout.createParallelGroup(CENTER)
            	.addComponent(button_check)
            	.addComponent(button_clear)
            	.addComponent(button_admin))
            .addGroup(layout.createParallelGroup(LEADING)
                .addComponent(label_output)
                .addComponent(output))
        );
       
        layout.linkSize(SwingConstants.HORIZONTAL, button_check, button_clear);

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(BASELINE)
                .addComponent(label_input)   
                .addComponent(label_output))
            
            .addGroup(layout.createParallelGroup(LEADING)
                .addComponent(input)
                .addGroup(layout.createSequentialGroup()      		
                	.addComponent(button_check)
                	.addComponent(button_clear)
                	.addComponent(button_admin))
                .addComponent(output))
        );

        layout.linkSize(SwingConstants.VERTICAL, button_check, button_clear);
        setTitle("check");
//        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(
                                  "javax.swing.plaf.metal.MetalLookAndFeel");
                                //  "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                                //UIManager.getCrossPlatformLookAndFeelClassName());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                new UI().setVisible(true);
            }
        });

	}
	
}
