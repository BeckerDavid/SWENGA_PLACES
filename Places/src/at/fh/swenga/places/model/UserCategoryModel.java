package at.fh.swenga.places.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "UserCategories")
public class UserCategoryModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private Set<UserModel> users;

	@Column(nullable = false, length = 30)
	private String name;

	public UserCategoryModel(String name) {
		super();
		this.name = name;
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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
