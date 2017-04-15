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
@Table(name = "SCHOOL_COURSES")
public class SchoolCourses implements Serializable {

	private static final long serialVersionUID = 5853140232506886418L;

	public SchoolCourses() {
	}

	@Id
	@SequenceGenerator(name="pk_sequence",sequenceName="entity_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
	@Column(name = "SCHOOL_COUSRE_ID", nullable = false)
	private long schoolCourseId;

	@OneToOne
	@JoinColumn(name = "SCHOOL_ID")
	private School school;

	@OneToOne
	@JoinColumn(name = "COURSE_ID")
	private Course course;

	public long getSchoolCourseId() {
		return schoolCourseId;
	}

	public void setSchoolCourseId(long schoolCourseId) {
		this.schoolCourseId = schoolCourseId;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		StringBuilder schoolBuilder = new StringBuilder();
		schoolBuilder.append(this.schoolCourseId).append("\t\t").append(this.school.toString()).append("\t\t")
				.append(this.course.toString());
		return schoolBuilder.toString();
	}

}
