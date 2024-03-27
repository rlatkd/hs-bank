package utils;

import enumeration.quiz.Quiz;

import java.util.*;

public class QuizGenerator {
    public static List<Quiz> generateQuizList(){
        List<Quiz> quizList = Arrays.asList(Quiz.values());
        Collections.shuffle(quizList);
        return quizList;
    }
}
