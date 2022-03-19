package dao;

import java.util.List;

public interface GenericDao<T> {

    public T create(T t);
    public T read(int id);
    public T update(T t);
    public T delete(int id);

    public List<T> listAll();

}
