package sadden.dictionary.op;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

import app.TrieUtil;

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
	public TrieUtil ReadDictionary(TrieUtil trieUtil)
	{
		try {

			freader = new FileReader(path);
			breader = new BufferedReader(freader);
			String sCurrentLine;

			while ((sCurrentLine = breader.readLine()) != null) {
				sCurrentLine = sCurrentLine.toLowerCase().replaceAll("[^a-z ]", "");
				if(sCurrentLine.matches("^[a-zA-Z]*$")) {
					trieUtil.insert(sCurrentLine);
					
				}
//				System.out.println(sCurrentLine);
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
		return trieUtil;
	}
	
	/**
	 * write a word into the dictionary
	 * @param word
	 */
	public boolean WriteDictionary(String word)
	{
		 try {  

	            
	            RandomAccessFile randomFile = new RandomAccessFile(path, "rw");   
	            long fileLength = randomFile.length();
	            randomFile.seek(fileLength);
	            randomFile.writeBytes("\n"+word);
	            randomFile.close();
	            
	            
	            return true;
	        } catch (Exception e) {  
	            e.printStackTrace();  
	            System.out.println("writting failure");  
	            return false;
	        }         
	}

//	long begintime = System.currentTimeMillis();	
//	 long begintime2 = System.currentTimeMillis();
//	 System.out.println(begintime2 - begintime);
}
