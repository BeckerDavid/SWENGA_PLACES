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
@Table(name = "JourneyDestination")
public class JourneyDestinationModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(mappedBy = "journeyDestination", fetch = FetchType.LAZY)
	private Set<JourneyModel> journeies;

	@OneToMany(mappedBy = "destinationJourney", fetch = FetchType.LAZY)
	private Set<DestinationModel> destinations;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<JourneyModel> getJourneies() {
		return journeies;
	}

	public void setJourneies(Set<JourneyModel> journeies) {
		this.journeies = journeies;
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
		JourneyDestinationModel other = (JourneyDestinationModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JourneyDestinationModel [id=" + id + ", journeies=" + journeies + ", destinations=" + destinations
				+ "]";
	}

}
