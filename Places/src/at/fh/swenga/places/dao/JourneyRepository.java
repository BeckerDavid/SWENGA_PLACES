package at.fh.swenga.places.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import at.fh.swenga.places.model.JourneyModel;

public interface JourneyRepository extends JpaRepository<JourneyModel, Integer> {

}
