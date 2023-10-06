package med.vol.api.domain.consulta;

import med.vol.api.infra.exception.ValidacaoException;
import med.vol.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import med.vol.api.domain.medico.Medico;
import med.vol.api.domain.medico.MedicoRepository;
import med.vol.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("ID do paciente não existe!");
        }

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("ID do médico não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.findById(dados.idPaciente()).get();

        if (medico == null){
            throw new ValidacaoException("Não existe médico disponível para essa especialidade nesta data");
        }

        var consulta = new Consulta(medico, paciente, dados.data());

        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }



    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null){
            return medicoRepository.findById(dados.idMedico()).get();
        }

        if (dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória quando médico não escolhido!");
        }
        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public DadosDetalhamentoConsulta deletar(DadosDeletandoConsulta dados) {
        var consulta = consultaRepository.findById(dados.id()).get();
        if (!consulta.getAtivo()){
            throw new ValidacaoException("Consulta não encontrada!");
        }
        var hoje = LocalDateTime.now();
        var dataconsulta = consulta.getData();
        var diferenca = Duration.between(hoje, dataconsulta).toHours();

        if (diferenca < 24){
            throw new ValidacaoException("Consultas só podem ser desmarcadas com 24h de antecedência");
        }
        var cancelandoConsulta = consultaRepository.getReferenceById(consulta.getId());
        cancelandoConsulta.excluir(dados.motivo());

        return null;
    }
}