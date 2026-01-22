import java.util.Random;
import java.util.Scanner;

public class MathTrainer {

    // ✅ Allowed numbers (already learned)
    static final int[] NUMBERS = {1, 2, 3, 4, 5, 6, 8, 10};

    // ✅ Only change these constants:
    static final int TARGET_SCORE = 20;  // Goal points to finish a round
    static final int STREAK_STEP = 4;    // Every N correct answers -> bonus (3,6,9,12,...)

    // 🎨 Console colors (ANSI)
    static final String RESET = "\u001B[0m";
    static final String GREEN = "\u001B[32m";
    static final String RED = "\u001B[31m";
    static final String BLUE = "\u001B[34m";
    static final String YELLOW = "\u001B[33m";
    static final String CYAN = "\u001B[36m";

    // ⭐ Motivation phrases
    static final String[] PRAISE = {
            "Great job! 💪",
            "Awesome! ⭐",
            "Keep going! 🔥",
            "Well done! 😄",
            "You can do it! 🚀"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int totalRounds = 0;
        int totalAttempts = 0;
        int totalCorrectAnswers = 0; // counts how many tasks were solved correctly (after repeats)
        int totalWrongAttempts = 0;  // counts wrong inputs

        printHeader();

        boolean playAgain = true;

        while (playAgain) {
            totalRounds++;

            int score = 0;
            int attemptsThisRound = 0;
            int wrongThisRound = 0;
            int streak = 0;

            System.out.println(CYAN + "🔹 Round " + totalRounds + " started!" + RESET);
            System.out.println("Goal: " + TARGET_SCORE + " points");
            System.out.println("Correct: +1 | Wrong: -1 (score never below 0)");
            System.out.println("Streak bonus: every " + STREAK_STEP + " correct answers in a row");
            System.out.println();

            while (score < TARGET_SCORE) {

                boolean isMultiplication = random.nextBoolean();

                int a = NUMBERS[random.nextInt(NUMBERS.length)];
                int b = random.nextInt(11); // 0–10

                int correctAnswer;
                String question;

                if (isMultiplication) {
                    correctAnswer = a * b;
                    question = a + " × " + b + " = ";
                } else {
                    // Division without remainder: (a*b) ÷ a = b
                    correctAnswer = b;
                    int result = a * b;
                    question = result + " ÷ " + a + " = ";
                }

                boolean answeredCorrectly = false;

                // Repeat the same question until the child answers correctly
                while (!answeredCorrectly) {
                    //System.out.print(BLUE + "Score: " + score + RESET + " | " + question);
                    System.out.print(question);

                    // Safer input: if not an int -> ask again (no score change)
                    if (!scanner.hasNextInt()) {
                        scanner.next(); // discard invalid token
                        System.out.println(RED + "Please enter a number 🙂" + RESET);
                        continue;
                    }

                    int userAnswer = scanner.nextInt();
                    attemptsThisRound++;
                    totalAttempts++;

                    if (userAnswer == correctAnswer) {
                        // ✅ Correct
                        score++;
                        streak++;
                        answeredCorrectly = true;
                        totalCorrectAnswers++;

                        String praise = PRAISE[random.nextInt(PRAISE.length)];
                        System.out.println(GREEN + "✅ " + praise + " +1 point" + RESET);

                        // 🔥 Universal streak bonus:
                        // If STREAK_STEP=3 -> bonuses at 3,6,9,12,...
                        if (streak % STREAK_STEP == 0) {
                            int bonus = streak / STREAK_STEP; // 3->1, 6->2, 9->3, 12->4, ...
                            score += bonus;

                            System.out.println(
                                    CYAN + "🔥 " + streak + " correct in a row! Bonus +" + bonus + "!" + RESET
                            );
                        }

                        System.out.println(BLUE + "Current score: " + score + RESET);
                        System.out.println();

                    } else {
                        // ❌ Wrong: -1 point, repeat question
                        score--;
                        if (score < 0) score = 0; // 🛟 no negative scores

                        streak = 0; // streak resets only on mistake

                        wrongThisRound++;
                        totalWrongAttempts++;

                        System.out.println(RED + "❌ Wrong! -1 point. Try again 🙂" + RESET);
                        System.out.println(BLUE + "Current score: " + score + RESET);
                        System.out.println();
                    }
                }
            }

            // Round summary
            System.out.println(YELLOW + "🏆 Round finished!" + RESET);
            System.out.println("Points: " + score + " / " + TARGET_SCORE);
            System.out.println("Attempts this round: " + attemptsThisRound);
            System.out.println("Wrong attempts this round: " + wrongThisRound);
            System.out.println();

            System.out.print("Play again? (yes + / no -): ");
            playAgain = scanner.next().equalsIgnoreCase("+");
            System.out.println();
        }

        // Final summary
        System.out.println(CYAN + "🏁 Game over!" + RESET);
        System.out.println("Rounds played: " + totalRounds);
        System.out.println("Total attempts: " + totalAttempts);
        System.out.println("Correct answers: " + totalCorrectAnswers);
        System.out.println("Wrong attempts: " + totalWrongAttempts);

        scanner.close();
    }

    private static void printHeader() {
        System.out.println(CYAN);
        System.out.println("══════════════════════════════════════");
        System.out.println("   🎲 Math Game: Multiplication & Division");
        System.out.println("══════════════════════════════════════");
        System.out.println(RESET);

        System.out.print("Numbers used: ");
        for (int n : NUMBERS) {
            System.out.print(n + "  ");
        }
        System.out.println("\n");

        System.out.println("Round goal: " + TARGET_SCORE + " points");
        System.out.println("Correct: +1 | Wrong: -1 (score never below 0)");
        System.out.println("Streak bonus: every " + STREAK_STEP + " correct answers in a row");
        System.out.println();
    }
}
