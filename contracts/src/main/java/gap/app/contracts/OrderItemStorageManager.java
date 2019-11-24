package gap.app.contracts;

import com.gal.entities.OrderItem;

import java.util.Collection;

public interface OrderItemStorageManager extends StorageManager<OrderItem> {
    Collection<OrderItem> getByOrderId(int orderId);
    void saveOrderItems(Collection<OrderItem> orderItems);
}
