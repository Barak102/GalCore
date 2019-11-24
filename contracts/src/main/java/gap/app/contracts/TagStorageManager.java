package gap.app.contracts;

import java.util.Collection;

import com.gal.entities.Tag;

public interface TagStorageManager extends StorageManager<Tag> {
    Collection<Tag> getByOrderId(int orderId);
    void saveTagNamesToOrder(int orderId, String[] tags);
    void deleteTagNamesFromOrder(int orderId, String[] tags);
}
