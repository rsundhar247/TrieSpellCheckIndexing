package sadden.dictionary.op;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

public class Dictionary {

	public BufferedReader breader = null;
	public FileReader freader = null;
	public String path;
	//construct default file path
	public Dictionary()
	{
		path = "res//wordlist.txt";
	}
	
	public Dictionary(String filepath)
	{
		path = filepath;
	}
	/**
	 * read all words from dictionary and print
	 */
	public void ReadDictionary()
	{
		try {

			freader = new FileReader(path);
			breader = new BufferedReader(freader);

			//read a word from the list
			String sCurrentLine;

			while ((sCurrentLine = breader.readLine()) != null) {
				
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (breader != null)
					breader.close();
				if (freader != null)
					freader.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		
		}
	}
	
	/**
	 * write a word into the dictionary
	 * @param word
	 */
	public boolean WriteDictionary(String word)
	{
		 try {  
//	            FileOutputStream out = new FileOutputStream(path);  
//	            OutputStreamWriter outWriter = new OutputStreamWriter(out, "UTF-8");  
//	            BufferedWriter bufWrite = new BufferedWriter(outWriter);  
//	       
//	            bufWrite.write(word+"\r\n");  
//	              
//	            bufWrite.close();  
//	            outWriter.close();  
//	            out.close();  
	            
	            
	            RandomAccessFile randomFile = new RandomAccessFile(path, "rw");   
	            long fileLength = randomFile.length();
	            randomFile.seek(fileLength);
	            randomFile.writeBytes("\n"+word);
	            randomFile.close();
	            
	            
	            return true;
	        } catch (Exception e) {  
	            e.printStackTrace();  
	            System.out.println("writting failure！");  
	            return false;
	        }         
	}

}
