package at.fh.swenga.places.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Country")
public class CountryModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 3)
	private String countryShortCut;

	@Column(nullable = false, length = 100)
	private String countryName;

	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
	private Set<UserModel> users;

	@ManyToMany(mappedBy = "favoriteCountries")
	private Set<UserModel> favUsers;

	@ManyToMany(mappedBy = "countries", fetch = FetchType.EAGER)
	private Set<JourneyModel> journeys;

	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
	private Set<PlaceModel> places;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountryShortCut() {
		return countryShortCut;
	}

	public void setCountryShortCut(String countryShortCut) {
		this.countryShortCut = countryShortCut;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Set<UserModel> getUsers() {
		return users;
	}

	public void setUsers(Set<UserModel> users) {
		this.users = users;
	}

	public Set<UserModel> getFavUsers() {
		return favUsers;
	}

	public void setFavUsers(Set<UserModel> favUsers) {
		this.favUsers = favUsers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
		result = prime * result + ((countryShortCut == null) ? 0 : countryShortCut.hashCode());
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
		CountryModel other = (CountryModel) obj;
		if (countryName == null) {
			if (other.countryName != null)
				return false;
		} else if (!countryName.equals(other.countryName))
			return false;
		if (countryShortCut == null) {
			if (other.countryShortCut != null)
				return false;
		} else if (!countryShortCut.equals(other.countryShortCut))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CountryModel [id=" + id + ", countryShortCut=" + countryShortCut + ", countryName=" + countryName
				+ ", users=" + users + ", favUsers=" + favUsers + "]";
	}

	public CountryModel(String countryShortCut, String countryName) {
		super();
		this.countryShortCut = countryShortCut;
		this.countryName = countryName;
	}

	public CountryModel() {
		super();
	}

}
