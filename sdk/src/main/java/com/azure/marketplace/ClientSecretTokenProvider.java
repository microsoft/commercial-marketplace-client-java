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

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ClientSecretTokenProvider implements MarketplaceTokenProvider {
    private ClientSecretTokenProviderSettings settings;
    private final ClientLogger logger = new ClientLogger(ClientSecretTokenProvider.class);

    public ClientSecretTokenProvider(ClientSecretTokenProviderSettings settings) {
        this.settings = settings;
    }

    public String acquireToken() throws IOException {
        String authority = String.format(Constants.AadAuthority, this.settings.get_tenantId().toString());
        ConfidentialClientApplication app = ConfidentialClientApplication.builder(
                this.settings.get_clientId().toString(),
                ClientCredentialFactory.createFromSecret(this.settings.get_clientSecret()))
                .authority(authority)
                .build();

        ClientCredentialParameters clientCredentialParam = ClientCredentialParameters.builder(
                Collections.singleton(Constants.MarketplaceResourceScope))
                .build();

        CompletableFuture<IAuthenticationResult> future = app.acquireToken(clientCredentialParam);
        try {
            return future.get().accessToken();
        } catch (InterruptedException e) {
            logger.error(e.toString());
        } catch (ExecutionException e) {
            logger.error(e.toString());
        }

        return null;
    }
}
