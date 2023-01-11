package UWF.Project2;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

public class HashTable {
	private HashSet<String> dictionary = null;
	public HashTable() throws Exception {
		File fileObj = new File("dictionary.txt");
		Scanner scanner = new Scanner(fileObj);
		dictionary = new HashSet<String>();
		while (scanner.hasNextLine()) {
			dictionary.add(scanner.nextLine());
		}
	}
	
	public boolean contains(String str) {
		if (dictionary.contains(str) || dictionary.contains(unCapitalized(str))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String suggestions(String str) {
		String returnString = "";
		returnString += wrongCapitalization(str);
		returnString += letterMissing(str);
		returnString += letterAdded(str);
		returnString += lettersReversed(str);
		if (returnString.equals("")) {
			returnString = "No suggestions found";
		}
		return returnString;
	}
	
	public String wrongCapitalization(String str) {
		String returnStr = "";
		if (dictionary.contains(capitalized(str))) {
			returnStr += "\n" + capitalized(str);
		}
		if (dictionary.contains(lowerCase(str))) {
			returnStr += "\n" + lowerCase(str);
		}
		if (dictionary.contains(upperCase(str))) {
			returnStr += "\n" + upperCase(str);
		}
		return returnStr;
	}
	
	public String letterMissing(String str) {
		String returnStr = "";
		char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		for (int i = 0; i < alphabet.length; ++i) {
			for (int j = 0; j <= str.length(); ++j) {
				StringBuffer strBuff = new StringBuffer(str);
				strBuff.insert(j, alphabet[i]);
				String tempStr = strBuff.toString();
				if (dictionary.contains(capitalized(tempStr))) {
					returnStr += "\n" + capitalized(tempStr);
				}
				if (dictionary.contains(lowerCase(tempStr))) {
					returnStr += "\n" + lowerCase(tempStr);
				}
				if (dictionary.contains(upperCase(tempStr))) {
					returnStr += "\n" + upperCase(tempStr);
				}
			}
		}
		return returnStr;
	}
	
	public String letterAdded(String str) {
		String returnStr = "";
		for (int i = 0; i < str.length(); ++i) {
			StringBuilder sb = new StringBuilder(str);
			sb.deleteCharAt(i);
			if (dictionary.contains(capitalized(sb.toString()))) {
				returnStr += "\n" + capitalized(sb.toString());
			}
			if (dictionary.contains(lowerCase(sb.toString()))) {
				returnStr += "\n" + lowerCase(sb.toString());
			}
			if (dictionary.contains(upperCase(sb.toString()))) {
				returnStr += "\n" + upperCase(sb.toString());
			}
		}
		return returnStr;
	}
	
	public String lettersReversed(String str) {
		String returnStr = "";
		for (int i = 1; i < str.length(); ++i) {
			int j = i - 1;
			StringBuilder sb = new StringBuilder(str);
	        char l = sb.charAt(j);
	        char r = sb.charAt(i);
	        sb.setCharAt(j, r);
	        sb.setCharAt(i, l);
			if (dictionary.contains(capitalized(sb.toString()))) {
				returnStr += "\n" + capitalized(sb.toString());
			}
			if (dictionary.contains(lowerCase(sb.toString()))) {
				returnStr += "\n" + lowerCase(sb.toString());
			}
			if (dictionary.contains(upperCase(sb.toString()))) {
				returnStr += "\n" + upperCase(sb.toString());
			}
		}
		return returnStr;
	}
	
	public String unCapitalized(String str) {
		StringBuilder sb = new StringBuilder(str);
		char firstChar = sb.charAt(0);
		firstChar = Character.toLowerCase(firstChar);
		sb.setCharAt(0, firstChar);
		return sb.toString();
	}
	
	public String capitalized(String str) {
		str = str.toLowerCase();
		char c = str.charAt(0);
		c = Character.toUpperCase(c);
		str = str.substring(1);
		return c + str;
	}
	
	public String lowerCase(String str) {
		str = str.toLowerCase();
		return str;
	}
	
	public String upperCase(String str) {
		str = str.toUpperCase();
		return str;
	}
}
