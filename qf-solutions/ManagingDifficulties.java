import java.util.*;
import java.util.Scanner;
public class ManagingDifficulties {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int q = 0; q < t; q++) {
			int n = sc.nextInt();
			int[] a = new int[n];
			int ans = 0;
			Map<Integer, Integer> c = new HashMap<>();;
			for (int j = 0; j < n; j++) {
				a[j] = sc.nextInt();
			}
			for (int j = n - 1; j > 0; j--) {
				for (int i = 0; i < j; i++) {
					ans += c.getOrDefault(2 * a[j] - a[i], 0);
				}
				c.computeIfPresent(a[j], (key, value) -> value + 1);
				c.putIfAbsent(a[j], 1);
			}
			System.out.println(ans);
	   }
    }
}