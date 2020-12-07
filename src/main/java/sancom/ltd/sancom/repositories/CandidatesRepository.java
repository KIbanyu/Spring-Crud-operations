package sancom.ltd.sancom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sancom.ltd.sancom.entities.CandidatesEntity;

public interface CandidatesRepository extends JpaRepository<CandidatesEntity, Long> {
}
