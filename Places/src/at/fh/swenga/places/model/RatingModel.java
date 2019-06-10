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
@Table(name = "Rating")
public class RatingModel {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(mappedBy = "rating", fetch = FetchType.LAZY)
	private Set<RecommendationModel> recommendations;

	@Column(nullable = false, length = 30)
	private String ratingLevel;

	public RatingModel(String ratingLevel) {
		super();
		this.ratingLevel = ratingLevel;
	}

	public RatingModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<RecommendationModel> getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(Set<RecommendationModel> recommendations) {
		this.recommendations = recommendations;
	}

	public String getRatingLevel() {
		return ratingLevel;
	}

	public void setRatingLevel(String ratingLevel) {
		this.ratingLevel = ratingLevel;
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
		RatingModel other = (RatingModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RatingModel [id=" + id + ", recommendations=" + recommendations + ", ratingLevel=" + ratingLevel + "]";
	}

}
