package br.com.marvel.loja.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PromoService promoService;

	@Autowired
	private EntityManager em;

	@PostMapping
	public ResponseEntity<?> cadastroCliente(@Valid @RequestBody ClienteMapper clienteRequest, UriComponentsBuilder uriBuilder) {

		Cliente cliente = clienteRequest.map();
		clienteRepository.save(cliente);
		URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(cliente);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> listaCliente(@PathVariable Long id) {

		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isEmpty()) {
			return ResponseEntity.badRequest().body("Cliente não Cadastrado");
		}

		return ResponseEntity.ok(cliente.get());
	}

	@GetMapping("{id}/promo")
	@Transactional
	public ResponseEntity<?> listaClientePromo(@PathVariable Long id) {

		Optional<Cliente> clienteSql = clienteRepository.findById(id);
		if (clienteSql.isEmpty()) {
			return ResponseEntity.badRequest().body("Cliente não Cadastrado");
		}
		Cliente cliente = clienteSql.get();

		System.out.println("Preco sem desconto " + cliente.getComics().get(1).getPrecos().get(0).getPreco());
		for (int i = 0; i < cliente.getComics().size(); i++) {

			String isbn = "1";
			Boolean bol = promoService.checkDiaPromo(isbn);
			if (bol) {
				cliente.getComics().get(i).setPromoDay(bol);
			}


		}
		
		for (int y = 0;y < cliente.getComics().size();y++) {
			for(int x = 0;x < cliente.getComics().get(x).getPrecos().size();x++) {
				System.out.println("Preco sem desconto " + cliente.getComics().get(y).getPrecos().get(x).getPreco());
				cliente.getComics().get(y).getPrecos().get(x).setPreco();
				System.out.println("Preco com desconto " + cliente.getComics().get(y).getPrecos().get(x).getPreco());
			}
		}
		
		
		em.persist(cliente);


		return ResponseEntity.ok(cliente);
	}

}