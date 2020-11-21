package com.dafu.mapserver.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "s_user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Audited
public class SUser extends Auditable<String>{
	@Id
	@SequenceGenerator(name = "s_user_seq", sequenceName = "s_user_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "s_user_seq", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	private String username;
	private String password;
	private String name;
	private String surname;
	private String address;
	private String email;
	private String phone;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable(name="s_user_role",
        joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}
    )
    private List<SRole> roles;
}
