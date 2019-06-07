package at.fh.swenga.places.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Recommendations")
public class RecommendationModel {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 30)
	private String title;
	
	@Column(length = 30)
	private String region;
	
	@Column(length = 30)
	private String city;
	
	@Column(length = 500)
	private String description;
	
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	@NotNull(message = "Date cannot be null")
	private LocalDate startDate;
	
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	@NotNull(message = "Date cannot be null")
	private LocalDate endDate;
	
    @OneToMany(mappedBy="recommendation",fetch=FetchType.LAZY)
    private Set<PictureModel> pictures;
		
	@ManyToOne (cascade = CascadeType.PERSIST)
	private CountryModel country;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	private UserModel user;
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	private RatingModel rating;
	
	
}
