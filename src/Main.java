import java.util.Random;

import static utils.Utils.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        Thread palindrome = new Thread(() -> {
            for (String text : texts) {
                if (isPalindrome(text) && !isSameChar(text)) {
                    incrementCounter(text.length());
                }
            }
        });

        Thread sameChar = new Thread(() -> {
            for (String text : texts) {
                if (isSameChar(text)) {
                    incrementCounter(text.length());
                }
            }
        });

        Thread ascendingOrder = new Thread(() -> {
            for (String text : texts) {
                if (isAscendingOrder(text) && !isSameChar(text)) {
                    incrementCounter(text.length());
                }
            }
        });

        ascendingOrder.start();
        sameChar.join();
        ascendingOrder.join();
        palindrome.join();

        System.out.println("Красивых слов с длинной 3: " + counter3 + " шт");
        System.out.println("Красивых слов с длинной 4: " + counter4 + " шт");
        System.out.println("Красивых слов с длинной 5: " + counter5 + " шт");
    }
}