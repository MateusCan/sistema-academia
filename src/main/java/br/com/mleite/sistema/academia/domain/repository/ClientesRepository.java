package br.com.mleite.sistema.academia.domain.repository;

import br.com.mleite.sistema.academia.domain.entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClientesRepository extends JpaRepository<Clientes, Long> {

    List<Clientes> findByNomeContaining(String nome);
}
