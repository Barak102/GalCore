package gap.app.contracts;

import java.util.Collection;

import com.gal.entities.Order;

public interface OrderStorageManager extends StorageManager<Order> {
    void cancel(int orderId);

    void approve(int orderId);

    Collection<Order> getByClientId(int clientId);

    Collection<Order> getByTagsName(String[] tags);

    Collection<Order> getByTagsIds(int[] tagsIds);
}
