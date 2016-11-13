package pl.pyda.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pyda.model.Currency;
import pl.pyda.model.Order;
import pl.pyda.repository.OrderRepository;
import pl.pyda.service.calculation.BritishCalculationStrategy;
import pl.pyda.service.calculation.CalculationStrategy;
import pl.pyda.service.calculation.PolandCalculationStrategy;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jakubpyda on 13/11/16.
 */
@Service
@Slf4j
public class CalculationService {

	private final OrderRepository orderRepository;

	@Autowired
	public CalculationService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public List<Order> calculateOrders(List<Order> orders) {
		return orders.stream()
				.map(this::calculateOrder)
				.map(orderRepository::save)
				.collect(Collectors.toList());
	}

	private Order calculateOrder(Order order) {
		log.info("Calculating price for orderNumber = " + order.getOrderNumber());
		Currency currency = order.getCurrency();
		CalculationStrategy strategy = getCalculationStrategy(currency);
		return strategy.calculatePrice(order);
	}

	private CalculationStrategy getCalculationStrategy(Currency currency) {
		if ("PLN".equals(currency.getName())) {
			return new PolandCalculationStrategy();
		} else if ("GBP".equals(currency.getName())) {
			return new BritishCalculationStrategy();
		} else {
			throw new IllegalArgumentException("Currency not supported");
		}
	}


}
