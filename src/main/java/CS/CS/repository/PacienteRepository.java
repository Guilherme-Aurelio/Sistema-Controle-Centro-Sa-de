package CS.CS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import CS.CS.domain.paciente.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{
    
}
