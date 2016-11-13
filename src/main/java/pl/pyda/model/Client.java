package pl.pyda.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by jakubpyda on 09/11/16.
 */
@ToString
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@AttributeOverride(name = "id", column = @Column(name = "Client_Id"))
public class Client extends BaseEntity {

	@JoinColumn
	@ManyToOne(cascade = CascadeType.ALL)
	Country country;

	private String firstName;
	private String lastName;

}