import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        String result = Main.calc(inputLine);
        Main.printResult(result);
    }

    public static void printResult(String result){
        System.out.println(result);
    }

    public static String calc(String input) throws Exception {
        String result = null;
        Main calculator = new Main(); //������� ������ �����������
        String[] strings = calculator.splitingStringIntoSubstring(input); //��� ������ ������, ��������� ������ �� ��������� � ��� ���� ��������� ���������� �� ������������ ������� � ��������� ���������, ���� �� �������������, � ����������
        result = calculator.expression(strings);

        if (result != null)
            return result;
        else
            throw new Exception();
    }

    public String expression(String[] strings) throws Exception {
        String firstNumb = strings[0]; //��������� 1-�� ������� � ����������, ������� ���� ������������
        String operator = strings[1]; //��������� �������� � ����������, ������� ���� ������������
        String secondNumb = strings[2]; //��������� 2-�� ������� � ����������, ������� ���� ������������
        String decision = null; //����������� ���������� � ������
        try {
            decision = solvingAnExampleOfArabicNumerals(firstNumb, operator, secondNumb);
        } catch (Exception e) {
            decision = solvingAnExampleOfRomanNumerals(firstNumb, operator, secondNumb);
        }
        if (decision == null)
            throw new Exception(); //���� �� ���������� ��������� �������� � ���������� � ��� ���������� null, �� ����������� ���������� � ��������� ���������
        return decision; //������� ��� ����� � �������
    }

    public String solvingAnExampleOfRomanNumerals(String firstNum, String operator, String secondNum) throws Exception {
        int numb1 = RomanNumerals.valueOf(firstNum).getValue(); //�������� �������� �� ���������� ���� ������ � enum
        int numb2 = RomanNumerals.valueOf(secondNum).getValue(); //�������� �������� �� ���������� ���� ������ � enum
        int result = Integer.parseInt(MathOperation.operation(numb1, operator, numb2)); //������� ��������� ��� ������ ������
        return Converting.convertingRomanNumbers(result); //������������ �������� ����� � �������
    }

    public String solvingAnExampleOfArabicNumerals(String firstNum, String operator, String secondNum) {
        int numbOne = Integer.parseInt(firstNum);
        int numbTwo = Integer.parseInt(secondNum);
        return MathOperation.operation(numbOne, operator, numbTwo);
    }

    public String[] splitingStringIntoSubstring(String value) throws Exception {
        String[] strings = value.split(" "); //��������� ������ �� ���������
        try {
            checkArrayStringsForArabian(strings); //��� ������ ������ ��������� �������� �� �������� ��������� ������� + ���������� �������
        } catch (Exception e) {
            checkArrayStringsForRoman(strings);//���� ����� �������� �� ���������, ��������� ��� ��� �������� �������� + ���������� �������
        }
        return strings; //��������� ���������� ���������, ���� �� ������������� ��� ��� ���� ��������
    }

    public void checkArrayStringsForRoman(String[] strings) throws Exception {
        if (strings.length != 3)
            throw new Exception(); //���� ������ ������� �� ����� 3 (�� ������� � ��� ������ ���� 2 �������� � 1 ��������), �� ���������� ���������� � ������������ � �����, ������� ������ ���� �����
        RomanNumerals[] romanNumerals = RomanNumerals.values(); //��������� ��� ���������� enum � ������
        ArrayList<RomanNumerals> arrayList = new ArrayList<>(); //����������� � ������ ���������� �����
        Collections.addAll(arrayList, romanNumerals); //��������� � ���� ������, ��� ����������� �������� ������� ������ �����
        boolean check = false; //����������� ����������
        try {
            check = arrayList.contains(RomanNumerals.valueOf(strings[0])) &&
                    arrayList.contains(RomanNumerals.valueOf(strings[2])); //���� 1-�� �������� � 2-�� �������� ���������� � �����, �� ���������� true
        } catch (Exception e) {
            throw new Exception(); //���� �� ����������, ���������� ���������� � �������� check �������� false
        }
        //if (!check) throw new Exception(); //���� �������� false, �� ���������� ����������
    }

    public void checkArrayStringsForArabian(String[] strings) throws Exception {
        if (strings.length != 3)
            throw new Exception(); //���� ������ ������� �� ����� 3 (�� ������� � ��� ������ ���� 2 �������� � 1 ��������), �� ���������� ���������� � ������������ � �����, ������� ������ ���� �����
        int firstNumb = 0; //����������� ���������� � ������
        int secondNumb = 0; //����������� ���������� � ������
        try {
            firstNumb = Integer.parseInt(strings[0]); //������� ������������� ��������� � �����
            secondNumb = Integer.parseInt(strings[2]);//������� ������������� ��������� � �����
        } catch (Exception e) {
            throw new Exception(); //���� �� �������������, ���������� ���������� � ������������ � ������, ������� ������ ���� �����
        }
        if (firstNumb > 10 || firstNumb < 1 || secondNumb > 10 || secondNumb < 1)
            throw new Exception(); //���� � ������ ������ �������� ������ �������, � ���������� ��������� ���� ������� �� �������, ���� �� ��������, �� ���������� ���������� � ������������ � ������, ������� ������ ���� �����
    }
}

class MathOperation {
    public static String operation(int firstNumb, String operator, int secondNumb) {
        return switch (operator) {
            case "+" -> String.valueOf(firstNumb + secondNumb);
            case "-" -> String.valueOf(firstNumb - secondNumb);
            case "/" -> String.valueOf(firstNumb / secondNumb);
            case "*" -> String.valueOf(firstNumb * secondNumb);
            default -> null;
        };
    }
}

class Converting {
    public static String convertingRomanNumbers(int number) throws Exception {
        String romNumber = Converting.numberConversion(number);
        if (number < 1) {
            throw new Exception();
        } else if (number > 10 && number < 20) {
            return "X" + romNumber;
        } else if (number >= 20 && number < 30) {
            if (romNumber == null)
                return "XX";
            else
                return "XX" + romNumber;
        } else if (number >= 30 && number < 40) {
            if (romNumber == null)
                return "XXX";
            else
                return "XXX" + romNumber;
        } else if (number >= 40 && number < 50) {
            if (romNumber == null)
                return "XL";
            else
                return "XL" + romNumber;
        } else if (number >= 50 && number < 60) {
            if (romNumber == null)
                return "L";
            else
                return "L" + romNumber;
        } else if (number >= 60 && number < 70) {
            if (romNumber == null)
                return "LX";
            else
                return "LX" + romNumber;
        } else if (number >= 70 && number < 80) {
            if (romNumber == null)
                return "LXX";
            else
                return "LXX" + romNumber;
        } else if (number >= 80 && number < 90) {
            if (romNumber == null)
                return "LXXX";
            else
                return "LXXX" + romNumber;
        } else if (number >= 90 && number < 100) {
            if (romNumber == null)
                return "XC";
            else
                return "XC" + romNumber;
        } else if (number == 100) {
            return "C";
        } else {
            return romNumber;
        }
    }

    public static String numberConversion(int number) {
        if (number == 10)
            return "X";
        char[] chars = String.valueOf(number).toCharArray();
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

    RomanNumerals(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}