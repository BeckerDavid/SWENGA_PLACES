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
}
