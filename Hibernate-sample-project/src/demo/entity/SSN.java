/**
 * 
 */
package demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author PatchamuthuA
 *
 */
@Entity
@Table(name = "SSN")
public class SSN implements Serializable {

	private static final long serialVersionUID = 1960969844194166423L;

	public SSN() {
	}

	@Id
	@SequenceGenerator(name="pk_sequence",sequenceName="entity_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
	@Column(name = "SSN_ID", nullable = false)
	private long ssnId;

	@Column(name = "SSN_NUMBER", nullable = false)
	private String ssnNumber;

	@Column(name = "WHEN_PROVIDED", nullable = false)
	private Date whenProvided;

	public long getSsnId() {
		return ssnId;
	}

	public void setSsnId(long ssnId) {
		this.ssnId = ssnId;
	}

	public String getSsnNumber() {
		return ssnNumber;
	}

	public void setSsnNumber(String ssnNumber) {
		this.ssnNumber = ssnNumber;
	}

	public Date getWhenProvided() {
		return whenProvided;
	}

	public void setWhenProvided(Date whenProvided) {
		this.whenProvided = whenProvided;
	}

	@Override
	public String toString() {
		StringBuilder ssnBuilder = new StringBuilder();
		ssnBuilder.append(this.ssnId).append("\t\t").append(this.ssnNumber).append("\t\t").append(this.whenProvided);
		return ssnBuilder.toString();
	}
}
