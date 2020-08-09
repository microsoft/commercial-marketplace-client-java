package com.azure.spring.marketplace;

import com.microsoft.rest.credentials.ServiceClientCredentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.UUID;

@Service
public class CertificateTokenProvider implements ServiceClientCredentials {
    UUID _tenantId;
    UUID _clientId;
    X509Certificate _clientSecret;

    public CertificateTokenProvider(UUID tenantId, UUID clientId, X509Certificate clientSecret) {
        _tenantId = tenantId;
        _clientId = clientId;
        _clientSecret = clientSecret;
    }

    @Override
    public void applyCredentialsFilter(OkHttpClient.Builder builder) {
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
                                .addHeader("Authorize", bearerToken);
                        request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                });
    }

    private String acquireToken(){
        return null;
    }
}
