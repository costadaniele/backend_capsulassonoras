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

import br.fatec.capsula.model.Regiao;
import br.fatec.capsula.service.RegiaoService;

@RestController
@RequestMapping(value = "/regioes")
public class RegiaoController implements ControllerInterface<Regiao> {

	@Autowired
	private RegiaoService service;

	@Override
	@GetMapping
	public ResponseEntity<List<Regiao>> get() {
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	@GetMapping(value = "{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		Regiao _reg = service.findById(id);
		if (_reg != null) {
			return ResponseEntity.ok(_reg);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<Regiao>> getAll(Pageable pageable) {
		return ResponseEntity.ok(service.findAll(pageable));
	}

	@GetMapping(value = "/page/{letra}")
	public ResponseEntity<Page<Regiao>> getByLetra(Pageable pageable, @PathVariable("letra") Character letra) {
		return ResponseEntity.ok(service.findByCidade(pageable, letra));
	}
	

	@Override
	@PostMapping
	public ResponseEntity<Regiao> post(@Valid @RequestBody Regiao obj) {
		service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@Override
	@PutMapping
	public ResponseEntity<?> put(@Valid @RequestBody Regiao obj) {
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
