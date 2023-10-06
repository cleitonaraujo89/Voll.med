package med.vol.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.domain.endereco.Endereco;

@Table(name = "medicos") // anotação do JPA
@Entity(name = "Medico") // anotação JPA
@Getter // anotação lombok para gerar os getters
@NoArgsConstructor // anotação lombok para gerar o contrutor vazio
@AllArgsConstructor // anotação lombok para gerar o contrutor com todos
@EqualsAndHashCode(of = "id") // anotação lombok gera o equals eo hashcode no id
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedico dados) { //recebe um objeto do tipo DadosCadastroMedico
        this.ativo = true; // atribuido na criação
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }

    }

    public void excluir(){
        this.ativo = false;
    }
}
