package zoo.repositories;

import org.springframework.data.repository.CrudRepository;
import zoo.model.User;

/*
 * Declaring a repository class */
public interface UserRepository
        extends CrudRepository<
                User, Integer > {
    User findByEmail(String email);
    User findById(int userId);
    User findByName(String name);
}

