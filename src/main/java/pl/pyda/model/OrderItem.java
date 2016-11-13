package pl.pyda.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by jakubpyda on 09/11/16.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Entity
@AttributeOverride(name = "id", column = @Column(name = "OrderItem_Id"))
public class OrderItem extends BaseEntity {
	Long quantity;

	@JoinColumn
	@OneToOne(cascade = CascadeType.ALL)
	Item item;

	public Long calculatePrice() {
		return item.getPrice() * quantity;
	}
}
