import java.io.*;
import java.util.*;
class Scanner {
	char[] buffer = new char[1024];
	StringBuilder token = new StringBuilder();
	int index = -1;
	int count = 1;
	Reader reader;
	int read;
	int wordIndex = 0;
	StringBuilder wordToken = new StringBuilder();
	StringBuilder word = new StringBuilder();
	Scanner(InputStream inputStream) {
		this.reader = new InputStreamReader(inputStream);
		try {
			read = reader.read(buffer);
		} catch (IOException e) {
            System.out.println("Input error: " + e.getMessage());
        }
	}
	Scanner(String file) {
		try {
			this.reader = new InputStreamReader(
					new FileInputStream(file),
					"UTF8"
				);;
		} catch (IOException e) {
            System.out.println("Input file reading error: " + e.getMessage());
		}
			
		try {
			read = reader.read(buffer);
		} catch (IOException e) {
            System.out.println("Input file reading error: " + e.getMessage());
        }
	}
	String next() {
		token.setLength(0);
		while (this.hasNext()) {
			for (int i = index + 1; i < buffer.length; i++) {
				if (buffer[i] == '\n' || Character.isWhitespace(buffer[i]) || buffer[i] == '\0') {
					if (buffer[i] == '\n') {
						count++;
					}
					if (token.length() >= 1) {
						index = i;
						return new String(token);
					}				
				} else {
					token.append(buffer[i]);
				}
			}
			Arrays.fill(buffer, '\0');
			index = -1;
			try {
				read = reader.read(buffer);
			} catch (IOException e) {
				System.out.println("Input file reading error: " + e.getMessage());
			}
		}
		return new String(token);
	}
	String nextWord() {
		wordToken.setLength(0);
		while (this.hasNext()) {
			while (wordIndex < word.length()) {
				if (Character.isLetter(word.charAt(wordIndex)) || word.charAt(wordIndex) == '\'' 
				|| Character.getType(word.charAt(wordIndex)) == Character.DASH_PUNCTUATION) {
					wordToken.append(word.charAt(wordIndex));
				} else {
					if (wordToken.length() > 0) {					
						wordIndex++;
						return new String(wordToken);
					}
				}
				wordIndex++;
			}
			if (wordToken.length() > 0) {
				return new String(wordToken);
			}
			wordIndex = 0;
			word = new StringBuilder(this.next());
		}
		return new String(wordToken);
	}
	boolean hasNext() {
		return (read >= 0);
	}
	void close() {
		try {
			reader.close();
		} catch (IOException e) {
            System.out.println("Scanner close error" + e.getMessage());
		}
	}
}
	
	
	