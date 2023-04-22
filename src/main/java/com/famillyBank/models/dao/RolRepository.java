package com.famillyBank.models.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.famillyBank.models.entity.ERole;
import com.famillyBank.models.entity.Role;

public interface RolRepository extends JpaRepository<Role, Long> {
	  Optional<Role> findByName(ERole name);
	}
