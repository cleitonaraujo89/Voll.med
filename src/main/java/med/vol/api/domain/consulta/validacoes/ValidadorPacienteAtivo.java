package med.vol.api.domain.consulta.validacoes;

import med.vol.api.infra.exception.ValidacaoException;
import med.vol.api.domain.consulta.DadosAgendamentoConsulta;
import med.vol.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private PacienteRepository repository;

    public void validar (DadosAgendamentoConsulta dados){

        var pacienteAtivo = repository.findAtivoById(dados.idPaciente());
        if (!pacienteAtivo){
            throw new ValidacaoException("Paciente solicitado encontra-se inativo no sistema");
        }
    }
}
