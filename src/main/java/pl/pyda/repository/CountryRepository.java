package pl.pyda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pyda.model.Country;

/**
 * Created by jakubpyda on 11/11/16.
 */
public interface CountryRepository extends JpaRepository<Country, Long> {
	Country findByShortName(String name);
}
