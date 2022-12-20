import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reverse {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		List<List<Integer>> matrix = new ArrayList<>();
		while (true) {
			List<Integer> list = scanner.nextLine();
			if (list == null) {
				break;
			}
			Collections.reverse(list);
			matrix.add(list);
		}
		Collections.reverse(matrix);
		for (List<Integer> list : matrix) {
			for (Integer x : list) {
				System.out.print(x + " ");
			}
			System.out.println();
		}
	}
}