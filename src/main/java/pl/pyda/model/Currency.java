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
@AttributeOverride(name = "id", column = @Column(name = "Currency_Id"))
public class Currency extends BaseEntity {
	String name;
	Double exchangeRate;
}
