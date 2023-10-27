import java.util.Scanner;

public class HaikuSolver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(replaceCharacters(s));
    }

    public static String replaceCharacters(String s) {
        String yandex = "yandex";
        String cup = "cup";
        String yandexUpper = "Yandex";
        String cupUpper = "Cup";

        // Проверяем каждый символ в строке
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            char currentCharUpper = Character.toUpperCase(currentChar);
            if (Character.isUpperCase(currentChar)) {
                if (i + yandex.length() <= s.length() && s.substring(i, i + yandex.length()).equalsIgnoreCase(yandex)) {
                    s = s.substring(0, i) + yandexUpper + s.substring(i + yandex.length());
                } else if (i + cup.length() <= s.length() && s.substring(i, i + cup.length()).equalsIgnoreCase(cup)) {
                    s = s.substring(0, i) + cupUpper + s.substring(i + cup.length());
                }
            }
        }

        return s;
    }
}