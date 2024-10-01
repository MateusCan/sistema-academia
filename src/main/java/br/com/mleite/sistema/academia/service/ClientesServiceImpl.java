package br.com.mleite.sistema.academia.service;

import br.com.mleite.sistema.academia.domain.entity.Clientes;
import br.com.mleite.sistema.academia.domain.repository.ClientesRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientesServiceImpl {

    private final ClientesRepository clientesRepository;

    public Clientes criarCliente(Clientes clientes){

        Clientes cliente = montaEntidade(clientes);
        cliente.setDataInclusao(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        return clientesRepository.save(cliente);
    }

    public Clientes atualizarCliente(Clientes clienteExistente) {
        return clientesRepository.save(montaEntidade(clienteExistente));
    }

    @PostConstruct
    public void init(){
        atualizarDiasEncerramentoPlano();
    }

    private void atualizarDiasEncerramentoPlano() {
        List<Clientes> clientes = clientesRepository.findAll(); // Buscando todos os clientes
        Date hoje = new Date();

        for (Clientes cliente : clientes) {
            if (cliente.getDataFimPlano() != null) {

                long diasEncerramentoPlano = cliente.getDataFimPlano().getTime() - hoje.getTime();
                diasEncerramentoPlano = diasEncerramentoPlano / (1000 * 60 * 60 * 24) +1;
                cliente.setDiasEncerramentoPlano(diasEncerramentoPlano);
            }
        }

        clientesRepository.saveAll(clientes);
    }

    private Clientes montaEntidade(Clientes clientes) {
        LocalDate dataInicioPlano = LocalDate.now();
        clientes.setDataInicioPlano(java.sql.Date.valueOf(dataInicioPlano));

        LocalDate dataFimPlano;

        switch (clientes.getPlanoCliente()) {
            case MENSAL:
                dataFimPlano = dataInicioPlano.plusMonths(1);
                break;
            case TRIMESTRAL:
                dataFimPlano = dataInicioPlano.plusMonths(3);
                break;
            case SEMESTRAL:
                dataFimPlano = dataInicioPlano.plusMonths(6);
                break;
            case ANUAL:
                dataFimPlano = dataInicioPlano.plusYears(1);
                break;
            default:
                throw new IllegalArgumentException("Plano inválido: " + clientes.getPlanoCliente());
        }

        long diasEncerramentoPlano = ChronoUnit.DAYS.between(LocalDate.now(), dataFimPlano);
        clientes.setDiasEncerramentoPlano(diasEncerramentoPlano);

        clientes.setDataFimPlano(java.sql.Date.valueOf(dataFimPlano));
        return clientes;
    }
}
