package at.fh.swenga.places.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "users")

public class UserModel {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="username", unique=true, nullable = false, length = 45)
	private String username;

	@Column(name="password", nullable = false, length = 60)
	private String password;

	@Column(name="enabled", nullable=false)
	private boolean enabled;
	
	@Column(nullable = false, length = 30)
	private String firstName;

	@Column(nullable = false, length = 30)
	private String lastName;

	@Column(nullable = false, length = 30)
	private String mail;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] profilePicture;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private CountryModel country;

	@ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Set<UserCategoryModel> category;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<RecommendationModel> recommendations;

	public UserModel() {}
	
	public UserModel(String username, String password, String firstName, String lastName, String mail,
			CountryModel country, Set<UserCategoryModel> cat, boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.country = country;
		this.category = cat;
		this.enabled = enabled;
	}
	
	public UserModel(String username, String firstName, String lastName, String mail,
			CountryModel country) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.country = country;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	public Set<UserCategoryModel> getCategory() {
		return category;
	}

	public void setCategory(Set<UserCategoryModel> category) {
		this.category = category;
	}

	public Set<RecommendationModel> getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(Set<RecommendationModel> recommendations) {
		this.recommendations = recommendations;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public CountryModel getCountry() {
		return country;
	}

	public void setCountry(CountryModel country) {
		this.country = country;
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
		UserModel other = (UserModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", mail=" + mail + ", country=" + country + ", profilePicture="
				+ Arrays.toString(profilePicture) + ", category=" + category
				+ ", recommendations=" + recommendations + "]";
	}
	
	public void addUserCategory(UserCategoryModel cat) {
		if(category==null) category = new HashSet<UserCategoryModel>();
		category.add(cat);
		}
	
	public void encryptPassword() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		password = passwordEncoder.encode(password);		
	}

}
