package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class editDis {
	public static ArrayList<String> Suggestion(String word)
	{
		ArrayList<String> sugg = new ArrayList<String>();
		int min = 999;
		BufferedReader breader = null;
		FileReader freader = null;
		String path = "res//wordlist.txt";
		try {		
			freader = new FileReader(path);
			breader = new BufferedReader(freader);
			String sCurrentLine;

			while ((sCurrentLine = breader.readLine()) != null) {
				sCurrentLine = sCurrentLine.toLowerCase().replaceAll("[^a-z ]", "");
				if(sCurrentLine.matches("^[a-zA-Z]*$")) {
					int distance = minDistance(word, sCurrentLine);
					if(distance <= min)
					{
						if(distance == min)
						{
							sugg.add(sCurrentLine);
						//	System.out.println(sCurrentLine);
							min = distance;
						}
						else
						{
							sugg.clear();
							sugg.add(sCurrentLine);
						//	System.out.println(sCurrentLine);
							min = distance;
						}
						
					}

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
		return sugg;
	}
	
	public static int minDistance(String word1, String word2) {
        if (word1 == null && word2 == null)
            return 0;
        if (word1 == null || word1.length() == 0)
            return word2.length();
        if (word2 == null || word2.length() == 0)
            return word1.length();
            
        int[][] d = new int[word1.length() + 1][];
        for (int i = 0; i <= word1.length(); i ++) {
            d[i] = new int[word2.length() + 1];
            d[i][0] = i;
            for (int j = 0; j <= word2.length(); j ++)
                d[0][j] = j;
         }            
                
        for (int i = 1; i <= word1.length(); i ++) {
            for (int j = 1; j <= word2.length(); j ++) {
                int count = Integer.MAX_VALUE;
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    count = d[i - 1][j - 1];
                else
                    count = d[i - 1][j - 1] + 1;
                
                d[i][j] = 
                    Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1), count);                
            }               
        }
        return d[word1.length()][word2.length()];
    }

  
}