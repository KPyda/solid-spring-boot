package pl.pyda.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pyda.model.Client;
import pl.pyda.model.Item;
import pl.pyda.model.Order;
import pl.pyda.model.OrderItem;
import pl.pyda.repository.ClientRepository;
import pl.pyda.repository.OrderRepository;

import java.io.BufferedReader;
import java.util.List;

/**
 * Created by jakubpyda on 09/11/16.
 */
@Service
public class OrderService {

	private final ClientRepository clientRepository;
	private final OrderRepository orderRepository;

	@Autowired
	public OrderService(ClientRepository clientRepository, OrderRepository orderRepository) {
		this.clientRepository = clientRepository;
		this.orderRepository = orderRepository;
	}

	private Order toOrder(String line) {
		OrderDTO orderDTO = new OrderDTO(line).invoke();
		Client client = clientRepository.findByFirstNameAndLastName(orderDTO.getFirstName(), orderDTO.getLastName());
		Order orderOptional = retrieveOrder(orderDTO.getOrderNumber(), client);
		if (orderOptional != null) {
			return addItemToOrder(orderOptional, orderDTO);
		} else {
			return createOrder(client, orderDTO);
		}
	}

	@Transactional
	private Order createOrder(Client client, OrderDTO orderDTO) {
		Order order = Order.builder()
				.client(client)
				.orderItems(Sets.newHashSet(OrderItem.builder()
						.item(Item.builder()
								.name(orderDTO.getItemName())
								.price(orderDTO.getPrice())
								.build())
						.quantity(orderDTO.getQuantity())
						.build()))
				.orderNumber(orderDTO.getOrderNumber())
				.currency(client.getCountry().getCurrency())
				.build();
		return orderRepository.save(order);
	}

	@Transactional
	private Order addItemToOrder(Order order, OrderDTO orderDTO) {
		OrderItem orderItem = OrderItem.builder()
				.item(Item.builder()
						.name(orderDTO.getItemName())
						.price(orderDTO.getPrice())
						.build())
				.quantity(orderDTO.getQuantity())
				.build();
		order.addOrderItem(orderItem);
		return orderRepository.save(order);
	}

	private Order retrieveOrder(Long orderNumber, Client client) {
		return orderRepository.findByOrderNumberAndClient(orderNumber, client);
	}

	public List<Order> parseToOrders(BufferedReader br) {
		List<Order> orders = Lists.newArrayList();
		br.lines().forEach(s -> orders.add(toOrder(s))); //wiem brzydkie
		return orderRepository.findAll();
	}

	@Getter
	private class OrderDTO {
		private String line;
		private String firstName;
		private String lastName;
		private Long quantity;
		private String itemName;
		private Long price;
		private Long orderNumber;

		OrderDTO(String line) {
			this.line = line;
		}

		OrderDTO invoke() {
			String[] strings = line.split(" ");
			firstName = strings[1];
			lastName = strings[0];
			quantity = Long.valueOf(strings[3]);
			itemName = strings[4];
			price = Long.valueOf(strings[5]);
			orderNumber = Long.valueOf(strings[2]);
			return this;
		}
	}
}
