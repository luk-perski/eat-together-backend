package pl.perski.eat.together.utils;

import pl.perski.eat.together.database.model.UserData;
import pl.perski.eat.together.database.repository.UserRepository;
import pl.perski.eat.together.exeption.EntityNotFoundException;

import java.util.Optional;

public class UserUtils {
    public static UserData getUserById(UserRepository userRepository, int id) {
        Optional<UserData> userData = userRepository.findById(id);
        if (!userData.isPresent()) {
            throw new EntityNotFoundException(UserData.class, "id", Integer.toString(id));
        }
        return userData.get();
    }
}
