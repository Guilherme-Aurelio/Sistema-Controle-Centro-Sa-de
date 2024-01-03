package CS.CS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import CS.CS.domain.consulta.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>{
    
}
