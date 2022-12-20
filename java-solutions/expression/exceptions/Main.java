package expression.exceptions;

import expression.Operand;

public class Main {
    public static void main(String[] args) {
        String s = "1000000*x*x*x*x*x/(x-1)";
        Operand expression = null;
        try {
            expression = (Operand) new ExpressionParser().parse(s);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("x\tf");
        for (int x = 0; x < 11; x++) {
            System.out.print(x + "\t");
            try {
                System.out.println(expression.evaluate(x));
            } catch (EvaluateException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
