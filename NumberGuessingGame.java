import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 100;
    private static final int MAX_ATTEMPTS = 10;

    private Scanner scanner;
    private Random random;

    public NumberGuessingGame() {
        scanner = new Scanner(System.in);
        random = new Random();
    }

    // Method to play a single round of the game
    private void playRound() {
        int targetNumber = random.nextInt(MAX_RANGE - MIN_RANGE + 1) + MIN_RANGE;
        int attempts = 0;
        boolean guessedCorrectly = false;

        System.out.println("Guess the number between " + MIN_RANGE + " and " + MAX_RANGE);

        while (attempts <MAX_ATTEMPTS && !guessedCorrectly) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            attempts++;

            if (guess < MIN_RANGE || guess > MAX_RANGE) {
                System.out.println("Please guess a number between " + MIN_RANGE + " and " + MAX_RANGE + ".");
            } else if (guess <targetNumber) {
                System.out.println("Too low. Try again.");
            } else if (guess > targetNumber) {
                System.out.println("Too high. Try again.");
            } else {
                guessedCorrectly = true;
                System.out.println("Congratulations! You've guessed the number in "+attempts +"attempts.");
            }
        }

        if (!guessedCorrectly) {
            System.out.println("Sorry, you've run out of attempts. The correct number was " + targetNumber + ".");
        }
    }
    
    // Method to start the game and handle multiple rounds
    private void startGame() {
        int roundsWon = 0;
        int totalRounds = 0;

        String playAgain;
        do {
            System.out.println("\nStarting a new round...");
            playRound();
            totalRounds++;
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().toLowerCase();

            if (playAgain.equals("yes")) {
                roundsWon++;
            }
        } while (playAgain.equals("yes"));

        System.out.println("\nGame Over!");
        System.out.println("Total Rounds Played: " + totalRounds);
        System.out.println("Rounds Won: " + roundsWon);
    }
    public static void main(String[] args) {
        NumberGuessingGame game = new NumberGuessingGame();
        game.startGame();
    }
}
