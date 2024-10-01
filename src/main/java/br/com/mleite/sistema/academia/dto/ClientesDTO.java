package br.com.mleite.sistema.academia.dto;

import br.com.mleite.sistema.academia.config.CustomizacaoDateDeserializador;
import br.com.mleite.sistema.academia.domain.entity.enums.PlanoCliente;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ClientesDTO {

    @Id
    private Long idCliente;

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

}
