package kodlama.io.ecommerce.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import kodlama.io.ecommerce.common.constants.Security;
import lombok.experimental.UtilityClass;

import java.util.Date;

@UtilityClass
public class JwtHelper {

    public String createJwtToken(String userName){
         final Algorithm algorithm=Algorithm.HMAC256(Security.SECRET_KEY);

        return JWT.create()
                .withSubject(userName)
                .withExpiresAt(new Date(System.currentTimeMillis()+Security.EXPIRED_DAY))
                .sign(algorithm);
    }

    public DecodedJWT decodedJWT(String token) throws JWTVerificationException {
        final Algorithm algorithm=Algorithm.HMAC256(Security.SECRET_KEY);
        final JWTVerifier verifier=JWT.require(algorithm).build();

        return verifier.verify(token);


    }
}
