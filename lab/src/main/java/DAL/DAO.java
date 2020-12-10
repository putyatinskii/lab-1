package DAL;

import Classes.User;

public interface DAO<T> {
    void Add(T t);
    void Remove(int id);
    void Update(T t);
    T Read(int id);
}
