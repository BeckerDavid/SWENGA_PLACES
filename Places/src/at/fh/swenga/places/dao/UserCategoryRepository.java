package at.fh.swenga.places.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import at.fh.swenga.places.model.UserCategoryModel;

@Repository
@Transactional
public interface UserCategoryRepository extends JpaRepository<UserCategoryModel, Integer> {

	public List<UserCategoryModel> findAll();

	public UserCategoryModel findByRole(String role);

}
