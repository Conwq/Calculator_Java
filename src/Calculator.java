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

        int numb1 = 0;
        switch (firstNum){
            case "I": numb1 = 1; break;
            case "II": numb1 = 2; break;
            case "III": numb1 = 3; break;
            case "IV": numb1 = 4; break;
            case "V": numb1 = 5; break;
            case "VI": numb1 = 6; break;
            case "VII": numb1 = 7; break;
            case "VIII": numb1 = 8; break;
            case "IX": numb1 = 9; break;
            case "X": numb1 = 10; break;
        }
        return String.valueOf(numb1);
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