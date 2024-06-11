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
public class SepetGoruntuleResponse
{
    private Long sepetId;
    private Long userProfileId;
    private List<SepetDetayGoruntuleResponse> sepetDetayGoruntuleResponse;
    private Double araToplam;
    private Double vergi;
    private Double toplam;
    private String odemeTipi;
    private String servisTipi;

}
