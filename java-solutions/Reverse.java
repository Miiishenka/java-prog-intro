import java.util.Arrays;
public class Reverse {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[][] matrix = new String[1][];
		int[] lineLens = new int[1];
		String[] arr = new String[1];
		int k = 0;
		int line = 0;
		int countStr = 1;
		while (scanner.hasNext()) {
			String number = scanner.next();
			while (countStr != scanner.count) {
				if (line == matrix.length) {
					matrix = Arrays.copyOf(matrix, line * 2);
					lineLens = Arrays.copyOf(lineLens, line * 2);
				}
				matrix[line] = arr;
				lineLens[line] = k;
				line++;
				arr = Arrays.copyOf(arr, 0);
				k = 0;
				countStr++;
			}
			if (k == arr.length) {
				arr = Arrays.copyOf(arr, k * 2 + 1);
			}
			arr[k++] = number;
		}
		scanner.close();
		for (int i = line - 1; i >= 0; i--) {
			for (int j = lineLens[i] - 1; j > -1; j--) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}