package gap.app.contracts;

import com.gal.entities.User;

public interface UserStorageManager extends StorageManager<User> {
    User getByEmail(String email);
}
