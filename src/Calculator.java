import java.util.*;

public class Calculator {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Calculator calculator = new Calculator(); //������� ������ �����������
            String value = scanner.nextLine(); //��������� ��������� ������ � ����������
            String[] strings = calculator.splitingStringIntoSubstring(value); //��� ������ ������, ��������� ������ �� ��������� � ��� ���� ��������� ���������� �� ������������ ������� � ��������� ���������, ���� �� �������������, � ����������
            calculator.expression(strings); //���� ���������� ������
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void expression(String[] strings) throws Exception{
        String firstNumb = strings[0]; //��������� 1-�� ������� � ����������, ������� ���� ������������
        String operator = strings[1]; //��������� �������� � ����������, ������� ���� ������������
        String secondNumb = strings[2]; //��������� 2-�� ������� � ����������, ������� ���� ������������
        String answer = null; //����������� ���������� � ������
        try {
            answer = withInt(firstNumb, operator, secondNumb); //�������������, ����� �� ������� ���� ���� � ���� ��� ���������� ��� � ���� ����� ��� ������ ����������
        }
        catch (Exception e){
            answer = withString(firstNumb, operator, secondNumb);
        }
        if (answer == null) throw new Exception(); //���� �� ���������� ��������� �������� � ���������� � ��� ���������� null, �� ����������� ���������� � ��������� ���������
        System.out.println(answer); //������� ��� ����� � �������
    }

    public String withString(String firstNum, String operator, String secondNum) throws Exception{

        int numb1 = RomanNumerals.valueOf(firstNum).getValue();
        int numb2 = RomanNumerals.valueOf(secondNum).getValue();
        int result = Integer.parseInt(MathOperation.operation(numb1, operator,numb2));
        return convertingRomanNumbers(result);
    }

    public String convertingRomanNumbers(int number)throws Exception{
        String romNumber = numberConversion(number);

        if (number < 0)
            throw new Exception();

        else if (number > 10 && number < 20)
            return "X" + romNumber;

        else if (number >= 20 && number < 30){
            if (romNumber == null)
                return "XX";
            else
                return "XX" + romNumber;
        }

        else if (number >= 30 && number < 40) {
            if (romNumber == null)
                return "XXX";
            else
                return "XXX" + romNumber;
        }

        else if (number >= 40 && number < 50) {
            if (romNumber == null)
                return "XL";
            else
                return "XL" + romNumber;
        }
        else if (number >= 50 && number < 60) {
            if (romNumber == null)
                return "L";
            else
                return "L" + romNumber;
        }
        else if (number >= 60 && number < 70) {
            if (romNumber == null)
                return "LX";
            else
                return "LX" + romNumber;
        }
        else if (number >= 70 && number < 80) {
            if (romNumber == null)
                return "LXX";
            else
                return "LXX" + romNumber;
        }
        else if (number >= 80 && number < 90) {
            if (romNumber == null)
                return "LXXX";
            else
                return "LXXX" + romNumber;
        }
        else if (number >= 90 && number < 100) {
            if (romNumber == null)
                return "XC";
            else
                return "XC" + romNumber;
        }
        else if (number == 100)
            return "C";
        else
            return romNumber;
    }
    public String numberConversion(int number){
        if (number == 10)
            return "X";
        char [] chars = String.valueOf(number).toCharArray();
        int x = Integer.parseInt(String.valueOf(chars[chars.length - 1]));
        return switch (x) {
            case 1 -> "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            case 6 -> "VI";
            case 7 -> "VII";
            case 8 -> "VIII";
            case 9 -> "IX";
            default -> null;
        };
    }

    public String withInt(String firstNum, String operator, String secondNum) {
        int numbOne = Integer.parseInt(firstNum);
        int numbTwo = Integer.parseInt(secondNum);
        return MathOperation.operation(numbOne, operator, numbTwo);
//        switch (operator) {
//            case "+":
//                return String.valueOf(numbOne + numbTwo);
//            case "-":
//                return String.valueOf(numbOne - numbTwo);
//            case "/":
//                return String.valueOf(numbOne / numbTwo);
//            case "*":
//                return String.valueOf(numbOne * numbTwo);
//            default:
//                return null;
//        }
    }

    public String[] splitingStringIntoSubstring(String value) throws Exception{
        String[] strings = value.split(" "); //��������� ������ �� ���������
        try {
            checkArrayStringsForArabian(strings); //��� ������ ������ ��������� �������� �� �������� ��������� ������� + ���������� �������
        }
        catch (Exception e){
            checkArrayStringsForRoman(strings);//���� ����� �������� �� ���������, ��������� ��� ��� �������� �������� + ���������� �������
        }
        return strings; //��������� ���������� ���������, ���� �� ������������� ��� ��� ���� ��������
    }

    public void checkArrayStringsForRoman(String [] strings) throws Exception{
        if (strings.length != 3) throw new Exception(); //���� ������ ������� �� ����� 3 (�� ������� � ��� ������ ���� 2 �������� � 1 ��������), �� ���������� ���������� � ������������ � �����, ������� ������ ���� �����
        RomanNumerals [] romanNumerals = RomanNumerals.values(); //��������� ��� ���������� enum � ������
        ArrayList<RomanNumerals> arrayList = new ArrayList<>(); //����������� � ������ ���������� �����
        Collections.addAll(arrayList, romanNumerals); //��������� � ���� ������, ��� ����������� �������� ������� ������ �����
        boolean check = false; //����������� ����������
        try {
             check = arrayList.contains(RomanNumerals.valueOf(strings[0])) &&
                     arrayList.contains(RomanNumerals.valueOf(strings[2])); //���� 1-�� �������� � 2-�� �������� ���������� � �����, �� ���������� true
        }
        catch (Exception e){
            throw new Exception(); //���� �� ����������, ���������� ���������� � �������� check �������� false
        }
        //if (!check) throw new Exception(); //���� �������� false, �� ���������� ����������
    }

    public void checkArrayStringsForArabian(String[] strings) throws Exception{
        if (strings.length != 3) throw new Exception(); //���� ������ ������� �� ����� 3 (�� ������� � ��� ������ ���� 2 �������� � 1 ��������), �� ���������� ���������� � ������������ � �����, ������� ������ ���� �����
        int firstNumb = 0; //����������� ���������� � ������
        int secondNumb = 0; //����������� ���������� � ������
        try {
            firstNumb = Integer.parseInt(strings[0]); //������� ������������� ��������� � �����
            secondNumb = Integer.parseInt(strings[2]);//������� ������������� ��������� � �����
        }
        catch (Exception e){
            throw new Exception(); //���� �� �������������, ���������� ���������� � ������������ � ������, ������� ������ ���� �����
        }
        if (firstNumb > 10 || secondNumb > 10) throw new Exception(); //���� � ������ ������ �������� ������ �������, � ���������� ��������� ���� ������� �� �������, ���� �� ��������, �� ���������� ���������� � ������������ � ������, ������� ������ ���� �����
    }
}

class MathOperation{

    public static String operation(int firstNumb, String operator, int secondNumb){
        return switch (operator) {
            case "+" -> String.valueOf(firstNumb + secondNumb);
            case "-" -> String.valueOf(firstNumb - secondNumb);
            case "/" -> String.valueOf(firstNumb / secondNumb);
            case "*" -> String.valueOf(firstNumb * secondNumb);
            default -> null;
        };
    }
}

enum RomanNumerals {
    I(1),
    II(2),
    III(3),
    IV(4),
    V(5),
    VI(6),
    VII(7),
    VIII(8),
    IX(9),
    X(10);

    private final int value;
    RomanNumerals(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}