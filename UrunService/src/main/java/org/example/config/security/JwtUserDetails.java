package org.example.config.security;


import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtUserDetails implements UserDetailsService
{
    private final RabbitTemplate rabbitTemplate;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return null;
    }


    public UserDetails loadAuthById(Long authid)
    {
        //Rabbitle auth u çekme ve role bilgisin alma
        String role = (String)rabbitTemplate.convertSendAndReceive("direct.exchange", "key.findauthbyid", authid);

        String[] split = role.split(" ");
        System.out.println(split[0]);
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(split[0]));



        return User.builder()
                .username(split[1])
                .password("")
                .authorities(authorities)
                .build();
    }
}
