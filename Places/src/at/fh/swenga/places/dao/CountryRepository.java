package at.fh.swenga.places.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.places.model.CountryModel;
import at.fh.swenga.places.model.JourneyModel;

@Repository
@Transactional
public interface CountryRepository extends JpaRepository<CountryModel, Integer> {
	
}
