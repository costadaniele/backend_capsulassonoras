package br.fatec.capsula.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.fatec.capsula.model.Integrante;
import br.fatec.capsula.service.IntegranteService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping(value = "/integrantes")
public class IntegranteController implements ControllerInterface<Integrante> {
	
	@Autowired
	private IntegranteService service;
	
	@Override
	@GetMapping
	@ApiResponses(value = {
			 @ApiResponse(responseCode = "200", 
			 description = "Retorna a lista de integrantes"),
			 @ApiResponse(responseCode = "403", 
			 description = "Você não tem permissão para acessar este recurso"),
			 @ApiResponse(responseCode = "500", 
			 description = "Foi gerada uma exceção"),
			})
	
	public ResponseEntity<List<Integrante>>get() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@ApiResponses(value = {
			 @ApiResponse(responseCode = "200", 
			 description = "Retorna a lista de integrantes"),
			 @ApiResponse(responseCode = "404", 
			 description = "Não há uma pessoa física com o id fornecido"),
			 @ApiResponse(responseCode = "403", 
			 description = "Você não tem permissão para acessar este recurso"),
			 @ApiResponse(responseCode = "401", 
			 description = "Você não tem autoização para esse recurso"),
			})
	
	@Override
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		Integrante _integrante = service.findById(id);
		if (_integrante != null) {
			return ResponseEntity.ok(_integrante);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	} 
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<Integrante>> get(Pageable pageable) {
		return ResponseEntity.ok(service.findAll(pageable));
	}
	
	@GetMapping(value = "/capsula/{id}")
	public ResponseEntity<Page<Integrante>> getByCapsula(Pageable pageable, @PathVariable("id") Long capsulaId) {
		return ResponseEntity.ok(service.findByCapsula(pageable, capsulaId));
	}
	
	@GetMapping(value = "/regiao/{id}")
	public ResponseEntity<Page<Integrante>> getByRegiao(Pageable pageable, @PathVariable("id") Long regiaoId) {
		return ResponseEntity.ok(service.findByRegiao(pageable, regiaoId));
	}
	
	@GetMapping(value = "/count/{id}")
	public ResponseEntity<Long> countByRegiao(@PathVariable("id") Long regiaoId) {
		return ResponseEntity.ok(service.countByRegiao(regiaoId));
	}
	
	@Override
	@PostMapping
	public ResponseEntity<Integrante> post(@Valid @RequestBody Integrante obj) {
		service.create(obj);
		URI uri = ServletUriComponentsBuilder
		        .fromCurrentRequest().path("/{id}")
		        .buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@Override
	@PutMapping
	public ResponseEntity<?> put(@Valid @RequestBody Integrante obj) {
		if (service.update(obj)) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@Override
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
