package at.fh.swenga.places.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserCategoryModel implements java.io.Serializable {
	private static final long serialVersionUID = 4290441020117679859L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private Set<UserModel> users;

	@Column(name="role",nullable = false, length = 45)
	private String role;

	public UserCategoryModel(String role) {
		super();
		this.role = role;
	}

	public UserCategoryModel() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<UserModel> getUsers() {
		return users;
	}

	public void setUsers(Set<UserModel> users) {
		this.users = users;
	}

	public String getRole() {
		return role;
	}
	
	public void setRole(String name) {
		this.role = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserCategoryModel other = (UserCategoryModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
