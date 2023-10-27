public class HaikuSolver {
    public static String createPerfectHaiku(String s) {
        int yandexCount = 0;
        int cupCount = 0;

        for (int i = 0; i <= s.length() - 6; i++) {
            if (s.substring(i, i + 6).equalsIgnoreCase("Yandex")) {
                yandexCount++;
            } else if (s.substring(i, i + 3).equalsIgnoreCase("Cup")) {
                cupCount++;
            }
        }

        if (yandexCount == 0 || cupCount == 0) {
            return s;
        }

        if (yandexCount >= cupCount) {
            return s.replaceFirst("(?i)Cup", "Yandex");
        } else {
            return s.replaceFirst("(?i)Yandex", "Cup");
        }
    }

    public static void main(String[] args) {
        String haiku = "Yandex is great, Cup is also great";
        String perfectHaiku = createPerfectHaiku(haiku);
        System.out.println(perfectHaiku);
    }
}