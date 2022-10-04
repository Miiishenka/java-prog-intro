/* import java.util.Scanner;
import java.util.ArrayList;
public class Reverse {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
		while (sc.hasNextLine()) {
			ArrayList<Integer> num = new ArrayList<>();
			String str = sc.nextLine();
			Scanner s = new Scanner(str);
			if (!s.hasNextInt()) {
				num.add(null);
			}
			while (s.hasNextInt()) {
				num.add(s.nextInt());
			}
			arr.add(num);
		}
		for (int i = arr.size() - 1; i >= 0; i--) {
			for (int j = arr.get(i).size() - 1; j >= 0; j--) {
				if (arr.get(i).get(j) == null) {
					System.out.println();
				} else {
					System.out.print(arr.get(i).get(j) + " ");
					if (j == 0) {
						System.out.println();
					}
				}
			}
		}
	}
}
*/
import java.util.Scanner;
import java.util.ArrayList;
public class Reverse {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<StringBuilder> arr = new ArrayList<>();
		while (sc.hasNextLine()) {
			String s = " " + sc.nextLine();
			int begin = 0;
			int end = 0;
			boolean started = false;
			StringBuilder snew = new StringBuilder();
			for (int i = s.length() - 1; i >= 0; i--) {
				if (!(Character.isWhitespace(s.charAt(i)))) {
                    if (!started) {
						started = true;
						begin = i;
						end = i;
					} else {
						begin --;
					}
				} else {
					if (started) {
						snew.append(s.substring(begin, end + 1) + " ");
					started = false;
					}
				}
			}
			arr.add(snew);
		}
		for (int i = arr.size() - 1; i >= 0; i--) {
			System.out.println(arr.get(i));
		}
	}
}