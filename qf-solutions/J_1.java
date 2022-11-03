import java.util.Scanner;

public class JustTheLastDigit {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] trails = new int[n][n];
		for (int i = 0; i < n; i++) {
			String s = sc.next();
			for (int j = 0; j < s.length(); j++) {
				trails[i][j] = (s.charAt(j) - '0') % 2;
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (trails[i][j] > 0) {
					for (int k = j + 1; k < n; k++) {
						trails[i][k] ^= trails[j][k];
					}
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(trails[i][j]);
			}
			System.out.println();
		}
	}
}