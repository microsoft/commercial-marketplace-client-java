package com.azure.spring.marketplace;

import com.microsoft.rest.credentials.ServiceClientCredentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public interface MarketplaceTokenProvider extends ServiceClientCredentials {

    default void applyCredentialsFilter(OkHttpClient.Builder builder) {
        builder.addNetworkInterceptor(
                new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = null;
                        Request original = chain.request();
                        String token = acquireToken();
                        String bearerToken = String.format("Bearer %s", token);
                        // Request customization: add request headers
                        Request.Builder requestBuilder = original.newBuilder()
                                .addHeader("Authorization", bearerToken);
                        request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                });
    }

    String acquireToken() throws IOException;
}
