package med.vol.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
/**
 *   herdamos a interface JpaRepository e passamos a Classe e o tipo do atributo do id (Long)
 *     desta forma criamos a interface repository que herdará todos os métodos da JpaRepository
 *     e estará pronta para uso.
 */
public interface MedicoRepository extends JpaRepository <Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select m from Medico m where
            m.ativo = true and 
            m.especialidade = :especialidade and
            m.id not in (
                select c.medico.id  from Consulta c
                where
                c.data = :data
            )
            order by rand()
            limit 1
            """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    @Query("""
            select m.ativo from Medico m
            where m.id = :id
            """)
    boolean findAtivoById(Long id);
}
