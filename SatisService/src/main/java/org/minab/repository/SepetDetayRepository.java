package org.minab.repository;

import org.minab.entity.Sepet;
import org.minab.entity.SepetDetay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SepetDetayRepository extends JpaRepository<SepetDetay, Long> {


    List<SepetDetay> findAllBySepetId(Long sepetId);
}
