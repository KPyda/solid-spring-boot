package pl.pyda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pyda.model.Currency;

/**
 * Created by jakubpyda on 11/11/16.
 */
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
	Currency findByName(String name);
}
