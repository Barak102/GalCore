package gap.app.contracts;

import com.gal.entities.Payment;

import java.util.Collection;

public interface PaymentStorageManager extends StorageManager<Payment> {
    Collection<Payment> getByOrderId(int orderId);
}
