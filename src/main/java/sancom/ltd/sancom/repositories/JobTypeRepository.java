package sancom.ltd.sancom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sancom.ltd.sancom.entities.EntityJobType;


import java.util.List;

public interface JobTypeRepository extends JpaRepository<EntityJobType, Long> {


    EntityJobType findDistinctById(long id);

    List<EntityJobType> findAll();
}
