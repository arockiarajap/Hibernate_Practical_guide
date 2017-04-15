/**
 * 
 */
package demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Arockia
 *
 */
@Entity
@Table(name = "PERSON_ADDRESS")
public class PersonAddress implements Serializable {

	private static final long serialVersionUID = -7314901173305176948L;

	public PersonAddress() {
	}

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "entity_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column(name = "PERSON_ADDRESS_ID", nullable = false)
	private long personAddressId;

	@OneToOne
	@JoinColumn(name = "PERSON_ID")
	private Person person;

	@OneToOne
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;

	public long getPersonAddressId() {
		return personAddressId;
	}

	public void setPersonAddressId(long personAddressId) {
		this.personAddressId = personAddressId;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		StringBuilder schoolBuilder = new StringBuilder();
		schoolBuilder.append(this.personAddressId).append("\t\t").append(this.person.toString()).append("\t\t")
				.append(this.address.toString());
		return schoolBuilder.toString();
	}
}
