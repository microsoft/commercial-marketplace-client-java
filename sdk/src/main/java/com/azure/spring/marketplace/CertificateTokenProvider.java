package com.azure.spring.marketplace;

import org.springframework.stereotype.Service;

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
public class CertificateTokenProvider implements MarketplaceTokenProvider {
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

    public String acquireToken(){
      return null;
    }
}
