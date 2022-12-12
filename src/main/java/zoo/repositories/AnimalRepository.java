package zoo.repositories;

import org.springframework.data.repository.CrudRepository;
import zoo.model.Animal;

import java.util.Optional;

public interface AnimalRepository extends CrudRepository<
        Animal, Integer > {
    Iterable<Animal> findAll();

    Optional<Animal> findById(Integer id);

}
