package org.example.config.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HamburgerModel implements Serializable
{   Long id;
    String ad;
    String aciklama;
    Double birimFiyat;
}
