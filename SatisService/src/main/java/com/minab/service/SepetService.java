package com.minab.service;

import com.minab.entity.Sepet;
import com.minab.repository.SepetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SepetService {
    private final SepetRepository sepetRepository;


    @RabbitListener(queues = "savesepet")
    public Long save(Long userProfileId) {


        Sepet sepet = sepetRepository.save(Sepet.builder().userProfileId(userProfileId).build());

        return sepet.getId();

    }
}
