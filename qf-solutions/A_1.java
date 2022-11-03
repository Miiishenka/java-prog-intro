import java.util.Scanner;
public class AccurateMovement {
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       float a = sc.nextInt();
       float b = sc.nextInt();
       float n = sc.nextInt();
       System.out.println((int)(2 * Math.ceil((n - b) / (b - a)) + 1));
    }
}