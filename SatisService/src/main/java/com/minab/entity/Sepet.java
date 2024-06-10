package com.minab.entity;

import com.minab.entity.enums.EOdemeTipi;
import com.minab.entity.enums.EServisTipi;
import com.minab.entity.enums.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblsepet")
public class Sepet {
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

    Long userProfileId;
    Double araToplam;
    Double vergi;
    Double toplam;
    @Enumerated(EnumType.STRING)
    EOdemeTipi odemeTipi;
    @Enumerated(EnumType.STRING)
    EServisTipi servisTipi;
}
