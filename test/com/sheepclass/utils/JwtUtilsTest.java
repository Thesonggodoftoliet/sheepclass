package com.sheepclass.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;


public class JwtUtilsTest {

    @Test
    public void createToken() {
        try{
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create().withClaim("name","nihao").sign(algorithm);
            System.out.println("token "+token);
            DecodedJWT jwt = JWT.decode(token);
            Claim claim = jwt.getClaim("name");
            System.out.println(claim.asString());
        }catch (JWTCreationException e){

        }
    }
}