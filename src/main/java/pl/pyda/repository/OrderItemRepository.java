package pl.pyda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pyda.model.OrderItem;

/**
 * Created by jakubpyda on 13/11/16.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
