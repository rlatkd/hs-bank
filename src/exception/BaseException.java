package exception;

import java.io.*;
import java.util.Arrays;

public class BaseException extends Exception{

    private String logFilePath = "log\\log.txt";

    public BaseException(){
        super("시스템에 오류가 발생했습니다. 다시 시도해주세요.");
    }

    public BaseException(String message) throws BaseException {
        super(message);
        log();
    }

    private void log() throws BaseException {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try{
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            printStackTrace(printWriter);
            String stackTraceAsString = stringWriter.toString();
            printWriter.close();
            stringWriter.close();

            fileWriter =  new FileWriter(logFilePath, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(stackTraceAsString);
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new BaseException();
        } finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                throw new BaseException();
            }
        }
    }
}
