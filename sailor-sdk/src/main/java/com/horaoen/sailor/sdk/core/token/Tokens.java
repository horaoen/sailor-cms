package com.horaoen.sailor.sdk.core.token;

import lombok.Getter;
import lombok.Setter;

/**
 * @author horaoen
 */
@Getter
@Setter
public class Tokens {
    private String accessToken;
    private String refreshToken;
    
    public Tokens(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "Tokens{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
