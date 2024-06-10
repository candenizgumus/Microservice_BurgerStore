package com.candenizgumus.dto.request;

import com.candenizgumus.entity.enums.EAdresType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AdresSaveRequest
{
    Long userProfileId;
    EAdresType adresType;
    String adres;
}
