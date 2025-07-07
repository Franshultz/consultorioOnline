package ar.com.cdt.formacion.consultorioOnline.util;

import ar.com.cdt.formacion.consultorioOnline.dto.UsuarioIdResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // 🔐 Usá una clave secreta más segura y más larga en producción (mínimo 256 bits)
    private static final String SECRET_KEY = "MiClaveSuperSecretaJWTParaConsultorioOnline123456";

    private static Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(java.util.Base64.getEncoder().encodeToString(SECRET_KEY.getBytes()));
        return Keys.hmacShaKeyFor(keyBytes);
    }

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
}
