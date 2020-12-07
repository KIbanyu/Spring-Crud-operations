package sancom.ltd.sancom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sancom.ltd.sancom.entities.JobsEntity;

import java.util.List;


public interface JobsRepository extends JpaRepository<JobsEntity, Long> {

    List<JobsEntity> findAll();

    JobsEntity  findDistinctById(long id);

}
