package app;

import java.util.ArrayList;

public class TrieUtil {

	int CHARACTER_MAX = 26;
	int maxSuggestions = 4;
	
	class TrieNode {
		
		TrieNode children[] = new TrieNode[CHARACTER_MAX];
		boolean isEndOfWord;
		
		public TrieNode() {
			for(int i=0; i<CHARACTER_MAX; i++) {
				children[i] = null;
			}
			isEndOfWord = false;
		}
	}
	
	TrieNode root = new TrieNode();
	
	public void insert(String word) {
		TrieNode currentNode = root;
		word = word.toLowerCase();
		int length = word.length();
		char insert;
		
		for(int i=0; i<length; i++) {
			insert = word.charAt(i);
			TrieNode childNode = currentNode.children[insert-'a'];
			if(childNode == null) {
				currentNode.children[insert-'a'] = new TrieNode();
			}
			currentNode = currentNode.children[insert-'a'];
		}
		currentNode.isEndOfWord = true;
	}
	
	public boolean search(String word) {
		TrieNode currentNode = root;
		word = word.toLowerCase();
		int length = word.length();
		char search;
		
		for(int i=0; i<length; i++) {
			search = word.charAt(i);
			if(currentNode.children[search-'a'] != null) {
				currentNode = currentNode.children[search-'a'];
			} else {
				return false;
			}
		}
		if(currentNode.isEndOfWord) {
			return true;
		} else {
			return false;
		}
	}
	
	public void delete(String word) {
		TrieNode currentNode = root;
		word = word.toLowerCase();
		int length = word.length();
		char delete;
		
		for(int i=0; i<length; i++) {
			delete = word.charAt(i);
			TrieNode childNode = currentNode.children[delete-'a'];
			if(childNode != null) {
				currentNode = currentNode.children[delete-'a'];
			}
			if(i==length-1) {
				if(currentNode.isEndOfWord)
					currentNode.isEndOfWord = false;
				
				if(isFree(currentNode)) {
					currentNode = null;
					delete(word.substring(0, length-1));
				}
			}
		}
	}
	
	public boolean isFree(TrieNode node) {
		for(int i=0; i<CHARACTER_MAX; i++) {
			if(node.children[i] != null)
				return false;
		}
		return true;
	}
	
	public ArrayList<String> autoSuggest(String word) {
		
		ArrayList<String> idxList = new ArrayList<String>();
		TrieNode currentNode = root;
		word = word.toLowerCase();
		int length = word.length();
		char search;
		
		for(int i=0; i<length; i++) {
			search = word.charAt(i);
			if(currentNode.children[search-'a'] != null) {
				currentNode = currentNode.children[search-'a'];
			} else {
				break;
			}
		}
		
		if(currentNode.isEndOfWord) {
			idxList.add(word);
			--maxSuggestions;
		}
		
		idxList.addAll(autoSuggest(currentNode,word));
		
		return idxList;
	}
	
	public ArrayList<String> autoSuggest(TrieNode currentNode, String word){
		
		ArrayList<String> idxList = new ArrayList<String>();
		if(maxSuggestions>0) {
			
			for(int i=0; i<CHARACTER_MAX; i++) {
				if(maxSuggestions>0 && currentNode.children[i] != null) {
					currentNode = currentNode.children[i];
					char letter = (char) ('a'+i);
					word = word + letter;
					if(currentNode.isEndOfWord) {
						idxList.add(word);
						--maxSuggestions;
					}
					idxList.addAll(autoSuggest(currentNode, word));
				} else if(maxSuggestions<=0) {
					break;
				}
			}
		}
		
		return idxList;
	}
}