/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 **/
package com.azure.marketplace;

import com.azure.core.util.logging.ClientLogger;
import com.microsoft.aad.msal4j.ClientCredentialFactory;
import com.microsoft.aad.msal4j.ClientCredentialParameters;
import com.microsoft.aad.msal4j.ConfidentialClientApplication;
import com.microsoft.aad.msal4j.IAuthenticationResult;

import java.net.MalformedURLException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CertificateTokenProvider implements MarketplaceTokenProvider {
    CertificateTokenProviderSettings settings;
    private final ClientLogger logger = new ClientLogger(CertificateTokenProvider.class);
    public CertificateTokenProvider(CertificateTokenProviderSettings settings) {
        this.settings = settings;
    }

    public String acquireToken() {
        PrivateKey key = settings.getCertificatePrivateKey().getPrivateKey();

        String authority = String.format(Constants.AadAuthority, settings.getTenantId());

        try {
            ConfidentialClientApplication app = ConfidentialClientApplication.builder(
                    settings.getClientId().toString(),
                    ClientCredentialFactory.createFromCertificate(key,
                            (X509Certificate)settings.getCertificatePrivateKey().getCertificate()))
                    .authority(authority)
                    .build();
            ClientCredentialParameters clientCredentialParameters = ClientCredentialParameters.builder(
                    Collections.singleton(Constants.MarketplaceResourceScope)
            ).build();
            CompletableFuture<IAuthenticationResult> future = app.acquireToken(clientCredentialParameters);
            return future.get().accessToken();
        }catch (MalformedURLException e) {
            logger.error(e.toString());
        } catch (InterruptedException e) {
            logger.error(e.toString());
        } catch (ExecutionException e) {
            logger.error(e.toString());
        }

        return null;
    }
}
