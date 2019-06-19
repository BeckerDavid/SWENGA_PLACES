package at.fh.swenga.places.model;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

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
	private byte[] profilePicture;
 
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private CountryModel country;

	@ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Set<UserCategoryModel> category;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<RecommendationModel> recommendations;
	
	private boolean isPrivate;
	
	@ManyToMany
	private Set<PlaceModel> favoritePlaces;
	
	@OneToMany(mappedBy="users")
	private Set<JourneyModel> journeys;
	
	@ManyToMany
	private Set<CountryModel> favoriteCountries;
	
	@ManyToMany
	private Set<RecommendationModel> favRecommendations;
	
	@ManyToMany
	private Set<UserModel> favUsers;
	
	@ManyToMany
	private Set<UserModel> favoritedUsers;
	
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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


	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	public Set<RecommendationModel> getFavRecommendations() {
		return favRecommendations;
	}

	public void setFavRecommendations(Set<RecommendationModel> favRecommendations) {
		this.favRecommendations = favRecommendations;
	}

	public CountryModel getCountry() {
		return country;
	}

	public void setCountry(CountryModel country) {
		this.country = country;
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

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public Set<PlaceModel> getFavoritePlaces() {
		return favoritePlaces;
	}

	public void setFavoritePlaces(Set<PlaceModel> favoritePlaces) {
		this.favoritePlaces = favoritePlaces;
	}

	public Set<JourneyModel> getJourneys() {
		return journeys;
	}

	public void setJourneys(Set<JourneyModel> journeys) {
		this.journeys = journeys;
	}

	public Set<CountryModel> getFavoriteCountries() {
		return favoriteCountries;
	}

	public void setFavoriteCountries(Set<CountryModel> favoriteCountries) {
		this.favoriteCountries = favoriteCountries;
	}

	public Set<RecommendationModel> getFavReccomendations() {
		return favRecommendations;
	}

	public void setFavReccomendations(Set<RecommendationModel> favReccomendations) {
		this.favRecommendations = favReccomendations;
	}

	public Set<UserModel> getFavUsers() {
		return favUsers;
	}

	public void setFavUsers(Set<UserModel> favUsers) {
		this.favUsers = favUsers;
	}

	public Set<UserModel> getFavoritedUsers() {
		return favoritedUsers;
	}

	public void setFavoritedUsers(Set<UserModel> favoritedUsers) {
		this.favoritedUsers = favoritedUsers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((journeys == null) ? 0 : journeys.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((recommendations == null) ? 0 : recommendations.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		if (journeys == null) {
			if (other.journeys != null)
				return false;
		} else if (!journeys.equals(other.journeys))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (recommendations == null) {
			if (other.recommendations != null)
				return false;
		} else if (!recommendations.equals(other.recommendations))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}




	@Override
	public String toString() {
		return "UserModel [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", mail=" + mail + ", profilePicture="
				+ Arrays.toString(profilePicture) + ", country=" + country + ", category=" + category
				+ ", recommendations=" + recommendations + ", isPrivate=" + isPrivate + ", favoritePlaces="
				+ favoritePlaces + ", journeys=" + journeys + ", favoriteCountries=" + favoriteCountries
				+ ", favRecommendations=" + favRecommendations + ", favUsers=" + favUsers + ", favoritedUsers="
				+ favoritedUsers + "]";
	}

	public void addUserCategory(UserCategoryModel cat) {
		if(category==null) category = new HashSet<UserCategoryModel>();
		category.add(cat);
		}
	
	public void encryptPassword() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		password = passwordEncoder.encode(password);		
	}
	
	public String getPicturePNG() throws UnsupportedEncodingException {
		if (profilePicture == null) {
			return "bootstrap/img/default-avatar.png";
		}
		else {
			try {
				return "data:image/jpg;base64," + new String(Base64.getDecoder().decode(new String(profilePicture).getBytes("UTF-8")));
			} catch (UnsupportedEncodingException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            return "bootstrap/img/default-avatar.png";
	        }
		}
	}

	public UserModel(String username, String password, String firstName, String lastName, String mail,
			CountryModel country, boolean isPrivate) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.country = country;
		this.isPrivate = isPrivate;
	}

	public UserModel() {
		super();
	}

	public UserModel(String username, String password, boolean enabled, String firstName, String lastName, String mail,
			CountryModel country, boolean isPrivate) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.country = country;
		this.isPrivate = isPrivate;
	}

	public UserModel(String username, String password, boolean enabled, String firstName, String lastName, String mail,
			CountryModel country, boolean isPrivate, String profilePicture) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.country = country;
		this.isPrivate = isPrivate;
		this.profilePicture = Base64.getEncoder().encode(profilePicture.getBytes());
	}
	
}
