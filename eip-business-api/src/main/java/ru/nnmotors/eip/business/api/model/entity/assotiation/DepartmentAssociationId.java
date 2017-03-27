package ru.nnmotors.eip.business.api.model.entity.assotiation;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Embeddable
public class DepartmentAssociationId implements Serializable {
	private static final long serialVersionUID = -3105724366890999762L;

	@Column
	private Long userId;

	@Column
	private Long departmentId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(userId).append(departmentId).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		} else {
			DepartmentAssociationId otherObj = (DepartmentAssociationId) obj;
			return new EqualsBuilder().append(this.getUserId(), otherObj.getUserId())
					.append(this.getDepartmentId(), otherObj.getDepartmentId()).isEquals();
		}
	}

}
