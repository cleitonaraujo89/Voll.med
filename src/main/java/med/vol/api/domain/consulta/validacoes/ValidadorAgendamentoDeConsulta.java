package med.vol.api.domain.consulta.validacoes;

import med.vol.api.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosAgendamentoConsulta dados); // todos os validadores tem a mesma chamada de método
}
