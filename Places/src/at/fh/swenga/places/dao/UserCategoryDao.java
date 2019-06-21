package at.fh.swenga.places.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.places.model.UserCategoryModel;

@Repository
@Transactional
public class UserCategoryDao {

	@PersistenceContext
	protected EntityManager entityManager;

	public UserCategoryModel getRole(String role) {
		try {
			TypedQuery<UserCategoryModel> typedQuery = entityManager
					.createQuery("select ur from UserCategoryModel ur where ur.role= :role", UserCategoryModel.class);
			typedQuery.setParameter("role", role);
			return typedQuery.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void persist(UserCategoryModel userRole) {
		entityManager.persist(userRole);
	}

}
