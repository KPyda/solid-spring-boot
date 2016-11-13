package pl.pyda.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by jakubpyda on 11/11/16.
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String uuid = UUID.randomUUID().toString();

	public int hashCode() {
		return Objects.hashCode(uuid);
	}

	public boolean equals(Object that) {
		return this == that || that instanceof BaseEntity && Objects.equals(uuid, ((BaseEntity) that).uuid);
	}
}
