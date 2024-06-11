package org.candenizgumus.entity;

import org.candenizgumus.entity.enums.ECinsiyet;
import org.candenizgumus.entity.enums.EMusteriType;
import org.candenizgumus.entity.enums.EStatus;
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
@Table(name = "tbluserprofile")
public class UserProfile
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
    EStatus status = EStatus.PENDING;

    Long authId;
    String ad;
    String soyad;
    String email;
    String telefon;
    String tc;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    EMusteriType musteriType = EMusteriType.BIREYSEL;
    LocalDate dogumTarihi;
    @Enumerated(EnumType.STRING)
    ECinsiyet cinsiyet;
    @Builder.Default
    Double puan = 7.0;
    Double bakiye;
    Long sepetId;

}
