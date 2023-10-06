package med.vol.api.domain.consulta.validacoes;

import med.vol.api.infra.exception.ValidacaoException;
import med.vol.api.domain.consulta.DadosAgendamentoConsulta;
import med.vol.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {

        if(dados.idMedico() == null){
            return;
        }

        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if (!medicoEstaAtivo){
            throw new ValidacaoException("Médico solicitado encontra-se inativo no sistema");
        }
    }
}
