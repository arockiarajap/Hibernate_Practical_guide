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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Arockia
 *
 */
@Entity
@Table(name = "CITIZENSHIPS")
public class Citizenships implements Serializable {

	private static final long serialVersionUID = 4847982164831630407L;

	public Citizenships() {
	}

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "entity_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column(name = "CITIZENSHIPS_ID", nullable = false)
	private long citizenshipsId;

	@ManyToOne
	@JoinColumn(name = "PERSON_ID")
	private Person person;

	@Column(name = "COUNTRY", nullable = false)
	private String country;

	public long getCitizenshipsId() {
		return citizenshipsId;
	}

	public void setCitizenshipsId(long citizenshipsId) {
		this.citizenshipsId = citizenshipsId;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		StringBuilder schoolBuilder = new StringBuilder();
		schoolBuilder.append(this.citizenshipsId).append("\t\t").append(this.person).append("\t\t")
				.append(this.country);
		return schoolBuilder.toString();
	}

}
