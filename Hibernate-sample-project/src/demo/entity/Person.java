/**
 * 
 */
package demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author PatchamuthuA
 *
 */
/**
 * @author Arockia
 *
 */
@Entity
@Table(name = "PERSON")
@NamedQueries(value = { @NamedQuery(name = "allpersons", query = "select pe.name FROM Person pe") })
public class Person implements Serializable {

	private static final long serialVersionUID = -4681552989422606646L;

	public Person() {
	}

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "entity_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column(name = "PERSON_ID", nullable = false)
	private long personId;

	@Embedded
	private Name name;

	@OneToOne
	@JoinColumn(name = "SSN_ID", nullable = false)
	@Cascade(value = CascadeType.ALL)
	private SSN ssn;

	@OneToMany
	@JoinColumn(name = "PERSON_ID")
	private List<Citizenships> citizenships;

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public SSN getSsn() {
		return ssn;
	}

	public void setSsn(SSN ssn) {
		this.ssn = ssn;
	}

	public List<Citizenships> getCitizenships() {
		return citizenships;
	}

	public void setCitizenships(List<Citizenships> citizenships) {
		this.citizenships = citizenships;
	}

}
