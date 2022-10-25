import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class WsppLastL {
    public static void main(String[] args) {
        Map<String, IntList> words = new LinkedHashMap<>();
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
				if (words.containsKey(str)) { // NOTE обращение
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

/**
 * Testing LastL
 *     Running test 13: java WsppLastL "test13.in" "test13.out"
 * Exception in thread "main" java.lang.AssertionError: Line 8:
 *      expected `question 1 10`,
 *        actual `question 1 1`
 *         at base.Asserts.error(Asserts.java:75)
 *         at base.Asserts.assertTrue(Asserts.java:41)
 *         at base.Asserts.assertEquals(Asserts.java:20)
 *         at base.Runner.lambda$testEquals$0(Runner.java:36)
 *         at base.TestCounter.lambda$test$0(TestCounter.java:58)
 *         at base.TestCounter.lambda$testV$2(TestCounter.java:71)
 *         at base.Log.silentScope(Log.java:40)
 *         at base.TestCounter.testV(TestCounter.java:70)
 *         at base.TestCounter.test(TestCounter.java:57)
 *         at base.Runner.testEquals(Runner.java:30)
 *         at base.MainChecker.testEquals(MainChecker.java:25)
 *         at wordStat.WordStatChecker.test(WordStatChecker.java:66)
 *         at wordStat.WordStatChecker.test(WordStatChecker.java:49)
 *         at wspp.WsppTester.lambda$variant$9(WsppTester.java:76)
 *         at wordStat.WordStatChecker.test(WordStatChecker.java:44)
 *         at wspp.WsppTester.lambda$variant$10(WsppTester.java:23)
 *         at base.Selector.lambda$test$2(Selector.java:79)
 *         at base.Log.lambda$action$0(Log.java:18)
 *         at base.Log.silentScope(Log.java:40)
 *         at base.Log.scope(Log.java:31)
 *         at base.Log.scope(Log.java:24)
 *         at base.Selector.lambda$test$3(Selector.java:79)
 *         at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
 *         at base.Selector.test(Selector.java:79)
 *         at base.Selector.main(Selector.java:51)
 *         at wspp.FullWsppTest.main(FullWsppTest.java:57)
 */