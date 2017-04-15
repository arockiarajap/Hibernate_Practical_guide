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
 * @author PatchamuthuA
 *
 */
@Entity
@Table(name = "PERSON_SCHOOLS")
public class PersonSchools implements Serializable {

	private static final long serialVersionUID = 8480803855039480844L;

	public PersonSchools() {
	}

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "entity_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column(name = "PERSON_SCHOOL_ID", nullable = false)
	private long personSchoolsId;

	@OneToOne
	@JoinColumn(name = "PERSON_ID")
	private Person person;

	@OneToOne
	@JoinColumn(name = "SCHOOL_COUSRE_ID")
	private SchoolCourses schoolCourses;

	public long getPersonSchoolsId() {
		return personSchoolsId;
	}

	public void setPersonSchoolsId(long personSchoolsId) {
		this.personSchoolsId = personSchoolsId;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public SchoolCourses getSchoolCourses() {
		return schoolCourses;
	}

	public void setSchoolCourses(SchoolCourses schoolCourses) {
		this.schoolCourses = schoolCourses;
	}

	@Override
	public String toString() {
		StringBuilder courseBuilder = new StringBuilder();
		courseBuilder.append(this.personSchoolsId).append(" ").append(this.person).append(" ").append(this.schoolCourses);
		return courseBuilder.toString();
	}
}
