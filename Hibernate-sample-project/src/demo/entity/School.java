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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author PatchamuthuA
 *
 */

@Entity
@Table(name = "SCHOOL")
@NamedQueries(value = { @NamedQuery(query = "FROM School", name = "schooltabledata"),
		@NamedQuery(name = "allschools", query = "SELECT c.schoolName FROM School c") })
public class School implements Serializable {

	private static final long serialVersionUID = -4336026699398370773L;

	public School() {
	}

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "entity_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column(name = "SCHOOL_ID", nullable = false)
	private long schoolId;

	@Column(name = "NAME", nullable = false)
	private String schoolName;

	public long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(long schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	@Override
	public String toString() {
		StringBuilder schoolBuilder = new StringBuilder();
		schoolBuilder.append(this.schoolId).append("\t\t").append(this.schoolName).append("\t\t");
		return schoolBuilder.toString();
	}
}
