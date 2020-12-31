package com.mitocode.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.mitocode.model.Usuario;
import com.mitocode.repo.IUsuarioRepo;
import com.mitocode.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements UserDetailsService, IUsuarioService{

	@Autowired
	private IUsuarioRepo repo;	
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = repo.findOneByUsername(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException(String.format("Usuario no existe", username));
		}
		
		List<GrantedAuthority> roles = new ArrayList<>();
		
		usuario.getRoles().forEach(rol -> {
			roles.add(new SimpleGrantedAuthority(rol.getNombre()));
		});
		
		UserDetails ud = new User(usuario.getUsername(), usuario.getPassword(), roles);
		
		return ud;
	}

	@Override
	public Usuario registrar(Usuario pac) throws Exception {
		Usuario user = new Usuario();
		user.setIdUsuario(pac.getIdUsuario());
		user.setUsername(pac.getUsername());
		user.setPassword(bcrypt.encode(pac.getPassword()));
		user.setEnabled(pac.isEnabled());
		return repo.save(user);
	}

	@Override
	public Usuario modificar(Usuario pac) throws Exception {
		Usuario user = repo.findById(pac.getIdUsuario()).orElse(null);
		user.setEnabled(pac.isEnabled());
		user.setUsername(pac.getUsername());
		return repo.save(user);
	}

	@Override
	public List<Usuario> listar() throws Exception {
		return repo.findAll();
	}

	@Override
	public Usuario listarPorId(Integer id) throws Exception {
		return repo.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Integer id) throws Exception {
		Usuario user = repo.findById(id).orElse(null);
		user.setEnabled(false);
		this.modificar(user);
	}

	@Override
	public void cambiarPassword(Usuario u) throws Exception {
		Usuario user = repo.findById(u.getIdUsuario()).orElse(null);
		user.setPassword(bcrypt.encode(u.getPassword()));
		repo.save(user);
		
	}
}
