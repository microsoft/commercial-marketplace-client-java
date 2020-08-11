package com.azure.marketplace.tests;

import com.azure.marketplace.MarketplaceClient;
import com.azure.spring.marketplace.ClientSecretTokenProvider;
import com.azure.spring.marketplace.implementation.MarketplaceClientImpl;
import com.microsoft.rest.credentials.ServiceClientCredentials;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class MarketplaceClientCredentialConfig {
    @Bean
    public ServiceClientCredentials serviceClientCredentials() {
        String tenantId = System.getenv("AAD_TENANT_ID");
        String clientId = System.getenv("AAD_APP_CLIENT_ID");
        String clientSecret = System.getenv("AAD_APP_CLIENT_SECRET");

        if (StringUtils.isEmpty(tenantId)){
            throw new IllegalStateException("AAD_TENANT_ID, AAD_APP_CLIENT_ID, and AAD_APP_CLIENT_SECRET must be defined as environment variables.");
        }

        ClientSecretTokenProvider instance = new ClientSecretTokenProvider(
                UUID.fromString(tenantId),
                UUID.fromString(clientId),
                clientSecret);
        return instance;
    }

    @Bean
    public MarketplaceClient marketplaceClient() {
        MarketplaceClient instance = new MarketplaceClientImpl(this.serviceClientCredentials());
        return instance;
    }
}
