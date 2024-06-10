package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.enums.ECikartilacakUrunMalzemeleri;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tblhamburger")
public class Hamburger
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ElementCollection(targetClass = ECikartilacakUrunMalzemeleri.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<ECikartilacakUrunMalzemeleri> malzemeler;
}
