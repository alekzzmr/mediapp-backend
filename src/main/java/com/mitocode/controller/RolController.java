package com.mitocode.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Rol;
import com.mitocode.service.IRolService;

@RestController
@RequestMapping("/roles")
public class RolController {
	
	@Autowired
	private IRolService service;
	
	@GetMapping
	public ResponseEntity<List<Rol>> listar() throws Exception{
		List<Rol> obj = new ArrayList<>();
		obj = service.listar();
		return new ResponseEntity<List<Rol>>(obj, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Rol> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Rol obj = service.listarPorId(id);
		return new ResponseEntity<Rol>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody Rol rol) throws Exception{
		Rol obj = service.registrar(rol);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdRol()).toUri();
		return ResponseEntity.created(location).build();		
	}
	
	@PutMapping
	public ResponseEntity<Rol> modificar(@Valid @RequestBody Rol rol) throws Exception{
		Rol obj = service.registrar(rol);		
		return new ResponseEntity<Rol>(obj, HttpStatus.OK);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Rol obj = service.listarPorId(id);
		if(obj==null) throw new ModeloNotFoundException("ID NO ENCONTRADO: "+id);
		
		service.eliminar(id);		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
