package com.dafu.mapserver.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.envers.Audited;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "s_user_role")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Audited
public class SUserRole extends Auditable<String>{
	@Id
	@SequenceGenerator(name = "s_user_role_seq", sequenceName = "s_user_role_seq", initialValue = 5, allocationSize = 1)
	@GeneratedValue(generator = "s_user_role_seq", strategy = GenerationType.SEQUENCE)
	private Long id;

	
}
