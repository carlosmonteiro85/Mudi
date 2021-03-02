package br.com.alura.mvc.mudi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;

//indica ao spring 	que a classe é um repositorio, e que o spring deve instanciala
//e quando esta interface estende o JpaRepository vede se anortar a entidade da persistencia
//e o tipo da chave primaria, os metodos principais são herdados dessa classe

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	List<Pedido> findByStatus(StatusPedido status);

}
