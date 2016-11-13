package pl.pyda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pyda.model.Item;

/**
 * Created by jakubpyda on 11/11/16.
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
}
