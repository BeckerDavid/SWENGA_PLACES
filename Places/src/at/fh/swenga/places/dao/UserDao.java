package at.fh.swenga.places.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.places.model.UserModel;

@Repository
@Transactional
public class UserDao {

	@PersistenceContext
	protected EntityManager entityManager;
 
	public List<UserModel> findByUsername(String username) {
		TypedQuery<UserModel> typedQuery = entityManager.createQuery("select u from UserModel u where u.username = :name",
				UserModel.class);
		typedQuery.setParameter("name", username);
		List<UserModel> typedResultList = typedQuery.getResultList();
		return typedResultList;
	}
 
	public void persist(UserModel user) {
		entityManager.persist(user);
	}
}
