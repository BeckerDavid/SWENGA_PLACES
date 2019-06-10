package at.fh.swenga.places.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	@ManyToOne(cascade = CascadeType.PERSIST)
	private JourneyDestinationModel journeyDestination;

	public JourneyModel(@NotNull(message = "Date cannot be null") LocalDate arrivalDate,
			@NotNull(message = "Date cannot be null") LocalDate departureDate,
			JourneyDestinationModel journeyDestination) {
		super();
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		this.journeyDestination = journeyDestination;
	}

	public JourneyModel() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public JourneyDestinationModel getJourneyDestination() {
		return journeyDestination;
	}

	public void setJourneyDestination(JourneyDestinationModel journeyDestination) {
		this.journeyDestination = journeyDestination;
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
		JourneyModel other = (JourneyModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JourneyModel [id=" + id + ", arrivalDate=" + arrivalDate + ", departureDate=" + departureDate
				+ ", journeyDestination=" + journeyDestination + "]";
	}

}
