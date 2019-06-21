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
public interface RecommendationRepository extends JpaRepository<RecommendationModel, Integer> {

	public ArrayList<RecommendationModel> findByUserId(int id);

	// Query2
	@Query("SELECT r " + "FROM  RecommendationModel AS r " + "JOIN r.place AS p "
			+ "WHERE p.country.id = :countryId OR 0 = :countryId "
			+ "AND (LOWER(r.title) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.description) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(p.name) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.season) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.user.username) LIKE CONCAT('%', LOWER(:searchString), '%')) " + "ORDER BY r.id DESC ")
	public List<RecommendationModel> listNewest(@Param("countryId") int countryId,
			@Param("searchString") String searchString);

	// Query3
	@Query("SELECT r " + "FROM  RecommendationModel AS r " + "JOIN r.place AS p "
			+ "WHERE p.country.id = :countryId OR 0 = :countryId "
			+ "AND (LOWER(r.title) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.description) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(p.name) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.season) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.user.username) LIKE CONCAT('%', LOWER(:searchString), '%')) " + "ORDER BY p.name ")
	public List<RecommendationModel> listByPlaces(@Param("countryId") int countryId,
			@Param("searchString") String searchString);

	// Query4
	@Query("SELECT r " + "FROM  RecommendationModel AS r " + "JOIN r.place AS p "
			+ "WHERE p.country.id = :countryId OR 0 = :countryId "
			+ "AND (LOWER(r.title) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.description) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(p.name) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.season) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.user.username) LIKE CONCAT('%', LOWER(:searchString), '%')) " + "ORDER BY r.season ")
	public List<RecommendationModel> listBySeason(@Param("countryId") int countryId,
			@Param("searchString") String searchString);

	// Query5
	@Query("SELECT r " + "FROM  RecommendationModel AS r " + "JOIN r.place AS p "
			+ "WHERE p.country.id = :countryId OR 0 = :countryId "
			+ "AND (LOWER(r.title) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.description) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(p.name) LIKE CONCAT('%', LOWER(:searchString), '%')  "
			+ "OR LOWER(r.season) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.user.username) LIKE CONCAT('%', LOWER(:searchString), '%')) " + "ORDER BY r.user.username ")
	public List<RecommendationModel> listByUsername(@Param("countryId") int countryId,
			@Param("searchString") String searchString);

	// Query6
	@Query("SELECT r " + "FROM  RecommendationModel AS r " + "JOIN r.place AS p "
			+ "WHERE p.country.id = :countryId OR 0 = :countryId "
			+ "AND (LOWER(r.title) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.description) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(p.name) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.season) LIKE CONCAT('%', LOWER(:searchString), '%') "
			+ "OR LOWER(r.user.username) LIKE CONCAT('%', LOWER(:searchString), '%')) " + "ORDER BY r.id DESC ")
	public List<RecommendationModel> getRecommendationForJourney(@Param("countryId") int countryId,
			@Param("searchString") String searchString);

	@Query("SELECT r " + "FROM RecommendationModel AS r " + "WHERE id = :recId")
	public RecommendationModel findById(@Param("recId") int recId);

	public List<RecommendationModel> removeById(int id);
}
