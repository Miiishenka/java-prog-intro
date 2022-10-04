import java.util.Scanner;
import java.util.Arrays;
public class ReverseSum {
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
		int columnLen = m;
		int[] lineSums = new int[columnLen];
		int[] columnSums = new int[maxLine];
		for (int k = 0; k < columnLen; k++) {
			for (m = 0; m < lineLens[k]; m++) {
				lineSums[k] += matr[k][m];
				columnSums[m] += matr[k][m];
			}
		}
		for (int k = 0; k < columnLen; k++) {
			for (m = 0; m < lineLens[k]; m++) {
				System.out.print(lineSums[k] + columnSums[m] - matr[k][m] + " ");
			}
			System.out.println();
		}			
	}
}	