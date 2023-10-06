package med.vol.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository <Consulta, Long>{
    boolean  existsByMedicoIdAndData(Long idMedico, LocalDateTime data);

    Boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    @Query("""
            select c.ativo from Consulta c
            where c.id = :id
            """)
    Boolean findAtivoById(Long id);

    Boolean existsByMedicoIdAndDataAndAtivoTrue(Long aLong, LocalDateTime data);

    Consulta findByPacienteIdAndDataBetween(Long aLong, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
