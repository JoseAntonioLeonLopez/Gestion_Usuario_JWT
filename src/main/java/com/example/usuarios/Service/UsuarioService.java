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
	
	private final BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
    public UsuarioService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
	
	public List<Usuario> getAll() {
		return (List<Usuario>) usuarioRepository.findAll();
	}
	
	public Optional<Usuario> getById(Long id) {
		return  usuarioRepository.findById(id);
	}
	
	public void save(Usuario usuario) {
		String encryptedPassword = passwordEncoder.encode(usuario.getPasswd());
		usuario.setPasswd(encryptedPassword);
		usuarioRepository.save(usuario);
	}
	
	public void putById(Usuario usuario, Long id) {
		String encryptedPassword = passwordEncoder.encode(usuario.getPasswd());
		usuario.setPasswd(encryptedPassword);
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
