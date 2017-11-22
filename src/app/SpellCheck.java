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
import sadden.dictionary.op.*;

public class SpellCheck {
	
	static TrieUtil trieUtil = new TrieUtil();

	public static void main(String[] args) {
		String input = "Raj Imagination is the beginning of creation. You imagine what you desire; you will what you imagine; and at last you create what you will.";
		
		//Loading the list of words from dictionary		
		Dictionary dic = new Dictionary();
		trieUtil = dic.ReadDictionary(trieUtil);
		

		
		spellCheck(input); // function call to check the spelling of a sentence
		System.out.println("\n");
		System.out.println(trieUtil.autoSuggest("wh"));
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
			isValid = trieUtil.search(wordList[i]); // For each word, we call search function in trie. It returns true if the word is present in the trie, false otherwise.
			if(! isValid) {
				notValid.add(wordList[i]);
			}
		}
		
		if(! notValid.isEmpty()) {
			System.out.println("\n \nInvalid Words are :: ");
			for(String word: notValid) {
				System.out.println(word);
			}
		} else {
			System.out.println("\n \nNo Invalid words");
		}
	}
}