package com.minab.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblsiparis")
public class Siparis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long sepetId;
    Long userProfileId;
    Double toplamTutar;
    @CreationTimestamp
    LocalDateTime siparisTarihi;
}
