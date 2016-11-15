package pl.pyda.service.calculation;

import pl.pyda.model.Order;
import pl.pyda.model.OrderItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by jakubpyda on 13/11/16.
 */
public class BritishCalculationStrategy implements CalculationStrategy {
	@Override
	public Order calculatePrice(Order order) {
		Set<OrderItem> orderItems = order.getOrderItems();
		List<Double> prices = orderItems.stream()
				.map(OrderItem::calculatePrice)
				.map(price -> price / order.getCurrency().getExchangeRate())
				.map(price -> price * 1.40)
				.collect(Collectors.toList());
		return order.setPrice(calculateFinalPrice(prices));
	}

	private Double calculateFinalPrice(List<Double> prices) {
		Double finalPrice = 0.0;
		for (Double price : prices) {
			finalPrice += price;
		}
		return BigDecimal.valueOf(finalPrice)
				.setScale(2, RoundingMode.HALF_UP)
				.doubleValue();
	}
}
