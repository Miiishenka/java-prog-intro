import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

class IntList {
	int[] list;
	IntList(int[] list) {
		this.list = new int[0];
	}
	void add(int x) {
		this.list = Arrays.copyOf(this.list, this.list.length + 1);
		this.list[this.list.length - 1] = x;
	}
	void set(int position, int x) {
		this.list[position] = x;
	}
	int get(int position) {
		return this.list[position];
	}
	int length() {
		return this.list.length;
	}
}

public class WsppLastL {
    public static void main(String[] args) {
        Map<String, IntList> words = new LinkedHashMap<String, IntList>();
		int count = 1;
        try {
            try (Reader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(args[0]),
                StandardCharsets.UTF_8
            ))) {
                int read = reader.read();
                char[] word = new char[0];
                int k = 0;
				int position = 1;
                while (read >= 0) {
                    char symbol = (char) read;
                    symbol = Character.toLowerCase(symbol);
                    if (Character.isLetter(symbol) || symbol == '\''
                        || Character.getType(symbol) == Character.DASH_PUNCTUATION) {
                        word = Arrays.copyOf(word, k + 1);
                        word[k] = symbol;
                        k++;
                    } else {
                        if (word.length > 0) {
                            String str = new String(word);
                            if (words.containsKey(str)) {
								if (count != words.get(str).get(0)) {									
									words.get(str).set(0, count);
									words.get(str).set(1, words.get(str).get(1) + 1);
									words.get(str).add(position);
								} else {
									words.get(str).set(1, words.get(str).get(1) + 1);
									words.get(str).set(words.get(str).length() - 1, position);
								}
                            } else {
                                words.put(str, new IntList(new int[0]));
								words.get(str).add(count);
								words.get(str).add(1);
								words.get(str).add(position);
                            }
							position++;
                            word = Arrays.copyOf(word, 0);
                            k = 0;
                        }
                    }
					if (symbol == '\n') {
						count++;
						position = 1;
					}
                    read = reader.read();
                }
            }


        } catch (IOException e) {
            System.out.println("Input file reading error: " + e.getMessage());
        }
        try {
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(args[1]),
                StandardCharsets.UTF_8
            ))) {
                for (String key : words.keySet()) {
                    writer.write(key);
					for (int i = 1; i < words.get(key).length(); i++) {
						writer.write(" " + words.get(key).get(i));
					}
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Input file writing error: " + e.getMessage());
        }
    }
}