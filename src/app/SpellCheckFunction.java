package app;

import java.util.ArrayList;

import sadden.dictionary.op.Dictionary;

public class SpellCheckFunction {
	public static TrieUtil trieUtil = new TrieUtil();

	public SpellCheckFunction() {
		Dictionary dic = new Dictionary();
		trieUtil = dic.ReadDictionary(trieUtil);

	}

	public String Check(String content) {
		
		return spellCheck(content);
	}

	public static String spellCheck(String input) {

		if(input == null || input.equals(""))
		{
			System.out.println("empty");
			return null;
		}
		String res = "";
		boolean isValid = false;
		ArrayList<String> notValid = new ArrayList<String>();   // Used to store
																// the invalid
																// words (or)
																// misspelled
																// words
		String[] wordList = input.trim().toLowerCase().replaceAll("[^a-z ]", "").split(" "); 
		for (int i = 0; i < wordList.length; i++) {
			isValid = trieUtil.search(wordList[i]); // For each word, we call
													// search function in trie.
													// It returns true if the
													// word is present in the
													// trie, false otherwise.
			if (!isValid) {
				notValid.add(wordList[i]);
			}
		}

		if (!notValid.isEmpty()) {
			
			System.out.println("\n \nInvalid Words are :: ");
			for (String word : notValid) {
				System.out.println(word);
				res =res+word+"\n";
			}
		} else {
			System.out.println("\n \nNo Invalid words");
			res = null;
			
		}
		return res;
	}
}
