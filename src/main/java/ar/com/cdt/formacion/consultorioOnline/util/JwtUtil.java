package ar.com.cdt.formacion.consultorioOnline.util;

import ar.com.cdt.formacion.consultorioOnline.dto.UsuarioIdResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "MiClaveSuperSecretaJWTParaConsultorioOnline123456";

    private static Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(java.util.Base64.getEncoder().encodeToString(SECRET_KEY.getBytes()));
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // ✅ Generar el token
    public static String generarToken(UsuarioIdResponse user) {
        long expirationTimeMs = 3600000; // 1 hora
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTimeMs);

        return Jwts.builder()
                .setSubject(String.valueOf(user.getIdUsuario()))
                .claim("idUsuario", user.getIdUsuario())
                .claim("idMedico", user.getIdMedico())
                .claim("idPaciente", user.getIdPaciente())
                .claim("requiereSeleccion", user.isRequiereSeleccion())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Validar token (firma + expiración)
    public static boolean validarToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            System.out.println("Token inválido o expirado: " + e.getMessage());
            return false;
        }
    }

    // ✅ Extraer claims (datos) del token
    public static Claims obtenerClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ✅ Extraer ID del usuario
    public static int obtenerIdUsuario(String token) {
        Claims claims = obtenerClaims(token);
        return claims.get("idUsuario", Integer.class);
    }

    public static int obtenerIdMedico(String token) {
        Claims claims = obtenerClaims(token);
        return claims.get("idMedico", Integer.class);
    }

    public static int obtenerIdPaciente(String token) {
        Claims claims = obtenerClaims(token);
        return claims.get("idPaciente", Integer.class);
    }

    public static Boolean obtenerRequiereSeleccion(String token) {
        Claims claims = obtenerClaims(token);
        return claims.get("requiereSeleccion", Boolean.class);
    }
}