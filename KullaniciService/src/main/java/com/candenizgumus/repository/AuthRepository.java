package com.candenizgumus.repository;

import com.candenizgumus.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, Long>
{
}
