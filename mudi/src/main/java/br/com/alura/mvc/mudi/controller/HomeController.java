package br.com.alura.mvc.mudi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	PedidoRepository pedidosRepository;

	@GetMapping
	public String home(Model model) {

		Sort sort = Sort.by("dataDaEntrega").descending(); // ordenando os pedidos por ordem decrecente de data, pelo SpringData

		PageRequest paginacao = PageRequest.of(0, 10, sort); // aqui traz uma pagina com 10 pedidos no maximo

		List<Pedido> pedidos = pedidosRepository.findByStatus(StatusPedido.ENTREGUE, paginacao);
		model.addAttribute("pedidos", pedidos);
		return "home";
	}

	// quando houver esta exeção ele redirecionara para home
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}

}
