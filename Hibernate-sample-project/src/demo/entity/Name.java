/**
 * 
 */
package demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author PatchamuthuA
 *
 */
@Embeddable
public class Name implements Serializable {

	private static final long serialVersionUID = -8124325320195665064L;

	public Name() {
	}

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "MIDDLE_NAME", nullable = true)
	private String middleName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		StringBuilder courseBuilder = new StringBuilder();
		courseBuilder.append(this.firstName).append(" ").append(this.middleName).append(" ").append(this.lastName);
		return courseBuilder.toString();
	}

}
