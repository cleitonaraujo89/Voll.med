package med.vol.api.domain.consulta;

import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.consulta.validacoes.MotivoCancelamento;

public record DadosDeletandoConsulta(
        @NotNull
        Long id,
        @NotNull
        MotivoCancelamento motivo

) {
}
