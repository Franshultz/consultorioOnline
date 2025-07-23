package ar.com.cdt.formacion.consultorioOnline.dto;

public class RefreshTokenResponse {

    private String refreshToken;

    public RefreshTokenResponse(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
