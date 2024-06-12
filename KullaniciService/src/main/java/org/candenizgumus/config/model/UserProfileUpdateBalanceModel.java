package org.candenizgumus.config.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserProfileUpdateBalanceModel
{
    public Long userProfileId;
    public double toplamTutar;
}
