package app;

import java.util.ArrayList;

public class SpellCheck {
	
	static TrieUtil trie = new TrieUtil();

	public static void main(String[] args) {
		String input = "Imagination is the beginning of creation. You imagine what you desire; you will what you imagine; and at last you create what you will.";
		trie.insert("you");
		trie.insert("imagination");
		trie.insert("and");
		trie.insert("will");
		trie.insert("what");
		
		spellCheck(input);
	}
	
	public static void spellCheck(String input) {
		
		boolean isValid = false;
		ArrayList<String> notValid = new ArrayList<String>();
		String[] wordList = input.trim().toLowerCase().replaceAll("[^a-z ]", "").split(" ");
		
		for(int i=0; i<wordList.length; i++) {
			isValid = trie.search(wordList[i]);
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
	
	
}
