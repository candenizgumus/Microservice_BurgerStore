package org.minab.service;

import org.example.config.model.UserProfileUpdateBalanceModel;
import org.minab.dto.response.SepetGoruntuleResponse;
import org.minab.entity.Sepet;
import org.minab.entity.Siparis;
import org.minab.exceptions.ErrorType;
import org.minab.exceptions.SatisServiceException;
import org.minab.repository.SiparisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SiparisService
{
    private final SiparisRepository siparisRepository;
    private final SepetDetayService sepetDetayService;
    private final SepetService sepetService;
    private final RabbitTemplate rabbitTemplate;

    public String save(Long  sepetId)
    {
        //Sepet bulma ve kontrol
        Sepet sepet = sepetService.findById(sepetId);
        //SepetResponse ile sanal sepet yaratıp bakiyeleri hesaplama
        SepetGoruntuleResponse sepetGoruntule = sepetService.sepetiGoruntule(sepetId);
        // Kullanıcı bakiyesinin kontrolu ve bakiye güncelleme
        UserProfileUpdateBalanceModel message = UserProfileUpdateBalanceModel.builder().userProfileId(sepet.getUserProfileId()).toplamTutar(sepetGoruntule.getToplam()).build();
        Boolean bakiyeBoolean = (Boolean)rabbitTemplate.convertSendAndReceive("direct.exchange", "key.finduserprofileandupdatebalance", message);
        if (!bakiyeBoolean)
        {
            throw new SatisServiceException(ErrorType.INSUFFICIENT_BALANCE);
        }


        //Sepetteki ürünleri silme
        sepetService.sepetiTemizle(sepetId);


        Siparis siparis = Siparis.builder()
                .sepetId(sepetId)
                .userProfileId(sepet.getUserProfileId())
                .toplamTutar(sepetGoruntule.getToplam())
                .build();
        siparisRepository.save(siparis);
        return "Siparişiniz alınmıştır.";
    }
}
