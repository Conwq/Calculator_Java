import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Calculator {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Calculator calculator = new Calculator(); //создаем объект калькулятор
            String value = scanner.nextLine(); //сохраняем введенную строку в переменную
            String[] strings = calculator.enteringValue(value); //при помощи метода, разделяем строку на подстроки и при этом проверяем содержимое на соответствие условию и сохраняем результат, если он соответствует, в переменную
            calculator.expression(strings); //наше выполнение задачи
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void expression(String[] strings) throws Exception{
        String firstNumb = strings[0]; //сохраняем 1-ый операнд в переменную, который ввел пользователь
        String operator = strings[1]; //сохраняем оператор в переменную, который ввел пользователь
        String secondNumb = strings[2]; //сохраняем 2-ой операнд в переменную, который ввел пользователь
        String answer = null; //резервируем переменную в памяти
        try {
            answer = withInt(firstNumb, operator, secondNumb); //ПЕРЕПРОВЕРИТЬ, НУЖНА ЛИ ФУНКЦИЯ ТРАЙ КЭТЧ И ЕСЛИ НЕТ ОБЪЕДЕНИТЬ ВСЕ В ОДИН МЕТОД ПРИ ПОМОЩИ ПЕРЕГРУЗКИ
        }
        catch (Exception e){
            answer = withString(firstNumb, operator, secondNumb);
        }
        if (answer == null) throw new Exception(); //если не получилось присвоить значение в переменную и она сохранаяет null, то выбрасываем исключение и завершаем программу
        System.out.println(answer); //выводим наш ответ в консоль
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
        String[] strings = value.split(" "); //разделяем строку на подстроки
        try {
            checkArrayStringsForArabian(strings); //при помощи метода проверяем являются ли значения арабскими цифрами + соблюдение условия
        }
        catch (Exception e){
            checkArrayStringsForRoman(strings);//если цифры являются не арабскими, проверяем что они являются римскими + соблюдение условия
        }
        return strings; //возращаем полученный результат, если он соответствует той или иной проверке
    }

    public void checkArrayStringsForRoman(String [] strings) throws Exception{
        if (strings.length != 3) throw new Exception(); //если длинна массива не равна 3 (по условию у нас должно быть 2 операнда и 1 оператор), то выкидываем исключение и возвращаемся в метод, который вызвал этот метод
        RomanNumerals [] romanNumerals = RomanNumerals.values(); //сохраняем все переменные enum в массив
        ArrayList<RomanNumerals> arrayList = new ArrayList<>(); //резервируем в памяти переменную листа
        Collections.addAll(arrayList, romanNumerals); //добавляем в лист массив, для последующей проверки наличия нашего числа
        boolean check = false; //резервируем переменную
        try {
             check = arrayList.contains(RomanNumerals.valueOf(strings[0])) &&
                     arrayList.contains(RomanNumerals.valueOf(strings[2])); //если 1-ое значение и 2-ое значение содержится в листе, то возвращаем true
        }
        catch (Exception e){
            throw new Exception(); //если не содержится, выкидываем исключение и значение check остается false
        }
        //if (!check) throw new Exception(); //если значение false, то выкидываем исключение
    }

    public void checkArrayStringsForArabian(String[] strings) throws Exception{
        if (strings.length != 3) throw new Exception(); //если длинна массива не равна 3 (по условию у нас должно быть 2 операнда и 1 оператор), то выкидываем исключение и возвращаемся в метод, который вызвал этот метод
        int firstNumb = 0; //резервируем переменную в памяти
        int secondNumb = 0; //резервируем переменную в памяти
        try {
            firstNumb = Integer.parseInt(strings[0]); //пробуем преобразовать подстроку к числу
            secondNumb = Integer.parseInt(strings[2]);//пробуем преобразовать подстроку к числу
        }
        catch (Exception e){
            throw new Exception(); //если не преобразуется, выкидываем исключение и возвращаемся к методу, который вызвал этот метод
        }
        if (firstNumb > 10 || secondNumb > 10) throw new Exception(); //если в данном методе операции прошли успешно, в завершение проверяем наше условие по заданию, если не проходит, то выкидываем исключение и возвращаемся к методу, который вызвал этот метод
    }
}

class MathOperation{

}