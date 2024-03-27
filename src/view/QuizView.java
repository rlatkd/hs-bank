package view;

import dto.user.client.AddPointDto;
import enumeration.quiz.Quiz;
import exception.BaseException;
import service.ClientService;
import utils.QuizGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import static exception.BaseException.DEFAULT_MESSAGE;
import static exception.BaseException.log;

public class QuizView extends View{
    private final ClientService clientService;
    private JFrame quizFrame;
    private boolean isQuizOver;
    private int quizListIndex;
    private int point;


    public QuizView(int userId){
        this.userId = userId;
        this.clientService = ClientService.getInstance();
        this.quizFrame = new JFrame("금융 상식 퀴즈");
        this.isQuizOver = false;
        this.point = 0;
        this.quizListIndex = 0;
    }
    @Override
    protected void start()  {
        System.out.println("금융 상식 퀴즈에 오신걸 환영합니다!");
        System.out.println("1문제 당 1포인트를 얻으실 수 있습니다.");
        System.out.println("퀴즈는 30초 동안 진행됩니다.");
        try {
            System.out.println(
                    "고객님이 현재 보유하신 포인트는 "
                    + clientService.getCurrentClient(userId).getPoint()
                    + "입니다."
            );
        } catch (BaseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void proceed() {
        //startQuiz();
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
        Thread timerThread = new Thread(() -> startTimer());
        Thread quizThread = new Thread(() -> displayQuiz());

        timerThread.start();
        quizThread.start();

        try {
            timerThread.join();
        } catch (InterruptedException interruptedException) {
            try {
                log(interruptedException);
                System.out.println(DEFAULT_MESSAGE);
            } catch (BaseException e) {
                System.out.println(e.getMessage());
            }
            return;
        }

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
        System.out.println(point + " 포인트를 획득하셨습니다!");
        try {
            System.out.println(
                    "고객님이 현재 보유하신 포인트는 "
                    + clientService.getCurrentClient(userId).getPoint()
                    + "입니다."
            );
        } catch (BaseException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayQuiz() {
        List<Quiz> quizList = QuizGenerator.generateQuizList();
        quizFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isQuizOver = true;
            }
        });
        quizFrame.setTitle("금융 상식 퀴즈");
        quizFrame.setAlwaysOnTop(true);
        quizFrame.setLayout(new BorderLayout());

        JPanel questionPanel = new JPanel();
        JLabel questionLabel = new JLabel("Q" + (quizListIndex + 1) + ". "
                + quizList.get(quizListIndex).getQuestion());
        questionPanel.add(questionLabel);

        JPanel answerPanel = new JPanel();
        JTextField answerField = new JTextField(10);
        answerPanel.add(answerField);

        quizFrame.add(questionPanel, BorderLayout.NORTH);
        quizFrame.add(answerPanel, BorderLayout.CENTER);

        JButton submitButton = new JButton("확인");
        submitButton.addActionListener(e -> {
            String answer = answerField.getText();
            if(answer.equals(quizList.get(quizListIndex++).getAnswer())){
                point++;
            }
            if(quizListIndex == quizList.size()) {
                isQuizOver = true;
                quizFrame.dispose();
                return;
            }
            questionLabel.setText("Q" + (quizListIndex + 1) + ". "
                    + quizList.get(quizListIndex).getQuestion());
            answerField.setText("");
            quizFrame.pack();
            quizFrame.setLocationRelativeTo(null);
        });
        answerField.addActionListener(e -> submitButton.doClick());

        quizFrame.add(submitButton, BorderLayout.SOUTH);
        quizFrame.pack();
        quizFrame.setLocationRelativeTo(null);
        quizFrame.setVisible(true);
    }

    private void startTimer() {
        for (int i = 30; i > 0 ; i--) {
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
        quizFrame.dispose();
    }
}
