package com.example.usuarios.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.usuarios.Entity.Usuario;
import com.example.usuarios.Repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	private BCryptPasswordEncoder passwordEncoder;
	
	public List<Usuario> getAll() {
		return (List<Usuario>) usuarioRepository.findAll();
	}
	
	public Optional<Usuario> getById(Long id) {
		return  usuarioRepository.findById(id);
	}
	
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public void putById(Usuario usuario, Long id) {
		usuario.setId(id);
		usuarioRepository.save(usuario);
	}
	
	public void deleteById(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	public void deleteAll() {
		usuarioRepository.deleteAll();
	}
	
}
