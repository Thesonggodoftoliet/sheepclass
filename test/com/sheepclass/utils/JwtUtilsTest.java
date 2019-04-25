package com.sheepclass.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;
import org.junit.rules.Verifier;

import java.util.Calendar;


public class JwtUtilsTest {

    @Test
    public void createToken() {
        try{
            Calendar calendar = Calendar.getInstance();
            String url = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoibmloYW8iLCJpc3MiOiJMWUYiLCJ0aW1lIjoxNTU2MjAxNTc1NDI0fQ.1hR-c4jpM_7UDkeGgHwAaIKAYs0XYoWuN7uNpGvjHBI";
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create().withClaim("name","nihao").withClaim("time",calendar.getTimeInMillis()).withIssuer("LYF").sign(algorithm);
            System.out.println("token "+token+" "+url.equals(token));
            JWTVerifier verifier =JWT.require(algorithm).withIssuer("LYF").build();
            DecodedJWT jwt = verifier.verify(token);
        }catch (JWTVerificationException e){
            //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoibmloYW8iLCJpc3MiOiJMWUYifQ.dYmp5gCZgYQmLWgLAvrC8nrACSzalxD1GDa49Xpoxq0
            System.out.println("认证失败");
        }

    }
}