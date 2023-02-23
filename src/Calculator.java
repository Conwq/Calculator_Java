import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Calculator {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Calculator calculator = new Calculator(); //������� ������ �����������
            String value = scanner.nextLine(); //��������� ��������� ������ � ����������
            String[] strings = calculator.enteringValue(value); //��� ������ ������, ��������� ������ �� ��������� � ��� ���� ��������� ���������� �� ������������ ������� � ��������� ���������, ���� �� �������������, � ����������
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

    public String withString(String firstNum, String operator, String secondNum) {

        int numb1 = numberConversion(firstNum);
        int numb2 = numberConversion(secondNum);
        return String.valueOf(numb1 + numb2);
    }
    public int numberConversion(String number){
        switch (number){
            case "I": return 1;
            case "II": return 2;
            case "III": return 3;
            case "IV": return 4;
            case "V": return 5;
            case "VI": return 6;
            case "VII": return 7;
            case "VIII": return 8;
            case "IX": return 9;
            case "X": return 10;
            default: return 0;
        }
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

}