package at.fh.swenga.places.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.places.model.PictureModel;

@Repository
@Transactional
public interface PictureRepository extends JpaRepository<PictureModel, Integer> {

}
