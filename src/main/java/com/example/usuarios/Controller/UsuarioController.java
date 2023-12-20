package com.example.usuarios.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuarios.Entity.Usuario;
import com.example.usuarios.Service.UsuarioService;


@RestController
@CrossOrigin(origins = "http://localhost:4200") 
@RequestMapping(path = "/api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public List<Usuario> getAll() {
		return usuarioService.getAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Usuario> getById(@PathVariable("id") Long id) {
		return usuarioService.getById(id);
	}
	
	@PostMapping
	public void post(@RequestBody Usuario usuario) {
		usuarioService.save(usuario);
	}
	
	@PutMapping("/actualizar/{id}")
	public void putById(@RequestBody Usuario usuario, @PathVariable("id") Long id) {
		usuarioService.putById(usuario, id);
	}
	
	@DeleteMapping("/borrar/{id}")
	public void deleteById(@PathVariable("id") Long id) {
		usuarioService.deleteById(id);
	}
	
	@DeleteMapping
	public void deleteAll() {
		usuarioService.deleteAll();
	}
	
}
