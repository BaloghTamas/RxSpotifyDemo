package hu.bme.aut.android.spotifydemo.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public final class Token {
    @SerializedName("access_token")
    @Nullable
    private String token;
    @SerializedName("token_type")
    @Nullable
    private String type;
    @SerializedName("expires_in")
    @Nullable
    private Integer expires;

    public Token(String token, String type, Integer expires) {
        this.token = token;
        this.type = type;
        this.expires = expires;
    }

    @Nullable
    public String getToken() {
        return token;
    }

    public void setToken(@Nullable String token) {
        this.token = token;
    }
}