package org.candenizgumus.dto.request;

import org.candenizgumus.entity.enums.EAdresType;
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
