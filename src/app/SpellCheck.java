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
		
		System.out.println("\n \n \nAre you a student or teacher. \nEnter s/S for Student; t/T for Teacher");
		String access = in.next();
		boolean fullAccess = (access.equals("t") || access.equals("T")) ? true : false;
		
		while(true) {
			System.out.println("\n\n\nEnter your Option:\n1. Spell Check.\n2. Auto Suggest.\n3. Insert.\n4. Delete");
			int option = in.nextInt();
			if(option == 1) {
				System.out.println("\nEnter the Sentence that needs to be spell checked.\nSpecial Characters and Numbers are not allowed.");
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
			} else if(option == 3) {
				if(! fullAccess) {
					System.out.println("You don't have permission to insert a word. Get a Teacher permission.");
					continue;
				}
				System.out.println("\nEnter the Word that needs to be inserted.\nNote - Special Characters and Numbers are not allowed.");
				String insert = in.next();
				insert = insert.trim().toLowerCase().replaceAll("[^a-z ]", "");
				try{
					trieUtil.insert(insert);
					boolean flag = dic.WriteDictionary(insert);
					if(!flag) {
						System.out.println("Error in inserting the word. Please make sure its a valid alphabet only word.");
						continue;
					}
					System.out.println(insert+" is inserted into the dictionary.");
				} catch(Exception e) {
					System.out.println("Error in inserting the word. Please make sure its a valid alphabet only word.");
				}
				
			} else if(option == 4) {
				if(! fullAccess) {
					System.out.println("You don't have permission to delete a word. Get a Teacher permission.");
					continue;
				}
				System.out.println("\nEnter the Word that needs to be deleted.\nNote - Special Characters and Numbers are not allowed.");
				String delete = in.next();
				delete = delete.trim().toLowerCase().replaceAll("[^a-z ]", "");
				try{
					trieUtil.delete(delete);
					boolean flag = dic.DeleteFromDictionary(delete);
					if(!flag) {
						System.out.println("Error in deleting the word. Please make sure its a valid alphabet only word.");
						continue;
					}
					System.out.println(delete+" is deleted from the dictionary.");
				} catch(Exception e) {
					System.out.println("Error in deleting the word. Please make sure its a valid alphabet only word.");
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