/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 */
package com.azure.marketplace.tests;

import com.microsoft.marketplace.CertificateTokenProvider;
import com.microsoft.marketplace.CertificateTokenProviderSettings;
import com.microsoft.marketplace.MarketplaceClient;
import com.microsoft.marketplace.saas.SaaSAPI;
import com.microsoft.marketplace.saas.models.Subscription;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.AssumptionViolatedException;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.Enumeration;
import java.util.UUID;

// To create a self-signed PFX for use on Windows or Linux, these commands will create a cert
// for you to use:
// openssl req -x509 -nodes -days 3650 -newkey rsa:4096 -keyout privateKey.key -out certificate.pem
//
// openssl pkcs12 -export -out certificate.pfx -inkey privateKey.key -in certificate.pem
//
// You can upload certificate.pem to Azure for use as the public key. Certificate.pfx is used locally.
// To then base64 encode the certificate for use as an environment variable, use this command:
//  base64 certificate.pfx -w 0  > cert.b64
// Then, set the environment variable equal to the value of cert.b64.
// If you are on Windows, this Powershell line ought to do the trick:
// $content = [System.Convert]::ToBase64String([System.Text.Encoding]::UTF8.GetBytes($(get-content $fileName)))
//
public class TestCertificateMarketplaceAPIs {
    private SaaSAPI client;

    private UUID subscriptionId;

    private void initClient(){
        String tenantId = System.getenv("AAD_TENANT_ID");
        String clientId = System.getenv("AAD_APP_CLIENT_ID");
        String clientCertificate = System.getenv("AAD_APP_CERT");
        String certificatePassword = System.getenv("AAD_APP_CERT_SECRET");
        KeyStore.PrivateKeyEntry privateKey = null;
        try {
            Base64.Decoder decoder = java.util.Base64.getDecoder();
            byte[] certificate = decoder.decode(clientCertificate);
            ByteArrayInputStream is = new ByteArrayInputStream(certificate);
            KeyStore ks= KeyStore.getInstance("PKCS12");
            ks.load(is, certificatePassword.toCharArray());

            String alias = null;
            Enumeration<String> aliases = ks.aliases();

            // The keystore has one cert. Just grab the first alias (typically, the ONLY alias)
            // and go forward.
            if(aliases.hasMoreElements()){
                alias = aliases.nextElement();
            }
            else {
                throw new IllegalStateException("AAD_TENANT_ID, AAD_APP_CLIENT_ID, AAD_APP_CERT_LOCATION, and AAD_APP_CERT_SECRET must be defined as environment variables.");
            }

            privateKey = (KeyStore.PrivateKeyEntry)ks.getEntry(alias, new KeyStore.PasswordProtection(certificatePassword.toCharArray()));
        } catch (IOException | CertificateException | NoSuchAlgorithmException | KeyStoreException | UnrecoverableEntryException e) {
            e.printStackTrace();
        }

        if (StringUtils.isEmpty(tenantId) || privateKey == null){
            throw new IllegalStateException("AAD_TENANT_ID, AAD_APP_CLIENT_ID, AAD_APP_CERT_LOCATION, and AAD_APP_CERT_SECRET must be defined as environment variables.");
        }

        CertificateTokenProviderSettings settings = new CertificateTokenProviderSettings();
        settings.setClientId(UUID.fromString(clientId));
        settings.setTenantId(UUID.fromString(tenantId));
        settings.setCertificatePrivateKey(privateKey);
        CertificateTokenProvider credentials = new CertificateTokenProvider(settings);

        this.client = MarketplaceClient.ApiClient(credentials);
    }
    @Before
    public void setup(){
        initClient();
        Subscription subscription = this.client.getFulfillmentOperations().listSubscriptionsAsync(null,
                UUID.randomUUID(), UUID.randomUUID()).blockFirst();
        if (null == subscription) {
            throw new AssumptionViolatedException("No subscriptions are active for this login. Giving up.");
        }

        this.subscriptionId = subscription.getId();
    }

    @Test
    public void getSubscriptionTest() {
        Subscription response = this.client.getFulfillmentOperations().getSubscriptionAsync(this.subscriptionId,
                UUID.randomUUID(), UUID.randomUUID()).block();
        Assert.assertEquals(this.subscriptionId, response.getId());
    }
}
