import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RomanNumerals {

    private static Map<Integer, String> numbersInRoman = Map.of(
            1, "I",
            5, "V",
            10, "X",
            50, "L",
            100, "C",
            500, "D",
            1000, "M",
            5000, "_V_"
    );

    public static void main(String... args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String input = reader.readLine();
            if (input.equals("stop")) return;
            if (!input.matches("\\d+")) {
                System.out.println("We wait only numbers");
                continue;
            }
            if (input.startsWith("0")) {
                System.out.println("We wait only numbers not starting from zero");
                continue;
            }
            if (Long.parseLong(input) > 5000) {
                System.out.println("We can parse only numbers less 5000");
                continue;
            }

            List<Integer> listNumbers = new ArrayList<>();

            String[] symbols = input.split("");
            for (int i = 0; i < input.length(); i++) {
                StringBuilder number = new StringBuilder(symbols[i]);

                int countOfZeros = input.length() - 1 - i;

                number.append("0".repeat(countOfZeros));
                if (number.toString().equals("0")) continue;
                listNumbers.add(Integer.valueOf(number.toString()));
            }

            StringBuilder result = new StringBuilder();

            listNumbers.forEach(n -> {
                getSymbolOfNumber(n, result);
            });

            System.out.println(result.toString());
        }
    }

    private static void getSymbolOfNumber(Integer num, StringBuilder result) {
        List<Integer> numbers = List.of(1, 5, 10, 50, 100, 500, 1000, 5000);
        if (numbers.contains(num)) {
            result.append(numbersInRoman.get(num));
            return;
        }

        Integer prev = 1;
        Integer next = num;

        int countOfZeroes = num.toString().length() - 1;

        for (int i = 0; i <= numbers.size() - 1; i++) {
            if (!(num > numbers.get(i))) {
                prev = numbers.get(i - 1);
                next = numbers.get(i);
                break;
            }
        }

        int difference;
        if ((num - prev) / Integer.parseInt("1" + "0".repeat(countOfZeroes)) >= 4
                || (num / Integer.parseInt("1" + "0".repeat(countOfZeroes)) == 4)) {
            difference = next - num;
            getSymbolOfNumber(difference, result);
            result.append(numbersInRoman.get(next));
        } else {
            difference = num - prev;
            result.append(numbersInRoman.get(prev));
            getSymbolOfNumber(difference, result);
        }
    }
}

