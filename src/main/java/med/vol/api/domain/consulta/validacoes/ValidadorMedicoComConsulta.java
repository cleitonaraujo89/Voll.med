package med.vol.api.domain.consulta.validacoes;

import med.vol.api.infra.exception.ValidacaoException;
import med.vol.api.domain.consulta.ConsultaRepository;
import med.vol.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComConsulta implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private ConsultaRepository repository;

    public void validar (DadosAgendamentoConsulta dados){
        var medicoPossuiConsulta = repository.existsByMedicoIdAndDataAndAtivoTrue(dados.idMedico(), dados.data());
//        var consultaAtiva = repository.findAtivoById(dados.);
//        if ()
        if (medicoPossuiConsulta){
            throw new ValidacaoException("Médico solicitado possui outra consulta no mesmo horário");
        }
    }
}
