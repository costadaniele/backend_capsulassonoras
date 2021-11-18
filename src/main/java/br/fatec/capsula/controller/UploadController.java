package br.fatec.capsula.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.fatec.capsula.service.UploadService;

@RestController
@RequestMapping("/upload")
public class UploadController {
	
	@Autowired
	private UploadService service;
	 
	@PostMapping(value = "/local")
	public ResponseEntity<Void> upload(@RequestParam("arquivo") MultipartFile arquivo) {
		try {
			URI uri = service.storeFile(arquivo);
			return ResponseEntity.created(uri).build();
		} catch (IOException | URISyntaxException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
