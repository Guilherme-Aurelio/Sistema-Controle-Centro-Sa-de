package CS.CS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import CS.CS.domain.medico.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{
    
}
