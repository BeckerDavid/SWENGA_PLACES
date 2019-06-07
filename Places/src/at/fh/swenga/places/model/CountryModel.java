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

}
