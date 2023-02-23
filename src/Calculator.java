import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Calculator {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String value = scanner.nextLine();
        String[] strings = calculator.enteringValue(value);
        calculator.expression(strings);

    }

    public void expression(String[] strings) {

        String firstNumb = strings[0];
        String operator = strings[1];
        String secondNumb = strings[2];
        String answer = null;
        try {
            answer = withInt(firstNumb, operator, secondNumb);
        } catch (Exception e) {
            withString(firstNumb, operator, secondNumb);
        }
    }

    public String withInt(String firstNum, String operator, String secondNum) {
        int numbOne = Integer.parseInt(firstNum);
        int numbTwo = Integer.parseInt(firstNum);
        switch (operator) {
            case "+":
                return String.valueOf(numbOne + numbTwo);
            case "-":
                return String.valueOf(numbOne - numbTwo);
            case "/":
                return String.valueOf(numbOne / numbTwo);
            case "*":
                return String.valueOf(numbOne * numbTwo);
            default:
                return null;
        }

    }

    public void withString(String firstNum, String operator, String secondNum) {
        String answer = null;

    }


    public int ppp(int firstNumb, String operator, int secondNumb) {
        if (firstNumb == 0 || secondNumb == 0) {
            return 0;
        }
        switch (operator) {
            case "+":
                return firstNumb + secondNumb;
            case "-":
                return firstNumb - secondNumb;
            case "/":
                return firstNumb / secondNumb;
            case "*":
                return firstNumb * secondNumb;
            default:
                return 0;
        }
    }


    public String[] enteringValue(String value) {
        String[] strings = value.split(" ");
        checkArrayStrings(strings);
        return strings;
    }


    public void checkArrayStrings(String[] strings) {
        try {
            if (strings.length > 3) {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}