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
	private String city;

	@Column(length = 500)
	private String description;

	@DateTimeFormat(pattern = "dd.MM.yyyy")
	@NotNull(message = "Date cannot be null")
	private LocalDate startDate;

	@DateTimeFormat(pattern = "dd.MM.yyyy")
	@NotNull(message = "Date cannot be null")
	private LocalDate endDate;

	@OneToMany(mappedBy = "recommendation", fetch = FetchType.LAZY)
	private Set<PictureModel> pictures;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private CountryModel country;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private UserModel user;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private RatingModel rating;

	public RecommendationModel(String title, String city, String description,
			@NotNull(message = "Date cannot be null") LocalDate startDate,
			@NotNull(message = "Date cannot be null") LocalDate endDate, CountryModel country, UserModel user,
			RatingModel rating) {
		super();
		this.title = title;
		this.city = city;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.country = country;
		this.user = user;
		this.rating = rating;
	}
	
	

	public RecommendationModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Set<PictureModel> getPictures() {
		return pictures;
	}

	public void setPictures(Set<PictureModel> pictures) {
		this.pictures = pictures;
	}

	public CountryModel getCountry() {
		return country;
	}

	public void setCountry(CountryModel country) {
		this.country = country;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public RatingModel getRating() {
		return rating;
	}

	public void setRating(RatingModel rating) {
		this.rating = rating;
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
		RecommendationModel other = (RecommendationModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RecommendationModel [id=" + id + ", title=" + title + ", city=" + city
				+ ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", pictures="
				+ pictures + ", country=" + country + ", user=" + user + ", rating=" + rating + "]";
	}

}
