package repository;

import entity.Entity;
import exception.BaseException;
import exception.DataAccessException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static exception.BaseException.log;

public abstract class Repository<E extends Entity> {
    protected List<E> entityList;
    protected String path;

    protected Repository(String path) {
        this.entityList = new ArrayList<>();
        this.path = path;
    }

    protected final void load() throws BaseException {
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            objectInputStream = new ObjectInputStream(bufferedInputStream);

            Object object = null;
            while ((object = objectInputStream.readObject()) != null)
                entityList = (ArrayList<E>) object;
        } catch (EOFException e) {
            log(e);
        } catch (IOException | ClassNotFoundException e) {
            log(e);
            throw new DataAccessException();
        } finally {
            try {
                if(objectInputStream != null) objectInputStream.close();
                if(bufferedInputStream != null) bufferedInputStream.close();
                if(fileInputStream != null) fileInputStream.close();
            } catch (IOException e) {
                log(e);
                throw new DataAccessException();
            }
        }
    }
    protected final void save() throws BaseException {
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            objectOutputStream = new ObjectOutputStream(bufferedOutputStream);

            objectOutputStream.writeObject(entityList);
        } catch (IOException e) {
            log(e);
            throw new DataAccessException();
        } finally {
            try {
                objectOutputStream.close();
                bufferedOutputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                log(e);
                throw new DataAccessException();
            }
        }
    }
    public final void update() throws BaseException {
        save();
    }

    public final int getNextId() throws BaseException {
        load();
        return entityList.isEmpty() ? 1 : entityList.get(entityList.size() - 1).getId() + 1;
    }
    public final void add(E entity) throws BaseException {
        load();
        entityList.add(entity);
        save();
    }
    public final E get(int id) throws BaseException {
        load();
        for(Entity entity : entityList)
            if(entity.getId() == id) return (E)entity;
        return null;
    }
    public final List<E> getEntityList() throws BaseException {
        load();
        return entityList;
    }
    public final void remove(int id) throws BaseException {
        load();
        for(int i = 0; i < entityList.size(); i++){
            if(entityList.get(i).getId() == id) {
            	entityList.remove(i);
                break;
            }
        }
        save();
    }
}
