package at.fh.swenga.places.model;

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
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	private RecommendationModel recommendation;
}
