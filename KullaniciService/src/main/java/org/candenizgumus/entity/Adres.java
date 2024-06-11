package org.candenizgumus.entity;

import org.candenizgumus.entity.enums.EAdresType;
import org.candenizgumus.entity.enums.EStatus;
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
@Table(name = "tbladres")
public class Adres
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


    Long userProfileId;
    @Enumerated(EnumType.STRING)
    EAdresType adresType;
    String adres;
}
