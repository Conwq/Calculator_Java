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
        Main calculator = new Main(); //создаем объект калькулятор
        String[] strings = calculator.splitingStringIntoSubstring(input); //при помощи метода, разделяем строку на подстроки и при этом проверяем содержимое на соответствие условию и сохраняем результат, если он соответствует, в переменную
        result = calculator.expression(strings);

        if (result != null)
            return result;
        else
            throw new Exception();
    }

    public String expression(String[] strings) throws Exception {
        String firstNumb = strings[0]; //сохраняем 1-ый операнд в переменную, который ввел пользователь
        String operator = strings[1]; //сохраняем оператор в переменную, который ввел пользователь
        String secondNumb = strings[2]; //сохраняем 2-ой операнд в переменную, который ввел пользователь
        String decision = null; //резервируем переменную в памяти
        try {
            decision = solvingAnExampleOfArabicNumerals(firstNumb, operator, secondNumb);
        } catch (Exception e) {
            decision = solvingAnExampleOfRomanNumerals(firstNumb, operator, secondNumb);
        }
        if (decision == null)
            throw new Exception(); //если не получилось присвоить значение в переменную и она сохранаяет null, то выбрасываем исключение и завершаем программу
        return decision; //выводим наш ответ в консоль
    }

    public String solvingAnExampleOfRomanNumerals(String firstNum, String operator, String secondNum) throws Exception {
        int numb1 = RomanNumerals.valueOf(firstNum).getValue(); //получаем значение по переменной типа стринг в enum
        int numb2 = RomanNumerals.valueOf(secondNum).getValue(); //получаем значение по переменной типа стринг в enum
        int result = Integer.parseInt(MathOperation.operation(numb1, operator, numb2)); //считаем результат при помощи метода
        return Converting.convertingRomanNumbers(result); //конвертируем арабские цифры в римские
    }

    public String solvingAnExampleOfArabicNumerals(String firstNum, String operator, String secondNum) {
        int numbOne = Integer.parseInt(firstNum);
        int numbTwo = Integer.parseInt(secondNum);
        return MathOperation.operation(numbOne, operator, numbTwo);
    }

    public String[] splitingStringIntoSubstring(String value) throws Exception {
        String[] strings = value.split(" "); //разделяем строку на подстроки
        try {
            checkArrayStringsForArabian(strings); //при помощи метода проверяем являются ли значения арабскими цифрами + соблюдение условия
        } catch (Exception e) {
            checkArrayStringsForRoman(strings);//если цифры являются не арабскими, проверяем что они являются римскими + соблюдение условия
        }
        return strings; //возращаем полученный результат, если он соответствует той или иной проверке
    }

    public void checkArrayStringsForRoman(String[] strings) throws Exception {
        if (strings.length != 3)
            throw new Exception(); //если длинна массива не равна 3 (по условию у нас должно быть 2 операнда и 1 оператор), то выкидываем исключение и возвращаемся в метод, который вызвал этот метод
        RomanNumerals[] romanNumerals = RomanNumerals.values(); //сохраняем все переменные enum в массив
        ArrayList<RomanNumerals> arrayList = new ArrayList<>(); //резервируем в памяти переменную листа
        Collections.addAll(arrayList, romanNumerals); //добавляем в лист массив, для последующей проверки наличия нашего числа
        boolean check = false; //резервируем переменную
        try {
            check = arrayList.contains(RomanNumerals.valueOf(strings[0])) &&
                    arrayList.contains(RomanNumerals.valueOf(strings[2])); //если 1-ое значение и 2-ое значение содержится в листе, то возвращаем true
        } catch (Exception e) {
            throw new Exception(); //если не содержится, выкидываем исключение и значение check остается false
        }
        //if (!check) throw new Exception(); //если значение false, то выкидываем исключение
    }

    public void checkArrayStringsForArabian(String[] strings) throws Exception {
        if (strings.length != 3)
            throw new Exception(); //если длинна массива не равна 3 (по условию у нас должно быть 2 операнда и 1 оператор), то выкидываем исключение и возвращаемся в метод, который вызвал этот метод
        int firstNumb = 0; //резервируем переменную в памяти
        int secondNumb = 0; //резервируем переменную в памяти
        try {
            firstNumb = Integer.parseInt(strings[0]); //пробуем преобразовать подстроку к числу
            secondNumb = Integer.parseInt(strings[2]);//пробуем преобразовать подстроку к числу
        } catch (Exception e) {
            throw new Exception(); //если не преобразуется, выкидываем исключение и возвращаемся к методу, который вызвал этот метод
        }
        if (firstNumb > 10 || firstNumb < 1 || secondNumb > 10 || secondNumb < 1)
            throw new Exception(); //если в данном методе операции прошли успешно, в завершение проверяем наше условие по заданию, если не проходит, то выкидываем исключение и возвращаемся к методу, который вызвал этот метод
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