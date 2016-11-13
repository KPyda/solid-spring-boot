package pl.pyda.model;

import lombok.*;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by jakubpyda on 09/11/16.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Entity
@AttributeOverride(name = "id", column = @Column(name = "Item_Id"))
public class Item extends BaseEntity {
	String name;
	Long price;
}
