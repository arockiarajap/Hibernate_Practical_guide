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
@Table(name = "SCHOOL_ADDRESS")
public class SchoolAddress implements Serializable {

	private static final long serialVersionUID = 3858691203720389945L;

	public SchoolAddress() {
	}

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "entity_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column(name = "SCHOOL_ADDRESS_ID", nullable = false)
	private long schoolAddressId;

	@OneToOne
	@JoinColumn(name = "SCHOOL_ID")
	private School school;

	@OneToOne
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;

	public long getSchoolAddressId() {
		return schoolAddressId;
	}

	public void setSchoolAddressId(long schoolAddressId) {
		this.schoolAddressId = schoolAddressId;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
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
		schoolBuilder.append(this.schoolAddressId).append("\t\t").append(this.school.toString()).append("\t\t")
				.append(this.address.toString());
		return schoolBuilder.toString();
	}
}
