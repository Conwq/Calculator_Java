import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Calculator {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Calculator calculator = new Calculator();
            String value = scanner.nextLine();
            String[] strings = calculator.enteringValue(value);
            calculator.expression(strings);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void expression(String[] strings) throws Exception{

        String firstNumb = strings[0];
        String operator = strings[1];
        String secondNumb = strings[2];
        String answer = null;
        try {
            answer = withInt(firstNumb, operator, secondNumb);
        }
        catch (Exception e){
            answer = withString(firstNumb, operator, secondNumb);
        }
        if (answer == null) throw new Exception();
        System.out.println(answer);
    }

    public String withString(String firstNum, String operator, String secondNum) {

        return  null;
    }


    public String withInt(String firstNum, String operator, String secondNum) {
        int numbOne = Integer.parseInt(firstNum);
        int numbTwo = Integer.parseInt(secondNum);
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





    public String[] enteringValue(String value) throws Exception{
        String[] strings = value.split(" ");
        try {
            checkArrayStringsForArabian(strings);
        }
        catch (Exception e){
            checkArrayStringsForRoman(strings);
        }
        return strings;
    }

    public void checkArrayStringsForRoman(String [] strings) throws Exception{
        if (strings.length != 3) throw new Exception();
        RomanNumerals [] romanNumerals = RomanNumerals.values();
        ArrayList<RomanNumerals> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, romanNumerals);
        boolean check = true;
        try {
             check = arrayList.contains(RomanNumerals.valueOf(strings[0])) &&
                     arrayList.contains(RomanNumerals.valueOf(strings[2]));
        }
        catch (Exception e){
            throw new Exception();
        }
        if (!check) throw new Exception();
    }






    public void checkArrayStringsForArabian(String[] strings) throws Exception{
        if (strings.length != 3) throw new Exception();
        int firstNumb = 0;
        int secondNumb = 0;
        try {
            firstNumb = Integer.parseInt(strings[0]);
            secondNumb = Integer.parseInt(strings[2]);
        }
        catch (Exception e){
            throw new Exception();
        }
        if (firstNumb > 10 || secondNumb > 10) throw new Exception();
    }
}