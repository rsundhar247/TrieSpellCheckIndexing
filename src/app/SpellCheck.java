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
import java.util.Scanner;

import sadden.dictionary.op.*;

public class SpellCheck {
	
	static TrieUtil trieUtil = new TrieUtil();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		//Loading the list of words from dictionary		
		Dictionary dic = new Dictionary();
		trieUtil = dic.ReadDictionary(trieUtil);
		
		while(true) {
			System.out.println("\n\n\nEnter your Option:\n1. Spell Check.\n2. Auto Suggest.");
			int option = in.nextInt();
			if(option == 1) {
				System.out.println("\nEnter the Sentence that needs to be spell checked.\nSpecial characters are not allowed.");
				String sentence = in.next();
				sentence += in.nextLine();
				spellCheck(sentence);
			} else if(option == 2) {
				System.out.println("\nEnter the Starting Character(s) to get suggestion.");
				String suggest = in.next();
				suggest += in.nextLine();
				ArrayList<String> suggestion = trieUtil.autoSuggest(suggest);
				if(suggestion != null) {
					System.out.println("\n"+suggestion);
				} else {
					System.out.println("Input word is wrong. Please try our Spell Check feature.");
				}
			} else {
				break;
			}
		}
		in.close();
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