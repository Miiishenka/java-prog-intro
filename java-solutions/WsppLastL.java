import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class WsppLastL {
    public static void main(String[] args) {
        Map<String, IntList> words = new LinkedHashMap<String, IntList>();
		Scanner scanner = new Scanner(args[0]); 
		int position = 1;
		int countStr = 1;
        while (scanner.read >= 0) {   
			String str = scanner.nextWord().toLowerCase();
			System.out.println(str);
			System.out.println(scanner.count);
			if (countStr != scanner.count) {
				countStr = scanner.count;
				position = 1;
			}
			if (str.length() > 0) {
				if (words.containsKey(str)) {
					words.get(str).set(1, words.get(str).get(1) + 1);
					if (countStr != words.get(str).get(0)) {									
						words.get(str).set(0, countStr);
						words.get(str).add(position);
					} else {
						words.get(str).set(words.get(str).size - 1, position);
					}
				} else {
					words.put(str, new IntList(new int[]{countStr, 1, position}));
				}    
				position++;	
			}				
        } 
		scanner.close();	
        try {
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(args[1]),
                StandardCharsets.UTF_8
            ))) {
                for (String key : words.keySet()) {
                    writer.write(key);
					for (int i = 1; i < words.get(key).size; i++) {
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