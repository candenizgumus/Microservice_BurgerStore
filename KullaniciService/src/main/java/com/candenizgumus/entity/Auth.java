package com.candenizgumus.entity;

import com.candenizgumus.entity.enums.ECinsiyet;
import com.candenizgumus.entity.enums.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblauth")
public class Auth
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @CreationTimestamp
    LocalDateTime createAt;
    @UpdateTimestamp
    LocalDateTime updateAt;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    EStatus status = EStatus.ACTIVE;


    String ad;
    String soyad;
    @Column(unique = true)
    String email;
    @Column(unique = true)
    String telefon;
    String sifre;
    LocalDate dogumTarihi;
    @Enumerated(EnumType.STRING)
    ECinsiyet cinsiyet;
}
