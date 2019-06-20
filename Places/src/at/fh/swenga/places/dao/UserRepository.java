package at.fh.swenga.places.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.places.model.UserModel;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserModel, Integer> {

	public UserModel findById(int id);
	
	public UserModel findByUsername(String username);

	public UserModel findFirstByUsername(String username);
	
	public UserModel findByToken(String token);
	
	public UserModel findFirstByToken(String token);
	
	@Query("Select u From UserModel u where u.username = :username")
	public UserModel getDefaultUser(@Param("username") String username);
	
	@Transactional
	@Modifying
	@Query("Update UserModel SET enabled = 0 WHERE id = :id")
	void disableUser(@Param("id") int id);
	
	@Transactional
	@Modifying
	@Query("Update UserModel SET enabled = 1 WHERE id = :id")
	void enableUser(@Param("id") int id);
	
	@Transactional
	@Modifying
	@Query("INSERT into users_Recommendations VALUES(:uId, :rId")
	void addFavRec(@Param("uId") int uId, @Param("rId") int rId);
	
	/*@Query("UPDATE UserModel "
			+ "JOIN CountryModel ON "
			+ "SET username = :username, firstName = :firstName, lastName = :lastName, mail = :mail, country = :CountryModel")
	UserModel updateUserProfile(@Param(username))*/
	


	
}


