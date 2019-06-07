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
	
    @OneToMany(mappedBy="journeyDestination",fetch=FetchType.LAZY)
    private Set<JourneyModel> journeies;
    
    @OneToMany(mappedBy="destinationJourney",fetch=FetchType.LAZY)
    private Set<DestinationModel> destinations;
	
}
