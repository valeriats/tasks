import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        boolean stop  = false;
        while (!stop) {
            String input = reader.readLine();
            if(input.equals("stop")) stop = true;

            List<Integer> listNumbers = new ArrayList<>();

            String[] simbols = input.split("");
            for (int i = 0; i < input.length(); i++) {
                StringBuilder number = new StringBuilder(simbols[i]);

                int countOfZeros = input.length() - 1 - i;

                number.append("0".repeat(countOfZeros));
                if(number.toString().equals("0")) continue;
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
        if(numbers.contains(num)) {
            result.append(numbersInRoman.get(num));
            return;
        }

        Integer prev = 1;
        Integer next = num;

        int countOfZeroes = num.toString().length() - 1;

        for (int i = 0; i<=numbers.size() - 1; i++) {
            if(!(num > numbers.get(i))) {
                prev = numbers.get(i - 1);
                next = numbers.get(i);
                break;
            }
        }

        int difference;
        if((num - prev) / Integer.valueOf("1" + "0".repeat(countOfZeroes)) >= 4
                ||  (num / Integer.valueOf("1" + "0".repeat(countOfZeroes)) == 4)) {
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
