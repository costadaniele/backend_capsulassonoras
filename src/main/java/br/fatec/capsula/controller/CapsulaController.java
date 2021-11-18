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

import br.fatec.capsula.model.Capsula;
import br.fatec.capsula.service.CapsulaService;

@RestController
@RequestMapping(value = "/capsulas")
public class CapsulaController implements ControllerInterface<Capsula> {
	
	@Autowired
	private CapsulaService service;
	
	@Override
	@GetMapping
	public ResponseEntity<List<Capsula>> get() {		
		return ResponseEntity.ok(service.findAll());
	}
	
	@Override
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		Capsula _capsula = service.findById(id);
		if (_capsula != null) {
			return ResponseEntity.ok(_capsula);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<Capsula>> get(Pageable pageable) {		
		return ResponseEntity.ok(service.findAll(pageable));
	}
	
	@GetMapping("/integrante/{id}")
	public ResponseEntity<Page<Capsula>> getByIntegrante(Pageable pageable, @PathVariable("id") Long integranteId) {		
		return ResponseEntity.ok(service.findByIntegrante(pageable, integranteId));
	}
	@GetMapping("/ano/{ano}")
	public ResponseEntity<Page<Capsula>> getByAno(Pageable pageable, @PathVariable("ano") Integer ano) {		
		return ResponseEntity.ok(service.findByAno(pageable, ano));
	}
	
	@GetMapping("/count/{from}/{to}")
	public ResponseEntity<Long> countByPeriodo(@PathVariable("from") Integer from, @PathVariable("to") Integer to) {		
		return ResponseEntity.ok(service.countByPeriodo(from, to));
	}
	
	@Override
	@PostMapping
	public ResponseEntity<Capsula> post(@Valid @RequestBody Capsula obj) {	
		service.create(obj);
		URI uri = ServletUriComponentsBuilder
		        .fromCurrentRequest().path("/{id}")
		        .buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	
	@Override
	@PutMapping
	public ResponseEntity<?> put(@Valid @RequestBody Capsula obj) {
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
