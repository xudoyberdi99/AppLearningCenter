package com.company.security;



import com.company.entity.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private static final long expireTime=1000*60*60*24;
    private static final String key="maxfiysuzqwertyuiopasdfghjklwqrewreqrwtrewteterwteqertreqtreqteqriowjiorijhirjhjgfhurghufgyteouwegfyiegfegfyuegfuygrfuygfou";

    public String generateToken(String username, Role role){
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS256, key)
                .claim("roles", role.getName())
                .compact();

        return token;
    }

    public String getUsernameFromToken(String token){
        try {
            return Jwts
                    .parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }catch (Exception e){
            return null;
        }
    }
}
