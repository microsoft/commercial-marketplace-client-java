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

//
// To create a self-signed PFX for use on Windows or Linux, these commands will create a cert
// for you to use:
// openssl req -x509 -nodes -days 3650 -newkey rsa:4096 -keyout privateKey.key -out certificate.pem
//
// openssl pkcs12 -export -out certificate.pfx -inkey privateKey.key -in certificate.pem
//
// You can upload certificate.pem to Azure for use as the public key. Certificate.pfx is used locally.
@Service
public class CertificateTokenProvider implements ServiceClientCredentials {
    UUID _tenantId;
    UUID _clientId;
    X509Certificate _clientSecret;
    private String _certificatePassword;

    public CertificateTokenProvider(UUID tenantId, UUID clientId, X509Certificate clientSecret, String certificatePassword) {
        _tenantId = tenantId;
        _clientId = clientId;
        _clientSecret = clientSecret;
        _certificatePassword = certificatePassword;
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
        
    }
}
