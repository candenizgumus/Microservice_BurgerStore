package org.minab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SiparisSaveRequestDto {
    Long sepetId;
    Long userProfileId;
    Double toplamTutar;
}
