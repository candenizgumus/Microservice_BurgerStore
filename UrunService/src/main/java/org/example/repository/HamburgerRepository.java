package org.example.repository;

import org.example.entity.Hamburger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HamburgerRepository extends JpaRepository<Hamburger, Long>
{
}
