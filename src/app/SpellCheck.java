/*
================================================================================================================
Project - Spell Check and AutoSuggest using Trie

Trie also known as prefix tree is used to store a dynamic set or associative array,
where the keys are usually strings

Key Features - Insert, Delete, Search, Spell Check, Auto Suggest
================================================================================================================
*/

package app;

import java.util.ArrayList;

public class SpellCheck {
	
	static TrieUtil trie = new TrieUtil();

	public static void main(String[] args) {
		String input = "Imagination is the beginning of creation. You imagine what you desire; you will what you imagine; and at last you create what you will.";
		
		/*
		 * Inserting a sample data. Need to replace this with read and load from dictionary. 
		 */
		trie.insert("you");
		trie.insert("imagination");
		trie.insert("and");
		trie.insert("why");
		trie.insert("what");
		trie.insert("while");
		trie.insert("winner");
		
		spellCheck(input); // function call to check the spelling of a sentence
		System.out.println("\n");
		System.out.println(trie.autoSuggest("wh"));
	}
	
	/*
	 * This function is used to spellCheck a given sentence.
	 */
	public static void spellCheck(String input) {
		
		boolean isValid = false;
		ArrayList<String> notValid = new ArrayList<String>(); // Used to store the invalid words (or) misspelled words
		String[] wordList = input.trim().toLowerCase().replaceAll("[^a-z ]", "").split(" "); // replaceAll function is used to ignore all the special characters (user's will be taken as users)
																							// We then split the sentence into an array of words, seperated by space
		for(int i=0; i<wordList.length; i++) {
			isValid = trie.search(wordList[i]); // For each word, we call search function in trie. It returns true if the word is present in the trie, false otherwise.
			if(! isValid) {
				notValid.add(wordList[i]);
			}
		}
		
		if(! notValid.isEmpty()) {
			System.out.println("Invalid Words are :: ");
			for(String word: notValid) {
				System.out.println(word);
			}
		} else {
			System.out.println("No Invalid words");
		}
	}
<<<<<<< HEAD
}
=======
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/master
>>>>>>> refs/remotes/origin/master
