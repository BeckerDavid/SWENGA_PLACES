package at.fh.swenga.places.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Destination")
public class DestinationModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 30)
	private String destinationPlace;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private CountryModel country;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private JourneyDestinationModel destinationJourney;

	public DestinationModel(String destinationPlace, CountryModel country, JourneyDestinationModel destinationJourney) {
		super();
		this.destinationPlace = destinationPlace;
		this.country = country;
		this.destinationJourney = destinationJourney;
	}

	public DestinationModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDestinationPlace() {
		return destinationPlace;
	}

	public void setDestinationPlace(String destinationPlace) {
		this.destinationPlace = destinationPlace;
	}

	public CountryModel getCountry() {
		return country;
	}

	public void setCountry(CountryModel country) {
		this.country = country;
	}

	public JourneyDestinationModel getDestinationJourney() {
		return destinationJourney;
	}

	public void setDestinationJourney(JourneyDestinationModel destinationJourney) {
		this.destinationJourney = destinationJourney;
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
		DestinationModel other = (DestinationModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DestinationModel [id=" + id + ", destinationPlace=" + destinationPlace + ", country=" + country
				+ ", destinationJourney=" + destinationJourney + "]";
	}

}
