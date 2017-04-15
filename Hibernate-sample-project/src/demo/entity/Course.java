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
@Table(name = "COURSE")
@NamedQueries(value = { @NamedQuery(query = "FROM Course", name = "courseTableData"),
		@NamedQuery(name = "allcourse", query = "SELECT c.courseName FROM Course c") })
public class Course implements Serializable {

	private static final long serialVersionUID = -7332646806259591896L;

	public Course() {
	}

	@Id
	@SequenceGenerator(name="pk_sequence",sequenceName="entity_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
	@Column(name = "COURSE_ID", nullable = false)
	private long courseId;

	@Column(name = "NAME", nullable = false)
	private String courseName;

	@Column(name = "DURATION_IN_YEARS", nullable = false)
	private int duration;

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		StringBuilder courseBuilder = new StringBuilder();
		courseBuilder.append(this.courseId).append("\t\t").append(this.courseName).append("\t\t").append(this.duration).append(" years");
		return courseBuilder.toString();
	}
}
