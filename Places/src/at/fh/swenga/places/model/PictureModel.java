package at.fh.swenga.places.model;

import java.util.Arrays;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Pictures")
public class PictureModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] recPicture;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private RecommendationModel recommendation;

	public PictureModel(byte[] recPicture, RecommendationModel recommendation) {
		super();
		this.recPicture = recPicture;
		this.recommendation = recommendation;
	}

	public PictureModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getRecPicture() {
		return recPicture;
	}

	public void setRecPicture(byte[] recPicture) {
		this.recPicture = recPicture;
	}

	public RecommendationModel getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(RecommendationModel recommendation) {
		this.recommendation = recommendation;
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
		PictureModel other = (PictureModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PictureModel [id=" + id + ", recPicture=" + Arrays.toString(recPicture) + ", recommendation="
				+ recommendation + "]";
	}

}
