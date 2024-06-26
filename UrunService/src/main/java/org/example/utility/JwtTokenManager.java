package org.example.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.example.entity.enums.ERole;
import org.minab.exceptions.ErrorType;
import org.minab.exceptions.UrunServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenManager
{

    @Value("${authservice.secret.secret-key}")
    String secretKey ;
    @Value("${authservice.secret.issuer}")
    String issuer ;
    Long expireTime = 1000L * 60*15; //15 dakikalık zaman

    //1. token üretmeli.
    public Optional<String> createTokenFromId(Long id)
    {
        String token = "";

        /**
         * Dikkat!
         * Claim objeleri içindeki değerler herkes tarafından görülebilir.
         * O yüzden claimler ile e-mail, password gibi herkesin görmesini istemediğimiz bilgileri payload kısmında tutmayız.
         */

        try
        {
            token = JWT.create()
                    .withAudience()
                    .withClaim("id", id)
                    .withIssuer(issuer)
                    .withIssuedAt(new Date()) //Tokenın yaratıldığı an.
                    .withExpiresAt(new Date(System.currentTimeMillis() + expireTime)) //Date, Instant
                    .sign(Algorithm.HMAC512(secretKey));

            return Optional.of(token);

        } catch (IllegalArgumentException | JWTCreationException e)
        {
            throw new UrunServiceException(ErrorType.TOKEN_CREATION_FAILED);

        }


    }

    public Optional<String> createTokenFromIdAndRole(Long id, ERole role)
    {
        String token = "";

        /**
         * Dikkat!
         * Claim objeleri içindeki değerler herkes tarafından görülebilir.
         * O yüzden claimler ile e-mail, password gibi herkesin görmesini istemediğimiz bilgileri payload kısmında tutmayız.
         */

        try
        {
            token = JWT.create()
                    .withAudience()
                    .withClaim("id", id)
                    .withClaim("role", role.toString())
                    .withIssuer(issuer)
                    .withIssuedAt(new Date()) //Tokenın yaratıldığı an.
                    .withExpiresAt(new Date(System.currentTimeMillis() + expireTime)) //Date, Instant
                    .sign(Algorithm.HMAC512(secretKey));

            return Optional.of(token);

        } catch (IllegalArgumentException | JWTCreationException e)
        {
            throw new UrunServiceException(ErrorType.TOKEN_CREATION_FAILED);

        }


    }

    //2. Token doğrulanmalı.
   /* public Boolean verifyToken(String token)
    {

    }*/
    //3. Tokendan bilgi çıkarımı yapmalı.

    public Optional<Long> getIdFromToken(String token)
    {



        try
        {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if (decodedJWT==null)
            {
                return Optional.empty();

            }


            return Optional.of(decodedJWT.getClaim("id").asLong());
        } catch (IllegalArgumentException e)
        {

            throw new UrunServiceException(ErrorType.TOKEN_ARGUMENT_NOTVALID);
        } catch (JWTVerificationException e)
        {
            throw new UrunServiceException(ErrorType.TOKEN_VERIFY_FAILED);
        }


    }

    public Optional<String> getRoleFromToken(String token)
    {



        try
        {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if (decodedJWT==null)
            {
                return Optional.empty();

            }


            return Optional.of(decodedJWT.getClaim("role").asString());
        } catch (IllegalArgumentException e)
        {

            throw new UrunServiceException(ErrorType.TOKEN_ARGUMENT_NOTVALID);
        } catch (JWTVerificationException e)
        {
            throw new UrunServiceException(ErrorType.TOKEN_VERIFY_FAILED);
        }


    }




}
