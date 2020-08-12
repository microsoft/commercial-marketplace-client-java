package com.azure.spring.marketplace;

import com.microsoft.aad.msal4j.ClientCredentialFactory;
import com.microsoft.aad.msal4j.ClientCredentialParameters;
import com.microsoft.aad.msal4j.ConfidentialClientApplication;
import com.microsoft.aad.msal4j.IAuthenticationResult;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


//
// To create a self-signed PFX for use on Windows or Linux, these commands will create a cert
// for you to use:
// openssl req -x509 -nodes -days 3650 -newkey rsa:4096 -keyout privateKey.key -out certificate.pem
//
// openssl pkcs12 -export -out certificate.pfx -inkey privateKey.key -in certificate.pem
//
// You can upload certificate.pem to Azure for use as the public key. Certificate.pfx is used locally.
@Service
public class CertificateTokenProvider implements MarketplaceTokenProvider {
    UUID _tenantId;
    UUID _clientId;
    KeyStore.PrivateKeyEntry _clientSecret;
    private X509Certificate _clientCert;

    public CertificateTokenProvider(UUID tenantId, UUID clientId, KeyStore.PrivateKeyEntry clientSecret, X509Certificate clientCert) {
        _tenantId = tenantId;
        _clientId = clientId;
        _clientSecret = clientSecret;
        _clientCert = clientCert;
    }

    public String acquireToken() {
        PrivateKey key = _clientSecret.getPrivateKey();
        String authority = String.format(Constants.AadAuthority, _tenantId);

        try {
            ConfidentialClientApplication app = ConfidentialClientApplication.builder(
                    _clientId.toString(),
                    ClientCredentialFactory.createFromCertificate(key, _clientCert))
                    .authority(authority)
                    .build();
            ClientCredentialParameters clientCredentialParameters = ClientCredentialParameters.builder(
                    Collections.singleton(Constants.MarketplaceResourceScope)
            ).build();
            CompletableFuture<IAuthenticationResult> future = app.acquireToken(clientCredentialParameters);
            return future.get().accessToken();
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }
}
