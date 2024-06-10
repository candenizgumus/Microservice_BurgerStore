package com.candenizgumus.repository;

import com.candenizgumus.entity.Adres;
import com.candenizgumus.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdresRepository extends JpaRepository<Adres, Long>
{
}
