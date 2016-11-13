package pl.pyda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pyda.model.Client;
import pl.pyda.model.Order;

/**
 * Created by jakubpyda on 11/11/16.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
	Order findByOrderNumberAndClient(Long orderNumber, Client client);
}
