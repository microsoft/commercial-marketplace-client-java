package com.azure.spring.marketplace;

import com.microsoft.aad.msal4j.ClientCredentialFactory;
import com.microsoft.aad.msal4j.ClientCredentialParameters;
import com.microsoft.aad.msal4j.ConfidentialClientApplication;
import com.microsoft.aad.msal4j.IAuthenticationResult;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ClientSecretTokenProvider implements MarketplaceTokenProvider {
    UUID _tenantId;
    UUID _clientId;
    String _clientSecret;

    public ClientSecretTokenProvider(UUID tenantId, UUID clientId, String clientSecret) {
        _tenantId = tenantId;
        _clientId = clientId;
        _clientSecret = clientSecret;
    }

    public String acquireToken() throws IOException {
        String authority = String.format(Constants.AadAuthority, _tenantId);
        ConfidentialClientApplication app = ConfidentialClientApplication.builder(
                _clientId.toString(),
                ClientCredentialFactory.createFromSecret(_clientSecret))
                .authority(authority)
                .build();

        ClientCredentialParameters clientCredentialParam = ClientCredentialParameters.builder(
                Collections.singleton(Constants.MarketplaceResourceScope))
                .build();

        CompletableFuture<IAuthenticationResult> future = app.acquireToken(clientCredentialParam);
        try {
            return future.get().accessToken();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }
}
