import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordStatWordsPrefix {
    public static void main(String[] args) {
        TreeMap<String, Integer> words = new TreeMap<>();
        try {
            try (Reader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(args[0]),
                StandardCharsets.UTF_8
            ))) {
                int read = reader.read();
                char[] word = new char[0];
                int k = 0;
                while (read >= 0) {
                    char symbol = (char) read;
                    symbol = Character.toLowerCase(symbol);
                    if (Character.isLetter(symbol) || symbol == '\''
                        || Character.getType(symbol) == Character.DASH_PUNCTUATION) {
                        if (k < 3) {
                            word = Arrays.copyOf(word, k + 1);
                            word[k] = symbol;
                            k++;
                        }
                    } else {
                        if (word.length > 0) {
                            String str = new String(word);
                            if (words.containsKey(str)) {
                                words.put(str, words.get(str) + 1); // :NOTE: getOrDefault
                            } else {
                                words.put(str, 1);
                            }
                            word = Arrays.copyOf(word, 0);
                            k = 0;
                        }
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
                    writer.write(key + " " + words.get(key));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Input file writing error: " + e.getMessage());
        }

    }
}