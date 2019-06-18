package at.fh.swenga.places.model;

import java.util.Calendar;
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
	private Calendar arrivalDate;

	@DateTimeFormat(pattern = "dd.MM.yyyy")
	@NotNull(message = "Date cannot be null")
	private Calendar departureDate;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private UserModel users;
	
	@Column(scale=2)
	private Float budget;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<CountryModel> countries;
	
	private String city1;
	
	@Column(nullable=true)
	private String city2;
	
	private int zip1;
	
	@Column(nullable=true)
	private int zip2;
	
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Calendar arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Calendar getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Calendar departureDate) {
		this.departureDate = departureDate;
	}

	public UserModel getUsers() {
		return users;
	}

	public void setUsers(UserModel users) {
		this.users = users;
	}
	
	public Float getBudget() {
		return budget;
	}

	public void setBudget(Float budget) {
		this.budget = budget;
	}

	public Set<CountryModel> getCountries() {
		return countries;
	}

	public void setCountries(Set<CountryModel> countries) {
		this.countries = countries;
	}

	public String getCity1() {
		return city1;
	}

	public void setCity1(String city1) {
		this.city1 = city1;
	}

	public String getCity2() {
		return city2;
	}

	public void setCity2(String city2) {
		this.city2 = city2;
	}

	public int getZip1() {
		return zip1;
	}

	public void setZip1(int zip1) {
		this.zip1 = zip1;
	}

	public int getZip2() {
		return zip2;
	}

	public void setZip2(int zip2) {
		this.zip2 = zip2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrivalDate == null) ? 0 : arrivalDate.hashCode());
		result = prime * result + ((departureDate == null) ? 0 : departureDate.hashCode());
		result = prime * result + id;
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
				+ ", users=" + users + "]";
	}

	public JourneyModel(@NotNull(message = "Date cannot be null") Calendar arrivalDate,
			@NotNull(message = "Date cannot be null") Calendar departureDate,
			Set<PlaceModel> journeyDestination, UserModel users) {
		super();
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		this.users = users;
	}

	public JourneyModel() {
		super();
	}

	public JourneyModel(@NotNull(message = "Date cannot be null") Calendar arrivalDate,
			@NotNull(message = "Date cannot be null") Calendar departureDate, Set<PlaceModel> journeyDestination,
			UserModel users, Float budget, Set<CountryModel> countries, String city1, String city2, int zip1,
			int zip2) {
		super();
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		this.users = users;
		this.budget = budget;
		this.countries = countries;
		this.city1 = city1;
		this.city2 = city2;
		this.zip1 = zip1;
		this.zip2 = zip2;
	}

	public JourneyModel(@NotNull(message = "Date cannot be null") Calendar arrivalDate,
			@NotNull(message = "Date cannot be null") Calendar departureDate, Set<PlaceModel> journeyDestination,
			UserModel users, Float budget, Set<CountryModel> countries, String city1, int zip1) {
		super();
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		this.users = users;
		this.budget = budget;
		this.countries = countries;
		this.city1 = city1;
		this.zip1 = zip1;
	}
	
}
