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
import com.mitocode.model.Menu;
import com.mitocode.service.IMenuService;

@RestController
@RequestMapping("/menus")
public class MenuController {
	
	@Autowired
	private IMenuService service;
	
	@GetMapping
	public ResponseEntity<List<Menu>> listar() throws Exception{
		List<Menu> menus = new ArrayList<>();
		menus = service.listar();
		return new ResponseEntity<List<Menu>>(menus, HttpStatus.OK);
	}
	
	@PostMapping("/usuario")
	public ResponseEntity<List<Menu>> listar(@RequestBody String nombre) throws Exception{
		List<Menu> menus = new ArrayList<>();
		menus = service.listarMenuPorUsuario(nombre);
		return new ResponseEntity<List<Menu>>(menus, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Menu> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Menu menu = service.listarPorId(id);
		return new ResponseEntity<Menu>(menu, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody Menu menu) throws Exception{
		Menu obj = service.registrar(menu);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMenu()).toUri();
		return ResponseEntity.created(location).build();		
	}
	
	@PutMapping
	public ResponseEntity<Menu> modificar(@Valid @RequestBody Menu menu) throws Exception{
		Menu obj = service.registrar(menu);		
		return new ResponseEntity<Menu>(obj, HttpStatus.OK);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Menu obj = service.listarPorId(id);
		if(obj==null) throw new ModeloNotFoundException("ID NO ENCONTRADO: "+id);
		
		service.eliminar(id);		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
