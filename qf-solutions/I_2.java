import java.util.Scanner;
public class  IdealPyramid {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] a = new int[n][3];
		int xl = 1000000000;
		int xr = -1000000000;
		int yl = 1000000000;
		int yr = -1000000000;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		for (int i = 0; i < n; i++) {
			xl = Math.min(xl, a[i][0] - a[i][2]);
			xr = Math.max(xr, a[i][0] + a[i][2]);
			yl = Math.min(yl, a[i][1] - a[i][2]);
			yr = Math.max(yr, a[i][1] + a[i][2]);
		}
		System.out.print((xl + xr) / 2 + " ");
		System.out.print((yl + yr) / 2 + " ");
		System.out.print((int) (Math.ceil((float) Math.max(xr - xl, yr - yl)) / 2));
    }
}