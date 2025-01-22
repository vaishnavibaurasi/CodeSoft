package com.quizgame;

import java.util.*;
import java.util.concurrent.*;

public class QuizGameWithTimer {

    static class Question {
        String questionText;
        String[] options;
        int correctAnswerIndex;

        public Question(String questionText, String[] options, int correctAnswerIndex) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Question> questions = new ArrayList<>();

        // Add questions to the quiz
        questions.add(new Question("What are the major languages spoken in Andhra Pradesh?", new String[]{"1. Odia and Telugu", "2. Telugu and Urdu", "3. Telugu and Kannada", "4. All of the above languages"}, 2));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"1. Earth", "2. Mars", "3. Jupiter", "4. Venus"}, 2));
        questions.add(new Question("Which is the largest coffee-producing state of India?", new String[]{"1. Kerala", "2. Tamil Nadu", "3. Karnataka", "4. Arunachal Pradesh"}, 3));
        questions.add(new Question("Which state has the largest area?", new String[]{"1. Maharashtra", "2. Madhya Pradesh", "3. Uttar Pradesh", "4. Rajasthan"}, 4));
        questions.add(new Question("What is the staple drink of Goa?", new String[]{"1. Toddy", "2. Feni", "3. Thandai", "4. Sattu Sharbat"}, 2));

        
        
        int score = 0;
        Map<Integer, Boolean> results = new HashMap<>();

        System.out.println("Welcome to the Quiz Game!");

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + question.questionText);
            for (String option : question.options) {
                System.out.println(option);
            }

            System.out.println("You have 10 seconds to answer.");
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<Integer> future = executor.submit(() -> {
                System.out.print("Enter your answer (1-4): ");
                return scanner.nextInt();
            });

            int answer = -1;
            try {
                answer = future.get(10, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                System.out.println("\nTime's up! Moving to the next question.");
                future.cancel(true);
            } catch (Exception e) {
                System.out.println("Invalid input. Moving to the next question.");
                future.cancel(true);
            }
            executor.shutdownNow();

            if (answer == question.correctAnswerIndex) {
                System.out.println("Correct!");
                score++;
                results.put(i + 1, true);
            } else if (answer != -1) {
                System.out.println("Wrong. The correct answer was " + question.correctAnswerIndex + ".");
                results.put(i + 1, false);
            }
        }

        System.out.println("\nQuiz Over!");
        System.out.println("Your final score is: " + score + "/" + questions.size());
        System.out.println("Summary:");
        for (int i = 0; i < questions.size(); i++) {
            System.out.println("Question " + (i + 1) + ": " + (results.getOrDefault(i + 1, false) ? "Correct" : "Incorrect"));
        }

        scanner.close();
    }
}

