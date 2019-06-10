package at.fh.swenga.places.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.places.model.UserModel;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserModel, Integer> {

}
