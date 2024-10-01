package br.com.mleite.sistema.academia.domain.entity;

import br.com.mleite.sistema.academia.config.CustomizacaoDateDeserializador;
import br.com.mleite.sistema.academia.domain.entity.enums.PlanoCliente;
import br.com.mleite.sistema.academia.validation.OnCreate;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCLIENTE")
    private Long idCliente;

    @NotEmpty(message = "{campo.nome.obrigatorio}", groups = OnCreate.class)
    private String nome;

    @NotEmpty(message = "{campo.sobrenome.obrigatorio}", groups = OnCreate.class)
    private String sobrenome;

    @JsonDeserialize(using = CustomizacaoDateDeserializador.class)
    @Temporal(TemporalType.DATE)
    @Column(name = "DATANASCIMENTO")
    private Date dataNascimento;

    @Column(length = 11)
    @NotEmpty(message = "{campo.cpf.obrigatorio}", groups = OnCreate.class)
    @CPF(message = "{campo.cpf.invalido}", groups = OnCreate.class)
    private String cpf;

    private String email;
    private String telefone;

    @Column(name = "PLANOCLIENTE")
    @Enumerated(EnumType.STRING)
    private PlanoCliente planoCliente;

    @JsonDeserialize(using = CustomizacaoDateDeserializador.class)
    @Temporal(TemporalType.DATE)
    @Column(name = "DATAINICIOPLANO")
    private Date dataInicioPlano;

    @JsonDeserialize(using = CustomizacaoDateDeserializador.class)
    @Temporal(TemporalType.DATE)
    @Column(name = "DATAFIMPLANO")
    private Date dataFimPlano;

    @Column(name = "DIASENCERRAMENTOPLANO")
    private Long diasEncerramentoPlano;

    @Column(name = "DATAINCLUSAO")
    private java.sql.Timestamp dataInclusao;

}
