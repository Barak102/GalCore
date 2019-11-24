package gap.app.contracts;

import java.io.IOException;
import java.util.Collection;

public interface StorageManager<T> {
    int save(T obj) throws IOException;
    T getById(int id) throws IOException;
    Collection<T> getByIds(int[] ids);
    void delete(int id);
    Collection<T> getAll();
}
