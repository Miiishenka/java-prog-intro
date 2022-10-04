import java.io.*;
import java.util.*;

public class WordStatInput {
	public static void main(String[] args) {
		Map<String, Integer> words = new LinkedHashMap<String, Integer>();
		try {
			Reader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(args[0]),
				"UTF8"
			));
			try {
				int read = reader.read();
				char[] word = new char[0];
				int k = 0;
				while (read >= 0) {
					char symbol = (char) read;
					symbol = Character.toLowerCase(symbol);
					if (Character.isLetter(symbol) || symbol == '\'' || Character.getType(symbol) == Character.DASH_PUNCTUATION) {
						if (k == word.length) {
							word = Arrays.copyOf(word, k + 1);
						}
						word[k] = symbol;
						k++;
					} else {
						if (word.length > 0) {
							String str = new String(word);
							if (words.containsKey(str)) {
								words.put(str, words.get(str) + 1);
							} else {
								words.put(str, 1);
							}
							word = Arrays.copyOf(word, 0);
							k = 0;
						}
					}	
					read = reader.read();
				}
			} finally {
				reader.close();
			}
			
		
		} catch (IOException e) {
			System.out.println("Input file reading error: " + e.getMessage());
		}
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(args[1]),
				"UTF8"
			));
			try {
				for (String key: words.keySet()) {
					writer.write(key + " " + words.get(key));
					writer.newLine();
				}
			} finally {
				writer.close();
			}
		} catch (IOException e) {
			System.out.println("Input file writing error: " + e.getMessage());
		}			
			
	}
}