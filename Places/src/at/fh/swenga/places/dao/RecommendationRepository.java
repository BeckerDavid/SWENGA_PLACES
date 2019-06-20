package at.fh.swenga.places.dao;

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

	//Query2
			@Query("SELECT r "
			+ "FROM  RecommendationModel AS r "
			+ "JOIN r.place AS p "
			+ "ORDER BY p.name ")
			public List<RecommendationModel> listByPlaces();
			
			@Query("SELECT r "
			+ "FROM  RecommendationModel AS r "
			+ "ORDER BY r.season ")
			public List<RecommendationModel> listBySeason();

			@Query("SELECT r "
			+ "FROM  RecommendationModel AS r "
			+ "WHERE LOWER(r.season) LIKE CONCAT('%', LOWER(:searchString), '%') "					
			+ "ORDER BY r.season ")
			public List<RecommendationModel> searchBySeason(
					@Param("searchString") String input);

			@Query("SELECT r "
			+ "FROM  RecommendationModel AS r "
			+ "JOIN r.user AS u "				
			+ "ORDER BY u.username ")
			public List<RecommendationModel> listByUsername();
			
			@Query("SELECT r "
					+ "FROM RecommendationModel AS r "
					+ "WHERE LOWER(r.title) LIKE CONCAT('%', LOWER(:searchString), '%') "
					+ "ORDER BY r.title" )
			public List<RecommendationModel> findByTitle(
					@Param("searchString") String input);
			
			
			/*				
//			//Query3
			@Query("SELECT r "
			+ "FROM  RecommendationModel AS r "
			+ "JOIN r.place AS p "
			+ "ORDER BY p.name ")	
			List<RecommendationModel> listByRating ();
		
//			//Query4 by name or colour als Named Query
			List<RecommendationModel> findByNameOrColour(
					@Param("searchString") String input);

//			//Query5 sort by rating
			@Query("SELECT m FROM RecommendationModel m ORDER BY m.rating DESC")
			List<RecommendationModel> orderByRating();
			
//			//Query6 count makeups by Producer
			int countByColour(String input);
			
//			//Query7	delete with given name
			List<RecommendationModel> removeByName(String name);
				
//			Query8 list by producer
			@Query("SELECT m "
					+ "FROM RecommendationModel AS m "
					+ "JOIN m.producer AS p "
					+ "WHERE LOWER(p.brandName) LIKE CONCAT('%', LOWER(:searchString), '%') "
					+ "ORDER BY p.brandName")
			List<RecommendationModel> prodcuerForMakeup(
					@Param("searchString")String input);
			
//			//Query9 Ordered list by Price
			@Query("SELECT m FROM RecommendationModel m ORDER BY m.price DESC")
			List<RecommendationModel> orderByPrice();
			
//			//Query10 Makeups by name ascending
			List<RecommendationModel> findTop5ByOrderByName();
			
//			//Query11  delete Expirirng today
			List<RecommendationModel> removeByExpirationDate(LocalDate date);		
			
			
//			//Query12 sort by expiration date
			List<RecommendationModel> findByExpirationDateBetween(LocalDate startDate, LocalDate endDate );		
			
			//Query 13 search for a country of producer
//			@Query("SELECT m "
//					+ "FROM RecommendationModel AS m "
//					+ "JOIN m.producer AS p "
//					+ "JOIN p.country AS c "
//					+ "WHERE c.name =: searchString "
//					+ "ORDER BY c.name")
//			List<RecommendationModel> countryForProducer(
//					@Param("searchString")String input);
	*/
}
