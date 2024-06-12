package org.minab.entity;

import org.minab.entity.enums.EOdemeTipi;
import org.minab.entity.enums.EServisTipi;
import org.minab.entity.enums.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

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
    @OneToMany( cascade = CascadeType.ALL,orphanRemoval = true)
    List<SepetDetay> sepetDetayList;
    @Builder.Default
    Double araToplam = 0.0;
    Double vergi;
    Double toplam;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    EOdemeTipi odemeTipi =  EOdemeTipi.KREDIKARTI;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    EServisTipi servisTipi = EServisTipi.PAKET_SERVIS;



}
