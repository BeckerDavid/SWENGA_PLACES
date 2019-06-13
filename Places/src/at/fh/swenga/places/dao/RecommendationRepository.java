package at.fh.swenga.places.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.places.model.CountryModel;
import at.fh.swenga.places.model.RecommendationModel;

@Repository
@Transactional
public interface RecommendationRepository extends JpaRepository<RecommendationModel, Integer>{

	
	
}
