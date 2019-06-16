package at.fh.swenga.places.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import at.fh.swenga.places.model.UserCategoryModel;
import at.fh.swenga.places.model.UserModel;

@Repository
@Transactional
public interface UserCategoryRepository extends JpaRepository<UserCategoryModel, Integer>{

}
 

