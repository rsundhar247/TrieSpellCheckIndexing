package sadden.dictionary.op;

import static javax.swing.GroupLayout.Alignment.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import app.SpellCheckFunction;
import app.editDis;
public class UI extends JFrame{

	public ArrayList<String> res;
	public SpellCheckFunction cf;
	
	
	
	public UI(){
		res = new ArrayList<String>();
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
        JButton button_replace = new JButton("Replace");
        
//        button_check.setVerticalTextPosition(AbstractButton.CENTER);
//        button_check.setHorizontalTextPosition(AbstractButton.LEADING);
        button_check.setMnemonic(KeyEvent.VK_C);

      
        button_clear.setMnemonic(KeyEvent.VK_Z);
        
        button_check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               String content = input.getText();
               cf = new SpellCheckFunction();
               res = SpellCheckFunction.spellCheck2(content);
               if(res ==null)
               {
            	   output.setText("No Wrong Spelling found");
               }
               else
               {
            	String out = "";
            	String sugg = "";
       			for (String word : res) {
       				
       				out =out+word+"\n";
       			}
       			for (String word : res) {
       				sugg =sugg+"suggestion for "+word+" :\n";
       				ArrayList<String> suggL = editDis.Suggestion(word);
       				int num = suggL.size();
       				if(num==1)
       				{
       					sugg =sugg+suggL.get(0)+"\t";
       				}
       				else if (num==2)
       				{
       					sugg =sugg+suggL.get(0)+"\t";
       					sugg =sugg+suggL.get(1)+"\t";
       				}
       				else
       				{
       					sugg =sugg+suggL.get(0)+"\t";
       					sugg =sugg+suggL.get(1)+"\t";
       					sugg =sugg+suggL.get(2)+"\t";
       				}
       				sugg =sugg+"\n";
       				
       			}
       			
       			String s1 = "Possible wrong spelling are below:\n";
       			String s2 = "\n-spelling suggestion------------\n";
       			
            	   output.setText("Possible wrong spelling are below:\n"+out+"\n-spelling suggestion------------\n"+sugg);
//            	   output.setText("<html><font color=\"red\">"+s1+out+s2+sugg+"</font></html>");
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
        
        button_replace.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	//replace all the wrong words by the suggestion
            	
            	String content = input.getText();
            	if(res == null)
            	{
            		
            	}
            	else
            	{
            		for (String word : res) {
            			ArrayList<String> sugg = editDis.Suggestion(word);
           				String autosugg = sugg.get(0);
           				content = content.replaceAll(word, autosugg);
           				System.out.println("replace "+word+" by "+autosugg);
           				
           			}
            		input.setText(content);
            	}
            	
            	
            	
            	
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
            	.addComponent(button_admin)
            	.addComponent(button_replace))
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
                	.addComponent(button_replace)
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
