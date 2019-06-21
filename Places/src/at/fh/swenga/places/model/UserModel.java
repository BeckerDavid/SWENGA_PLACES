package at.fh.swenga.places.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "users")

public class UserModel {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "username", unique = true, nullable = false, length = 45)
	private String username;

	@Column(name = "password", nullable = false, length = 60)
	private String password;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@Column(nullable = false, length = 30)
	private String firstName;

	@Column(nullable = false, length = 30)
	private String lastName;

	@Column(nullable = false, length = 30)
	private String mail;

	@Column(name = "token", nullable = false, unique = true)
	private String token;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private CountryModel country;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Set<UserCategoryModel> category;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<RecommendationModel> recommendations;

	private boolean isPrivate;

	@ManyToMany
	private Set<PlaceModel> favoritePlaces;

	@OneToMany(mappedBy = "users")
	private Set<JourneyModel> journeys;

	@ManyToMany
	private Set<CountryModel> favoriteCountries;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<RecommendationModel> favRecommendations;

	@ManyToMany
	private Set<UserModel> followers;

	@ManyToMany
	private Set<UserModel> following;

	@OneToOne(cascade = CascadeType.ALL)
	private PictureModel profilePicture;

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

	public PictureModel getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(PictureModel profilePicture) {
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

	public UserCategoryModel getCategory() {

		List<UserCategoryModel> mainList = new ArrayList<UserCategoryModel>();

		mainList.addAll(category);

		return mainList.get(0);
	}

	public String getCategoryString() {
		
		List<UserCategoryModel> mainList = new ArrayList<UserCategoryModel>();
		
		mainList.addAll(category);
		
		
		return Integer.toString(mainList.size());
		
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
		return followers;
	}

	public void setFavUsers(Set<UserModel> favUsers) {
		this.followers = favUsers;
	}

	public Set<UserModel> getFavoritedUsers() {
		return following;
	}

	public void setFavoritedUsers(Set<UserModel> favoritedUsers) {
		this.following = favoritedUsers;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Set<UserModel> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<UserModel> followers) {
		this.followers = followers;
	}

	public Set<UserModel> getFollowing() {
		return following;
	}

	public void setFollowing(Set<UserModel> following) {
		this.following = following;
	}

	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", mail=" + mail + ", country=" + country
				+ ", category=" + category + ", recommendations=" + recommendations + ", isPrivate=" + isPrivate
				+ ", favoritePlaces=" + favoritePlaces + ", journeys=" + journeys + ", favoriteCountries="
				+ favoriteCountries + ", favRecommendations=" + favRecommendations + ", favUsers=" + followers
				+ ", favoritedUsers=" + following + ", profilePicture=" + profilePicture + "]";
	}

	public void addUserCategory(UserCategoryModel cat) {
		if (category == null)
			category = new HashSet<UserCategoryModel>();
		category.add(cat);
	}
	
	public void removeUserCategory(UserCategoryModel cat) {
		category.remove(cat);
	}

	public void encryptPassword() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		password = passwordEncoder.encode(password);
	}

	public boolean isRecLiked(RecommendationModel recMod) {
		if (favRecommendations.contains(recMod)) {
			return true;
		} else {
			return false;
		}
	}

	public void changeFavRec(RecommendationModel recMod) {

		if(isRecLiked(recMod)) {
			favRecommendations.remove(recMod);
			System.out.println("Rec liked");
		} else {
			favRecommendations.add(recMod);
			System.out.println("Rec not liked");
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

	public UserModel(String username, String password, boolean isPrivate, String mail, String firstName,
			String lastName, String token, CountryModel country) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.isPrivate = isPrivate;
		this.token = token;
		this.country = country;
	}

	public UserModel(String username, String password, boolean enabled, String firstName, String lastName, String mail,
			CountryModel country, String token, boolean isPrivate) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.country = country;
		this.token = token;
		this.isPrivate = isPrivate;
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
			CountryModel country, boolean isPrivate, PictureModel profilePicture) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.country = country;
		this.isPrivate = isPrivate;
		this.profilePicture = profilePicture;
	}

	public UserModel(String username, boolean enabled, String password, String firstname, String lastname, String eMail,
			CountryModel country, boolean isPrivate, String token) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstname;
		this.lastName = lastname;
		this.mail = eMail;
		this.enabled = true;
		this.country = country;
		this.isPrivate = true;
		this.token = token;
	}

	public UserModel(String username, boolean enabled, String password, String firstname, String lastname, String eMail,
			String token) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstname;
		this.lastName = lastname;
		this.mail = eMail;
		this.enabled = true;
		this.token = token;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
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
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (enabled != other.enabled)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
