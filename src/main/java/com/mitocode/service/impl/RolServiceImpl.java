package com.mitocode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Rol;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IRolRepo;
import com.mitocode.service.IRolService;

@Service
public class RolServiceImpl extends CRUDImpl<Rol, Integer> implements IRolService{

	@Autowired
	private IRolRepo repo;
	
	@Override
	protected IGenericRepo<Rol, Integer> getRepo() {
		return repo;
	}

}
