package com.ravid.clothes_marketplace.app.service.security;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BearerTokenAuth implements Interceptor {

    private String token;

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String token) {
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        // If the request already have an authorization (eg. Basic auth), do nothing
        if (request.header("Authorization") == null) {
            request = request.newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
        }
        return chain.proceed(request);
    }
}