package org.minab.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(hidden = true)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> aciklamalar;
}
