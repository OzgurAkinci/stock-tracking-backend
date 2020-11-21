package com.dafu.mapserver.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.dafu.mapserver.domain.CustomListCollection;
import com.dafu.mapserver.domain.SRole;
import com.dafu.mapserver.repository.SRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/role")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Component
public class SRoleController {
	@Autowired
	protected SRoleDao roleDao;
	
	@GetMapping("/get/{id}")
	public SRole get(@PathVariable("id") Integer id) {
		return roleDao.getOne(id);
	}
	
	@GetMapping("/list")
	public Collection<SRole> list() {
		return roleDao.findAll().stream().collect(Collectors.toList());
	}
	
	@GetMapping("/listLazy/{lazyPage}/{lazyCount}")
	public CustomListCollection<SRole> listLazy(@PathVariable("lazyPage") Integer lazyPage, @PathVariable("lazyCount") Integer lazyCount,
												@RequestParam(required=false, name="roleName") String roleName) throws Exception {
		CustomListCollection<SRole> c = new CustomListCollection<SRole>();
		Pageable pageable = PageRequest.of(lazyPage, lazyCount, Sort.by("id").ascending());
		c.setData(roleDao.findRoleByFilters(roleName, pageable).stream().collect(Collectors.toList()));
		c.setTotalCount(roleDao.getTotalCount());
		return c;
	}
	
	@PostMapping("/save")
	ResponseEntity<?> save(@RequestBody SRole model) throws Exception {
		SRole u = roleDao.save(model);
		if(u != null) {
			return new ResponseEntity<SRole>(u, HttpStatus.CREATED);
		}else {
			return null;
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<SRole> deleteById(@PathVariable("id") Integer id) {
		try {
			roleDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<SRole> (HttpStatus.ACCEPTED);
	}
}
