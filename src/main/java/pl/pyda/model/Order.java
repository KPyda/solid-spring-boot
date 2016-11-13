package pl.pyda.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by jakubpyda on 09/11/16.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Entity
@Table(name = "S_Order")
@AttributeOverride(name = "id", column = @Column(name = "Order_Id"))
public class Order extends BaseEntity {
	Long orderNumber;
	Double price;

	@JoinColumn
	@OneToMany(cascade = CascadeType.ALL)
	Set<OrderItem> orderItems;

	@JoinColumn
	@ManyToOne(cascade = CascadeType.ALL)
	Client client;

	@JoinColumn
	@OneToOne
	Currency currency;

	public Order setPrice(Double price) {
		this.price = price;
		return this;
	}

	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
	}
}
