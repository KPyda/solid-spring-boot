package pl.pyda.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by jakubpyda on 09/11/16.
 */
@Builder
@ToString
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "Country_Id"))
public class Country extends BaseEntity {
	String name;
	String shortName;

	@JoinColumn
	@OneToOne(cascade = CascadeType.ALL)
	Currency currency;
}
