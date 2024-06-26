package org.candenizgumus.repository;

import org.candenizgumus.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, Long>
{
    Optional<Auth> findByEmail(String email);

    Optional<Auth> findOptionalByEmailAndSifre(String email,String sifre);
    Optional<Auth> findByEmailAndPasswordResetCode(String email, String passwordResetCode);
}
