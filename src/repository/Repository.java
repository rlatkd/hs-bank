package repository;

import entity.Account;
import entity.Entity;
import exception.DataAccessException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Repository<E extends Entity> {
    protected List<E> entityList;
    protected String path;

    protected Repository() {
        this.entityList = new ArrayList<>();
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
                entityList = (List<E>) object;
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

            objectOutputStream.writeObject(entityList);
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

    public final void update() throws DataAccessException {
        save();
    }
    protected final E getLastEntity() {
        return entityList.isEmpty() ? null : entityList.get(entityList.size() - 1);
    }

    public final void add(E entity) throws DataAccessException {
        load();
        entity.setId(getLastEntity().getId() + 1);
        entityList.add(entity);
        save();
    }
    public final E get(int id) throws DataAccessException {
        load();
        for(Entity entity : entityList)
            if(entity.getId() == id) return (E)entity;
        return null;
    }

    public final List<E> getEntityList() throws DataAccessException {
        load();
        return entityList;
    }

    public final void remove(int id) throws DataAccessException {
        load();
        for(int i = 0; i < entityList.size(); i++){
            if(entityList.get(i).getId() == id) entityList.remove(i);
            break;
        }
        save();
    }
}
