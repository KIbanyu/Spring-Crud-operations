package sancom.ltd.sancom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sancom.ltd.sancom.entities.CandidatesEntity;

import java.util.Optional;

public interface CandidatesRepository extends JpaRepository<CandidatesEntity, Long> {

    Optional<CandidatesEntity> findDistinctByEmail(String email);
    Optional<CandidatesEntity> findDistinctByPhone(String phone);
    CandidatesEntity findDistinctById(long id);
}
