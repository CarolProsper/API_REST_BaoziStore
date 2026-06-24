package com.example.BaoziStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BaoziStore.model.Cliente;
import com.example.BaoziStore.model.Pedido;
import com.example.BaoziStore.model.Produto;
import com.example.BaoziStore.repository.ClienteRepository;
import com.example.BaoziStore.repository.PedidoRepository;
import com.example.BaoziStore.repository.ProdutoRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@PostMapping
	public Pedido salvar(@RequestBody Pedido pedido) {

		Cliente cliente = clienteRepository.findById(pedido.getCliente().getId()).orElseThrow();

		Produto produto = produtoRepository.findById(pedido.getProduto().getId()).orElseThrow();

		pedido.setCliente(cliente);
		pedido.setProduto(produto);

		return pedidoRepository.save(pedido);
	}

	@GetMapping
	public List<Pedido> listar() {
		return pedidoRepository.findAll();
	}

	@GetMapping("/{id}")
	public Pedido buscar(@PathVariable Long id) {
		return pedidoRepository.findById(id).orElse(null);
	}

	@PutMapping("/{id}")
	public Pedido atualizar(@PathVariable Long id, @RequestBody Pedido novoPedido) {

		Pedido pedido = pedidoRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
		
		Cliente cliente = clienteRepository
	            .findById(novoPedido.getCliente().getId())
	            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

	    Produto produto = produtoRepository
	            .findById(novoPedido.getProduto().getId())
	            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

		pedido.setCliente(cliente);
		pedido.setProduto(produto);
		pedido.setQuantidade(novoPedido.getQuantidade());

		return pedidoRepository.save(pedido);
	}

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		pedidoRepository.deleteById(id);
	}
}
