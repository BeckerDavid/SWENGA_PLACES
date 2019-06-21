package at.fh.swenga.places.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.places.model.RecommendationModel;

@Repository
@Transactional
public interface RecommendationRepository extends JpaRepository<RecommendationModel, Integer>{

	public ArrayList<RecommendationModel> findByUserId(int id);
	
	//Query2
			@Query("SELECT r "
			+ "FROM  RecommendationModel AS r "
			+ "JOIN r.place AS p "
			+ "WHERE p.country.id = :countryId OR 0 = :countryId "
			+ "AND (LOWER(r.title) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.description) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.season) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.user.username) LIKE CONCAT('%', LOWER(:searchString), '%')) "
			+ "ORDER BY r.id DESC ")
			public List<RecommendationModel> listNewest(
					@Param("countryId") int countryId,
					@Param("searchString") String searchString);
	
	//Query3
			@Query("SELECT r "
			+ "FROM  RecommendationModel AS r "
			+ "JOIN r.place AS p "
			+ "WHERE p.country.id = :countryId OR 0 = :countryId "
			+ "AND (LOWER(r.title) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.description) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.season) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.user.username) LIKE CONCAT('%', LOWER(:searchString), '%')) "
			+ "ORDER BY p.name ")
			public List<RecommendationModel> listByPlaces(
					@Param("countryId") int countryId,
					@Param("searchString") String searchString);
	//Query4		
			@Query("SELECT r "
			+ "FROM  RecommendationModel AS r "
			+ "JOIN r.place AS p "
			+ "WHERE p.country.id = :countryId OR 0 = :countryId "
			+ "AND (LOWER(r.title) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.description) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.season) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.user.username) LIKE CONCAT('%', LOWER(:searchString), '%')) "			
			+ "ORDER BY r.season ")
			public List<RecommendationModel> listBySeason(
					@Param("countryId") int countryId,
					@Param("searchString") String searchString);
	//Query5		
			@Query("SELECT r "
			+ "FROM  RecommendationModel AS r "
			+ "JOIN r.place AS p "
			+ "WHERE p.country.id = :countryId OR 0 = :countryId "
			+ "AND (LOWER(r.title) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.description) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.season) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.user.username) LIKE CONCAT('%', LOWER(:searchString), '%')) "			
			+ "ORDER BY r.user.username ")
			public List<RecommendationModel> listByUsername(
					@Param("countryId") int countryId,
					@Param("searchString") String searchString);
			
			
			
	//Query6
			
			@Query("SELECT r "
			+ "FROM  RecommendationModel AS r "
			+ "JOIN r.place AS p "
			+ "WHERE p.country.id = :countryId OR 0 = :countryId "
			+ "AND (LOWER(r.title) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.description) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.season) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.user.username) LIKE CONCAT('%', LOWER(:searchString), '%')) "
			+ "ORDER BY r.id DESC ")
			public List<RecommendationModel> getRecommendationForJourney(
					@Param("countryId") int countryId,
					@Param("searchString") String searchString);
	
			
			/*
			 * SELECT r.title, c.countryName
			FROM IMA17_gradwohl_SWENGA_project_2.Recommendations AS r
			JOIN IMA17_gradwohl_SWENGA_project_2.Place AS p ON r.place_id = p.id
			JOIN IMA17_gradwohl_SWENGA_project_2.Country AS c ON p.country_id = c.id
			JOIN IMA17_gradwohl_SWENGA_project_2.Journey_Country AS jc ON c.id = jc.countries_id
			JOIN IMA17_gradwohl_SWENGA_project_2.Journey AS j ON jc.journeys_id = j.id 
			JOIN IMA17_gradwohl_SWENGA_project_2.users AS u ON j.users_id
			ORDER BY c.countryName;
			
			@Query("SELECT r "
			+ "FROM  RecommendationModel AS r "
			+ "JOIN r.place AS p "
			+ "JOIN p.country AS c "
			+ "JOIN c.journey_country AS jc "
			+ "JOIN jc.journey AS j 
			+ "JOIN j.users AS u "
			+ "WHERE p.country.id = :countryId OR 0 = :countryId "
			+ "AND (LOWER(r.title) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.description) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.season) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.user.username) LIKE CONCAT('%', LOWER(:searchString), '%')) "			
			+ "ORDER BY r.user.username "	
			+ "ORDER BY c.countryName ")
			public List<RecommendationModel> listByJourneyCountry();
			
*/
			
			@Query("SELECT r "
			+ "FROM RecommendationModel AS r "
			+ "WHERE id = :recId")
			public RecommendationModel findById(
					@Param("recId") int recId);
			
			public List<RecommendationModel> removeById(int id);
}
