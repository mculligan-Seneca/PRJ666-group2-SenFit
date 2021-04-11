package com.example.senfit.NetworkManager.Interceptor;

import androidx.annotation.Nullable;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class SenFitAuthenticator implements Authenticator {
    private String token;
    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, Response response) throws IOException {
        return null;
    }
}
