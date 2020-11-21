package com.dafu.mapserver.repository;


import java.util.List;

import com.dafu.mapserver.domain.SRole;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface SRoleDao extends JpaRepository<SRole, Integer>{
	@Query("SELECT count(id) FROM SRole")
	public Long getTotalCount() throws Exception;	
	
	@Query("SELECT e FROM SRole e WHERE e.roleName like %:roleName%")
	public List<SRole> findRoleByFilters(@Param("roleName") String roleName, Pageable pageable) throws Exception;	
}	

