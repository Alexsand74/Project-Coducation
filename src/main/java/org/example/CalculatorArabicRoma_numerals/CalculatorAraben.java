package org.example.CalculatorArabicRoma_numerals;
import java.util.Scanner;
import java.util.TreeMap;

public class CalculatorAraben {
    public static void main(String[] args) {
        //2+3
        //V-VII
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.print("  Введите выражение: ");
        String exp = scn.nextLine();
        //  Определяем арифметическое действие:
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (exp.contains(actions[i])) {
                actionIndex = i; //   запоминаем индекс знака действия от 0 включительно 3
                break;
            }
        }
        //  Если не нашли арифметического действия, завершаем программу
        if (actionIndex == -1) {
            System.out.println("  Некорректное выражение");
            return; //   завершаем работу метода main

        }
        // "2+3".split("+")-> {"2", "3"}

        //   regexActions[actionIndex] берется символ из массива regexActions так как в нем прописаны
        //   регулярные выражения нужные для работы метода split, + и знак * должны быть экранированы занком \\

        String[] data = exp.split(regexActions[actionIndex]);
        //  Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int a, b;
            //  конвертируем арабские числа из строки в число
            a = Integer.parseInt(data[0]); // переводим с типа String в тип int, для арифметических действий
            b = Integer.parseInt(data[1]);

            // выполняем с числами арифметическое действие
            int result;
            switch (actions[actionIndex]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                default:
                    result = a / b;
                    break;
            }
            // если числа были арабские, возвращаем результат в арабском числе
            System.out.println(result);
        } else {
            System.out.println(" Числа должны быть в одном формате");
        }

    }
}


class Converter {
        TreeMap<Character, Integer> romanKeyMap = new TreeMap<>();

        public void Converter() {
            romanKeyMap.put('I', 1);
            romanKeyMap.put('V', 5);
            romanKeyMap.put('X', 10);
            romanKeyMap.put('L', 50);
            romanKeyMap.put('C', 100);
            romanKeyMap.put('D', 500);
            romanKeyMap.put('M', 1000);
        }
        public boolean isRoman(String number) {
            //"V"->'V' number.charAt(0) -> char
            //есть ли  с таким символом ключ в  romanKeyMap
            return romanKeyMap.containsKey(number.charAt(0)); //is there with such a symbol, the key is in romanKeyMap
        }
 }
