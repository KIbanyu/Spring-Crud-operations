package sancom.ltd.sancom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sancom.ltd.sancom.entities.EntityEducationLevels;

import java.util.List;

public interface EducationLevelsRepository extends JpaRepository<EntityEducationLevels, Long> {

    List<EntityEducationLevels> findAll();

    EntityEducationLevels findDistinctById(long id);

}
