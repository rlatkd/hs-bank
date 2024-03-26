package exception;

import lombok.extern.java.Log;
import utils.FilePathConstants;
import utils.DateTimeGenerator;

import java.io.*;

public abstract class BaseException extends Exception{

    public BaseException(){
        super("시스템에 오류가 발생했습니다. 다시 시도해주세요.");
    }

    public BaseException(String message) throws BaseException {
        super(message);
        log();
    }

    public static void log(Exception exception) throws BaseException {
        StringWriter stringWriter = null;
        PrintWriter printWriter = null;

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try{
            stringWriter = new StringWriter();
            printWriter = new PrintWriter(stringWriter);
            exception.printStackTrace(printWriter);

            fileWriter =  new FileWriter(FilePathConstants.LOG_PATH, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("[" + DateTimeGenerator.getDateTimeNow() + "] " + stringWriter);
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new LogException();
        } finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
                stringWriter.close();
                printWriter.close();
            } catch (IOException e) {
                throw new LogException();
            }
        }
    }

    private void log() throws BaseException {
        StringWriter stringWriter = null;
        PrintWriter printWriter = null;

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try{
            stringWriter = new StringWriter();
            printWriter = new PrintWriter(stringWriter);
            printStackTrace(printWriter);

            fileWriter =  new FileWriter(FilePathConstants.LOG_PATH, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("[" + DateTimeGenerator.getDateTimeNow() + "] " + stringWriter.toString());
            bufferedWriter.newLine();
        } catch (IOException e) {
            log(e);
            throw new LogException();
        } finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
                stringWriter.close();
                printWriter.close();
            } catch (IOException e) {
                log(e);
                throw new LogException();
            }
        }
    }
}
