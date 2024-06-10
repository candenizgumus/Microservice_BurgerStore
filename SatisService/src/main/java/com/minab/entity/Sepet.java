package com.minab.entity;

import com.minab.entity.enums.EOdemeTipi;
import com.minab.entity.enums.EServisTipi;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    Long userProfileId;
    Double araToplam;
    Double vergi;
    Double toplam;
    @Enumerated(EnumType.STRING)
    EOdemeTipi odemeTipi;
    @Enumerated(EnumType.STRING)
    EServisTipi servisTipi;
}
