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
@Table(name = "Country")
public class CountryModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 3)
	private String countryShortCut;

	@Column(nullable = false, length = 30)
	private String countryName;

	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
	private Set<RecommendationModel> recommendations;

	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
	private Set<DestinationModel> destinations;

	public CountryModel() {
		super();
	}

	public CountryModel(String countryShortCut, String countryName) {
		super();
		this.countryShortCut = countryShortCut;
		this.countryName = countryName;

	}

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

	public Set<RecommendationModel> getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(Set<RecommendationModel> recommendations) {
		this.recommendations = recommendations;
	}

	public Set<DestinationModel> getDestinations() {
		return destinations;
	}

	public void setDestinations(Set<DestinationModel> destinations) {
		this.destinations = destinations;
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
		CountryModel other = (CountryModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CountryModel [id=" + id + ", countryShortCut=" + countryShortCut + ", countryName=" + countryName
				+ ", recommendations=" + recommendations + ", destinations=" + destinations + "]";
	}

}
