package com.dafu.mapserver.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.dafu.mapserver.domain.SUserRole;
import com.dafu.mapserver.repository.SUserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/userRole")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Component
public class SUserRoleController {
	@Autowired
	protected SUserRoleDao userRoleDao;
	
	@GetMapping("/get/{id}")
	public SUserRole get(@PathVariable("id") Integer id) {
		return userRoleDao.getOne(id);
	}
	
	@GetMapping("/list")
	public Collection<SUserRole> list() {
		return userRoleDao.findAll().stream().collect(Collectors.toList());
	}
	
	@PostMapping("/save")
	ResponseEntity<?> save(@RequestBody SUserRole model) throws Exception {
		SUserRole u = userRoleDao.save(model);
		if(u != null) {
			return new ResponseEntity<SUserRole>(u, HttpStatus.CREATED);
		}else {
			return null;
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<SUserRole> deleteById(@PathVariable("id") Integer id) {
		try {
			userRoleDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<SUserRole> (HttpStatus.ACCEPTED);
	}
}
