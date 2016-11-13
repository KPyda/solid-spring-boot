package pl.pyda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pyda.model.Client;

/**
 * Created by jakubpyda on 11/11/16.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {
	Client findByFirstNameAndLastName(String firstName, String lastName);
}
