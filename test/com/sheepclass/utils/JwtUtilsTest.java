//package com.sheepclass.utils;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTCreationException;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.interfaces.Claim;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import org.junit.Test;
//import org.junit.rules.Verifier;
//
//
//public class JwtUtilsTest {
//
//    @Test
//    public void createToken() {
//        try{
//            Algorithm algorithm = Algorithm.HMAC256("secret");
//            String token = JWT.create().withClaim("name","nihao").withIssuer("LYF").sign(algorithm);
//            System.out.println("token "+token);
//            JWTVerifier verifier =JWT.require(algorithm).withIssuer("LY").build();
//            DecodedJWT jwt = verifier.verify(token);
//        }catch (JWTVerificationException e){
//
//        }
//        System.out.println("认证失败");
//    }
//}