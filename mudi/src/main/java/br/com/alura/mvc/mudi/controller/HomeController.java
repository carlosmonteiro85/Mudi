package br.com.alura.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String home(Model model, Principal principal) {

		List<Pedido> pedidos = pedidosRepository.findAllByUsuario(principal.getName());
		model.addAttribute("pedidos", pedidos);
		return "home";
	}

	// esta recebendo um valor na url q é jogado na variavel "statusPedido"
	@GetMapping("/{status}")
	//É declarado com a anotação @PathVariable esta variavel, do tipo String
	public String porStatus(@PathVariable("status") String status, Model model) {
		//A variável é convertida em Enum pelo método valueOf que recebe uma String e é usado o método
		//toUpperCase para transformar toda a String em maiuscula para que aceito no Enum
		List<Pedido> pedidos = pedidosRepository.findByStatus(StatusPedido.valueOf(status.toUpperCase()));
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status", status);
		return "home";
	}
	//quando houver esta exeção ele redirecionara para home
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}
	


}
