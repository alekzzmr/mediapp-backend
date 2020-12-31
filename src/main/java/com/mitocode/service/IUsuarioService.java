package com.mitocode.service;

import com.mitocode.model.Usuario;

public interface IUsuarioService extends ICRUD<Usuario, Integer>{
	
	void cambiarPassword(Usuario u) throws Exception;
}
