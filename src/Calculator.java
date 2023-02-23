import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Calculator {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String value = scanner.nextLine();
        String [] strings = calculator.enteringValue(value);
        calculator.expression(strings);

    }

    public void expression(String [] strings){
        int firstNumb = 0;
        int secondNumb = 0;
        String firstRomNum = null;
        String secondRomNum = null;
        try {
            firstNumb = Integer.parseInt(strings[0]);
            secondNumb = Integer.parseInt(strings[2]);
        }
        catch (Exception e){
            firstNumb = 0;
            secondNumb = 0; //не обязательно
            firstRomNum = strings[0];
            secondRomNum = strings [2];
        }
        String operator = strings[1];
        int value = ppp(firstNumb, operator, secondNumb);
        String valueRom = ppp(firstRomNum, operator, secondRomNum);
    }





    public String ppp(String firstRomNum, String operator, String secondRomNum){
        return null;
    }
    public int ppp(int firstNumb, String operator, int secondNumb){
        if (firstNumb == 0 || secondNumb == 0){
            return 0;
        }
        switch(operator){
            case "+": return firstNumb + secondNumb;
            case "-": return firstNumb - secondNumb;
            case "/": return firstNumb / secondNumb;
            case "*": return firstNumb * secondNumb;
            default: return 0;
        }
    }
    public String [] enteringValue(String value){
        String [] strings = value.split(" ");
        checkArrayStrings(strings);
        return strings;
    }
    public void checkArrayStrings(String [] strings) {
        try {
            if (strings.length > 3) {
                throw new Exception();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}