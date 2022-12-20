import java.io.*;
import java.util.*;
class Scanner {
	char[] buffer = new char[1024];
	StringBuilder token = new StringBuilder();
	int index = -1;
	int count = 1;
	BufferedReader reader;
	int read;
	int wordIndex = 0;
	StringBuilder wordToken = new StringBuilder();
	StringBuilder word = new StringBuilder();
	Scanner(InputStream inputStream) {
		this.reader = new BufferedReader(new InputStreamReader(inputStream));
	}
	Scanner(String file) {
		try {
			this.reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file),
					"UTF8"
			));;
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
				if (Character.isWhitespace(buffer[i]) || buffer[i] == '\0') {
					if (token.length() >= 1) {
						index = i;
						return new String(token);
					}
				} else if (buffer[i] == '\n' || buffer[i] == '\u000C' || buffer[i] == '\u0085' ||
						buffer[i] == '\u2029' || buffer[i] == '\u000B' || buffer[i] == '\u2028') {
					System.out.println("n");
					count++;
					if (token.length() >= 1) {
						index = i;
						return new String(token);
					}
				} else if (buffer[i] == '\r') {
					System.out.println("r");
					count++;
					if (i == buffer.length - 1) {
						try {
							read = reader.read(buffer);
							i = 0;
						} catch (IOException e) {
							System.out.println("Input file reading error: " + e.getMessage());
						}
					}
					if (buffer[i + 1] == '\n') {
						i++;
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
	List<Integer> nextLine() throws IOException {
		try {
			List<Integer> list = new ArrayList<>();
			String s = reader.readLine();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < s.length(); i++) {
				if (Character.isWhitespace(s.charAt(i))) {
					if (sb.length() > 0) {
						if (sb.charAt(sb.length() - 1) == 'o' || sb.charAt(sb.length() - 1) == 'O') {
							list.add(Integer.parseUnsignedInt(sb.substring(0, sb.length() - 1), 8));
						} else {
							list.add(Integer.parseInt(sb.toString()));
						}
						sb.setLength(0);
					}
				} else {
					sb.append(s.charAt(i));
					if (i == s.length() - 1) {
						if (sb.length() > 0) {
							if (sb.charAt(sb.length() - 1) == 'o' || sb.charAt(sb.length() - 1) == 'O') {
								list.add(Integer.parseUnsignedInt(sb.substring(0, sb.length() - 1), 8));
							} else {
								list.add(Integer.parseInt(sb.toString()));
							}
							sb.setLength(0);
						}
					}
				}
			}
			return list;
		} catch (NullPointerException e) {
			return null;
		}
	}
}
	
	
	