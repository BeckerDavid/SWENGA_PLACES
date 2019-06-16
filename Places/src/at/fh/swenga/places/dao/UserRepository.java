package at.fh.swenga.places.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.places.model.UserModel;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserModel, Integer> {

	UserModel findByUsername(String username);

	UserModel findFirstByUsername(String username);
	
	@Query("Select u From UserModel u where u.username = :username")
	UserModel getDefaultUser(@Param("username") String username);

	
}
