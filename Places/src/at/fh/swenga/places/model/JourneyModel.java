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
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	private JourneyDestinationModel journeyDestination;
	
	
}
