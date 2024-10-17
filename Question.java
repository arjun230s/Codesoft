import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent. ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public boolean isCorrectAnswer(int answerIndex) {
        return answerIndex == correctAnswerIndex;
    }
}

class QuizApplication {
    private List<Question> questions;
    private int score;
    private Scanner scanner;
    public QuizApplication() {
        questions = new ArrayList<>();
        scanner = new Scanner(System.in);
        initializeQuestions();
    }

    private void initializeQuestions() {
        // Initialize with some questions
        questions.add(new Question(
            "What is the capital of France?",
            new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"},
            2
        ));

        questions.add(new Question(
            "Which planet is known as the Red Planet?",
            new String[]{"1. Earth", "2. Mars", "3. Jupiter", "4. Saturn"},
            1
        ));

        questions.add(new Question(
            "What is the largest ocean on Earth?",
            new String[]{"1. Atlantic Ocean", "2. Indian Ocean", "3. Arctic Ocean", "4. Pacific Ocean"},
            3
        ));
    }

    public void startQuiz() {
        score = 0;
        int questionNumber = 1;
        for (Question question : questions) {
            System.out.println("Question" + questionNumber + ":");
            displayQuestion (question);
            if (getAnswerWithTimer (question)) {
                score++;
            }
            questionNumber++;
        }

        displayResults();
    }

    private void displayQuestion (Question question) {
        System.out.println(question.getQuestionText());
        for (String option : question.getOptions()) {
            System.out.println(option);
        }
    }

    private boolean getAnswerWithTimer (Question question) {
        final int[] answerIndex = new int[1];
        boolean[] answered = {false};

        // Timer task to handle time out
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor. schedule(() -> {
            if (!answered[0]) {
                System.out.println("Time is up!");
                answered [0] =true; // Mark as answered to stop further input
            }
        }, 10, TimeUnit. SECONDS); // 10 seconds timer
        // Read answer from user
        System.out.print("Enter your answer (1-4): ");
        while (!answered[0]) {
            if (scanner.hasNextInt()) {
                answerIndex[0] = scanner.nextInt() -1;
                if (answerIndex [0] >= 0 && answerIndex[0] < 4) {
                    answered [0] = true; // Mark as answered
                } else {
                    System.out.println("Invalid option. Please enter a number between 1 and 4.");
                }
            }
        }

        executor.shutdown(); // Shutdown the executor
        return question.isCorrectAnswer(answerIndex[0]);
    }

    private void displayResults() {
        System.out.println("\nQuiz Over!");
        System.out.println("Your score: " + score + "/" + questions.size());
    }

    public static void main(String[] args) {
        QuizApplication quiz = new QuizApplication();
        quiz.startQuiz();
    }
}
