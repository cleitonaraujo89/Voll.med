package med.vol.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vol.api.domain.endereco.DadosEndereco;

public record DadosCadastroMedico (
        @NotBlank // anotação de validação, checa se está nulo e vazio
        String nome,
        @NotBlank
        @Email // validação de Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}") // Validação de Dígitos = de 4 a 6 digitos
        String crm,
        @NotNull // como não é uma String é um enum... colocamos not null
        Especialidade especialidade,
        @Valid // essa marcação diz para o beanValidation validar dentro desse DTO tambem
        DadosEndereco endereco) {

}
