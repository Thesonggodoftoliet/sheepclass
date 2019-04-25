package com.sheepclass.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;

public class JwtUtils {
    public static String createToken(int userid) {
        try {
            Calendar calendar = Calendar.getInstance();
            Algorithm algorithm = Algorithm.HMAC256("secret");//签发秘钥
            String token = JWT.create().withIssuer("LYF").withClaim("userid",userid).withClaim("time",calendar.getTimeInMillis()).sign(algorithm);//签发人是LYF 并在token中存入UserId
            System.out.println("token " + token);
            return token;
        }catch (JWTCreationException e){

        }
        return null;
    }

    public static int verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("LYF").build();
            DecodedJWT jwt = verifier.verify(token);
            return 1;//验证成功
        }catch (JWTVerificationException e){

        }
        return 0;
    }

    public static int decodeToken(String token){
        try{
            DecodedJWT jwt = JWT.decode(token);
            Claim claim = jwt.getClaim("userid");//获取token中的UserId
            return claim.asInt();
        }catch (JWTDecodeException e){

        }
        return -1;
    }
}
