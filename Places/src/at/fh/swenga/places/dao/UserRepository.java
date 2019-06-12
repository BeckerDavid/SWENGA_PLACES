package at.fh.swenga.places.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.places.model.UserModel;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserModel, Integer> {

	//Query2
	@Query("SELECT m FROM MakeupModel m WHERE LOWER(m.name) LIKE CONCAT('%', LOWER(:searchString), '%')")
	List<UserModel> findUserWithID(
		@Param("searchString") String input);
	
}
