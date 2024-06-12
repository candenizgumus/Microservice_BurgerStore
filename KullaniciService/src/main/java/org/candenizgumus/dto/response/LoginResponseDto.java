package org.candenizgumus.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.candenizgumus.entity.enums.ERole;
import org.candenizgumus.entity.enums.EStatus;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class LoginResponseDto {
    Long id;
    String ad;
    String email;
    ERole role;
    EStatus status;
}
