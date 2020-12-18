package DAL;

import Classes.User;

public interface DAO<T> {
    void add(T t);
    void remove(T t);
    void update(T t);
    T readId(int id);
    T read(T t);
}
