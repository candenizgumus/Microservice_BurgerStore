package org.minab.service;

import org.example.config.model.UserProfileUpdateBalanceModel;
import org.minab.entity.Sepet;
import org.minab.entity.Siparis;
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
        // Kullanıcı bakiyesinin kontrolu ve bakiye güncelleme
        rabbitTemplate.convertAndSend("direct.exchange", "key.finduserprofileandupdatebalance", UserProfileUpdateBalanceModel.builder().userProfileId(sepet.getUserProfileId()).toplamTutar(sepet.getToplam()).build());


        //Sepetteki ürünleri silme
        sepetService.sepetiTemizle(sepetId);


        Siparis siparis = Siparis.builder()
                .sepetId(sepetId)
                .userProfileId(sepet.getUserProfileId())
                .toplamTutar(sepet.getToplam())
                .build();
        siparisRepository.save(siparis);
        return "Siparişiniz alınmıştır.";
    }
}
