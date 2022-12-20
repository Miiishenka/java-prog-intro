package expression.parser;

import expression.exceptions.ExpressionParser;
import expression.exceptions.ParseException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String string = scanner.next();
            System.out.println(new ExpressionParser().parse(string).toMiniString());
        }
    }
}
