package com.azure.marketplace.tests;

import com.azure.marketplace.MarketplaceClient;
import com.azure.spring.marketplace.CertificateTokenProvider;
import com.azure.spring.marketplace.implementation.MarketplaceClientImpl;
import com.microsoft.rest.credentials.ServiceClientCredentials;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.UUID;

@Configuration
public class MarketplaceCertConfig {
    @Bean
    public ServiceClientCredentials serviceClientCredentials() {
        String tenantId = System.getenv("AAD_TENANT_ID");
        String clientId = System.getenv("AAD_APP_CLIENT_ID");
        String clientSecretLocation = System.getenv("AAD_APP_CERT_LOCATION");
        String certificatePassword = System.getenv("AAD_APP_CERT_SECRET");
        X509Certificate clientSecret = null;
        KeyStore.PrivateKeyEntry privateKey = null;
        try {
            FileInputStream is = new FileInputStream(clientSecretLocation);
            KeyStore ks= KeyStore.getInstance("PKCS12");
            ks.load(is, certificatePassword.toCharArray());

            String alias = null;
            Enumeration<String> aliases = ks.aliases();

            // The keystore has one cert. Just grab the first alias (typically, the ONLY alias)
            // and go forward.
            while(aliases.hasMoreElements()){
                alias = aliases.nextElement();
                break;
            }
            if (alias == null){
                throw new IllegalStateException("AAD_TENANT_ID, AAD_APP_CLIENT_ID, AAD_APP_CERT_LOCATION, and AAD_APP_CERT_SECRET must be defined as environment variables.");
            }

            clientSecret = (X509Certificate) ks.getCertificate(alias);
            privateKey = (KeyStore.PrivateKeyEntry)ks.getEntry(alias, new KeyStore.PasswordProtection(certificatePassword.toCharArray()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (UnrecoverableEntryException e){
            e.printStackTrace();
        }

        if (StringUtils.isEmpty(tenantId) || clientSecret == null){
            throw new IllegalStateException("AAD_TENANT_ID, AAD_APP_CLIENT_ID, AAD_APP_CERT_LOCATION, and AAD_APP_CERT_SECRET must be defined as environment variables.");
        }

        CertificateTokenProvider instance = new CertificateTokenProvider(
                UUID.fromString(tenantId),
                UUID.fromString(clientId),
                privateKey, clientSecret);

        return instance;
    }

    @Bean
    public MarketplaceClient marketplaceClient() {
        MarketplaceClient instance = new MarketplaceClientImpl(this.serviceClientCredentials());
        return instance;
    }
}
