package view;

import dto.user.client.AddPointDto;
import enumeration.quiz.Quiz;
import exception.BaseException;
import service.ClientService;
import utils.QuizGenerator;

import javax.swing.*;
import java.io.IOException;

import static exception.BaseException.DEFAULT_MESSAGE;
import static exception.BaseException.log;

public class QuizView extends View{
    private int point;
    private boolean isTimeOver;
    private boolean isQuizOver;
    private final ClientService clientService;

    public QuizView(int userId){
        this.userId = userId;
        this.point = 0;
        this.isTimeOver = false;
        this.clientService = ClientService.getInstance();
    }
    @Override
    protected void start()  {
        System.out.println("금융 상식 퀴즈에 오신걸 환영합니다!");
        System.out.println("1문제 당 1포인트를 얻으실 수 있습니다.");
        System.out.println("퀴즈는 10초 동안 진행됩니다.");
    }

    @Override
    protected void proceed() {
        LOOP_1 : while (true){
            System.out.println("진행하시겠습니까?");
            System.out.println("1. 네\n2. 아니오");
            System.out.print("> ");
            try{
                switch (br.readLine()){
                    case "1" :
                        startQuiz();
                        break LOOP_1;
                    case "2" :
                        return;
                    default:
                        System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
                }
            }catch (IOException ioException){
                try {
                    log(ioException);
                    System.out.println(DEFAULT_MESSAGE);
                } catch (BaseException baseException) {
                    System.out.println(baseException.getMessage());
                }
            }
        }
    }

    @Override
    protected void end() {
        System.out.println("금융 상식 퀴즈를 종료합니다.");
    }

    private void startQuiz() {
        Thread quizThread = new Thread(() -> {
            displayQuiz();
        });

        Thread timerThread = new Thread(() -> {
            startTimer();
        });

        quizThread.start();
        timerThread.start();

        try {
            timerThread.join();
        } catch (InterruptedException interruptedException) {
            try {
                log(interruptedException);
                System.out.println(DEFAULT_MESSAGE);
            } catch (BaseException e) {
                System.out.println(e.getMessage());
            }
        }

        quizThread.interrupt();

        try {
            clientService.addBonus(
                    AddPointDto.builder()
                            .clientId(userId)
                            .point(point)
                            .build()
            );
        } catch (BaseException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println(point + "포인트를 획득하셨습니다!");

    }

    private void displayQuiz() {
        for (Quiz quiz : QuizGenerator.generateQuizList()) {
            if(isTimeOver) break;
            String answer = JOptionPane.showInputDialog(quiz.getQuestion());
            if(answer == null) continue;
            if(answer.equals(quiz.getAnswer())) point++;
        }
        isQuizOver = true;
    }

    private void startTimer() {
        for (int i = 10; i > 0 ; i--) {
            try {
                if(isQuizOver) break;
                System.out.println("남은 시간: " + i);
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
                try {
                    log(interruptedException);
                    System.out.println(DEFAULT_MESSAGE);
                } catch (BaseException e) {
                    System.out.println(DEFAULT_MESSAGE);
                }
            }
        }
        isTimeOver = true;
    }
}
