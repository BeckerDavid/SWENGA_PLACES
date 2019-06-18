package at.fh.swenga.places.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Place")
public class PlaceModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "place", fetch = FetchType.LAZY)
	@Column(length = 30)
	private Set<RecommendationModel> recPlace;
	
	@ManyToMany(mappedBy = "favoritePlaces")
	private Set<UserModel> users;
	
	@Column(nullable=false)
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<RecommendationModel> getRecPlace() {
		return recPlace;
	}

	public void setRecPlace(Set<RecommendationModel> recPlace) {
		this.recPlace = recPlace;
	}

	public Set<UserModel> getUsers() {
		return users;
	}

	public void setUsers(Set<UserModel> users) {
		this.users = users;
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
		PlaceModel other = (PlaceModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public PlaceModel(String name) {
		super();
		this.name = name;
	}

	public PlaceModel() {
		super();
	}
	
	

}
