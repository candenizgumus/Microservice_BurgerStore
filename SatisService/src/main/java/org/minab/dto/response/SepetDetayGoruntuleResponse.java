package org.minab.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SepetDetayGoruntuleResponse
{
    private String urunAdi;
    private Double urunFiyati;
    private Integer adet;
    private Double toplamFiyat;
    private List<String> aciklamalar;
}
