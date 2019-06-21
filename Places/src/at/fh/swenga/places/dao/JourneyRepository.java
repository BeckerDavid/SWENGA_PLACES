package at.fh.swenga.places.dao;

import java.util.ArrayList;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.places.model.JourneyModel;

@Repository
@Transactional
public interface JourneyRepository extends JpaRepository<JourneyModel, Integer> {

	ArrayList<JourneyModel> findByUsersId(int id);
	
}
