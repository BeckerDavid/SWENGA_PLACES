package at.fh.swenga.places.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import at.fh.swenga.places.model.JourneyModel;
import at.fh.swenga.places.model.UserModel;

public interface JourneyRepository extends JpaRepository<JourneyModel, Integer> {

	ArrayList<JourneyModel> findByUsersId(int id);
	
}
