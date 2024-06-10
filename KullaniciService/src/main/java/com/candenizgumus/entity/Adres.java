package com.candenizgumus.entity;

import com.candenizgumus.entity.enums.EAdresType;
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
@Table(name = "tbladres")
public class Adres
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long userProfileId;
    @Enumerated(EnumType.STRING)
    EAdresType adresType;
    String adres;
}
