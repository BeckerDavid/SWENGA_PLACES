package at.fh.swenga.places.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Journey")
public class JourneyModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@DateTimeFormat(pattern = "dd.MM.yyyy")
	@NotNull(message = "Date cannot be null")
	private LocalDate arrivalDate;

	@DateTimeFormat(pattern = "dd.MM.yyyy")
	@NotNull(message = "Date cannot be null")
	private LocalDate departureDate;

	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<PlaceModel> journeyDestination;
	
	@ManyToMany(mappedBy="journeys")
	@Column(nullable=false)
	private Set<UserModel> users;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public Set<PlaceModel> getJourneyDestination() {
		return journeyDestination;
	}

	public void setJourneyDestination(Set<PlaceModel> journeyDestination) {
		this.journeyDestination = journeyDestination;
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
		result = prime * result + ((arrivalDate == null) ? 0 : arrivalDate.hashCode());
		result = prime * result + ((departureDate == null) ? 0 : departureDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((journeyDestination == null) ? 0 : journeyDestination.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		JourneyModel other = (JourneyModel) obj;
		if (arrivalDate == null) {
			if (other.arrivalDate != null)
				return false;
		} else if (!arrivalDate.equals(other.arrivalDate))
			return false;
		if (departureDate == null) {
			if (other.departureDate != null)
				return false;
		} else if (!departureDate.equals(other.departureDate))
			return false;
		if (id != other.id)
			return false;
		if (journeyDestination == null) {
			if (other.journeyDestination != null)
				return false;
		} else if (!journeyDestination.equals(other.journeyDestination))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JourneyModel [id=" + id + ", arrivalDate=" + arrivalDate + ", departureDate=" + departureDate
				+ ", journeyDestination=" + journeyDestination + ", users=" + users + "]";
	}

	public JourneyModel(@NotNull(message = "Date cannot be null") LocalDate arrivalDate,
			@NotNull(message = "Date cannot be null") LocalDate departureDate,
			Set<PlaceModel> journeyDestination, Set<UserModel> users) {
		super();
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		this.journeyDestination = journeyDestination;
		this.users = users;
	}

	public JourneyModel() {
		super();
	}

	
	
}
