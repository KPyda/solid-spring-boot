package pl.pyda.service.calculation;

import pl.pyda.model.Order;

/**
 * Created by jakubpyda on 13/11/16.
 */
public interface CalculationStrategy {
	Order calculatePrice(Order order);
}
