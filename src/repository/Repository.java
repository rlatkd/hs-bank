package repository;

import exception.DataAccessException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Repository<E> {
    protected List<E> dataList;
    protected String path;

    protected Repository() {
        this.dataList = new ArrayList<>();
        this.path = "data\\";
    }

    protected final void load() throws DataAccessException {
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            objectInputStream = new ObjectInputStream(bufferedInputStream);

            Object object = null;
            while ((object = objectInputStream.readObject()) != null)
                dataList = (List<E>) object;
        } catch (EOFException e) {
        } catch (Exception e) {
            // 로깅 작업'
            throw new DataAccessException();
        } finally {
            try {
                if(objectInputStream != null) objectInputStream.close();
                if(bufferedInputStream != null) bufferedInputStream.close();
                if(fileInputStream != null) fileInputStream.close();
            } catch (Exception e) {
                // 로깅 작업
                throw new DataAccessException();
            }
        }
    }

    protected final void save() throws DataAccessException {
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            objectOutputStream = new ObjectOutputStream(bufferedOutputStream);

            objectOutputStream.writeObject(dataList);
        } catch (Exception e) {
            // 로깅 작업
            throw new DataAccessException();
        } finally {
            try {
                objectOutputStream.close();
                bufferedOutputStream.close();
                fileOutputStream.close();
            } catch (Exception e) {
                // 로깅 작업
                throw new DataAccessException();
            }
        }
    }
}
