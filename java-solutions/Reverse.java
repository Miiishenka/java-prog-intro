import java.util.Arrays;
import java.util.Scanner;
public class Reverse {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] matr = new int[1][];
		int[] lineLens = new int[1];
		int m = 0;
		int maxLine = 0;
		while (sc.hasNextLine()) {
			String str = sc.nextLine();
			Scanner s = new Scanner(str);
			int k = 0;
			int[] arr = new int[1];
			while (s.hasNextInt()) {
				if (k == arr.length) {
					arr = Arrays.copyOf(arr,k*3/2+1);
				}
				arr[k] = s.nextInt();
				k++;
			}				
			if (m == matr.length) {
				matr = Arrays.copyOf(matr,m*3/2+1);
				lineLens = Arrays.copyOf(lineLens,m*3/2+1);
			}
			matr[m] = arr;
			lineLens[m] = k;			
			m++;
			if (k > maxLine) {
				maxLine = k;
			}
		}
		for (int i = m - 1; i >= 0; i--) {
			for (int j = lineLens[i] - 1; j > -1; j--) {
				System.out.print(matr[i][j] + " ");
			}
			System.out.println();
		}
	}
}