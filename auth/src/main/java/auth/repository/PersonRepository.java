package auth.repository;

import auth.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    Person findByLogin(String login);
}
