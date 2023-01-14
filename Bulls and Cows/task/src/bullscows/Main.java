package bullscows;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder string = new StringBuilder();
        System.out.println("Please, enter the secret code's length:");
        String temp = "";
        int n;
        try {
            temp = scanner.nextLine();
            n = Integer.parseInt(temp);
        } catch (Exception e) {
            System.out.println("Error: " + '"' + temp + '"' + " isn't a valid number.");
            return;
        }
        System.out.println("Input the number of possible symbols in the code:");
        int numberOfCharacters = scanner.nextInt();
        if (numberOfCharacters > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        } else if (n > numberOfCharacters || n == 0) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.\n", n, numberOfCharacters);
            return;
        }
        StringBuilder g = new StringBuilder();
        g.append("*".repeat(n));
        if (numberOfCharacters <= 10) {
            System.out.printf("The secret is prepared: %s (0-9).\n", g);
        } else {
            char e = (char) ((char) numberOfCharacters + 86);
            System.out.printf("The secret is prepared: %s (0-9, a-%c).\n", g, e);

        }
        System.out.println("Okay, let's start a game!");
        int[] count = new int[130];
        int k = 0;
        Arrays.fill(count, 0);
        while (string.length() != n) {
            Random random = new Random(k);
            for (int i = 0; i < n; i++) {
                int x = random.nextInt(numberOfCharacters);
                if (x >= 10) {
                    x += 87;
                }
                count[x]++;
                if (count[x] == 1 && x < 10) {
                    string.append(Character.forDigit(x, 10));
                } else if (count[x] == 1 && x >= 10) {
                    string.append((char) x);
                }
                if (string.length() == n) break;
            }
            k++;
        }
        System.out.println(string);
        // -----------------------------------------------------
        int turn = 1;
        while (true) {
            System.out.println("Turn " + turn + ":");
            String guessNumber = scanner.next();
            turn++;
            String[] b = string.toString().split("");
            int bulls = 0, cows = 0;
            for (int i = 0; i < string.length(); i++) {
                if (guessNumber.charAt(i) == string.charAt(i)) {
                    bulls++;
                } else if (guessNumber.contains(b[i])) {
                    cows++;
                }
            }
            if (bulls == 0 && cows == 0) {
                System.out.print("None\n");
            } else if (bulls == 0) {
                System.out.printf("Grade: %d cow\n", cows);
            } else if (cows == 0) {
                System.out.printf("Grade: %d bull\n", bulls);
            } else {
                System.out.printf("Grade: %d bull and %d cow\n", bulls, cows);
            }

            if (guessNumber.equals(string.toString())) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
        }
    }
}
