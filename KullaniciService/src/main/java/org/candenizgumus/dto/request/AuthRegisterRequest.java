package org.candenizgumus.dto.request;

import org.candenizgumus.entity.enums.ECinsiyet;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AuthRegisterRequest
{
    String ad;
    String soyad;
    @NotNull
    String email;
    @NotNull
    String telefon;
    @NotNull
    String sifre;
    @NotNull
    String sifreTekrar;
    LocalDate dogumTarihi;
    ECinsiyet cinsiyet;
    @NotNull
    Boolean sozlesmeOnayMetni;
}
